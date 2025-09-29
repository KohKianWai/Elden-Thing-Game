package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.bedofchaos.GrowthAttacker;

/**
 * BoCAttackAction represents the unique attack action for actors(Bed of Chaos) implementing GrowthAttacker.
 * It delegates execution to the actor's growthAttack() method.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public class BoCAttackAction extends Action {

    /**
     * The actor (implementing GrowthAttacker) performing the attack.
     */
    private final GrowthAttacker attacker;

    /**
     * The target actor of the growth-based attack.
     */
    private final Actor target;

    /**
     * The direction of the target relative to the attacker.
     */
    private final String direction;

    /**
     * Constructor for BoCAttackAction.
     *
     * @param attacker  the actor performing the growth-based attack
     * @param target    the target of the attack
     * @param direction the direction of the target
     */
    public BoCAttackAction(GrowthAttacker attacker, Actor target, String direction) {
        this.attacker = attacker;
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the growth-based attack using the attacker's growthAttack() method.
     *
     * @param actor The actor performing the action.
     * @param map   The game map where the attack occurs.
     * @return a description of the attack result as a string.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return attacker.growthAttack(target, direction, map);
    }

    /**
     * Returns the description of this action in the in-game menu.
     *
     * @param actor The actor performing the action.
     * @return the description of the attack as a String.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " (Bed of Chaos attack)";
    }
}
