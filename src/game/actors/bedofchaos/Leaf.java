package game.actors.bedofchaos;

import game.actors.bedofchaos.effects.BoCHealingEffect;

/**
 * Leaf represents a growing leaf in the Bed of Chaos's structure.
 * Each leaf contributes 1 damage point and cannot grow further.
 * When a leaf grows, it heals the Bed of Chaos by a fixed amount.
 * The healing logic is handled by the HealingEffect class.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public class Leaf extends TreeStructure {

    /**
     * The healing effect triggered by this Leaf when grown.
     */
    private final BoCHealingEffect healingEffect;

    /**
     * Constructor for Leaf.
     * Initializes a healing effect with 5 health points to heal the Bed of Chaos.
     */
    public Leaf() {
        this.healingEffect = new BoCHealingEffect(5);
    }

    /**
     * Gets the damage contribution of this Leaf.
     * Each leaf contributes 1 damage point.
     *
     * @return the damage contribution (1)
     */
    @Override
    public int getAccumulatedDamage() {
        return 1;
    }

    /**
     * Triggers the healing effect of this Leaf on the Bed of Chaos.
     *
     * @param boss the Bed of Chaos to apply the healing effect to
     */
    @Override
    public void applyHealingFromLeaves(BedOfChaos boss) {
        healingEffect.applyEffect(boss);
    }

    /**
     * Returns the name of this TreeStructure part.
     *
     * @return the name of Leaf
     */
    @Override
    public String getPartName() {
        return "Leaf";
    }

}
