package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.WaterAction;
import game.enums.Ability;
import game.items.Waterable;

/**
 * An abstract class that represents a Sprout ground.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public abstract class Sprout extends Ground implements Waterable {
    private int timesWatered;
    private final int requiredWateringsToGrow;
    private MaturePlant maturePlant;

    /**
     * Constructor.
     *
     * @param displayChar               the character to display for this ground.
     * @param name                      the name of the sprout.
     * @param requiredWateringsToGrow   the watering times that required for the sprout to grow into a mature plant.
     * @param maturePlant               the mature plant that the sprout will grow into.
     */
    public Sprout(char displayChar, String name, int requiredWateringsToGrow, MaturePlant maturePlant){
        super(displayChar, name);
        this.timesWatered = 0;
        this.requiredWateringsToGrow = requiredWateringsToGrow;
        this.maturePlant = maturePlant;
    }

    /**
     * A method to water a sprout.
     * @param actor an actor who water the sprout.
     * @param map the game map where the actor at.
     * @return the result of watering a sprout.
     */
    @Override
    public String water(Actor actor, GameMap map) {
        this.timesWatered += 1;
        if(this.timesWatered == this.requiredWateringsToGrow){
            map.locationOf(actor).setGround(maturePlant);
            return this + " has been watered!" + "\n" +
                this + " has grown into a " + maturePlant;
        }

        return this + " has been watered!";
    }

    /**
     * Returns the list of actions available to the actor,
     *
     * @param actor     the actor standing on this ground.
     * @param location  the current location of the sprout.
     * @param direction the direction of the actor relative to this sprout.
     * @return a list of actions that the player can perform to the sprout.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(location.containsAnActor()){
            if(location.getActor().hasCapability(Ability.CAN_WATER)){
                actions.add(new WaterAction(this));
            }
        }
        return actions;
    }
}
