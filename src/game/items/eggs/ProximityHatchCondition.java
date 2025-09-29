package game.items.eggs;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.Creature;
import game.enums.CreatureType;
import game.utils.LocationUtils;

/**
 * ProximityHatchCondition is a class to handle hatch condition when certain entity with certain status is nearby
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class ProximityHatchCondition extends BaseHatchCondition{
    private Enum<?> surroundingCapability;

    /**
     * A constructor for ProximityHatchCondition class
     */
    public ProximityHatchCondition(Enum<?> surroundingCapability, CreatureType hatchCreature){
        super(hatchCreature);
        this.surroundingCapability = surroundingCapability;
    }

    /**
     * A method to check if the egg is able to hatch
     * @param egg egg to hatch
     * @param location the location of the egg
     * @return creature that hatch from the egg
     */
    @Override
    public Creature tryHatch(Egg egg, Location location) {
        Creature creature = null;
        // check if surrounding has a entity with certain capability
        if(LocationUtils.isCurrentOrSurroundingEntityHasCapability(location, this.surroundingCapability)){
            creature = createCreature();
        }
        return creature;
    }
}
