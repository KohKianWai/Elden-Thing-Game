package game.enums;

/**
 * Defines the available types of BehaviourSelector strategies.
 *
 * @author Harbir Singh
 * @version 1.0.0
 */
public enum SelectorType {
    /**
     * This selector evaluates behaviours in ascending priority order and executes the first valid action.
     */
    PRIORITY,

    /**
     * This selector picks a behaviour at random each turn and if the chosen behaviour has no valid action, the actor does nothing.
     */
    RANDOM
}
