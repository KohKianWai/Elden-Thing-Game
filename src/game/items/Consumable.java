package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Consumable is an interface that declares the consume() method
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface Consumable {
    /**
     * A method to consume a consumable
     * @param actor an actor who perform the consume action
     * @param map the game map where the actor at
     * @return the result of consuming as a String
     */
    String consume(Actor actor, GameMap map);
}

