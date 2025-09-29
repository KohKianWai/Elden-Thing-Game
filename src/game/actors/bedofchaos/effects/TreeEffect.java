package game.actors.bedofchaos.effects;

import game.actors.bedofchaos.BedOfChaos;

/**
 * TreeEffect is an interface that represents an effect triggered by a TreeStructure part of the Bed of Chaos.
 * Effects can include healing, damaging the boss, or other future effects.
 * Each effect implementation applies its effect when invoked by a TreeStructure.
 * This abstraction allows new effects to be added without modifying existing growth logic.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public interface TreeEffect {

    /**
     * Applies the effect to the Bed of Chaos.
     *
     * @param boss the Bed of Chaos to apply the effect to
     */
    void applyEffect(BedOfChaos boss);
}
