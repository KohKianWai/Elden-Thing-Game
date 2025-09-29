package game.enums;

/**
 * Use this enum to represent CreatureType.
 * Example #1: if the player is capable jumping over walls, you can attach Ability.WALL_JUMP to the player class
 *
 * @author Koh Kian Wai
 * @version 1.1.0
 * Modified by: Harbir Singh
 */
public enum CreatureType {
    /**
     * This creatureType represents a spirit goat
     */
    SPIRITGOAT,
    /**
     * This creatureType represents a omen sheep
     */
    OMENSHEEP,
    /**
     * This creatureType represents a golden beetle
     */
    GOLDENBEETLE,

    /**
     * This creatureType represents a spirit goat with random behaviour-picking
     */
    RANDOMSPIRITGOAT,

    /**
     * This creatureType represents a omen sheep with random behaviour-picking
     */
    RANDOMOMENSHEEP,

    /**
     * This creatureType represents a golden beetle with random behaviour-picking
     */
    RANDOMGOLDENBEETLE
}
