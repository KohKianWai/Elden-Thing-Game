package game.items.seeds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PlantAction;
import game.enums.Ability;
import game.items.Plantable;

/**
 * Seed class is a base class that represents a generic seed
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public abstract class Seed extends Item implements Plantable {

    private final int plantStaminaConsumed;

    /**
     * A constructor for Seed class
     * @param name the name of the seed
     * @param displayChar the display character of the seed
     * @param portable the portability of the seed
     * @param plantStaminaConsumed the stamina consumed to plant the seed
     */
    public Seed(String name, char displayChar, boolean portable, int plantStaminaConsumed){
        super(name, displayChar, portable);
        this.plantStaminaConsumed = plantStaminaConsumed;
    }

    /**
     * A method to plant a seed
     * @param actor an actor who plant
     * @param gameMap the game map where the actor at
     * @return the result of planting as a String
     */
    @Override
    public String plant(Actor actor, GameMap gameMap) {
        // if actor is a player, reduce the stamina
        if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
            if (actor.getAttribute(BaseActorAttributes.STAMINA) >= plantStaminaConsumed) {
                actor.modifyAttribute(
                    BaseActorAttributes.STAMINA,
                    ActorAttributeOperations.DECREASE,
                    plantStaminaConsumed);
            } else {
                return actor + " doesn't have enough stamina to plant " + this;
            }
        }
        return "";
    }

    /**
     * A method that return list of allowable actions that the item can perform to its owner
     * or to the current map while being carried by an actor
     * @param actor the actor that owns the item
     * @param gameMap the map where the actor is performing the action on
     * @return a collections of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap gameMap){
        ActionList actions = new ActionList();
        Location actorLocation = gameMap.locationOf(actor);
        if(actorLocation.getGround().hasCapability(Ability.PLANTABLE)){
            actions.add(new PlantAction(this));
        }
        return actions;
    }

    /**
     * A method to return the name attribute of a seed
     * @return the name attribute of a seed as String
     */
    @Override
    public String toString(){
        return super.toString();
    }
}
