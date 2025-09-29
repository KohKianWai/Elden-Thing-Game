package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Harvestable is an interface that declares the harvest() method
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface Harvestable {

    /**
     * A method to harvest an item
     * @param actor an actor who harvest
     * @param map the game map where the actor at
     * @return the result of harvesting as a String
     */
    String harvest(Actor actor, GameMap map);
}
