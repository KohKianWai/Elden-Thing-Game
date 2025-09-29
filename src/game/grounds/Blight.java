package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TalismanCureAction;
import game.enums.Ability;
import game.enums.Status;
import game.items.TalismanCurable;

/**
 * A class representing a blight covering the ground of the valley.
 * @author Adrian Kristanto
 */
public class Blight extends Ground implements TalismanCurable {

    private final int cureStaminaConsumed = 50;

    /**
     * A constructor for Blight class
     */
    public Blight() {
        super('x', "Blight");
        this.addCapability(Status.CURSED);
        this.addCapability(Ability.GROWABLE);
    }

    /**
     * A method to cure the blight by a talisman
     * @param actor the actor who uses the talisman to cure
     * @param gameMap the game map that the actor currently at
     * @return the result of curing as a String
     */
    @Override
    public String talismanCure(Actor actor, GameMap gameMap) {
        // check whether the actor is a player
        if(actor.hasAttribute(BaseActorAttributes.STAMINA)){
            if(actor.getAttribute(BaseActorAttributes.STAMINA) >= cureStaminaConsumed){
                actor.modifyAttribute(
                    BaseActorAttributes.STAMINA,
                    ActorAttributeOperations.DECREASE,
                    cureStaminaConsumed);
            } else {
                return actor + " doesn't have enough stamina to cure " + this;
            }
        }
        Location blightLocation = gameMap.locationOf(actor);
        // remove the cursed status of the ground and change the ground to soil
        blightLocation.getGround().removeCapability(Status.CURSED);
        blightLocation.setGround(new Soil());
        return "Farmer has cured the " + this + " and reveal the soil underneath it.";
    }

    /**
     * A method to return a new collection of the Actions that the other actor can do to the ground.
     * @param actor     the Actor performing the action
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(location.containsAnActor()){
            if(location.getActor().hasCapability(Ability.CURE_ENTITY)){
                actions.add(new TalismanCureAction(this));
            }
        }
        return actions;
    }
}
