package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.SelectorType;
import java.util.Map;

/**
 * Defines a mechanism for selecting which behaviour an actor should perform
 * on its turn, based on a map of available behaviours.
 *
 * @author Harbir Singh
 * @version 1.0.0
 */
public interface BehaviourSelector {
    /**
     * Chooses the next action for the given actor by evaluating
     * the provided map of behaviours.
     *
     * @param actor      the actor whose turn it is
     * @param behaviours a map from integer priority values to Behaviour instances
     * @param map        the current game map
     * @return the chosen Action, or null if no behaviour applies
     */
    Action chooseAction(Actor actor, Map<Integer, Behaviour> behaviours, GameMap map);

    /**
     * Returns the type of this selector, indicating how behaviours are chosen
     *
     * @return the SelectorType of this selector
     */
    SelectorType getSelectorType();
}
