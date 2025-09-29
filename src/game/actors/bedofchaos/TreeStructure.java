package game.actors.bedofchaos;

/**
 * TreeStructure is an abstract class representing a component of the Bed of Chaos's growing system.
 * It has parts (sub TreeStructures such as Branches or Leaves) and provides methods to manage them.
 * This forms the basis of the Bed of Chaos's recursive growth and damage calculation system.
 * Branch and Leaf classes will extend this base class to provide their own specific behaviour.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public abstract class TreeStructure {

    /**
     * Gets the total accumulated damage contribution of this TreeStructure and its parts.
     * This will be implemented by subclasses (Branch will add 3 damage, Leaf adds 1 damage).
     *
     * @return the accumulated damage contribution
     */
    public abstract int getAccumulatedDamage();

    /**
     * Triggers the effect of a TreeStructure on the Bed of Chaos.
     *
     * @param boss the Bed of Chaos to apply the effect to
     */
    public void applyHealingFromLeaves(BedOfChaos boss) {}

    /**
     * Returns the name of this TreeStructure part for descriptive messages.
     *
     * @return the name of the part ("Bed of Chaos", "Branch", "Leaf")
     */
    public abstract String getPartName();
}
