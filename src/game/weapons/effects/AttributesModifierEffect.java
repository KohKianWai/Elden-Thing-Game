package game.weapons.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * A class that handles purchase effects related to attribute modification.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class AttributesModifierEffect implements PurchaseEffect{

    private Enum<?> attribute;
    private ActorAttributeOperations operation;
    private int value;

    /**
     * Constructor of AttributesModifierEffect.
     *
     * @param attribute the attribute to modify
     * @param operation the operation of to perform (increase, decrease, update..)
     * @param value the value used to modify the attribute
     */
    public AttributesModifierEffect(Enum<?> attribute, ActorAttributeOperations operation, int value) {
        this.attribute = attribute;
        this.operation = operation;
        this.value = value;
    }

    /**
     * Apply the purchase effect of AttributesModifierEffect
     * @param buyer the actor who buys the weapon
     * @param map the game map that the buyer is currently at
     */
    @Override
    public void applyEffect(Actor buyer, GameMap map) {
        buyer.modifyAttribute(attribute, operation, value);
        if (!buyer.isConscious()){
            buyer.unconscious(map);
        }
    }
}
