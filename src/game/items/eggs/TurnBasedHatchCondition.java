package game.items.eggs;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.Creature;
import game.enums.CreatureType;

import java.util.function.Supplier;

/**
 * A class that represent turn-based hatch condition which allows an egg to hatch
 * after a certain number of turns have passed.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class TurnBasedHatchCondition extends BaseHatchCondition{
    private final int requiredTurns;

    public TurnBasedHatchCondition(int requiredTurns, CreatureType hatchCreature) {
        super(hatchCreature);
        this.requiredTurns = requiredTurns;
    }

    @Override
    public Creature tryHatch(Egg egg, Location location) {
        Creature creature = null;
        if(egg.getHatchCounter() >= requiredTurns){
            creature = createCreature();
        }
        return creature;
    }
}
