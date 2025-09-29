package game.items.eggs;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.BehaviourSelector;
import game.enums.CreatureType;

/**
 * A class that represents an OmenSheepEgg.
 *
 * @author Lee Hou Wen
 * @version 1.1.0
 * Modified by: Koh Kian Wai & Harbir Singh
 */
public class OmenSheepEgg extends Egg {

    private final int increaseMaxHealth = 10;
    private final int hatchRequiredTurns = 3;

    /**
     * Constructs an Omen Sheep Egg with a hatch condition based on the given selector.
     *
     * @param selector the BehaviourSelector used by the parent creature, determining which CreatureType will hatch
     */
    public OmenSheepEgg(BehaviourSelector selector) {
        super("Omen Sheep Egg", '0', true);
        // create hatch condition class to check for hatch condition

        CreatureType type = getCreatureTypeForSelector(selector);
        hatchConditions.put(999, new TurnBasedHatchCondition(this.hatchRequiredTurns, type));
    }

    /**
     * A method that simulates the passage of time and updates the object's state accordingly.
     * @param currentLocation The location of the OmenSheepEgg.
     */
    @Override
    public void tick(Location currentLocation) {
        incrementHatchCounter();
        super.tick(currentLocation);
    }

    /**
     * A method to consume an OmenSheepEgg.
     * @param actor an actor who consume the OmenSheepEgg.
     * @param map the game map where the actor at.
     * @return the result of consuming an OmenSheepEgg.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, increaseMaxHealth);
        return actor + " ate " + this;
    }

    private CreatureType getCreatureTypeForSelector(BehaviourSelector selector) {
        switch(selector.getSelectorType()){
            case RANDOM:
                return CreatureType.RANDOMOMENSHEEP;
            case PRIORITY:
                return CreatureType.OMENSHEEP;

            default:
                return null;
        }
    }
}
