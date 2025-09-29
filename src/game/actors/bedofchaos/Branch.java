package game.actors.bedofchaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Branch represents a growing branch in the Bed of Chaos's structure.
 * Each branch contributes 3 damage points and can grow further parts (branches or leaves).
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public class Branch extends TreeStructure implements Growable {

    /**
     * The list of growable parts (specifically new Branches) that this branch has grown.
     */
    private List<Growable> growablePart;

    /**
     * The list of all parts (Branches and Leaves) that this branch has grown.
     */
    private List<TreeStructure> parts;

    /**
     * Constructs a new Branch with empty lists for growable parts
     * and tree parts.
     */
    public Branch(){
        growablePart = new ArrayList<>();
        parts = new ArrayList<>();
    }

    /**
     * Executes the growth cycle for this branch.
     * Each time this method is called, the branch randomly decides whether
     * to grow a new Branch or a new Leaf (50% chance for each).
     * If a new branch is grown, it is added to the growablePart list
     * to participate in future growth cycles.
     * After growing a new part, this method recursively calls growThisTurn()
     * on all previously grown Branch parts to propagate growth further
     * down the tree.
     *
     * @return a string describing the growth that occurred during this turn
     */
    @Override
    public String growThisTurn() {
        String growthMessage = "";
        Random random = new Random();
        if (random.nextBoolean()) {
            Branch newBranch = new Branch();
            growablePart.add(newBranch);
            parts.add(newBranch);
            growthMessage += getPartName() + " grows a new branch\n";
        } else {
            parts.add(new Leaf());
            growthMessage += getPartName() + " grows a new leaf\n";
        }

        for (Growable part : growablePart) {
            growthMessage += part.growThisTurn();

        }
        return growthMessage;
    }

    /**
     * Gets the total accumulated damage contribution of this Branch and its parts.
     * Each Branch contributes 3 damage points, and accumulates the damage for all branches grown.
     *
     * @return the accumulated damage contribution
     */
    @Override
    public int getAccumulatedDamage() {
        int totalDamage = 3; // Branch base damage
        for (TreeStructure part : parts) {
            totalDamage += part.getAccumulatedDamage();
        }
        return totalDamage;
    }

    /**
     * Applies healing from any Leaf structures contained in this
     * branch's subtree to the specified BedOfChaos instance.
     * This recursively traverses all parts of the branch.
     *
     * @param boss the Bed of Chaos to heal
     */
    @Override
    public void applyHealingFromLeaves(BedOfChaos boss) {
        for (TreeStructure part : parts) {
            part.applyHealingFromLeaves(boss);
        }
    }

    /**
     * Returns the name of this TreeStructure part.
     *
     * @return the name of Branch
     */
    @Override
    public String getPartName() {
        return "Branch";
    }

    /**
     * Returns the list of all parts grown by this branch (both branches and leaves).
     *
     * @return the list of parts
     */
    public List<TreeStructure> getParts() {
        return parts;
    }
}
