package game.actors.bedofchaos.effects;

import game.actors.bedofchaos.BedOfChaos;

/**
 * BoCHealingEffect is a TreeEffect that heals the Bed of Chaos by a specified amount of health points.
 * This effect is used by Leaves to heal the Bed of Chaos each time they grow.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public class BoCHealingEffect implements TreeEffect {

    /**
     * The amount of health points to heal the Bed of Chaos.
     */
    private final int healAmount;

    /**
     * Constructs a new BoCHealingEffect with the specified healing amount.
     *
     * @param healAmount the amount of health points to heal the Bed of Chaos
     */
    public BoCHealingEffect(int healAmount) {
        this.healAmount = healAmount;
    }

    /**
     * Applies the healing effect to the BedOfChaos by invoking its heal method.
     *
     * @param boss the BedOfChaos to heal
     */
    @Override
    public void applyEffect(BedOfChaos boss) {
        boss.heal(healAmount);
    }
}
