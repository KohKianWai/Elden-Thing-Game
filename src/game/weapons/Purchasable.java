package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Purchasable {

    /**
     * A method to execute the full purchasable purchase behaviour including
     * the extra effects from buying from a particular merchant.
     *
     * @param buyer   The actor buying the item
     * @param gameMap The current game map where the actor is at
     */
    void purchase(Actor buyer, GameMap gameMap);

    /**
     * A method to check if the buyer can purchase the item
     *
     * @param buyer   The actor buying the item
     * @return boolean indicating if the buyer can purchase the item
     */
    boolean canPurchase(Actor buyer);
}
