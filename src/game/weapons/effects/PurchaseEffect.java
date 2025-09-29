package game.weapons.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;

/**
 * Interface for the purchase effect logic
 * Implemented by other purchase effect
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface PurchaseEffect {
    /**
     * Apply the purchase effect
     * @param buyer the actor who buys the item
     * @param map the game map that the buyer is currently at
     */
    void applyEffect(Actor buyer, GameMap map);
}
