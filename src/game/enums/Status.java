package game.enums;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class
 * @author Riordan D. Alfredo
 */
public enum Status {
    /**
     * A status indicates that an actor is hostile towards enemies
     */
    HOSTILE_TO_ENEMY,

    /**
     * A status indicates that an actor is hostile toward the player.
     */
    HOSTILE_TO_PLAYER,

    /**
     * A status indicates that the ground is cursed
     */
    CURSED,

    /**
     * A status indicates that the game entity is blessed by grace
     */
    BLESSED
}
