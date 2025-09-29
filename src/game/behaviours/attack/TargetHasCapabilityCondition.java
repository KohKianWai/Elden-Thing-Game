package game.behaviours.attack;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An {@link AttackCondition} that checks if the target actor has a specific capability.
 * This allows filtering attack targets based on their roles, traits, or states.
 *
 * For example, an actor might only attack targets with the MOVABLE or HOSTILE capability.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class TargetHasCapabilityCondition implements AttackCondition {
    private Enum<?> capability;

    /**
     * Constructs a new condition that checks for a specific capability on the target.
     *
     * @param capability the capability that the target must possess
     */
    public TargetHasCapabilityCondition(Enum<?> capability){
        this.capability = capability;
    }

    /**
     * Returns true if the target has the specified capability.
     *
     * @param attacker the actor attempting the attack
     * @param target the potential target of the attack
     * @return true if the target has the required capability, false otherwise
     */
    @Override
    public boolean canAttack(Actor attacker, Actor target) {
        return target.hasCapability(capability);
    }
}
