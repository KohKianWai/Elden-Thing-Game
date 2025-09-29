package game.items.eggs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actors.creatures.Creature;
import game.items.Consumable;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


/**
 * An abstract class that represents an Egg item.
 *
 * @author Lee Hou Wen
 * @version 1.0.0
 * Modified by: Koh Kian Wai
 */
public abstract class Egg extends Item implements Consumable {

    private int hatchCounter;  // can we include this attribute if it is not turn based?
    private final Random random;

    /**
     * hatchConditions attribute is a tree map that stores all the hatchConditions of an egg.
     */
    protected Map<Integer, HatchCondition> hatchConditions = new TreeMap<>();

    /**
     * Constructor.
     *
     * @param name        the name of the egg.
     * @param displayChar the character to display for this item.
     * @param portable    whether the egg can be picked up and carried.
     */
    public Egg(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.hatchCounter = 0;
        this.random = new Random();
    }

    /**
     * Returns a list of allowable actions for the actor that carrying this egg.
     *
     * @param owner the actor who is carrying this item.
     * @param map   the map the actor is on.
     * @return a list containing the allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * A method that simulates the passage of time and updates the object's state accordingly.
     * @param currentLocation The location of the egg.
     */
    @Override
    public void tick(Location currentLocation) {
        Creature hatchCreature = this.checkHatchCondition(currentLocation);
        if (hatchCreature != null) {
            Location hatchLocation = getValidHatchLocation(currentLocation, hatchCreature);
            // delay the egg hatching when the surrounding tiles are occupied
            if(hatchLocation != null){
                currentLocation.removeItem(this);
                hatchLocation.addActor(hatchCreature);
            }
        }
    }

    /**
     * Checks all hatch conditions to determine if the egg should hatch.
     *
     * @param eggLocation the location of the egg.
     * @return a creature if hatching conditions are met, otherwise null.
     */
    public Creature checkHatchCondition(Location eggLocation){
        // check hatch condition
        for(HatchCondition hatchCondition: hatchConditions.values()){
            Creature hatchCreature = hatchCondition.tryHatch(this, eggLocation);
            if(hatchCreature != null){
                return hatchCreature;
            }
        }
        return null;
    }

    /**
     * Determines if there is a valid location for the creature to spawn.
     *
     * @param eggLocation   the location of the egg.
     * @param hatchCreature the creature that is to be spawned.
     * @return a valid location where the creature can spawn, or null if none are available.
     */
    public Location getValidHatchLocation(Location eggLocation, Creature hatchCreature){
        ArrayList<Location> validHatchLocation = new ArrayList<>();
        if(eggLocation.canActorEnter(hatchCreature)){
            return eggLocation;
        }
        for(Exit exit: eggLocation.getExits()){
            Location destination = exit.getDestination();
            if(destination.canActorEnter(hatchCreature)){
                validHatchLocation.add(destination);
            }
        }
        if(!validHatchLocation.isEmpty()){
            Location hatchLocation = validHatchLocation.get(random.nextInt(validHatchLocation.size()));
            return hatchLocation;
        }
        // return null if no valid hatching location
        return null;
    }

    /**
     * Returns the turns that have passed since the egg was produced.
     *
     * @return the number of turns that passed.
     */
    public int getHatchCounter() {
        return hatchCounter;
    }

    /**
     * Increments the hatch counter by one.
     */
    public void incrementHatchCounter(){
        this.hatchCounter += 1;
    }
}
