package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Ability;

/**
 * A class representing the soil in the valley
 * @author Adrian Kristanto
 */
public class Soil extends Ground {

    /**
     * A constructor for Soil class
     */
    public Soil() {
        super('.', "Soil");
        this.addCapability(Ability.PLANTABLE);
        this.addCapability(Ability.GROWABLE);
    }
}
