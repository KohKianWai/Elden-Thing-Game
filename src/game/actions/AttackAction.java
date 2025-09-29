package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.*;

/**
 * AttackAction class is a class that represents an attack action
 * Cite: Created by FIT2099 Teaching Team in demo.huntsman.AttackAction
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class AttackAction extends Action {

    private Actor target;
    private String direction;
    private Weapon weapon;

    /**
     * A constructor for AttackAction class
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     * @param weapon the weapon used in the attack
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * A constructor for AttackAction class
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * A method to execute the attack action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of execution as a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        String result = weapon.attack(actor, target, map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * A method to show the description of the attack action on the menu
     * @param actor The actor performing the action.
     * @return the description of the attack action as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}
