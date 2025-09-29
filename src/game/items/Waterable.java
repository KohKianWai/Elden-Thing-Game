package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Waterable is an interface that declares the water() method
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface Waterable {

    /**
     * A method to water an item
     * @param actor an actor who water
     * @param map the game map where the actor at
     * @return the result of watering as a String
     */
    String water(Actor actor, GameMap map);
}
