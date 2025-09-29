package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ProduceOffspringAction;
import game.actors.creatures.Creature;
import game.enums.Ability;
import game.enums.Status;
import game.utils.LocationUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents the NPC's behaviour of producing an offspring.
 *
 * @author Lee Hou Wen
 * @version 1.0.0
 */
public class ProduceOffspringBehaviour implements Behaviour {

    private final Random random;

    private Creature offspring;

    private int breedCounter;

    private int breedCooldown;

    /**
     * Constructor.
     *
     * @param offspring The creature object to be produced when this behaviour is executed.
     * @param breedCounter The breeding counter
     * @param breedCooldown  The breeding cooldown to avoid overflow of creatures
     */
    public ProduceOffspringBehaviour(Creature offspring, int breedCounter, int breedCooldown) {
        this.random = new Random();
        this.offspring = offspring;
        this.breedCounter = breedCounter;
        this.breedCooldown = breedCooldown;
    }

    /**
     * Returns a ProduceOffspringAction to allow the creature to produce an offspring when there is a
     * at least a valid location to do so, else returns null.
     *
     * @param actor the NPC acting the behaviour.
     * @param map the map containing the NPC.
     * @return an ProduceOffspringAction object for producing the offspring, or null if no valid location.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        //if there's no blessed entity surrounding, it cannot produce offspring
        if (!LocationUtils.isCurrentOrSurroundingEntityHasCapability(map.locationOf(actor), Status.BLESSED)){
            return null;
        }
        //if breed is in cooldown, it also cannot produce offspring
        if (breedCounter % breedCooldown != 0){
            return null;
        }

        ArrayList<Location> locations = new ArrayList<>();
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            //collecting the valid locations
            if (destination.canActorEnter(actor)) {
                locations.add(destination);
            }
        }
        if (!locations.isEmpty()) {
            //getting a random location
            int randomLocation = random.nextInt(locations.size());
            Location locationOfOffspring = locations.get(randomLocation);

            //returning the action
            return new ProduceOffspringAction(locationOfOffspring, offspring);
        }
        //if all surrounding location is not empty, cannot produce offspring
        else {
            return null;
        }
    }
}


