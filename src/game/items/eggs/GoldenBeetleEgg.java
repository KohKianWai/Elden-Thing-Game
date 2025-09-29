package game.items.eggs;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.BehaviourSelector;
import game.enums.CreatureType;
import game.enums.Status;

/**
 * GoldenBeetleEgg is a class that represents an Golden Beetle Egg
 *
 * @author Chin Xin Qing
 * @version 1.1.0
 * Modified by: Harbir Singh
 */
public class GoldenBeetleEgg extends Egg {
    private final int restoreStaminaPoint = 20;

    /**
     * Constructs a Golden Beetle Egg with a hatch condition based on the given behaviour selector.
     *
     * @param selector the BehaviourSelector determining which CreatureType to hatch
     */
    public GoldenBeetleEgg(BehaviourSelector selector) {
        super("Golden Beetle Egg", '0', true);

        CreatureType type = getCreatureTypeForSelector(selector);
        hatchConditions.put(0, new ProximityHatchCondition(Status.CURSED, type));
    }

    /**
     * A method to consume GoldenBeetleEgg
     * @param actor an actor who perform the consume action
     * @param map the game map where the actor at
     * @return the result of consuming as a String
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        // remove from inventory after use
        actor.removeItemFromInventory(this);
        // increase stamina
        actor.modifyAttribute(
                BaseActorAttributes.STAMINA,
                ActorAttributeOperations.INCREASE,
                restoreStaminaPoint);
        return actor + " ate " + this + " and restored " + restoreStaminaPoint + " stamina";
    }

    private CreatureType getCreatureTypeForSelector(BehaviourSelector selector){
        switch(selector.getSelectorType()){
            case RANDOM:
                return CreatureType.RANDOMGOLDENBEETLE;
            case PRIORITY:
                return CreatureType.GOLDENBEETLE;

            default:
                return null;
        }
    }
}
