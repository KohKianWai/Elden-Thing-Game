package game.actors.people.monologues.conditions;

import game.actors.people.monologues.MonologueContext;
import game.actors.people.monologues.conditions.BaseMonologueCondition;

/**
 * EmptyInventoryMonologueCondition is a condition that triggers a monologue
 * when the actor inventory is empty.
 * This can be used to indicate need or vulnerability when the actor carries no items.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class EmptyInventoryMonologueCondition extends BaseMonologueCondition {

    /**
     * A constructor for EmptyInventoryMonologueCondition class.
     *
     * @param monologueText the monologue text to be displayed when condition is satisfied
     */
    public EmptyInventoryMonologueCondition(String monologueText){
        super(monologueText);
    }


    /**
     * A method to check if the condition is satisfied based on whether
     * the actor's inventory is empty.
     *
     * @param monologueContext the context containing the actor's state
     * @return true if the actor inventory is empty, false otherwise
     */
    @Override
    public boolean isConditionSatisfied(MonologueContext monologueContext) {
        return monologueContext.getListener().getItemInventory().isEmpty();
    }
}
