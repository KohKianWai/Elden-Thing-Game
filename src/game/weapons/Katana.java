package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.effects.AttributesModifierEffect;
import game.weapons.effects.PurchaseEffect;

import java.util.ArrayList;

/**
 * Class representing a purchasable weapon called Katana.
 * This weapon deals 50 damage points with a 60% chance
 * to hit the target.
 * Katana symbol: 'j'
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public class Katana extends WeaponItem implements Purchasable{
    private ArrayList<PurchaseEffect> purchaseEffects;
    private int price;

    /**
     * Constructor for Katana.
     * Added basic on purchase effect for Katana.
     */
    public Katana(ArrayList<PurchaseEffect> purchaseEffects, int price) {
        super("Katana", 'j', 50, "delivers", 60);
        this.purchaseEffects = purchaseEffects;
        this.price = price;
        this.purchaseEffects.add(
            new AttributesModifierEffect(BaseActorAttributes.HEALTH,
                ActorAttributeOperations.DECREASE, 25)
        );
    }

    /**
     * A method to execute the full purchasable purchase behaviour including
     * the extra effects from buying from a particular merchant.
     *
     * @param buyer   The actor buying the item
     * @param gameMap The current game map where the actor is at
     */
    @Override
    public void purchase(Actor buyer, GameMap gameMap) {
        for(PurchaseEffect purchaseEffect: this.purchaseEffects){
            purchaseEffect.applyEffect(buyer, gameMap);
        }
        buyer.addItemToInventory(this);
    }

    /**
     * A method to check if the buyer can purchase the item
     *
     * @param buyer   The actor buying the item
     * @return boolean indicating if the buyer can purchase the item
     */
    @Override
    public boolean canPurchase(Actor buyer) {
        return buyer.getBalance() >= this.price;
    }

    /**
     * A method to represent all details of this item
     * @return all details of this item
     */
    @Override
    public String toString(){
        String ret = super.toString();
        ret += " (" + this.price + " runes) ";
        return ret;
    }
}
