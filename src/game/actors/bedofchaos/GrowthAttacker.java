package game.actors.bedofchaos;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for bed of chaos entity that performs a growth-based attack.
 * It encapsulates the attack logic of bed of chaos.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public interface GrowthAttacker {

    /**
     * Executes the growth-based attack on a nearby target.
     *
     * @param target    the target actor
     * @param direction the direction of the target
     * @param map       the map the actors are on
     * @return the result of the attack as a String
     */
    String growthAttack(Actor target, String direction, GameMap map);
}
