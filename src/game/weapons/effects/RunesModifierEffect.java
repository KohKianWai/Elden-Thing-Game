package game.weapons.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A class that handles purchase effects related to runes modification.
 *
 * @author Chin Xin Qing
 * @version 1.0.0
 */
public class RunesModifierEffect implements PurchaseEffect{

    private int value;

    /**
     * Constructor of RunesModifierEffect.
     *
     * @param value the value used to modify the wallet
     */
    public RunesModifierEffect(int value) {
        this.value = value;
    }

    /**
     * Apply the purchase effect of RunesModifierEffect
     * @param buyer the actor who buys the weapon
     * @param map the game map that the buyer is currently at
     */
    @Override
    public void applyEffect(Actor buyer, GameMap map) {
        buyer.addBalance(value);
    }
}
