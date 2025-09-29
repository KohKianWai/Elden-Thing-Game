package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.Purchasable;

/**
 * An action that allows an actor to purchase a purchasable from a merchant.
 *
 * @author Chin Xin Qing
 * @version 1.0.0
 */
public class PurchaseAction extends Action {

    private final Purchasable purchasable;
    private final Actor seller;

    /**
     * Constructor for PurchaseAction.
     *
     * @param purchasable the purchasable to be purchased
     * @param seller the merchant selling the purchasable
     */
    public PurchaseAction(Purchasable purchasable, Actor seller) {
        this.purchasable = purchasable;
        this.seller = seller;
    }

    /**
     * A method to execute the purchase of the purchasable.
     * Purchasing the purchasable will apply its purchase effects on the buyer.
     *
     * @param actor   The actor performing the purchase
     * @param map     The map the actor is on
     * @return a message describing the result of the purchase as a string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //if actor do not have enough runes
        if(!purchasable.canPurchase(actor)){
            return actor + " could not afford to buy " + purchasable;
        }
        //purchase the item
        purchasable.purchase(actor, map);
        return this.menuDescription(actor);
    }

    /**
     * A method to return the menu description for this action.
     *
     * @param actor The actor performing the action
     * @return a description string to show in the action menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + purchasable + "from " + seller;
    }
}
