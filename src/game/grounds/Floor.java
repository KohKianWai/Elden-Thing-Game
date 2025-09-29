package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Ability;

/**
 * A class that represents the floor inside a building.
 * @author Riordan D. Alfredo
 */
public class Floor extends Ground {

    /**
     * A constructor for Floor class
     */
    public Floor() {
        super('_', "Floor");
        this.addCapability(Ability.GROWABLE);
    }
}
