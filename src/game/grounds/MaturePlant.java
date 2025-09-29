package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.HarvestAction;
import game.items.Harvestable;
import game.items.PlantItem;

/**
 * An abstract class that represents a MaturePlant ground.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public abstract class MaturePlant extends Ground implements Harvestable {
    private final int harvestStamina;
    private PlantItem harvestPlantItem;

    /**
     * Constructor.
     *
     * @param displayChar       the character to display for this ground.
     * @param name              the name of the mature plant.
     * @param harvestStamina    the required stamina to harvest the mature plant.
     * @param harvestPlantItem  the plant item that the player will get after harvesting the mature plant.
     */
    public MaturePlant(char displayChar, String name, int harvestStamina, PlantItem harvestPlantItem){
        super(displayChar, name);
        this.harvestStamina = harvestStamina;
        this.harvestPlantItem = harvestPlantItem;
    }

    /**
     * A method to harvest a mature plant.
     * @param actor an actor who harvest the mature plant.
     * @param map the game map where the actor at.
     * @return the result of harvesting a mature plant.
     */
    @Override
    public String harvest(Actor actor, GameMap map) {
        if(actor.getAttribute(BaseActorAttributes.STAMINA) >= harvestStamina){
            actor.modifyAttribute(
                BaseActorAttributes.STAMINA,
                ActorAttributeOperations.DECREASE,
                harvestStamina
            );

            map.locationOf(actor).setGround(new Soil());
            actor.addItemToInventory(harvestPlantItem);
            return actor + " has harvested the " + this;
        }

        return actor + " does not have enough stamina to harvest " + this;
    }

    /**
     * Returns the list of actions available to the actor,
     *
     * @param actor     the actor standing on this ground.
     * @param location  the current location of the mature plant.
     * @param direction the direction of the actor relative to this mature plant.
     * @return a list of actions that the player can perform to the mature plant.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(location.containsAnActor()){
            if(location.getActor().hasAttribute(BaseActorAttributes.STAMINA)){
                actions.add(new HarvestAction(this));
            }
        }
        return actions;
    }
}
