package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.ArrayList;
import java.util.Random;

/**
 * WanderBehaviour is a class that represents a wander behaviour of a NPC
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class WanderBehaviour implements Behaviour {
    private final Random random;

    /**
     * A constructor for WanderBehaviour class
     */
    public WanderBehaviour(){
        this.random = new Random();
    }

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     * Cite: Created by FIT2099 Teaching Team in demo.huntsman.WanderBehaviour getAction() method
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }
    }
}
