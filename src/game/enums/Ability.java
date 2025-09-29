package game.enums;

/**
 * Use this enum to represent abilities.
 * Example #1: if the player is capable jumping over walls, you can attach Ability.WALL_JUMP to the player class
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public enum Ability {
    /**
     * This ability represents a ground that can be planted
     */
    PLANTABLE,

    /**
     * This ability represents a ground that can grow a crops
     */
    GROWABLE,

    /**
     * This ability represents an item that can cure other entities
     */
    CURE_ENTITY,

    /**
     * This ability represents an actor that can move
     */
    MOVABLE,

    /**
     * This ability represents an actor that can be followed
     */
    FOLLOWABLE,

    /**
     * This ability represents an actor that can water a sprout
     */
    CAN_WATER
}