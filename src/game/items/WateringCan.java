package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.Ability;

/**
 * A class representing a WateringCan that an actor can pick up and drop
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class WateringCan extends Item {

    /**
     * A constructor for WateringCan class
     */
    public WateringCan(){
        super("Watering Can", 'W', true);
        this.addCapability(Ability.CAN_WATER);
    }
}
