package game.actors.people.monologues.conditions;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.people.monologues.MonologueContext;

/**
 * LowHpMonologueCondition is a condition that triggers a monologue when
 * the actor's current health is below a specific threshold.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class LowHpMonologueCondition extends BaseMonologueCondition {

    private int hitPointsThreshold;

    /**
     * A constructor for LowHpMonologueCondition class.
     *
     * @param hitPointsThreshold the threshold below which the monologue should trigger
     * @param monologueText the monologue text to be displayed when condition is satisfied
     */
    public LowHpMonologueCondition(int hitPointsThreshold, String monologueText){
        super(monologueText);
        this.hitPointsThreshold = hitPointsThreshold;
    }

    /**
     * A method to check if the condition is satisfied based on actor's current health.
     *
     * @param monologueContext the context containing information about the actor
     * @return true if actor's health is below the threshold, false otherwise
     */
    @Override
    public boolean isConditionSatisfied(MonologueContext monologueContext) {
        return monologueContext.getListener().getAttribute(BaseActorAttributes.HEALTH) < hitPointsThreshold;
    }
}
