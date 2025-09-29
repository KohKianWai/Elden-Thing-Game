package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Plantable is an interface that declares the plant() method
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface Plantable {

    /**
     * A method to plant an item
     * @param actor an actor who plant
     * @param gameMap the game map where the actor at
     * @return the result of planting as a String
     */
    String plant(Actor actor, GameMap gameMap);
}
