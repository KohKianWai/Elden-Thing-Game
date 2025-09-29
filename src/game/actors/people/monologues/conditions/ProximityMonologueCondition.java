package game.actors.people.monologues.conditions;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.people.monologues.MonologueContext;
import game.utils.LocationUtils;

/**
 * ProximityMonologueCondition is a condition that triggers a monologue when
 * a nearby location (or the current location) contains an entity with a specific capability.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 * Modified by: Chin Xin Qing
 */
public class ProximityMonologueCondition extends BaseMonologueCondition {

    private Enum<?> surroundingCapability;

    /**
     * A constructor for ProximityMonologueCondition class.
     *
     * @param surroundingCapability the capability to check for in surrounding entities or ground
     * @param monologueText the monologue text to be displayed when condition is satisfied
     */
    public ProximityMonologueCondition(Enum<?> surroundingCapability, String monologueText){
        super(monologueText);
        this.surroundingCapability = surroundingCapability;
    }

    /**
     * A method to check if the condition is satisfied based on whether the
     * actor's current or adjacent locations contain an entity with the specified capability.
     *
     * @param monologueContext the context containing the speaker and map information
     * @return true if the specified capability is found in the vicinity, false otherwise
     */
    @Override
    public boolean isConditionSatisfied(MonologueContext monologueContext) {
        GameMap map = monologueContext.getGameMap();
        Actor speaker = monologueContext.getSpeaker();

        return LocationUtils.isCurrentOrSurroundingEntityHasCapability(map.locationOf(speaker), this.surroundingCapability);
    }
}
