package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.SelectorType;

import java.util.Map;

/**
 * A BehaviourSelector that evaluates behaviours in order of their keys (priority) and returns the first valid action.
 * Lower integer keys represent higher priority.
 *
 * @author Harbir Singh
 * @version 1.0.0
 */
public class PriorityBehaviourSelector implements BehaviourSelector {

    /**
     * Iterates through the provided behaviours (in key order) and returns
     * the first non-null Action produced by a behaviour.
     *
     * @param actor      the actor whose behaviours are being evaluated
     * @param behaviours a map from integer priority values to Behaviour instances
     * @param map        the current game map
     * @return the first valid Action, or null if none apply
     */
    @Override
    public Action chooseAction(Actor actor, Map<Integer, Behaviour> behaviours, GameMap map) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(actor, map);
            if (action != null) {
                return action;
            }
        }
        return null;
    }

    /**
     * Returns the selector type for this implementation.
     *
     * @return SelectorType.PRIORITY
     */
    @Override
    public SelectorType getSelectorType() {
        return SelectorType.PRIORITY;
    }

}
