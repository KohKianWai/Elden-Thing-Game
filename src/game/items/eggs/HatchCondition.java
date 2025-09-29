package game.items.eggs;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.Creature;

/**
 * HatchCondition is an interface that declares the tryHatch() method.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface HatchCondition {

    /**
     * A method to try hatch an egg.
     * @param egg which is hatching.
     * @param location the location where the egg at.
     * @return the hatched creature.
     */
    Creature tryHatch(Egg egg, Location location);
}
