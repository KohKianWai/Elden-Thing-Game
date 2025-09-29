package game.behaviours.attack;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * An {@link AttackCondition} that allows an actor to attack a target only if the target's
 * current health is above a specified threshold.
 * Useful for avoiding weak or dying enemies.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class TargetAboveHpCondition implements AttackCondition {
    private int hitPointsThreshold;

    /**
     * Constructor to initialize the HP threshold.
     *
     * @param hitPointsThreshold the minimum HP required for the target to be eligible for attack
     */
    public TargetAboveHpCondition(int hitPointsThreshold){
        this.hitPointsThreshold = hitPointsThreshold;
    }

    /**
     * Returns true if the target has more health than the threshold.
     *
     * @param attacker the actor attempting the attack
     * @param target the potential target of the attack
     * @return true if target's health is above the threshold, false otherwise
     */
    @Override
    public boolean canAttack(Actor attacker, Actor target) {
        return target.getAttribute(BaseActorAttributes.HEALTH) > hitPointsThreshold;
    }
}
