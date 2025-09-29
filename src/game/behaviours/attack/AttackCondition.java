package game.behaviours.attack;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface representing a condition that determines whether an attacker
 * can attack a given target.
 * Implementing classes define specific conditions under which an attack is allowed.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface AttackCondition {

    /**
     * Checks whether the attacker can attack the target based on the condition.
     *
     * @param attacker the actor attempting the attack
     * @param target the potential target of the attack
     * @return true if the attack is allowed, false otherwise
     */
    boolean canAttack(Actor attacker, Actor target);
}
