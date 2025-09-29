package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.Ability;

/**
 * A class representing a Talisman that an actor can pick up and drop
 * @author Adrian Kristanto
 */
public class Talisman extends Item {

    /**
     * A constructor for Talisman class
     */
    public Talisman() {
        super("Talisman", 'o', true);
        this.addCapability(Ability.CURE_ENTITY);
    }
}
