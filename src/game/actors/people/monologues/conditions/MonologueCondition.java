package game.actors.people.monologues.conditions;

import game.actors.people.monologues.MonologueContext;

/**
 * MonologueCondition defines the contract for any condition that determines
 * whether a specific monologue should be triggered based on a given context.
 * Implementing classes provide specific logic for evaluating conditions,
 * such as low health, proximity to entities, or empty inventory.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface MonologueCondition {

    /**
     * Evaluates whether the condition is satisfied based on the provided context.
     *
     * @param monologueContext the context that provides information about the actor and environment
     * @return true if the condition is met and it will be false otherwise
     */
    boolean isConditionSatisfied(MonologueContext monologueContext);

    /**
     * Retrieves the monologue text associated with this condition.
     *
     * @return the monologue text as a String
     */
    String getMonologueText();
}
