package game.actors.people.monologues.conditions;

import game.actors.people.monologues.MonologueContext;

/**
 * DefaultMonologueCondition is a fallback condition that always returns true,
 * ensuring that a monologue will be displayed when no other specific condition is met.
 *
 * This is typically used as the last-resort condition in a prioritized list of monologue conditions.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class DefaultMonologueCondition extends BaseMonologueCondition {

    /**
     * A constructor for DefaultMonologueCondition class.
     *
     * @param monologueText the monologue text to be displayed unconditionally
     */
     public DefaultMonologueCondition(String monologueText){
         super(monologueText);
     }

    /**
     * A method that always returns true, indicating the condition is always satisfied.
     *
     * @param monologueContext the context in which the monologue is being considered
     * @return true always
     */
    @Override
    public boolean isConditionSatisfied(MonologueContext monologueContext) {
        return true;
    }
}
