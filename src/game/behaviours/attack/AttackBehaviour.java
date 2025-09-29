package game.behaviours.attack;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A behaviour that allows an actor to attack nearby valid targets based on a list of conditions.
 * Only targets that satisfy all specified {@link AttackCondition}s will be considered.
 * One target is chosen randomly among valid ones for the attack.
 * Example usage: Guts only attacks targets with high HP and MOVABLE capability.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class AttackBehaviour implements Behaviour {
    private Random random = new Random();
    private List<AttackCondition> attackConditions;

    /**
     * Constructs an AttackBehaviour with a list of conditions that targets must satisfy.
     *
     * @param attackConditions the list of conditions required for a target to be attacked
     */
    public AttackBehaviour(List<AttackCondition> attackConditions){
        this.attackConditions = attackConditions;
    }

    /**
     * Returns an attack action if a valid target is found in adjacent locations.
     * A target is considered valid if it satisfies all attack conditions.
     * One valid target is randomly selected for the attack.
     *
     * @param actor the actor performing the action
     * @param map the map containing the actor
     * @return a new {@link AttackAction} against a valid target, or null if none found
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()){
            Actor target = exit.getDestination().getActor();
            boolean flag = false;
            if (target != null){
                for(AttackCondition attackCondition: this.attackConditions){
                    // if any condition is not met
                    // exit the inner loop early
                    if(!attackCondition.canAttack(actor, target)){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    continue;    // skip to next actor in the surrounding
                }
                actions.add(new AttackAction(target, exit.getName()));
           }
        }

        if (!actions.isEmpty()){
            return actions.get(random.nextInt(actions.size()));
        } else {
            return null;
        }
    }
}
