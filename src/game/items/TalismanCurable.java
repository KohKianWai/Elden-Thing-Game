package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * TalismanCurable is an interface that declares the talisman's cure method
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface TalismanCurable {

    /**
     * A method to cure the instance by talisman
     * @param actor the actor who uses the talisman to cure
     * @param gameMap the game map that the actor currently at
     * @return the cure result as a string
     */
    String talismanCure(Actor actor, GameMap gameMap);
}
