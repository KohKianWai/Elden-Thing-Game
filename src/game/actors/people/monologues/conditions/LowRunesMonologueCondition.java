package game.actors.people.monologues.conditions;

import game.actors.people.monologues.MonologueContext;

/**
 * LowRunesMonologueCondition is a condition that triggers a monologue
 * when the actor's rune balance is below a specified threshold.
 * This can be used to reflect financial desperation or need in the game world.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class LowRunesMonologueCondition extends BaseMonologueCondition {
    private int runesThreshold;

    /**
     * A constructor for LowRunesMonologueCondition class.
     *
     * @param runesThreshold the rune balance threshold that triggers the monologue
     * @param monologueText the monologue text to be displayed when condition is satisfied
     */
    public LowRunesMonologueCondition(int runesThreshold, String monologueText){
        super(monologueText);
        this.runesThreshold = runesThreshold;
    }

    /**
     * A method to check if the condition is satisfied based on whether
     * the actor's rune balance is below the specified threshold.
     *
     * @param monologueContext the context containing the actor's balance
     * @return true if the rune balance is below the threshold, false otherwise
     */
    @Override
    public boolean isConditionSatisfied(MonologueContext monologueContext) {
        return monologueContext.getListener().getBalance() < runesThreshold;
    }
}
