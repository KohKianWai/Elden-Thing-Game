package game.actors.bedofchaos;

import java.util.List;

/**
 * Growable defines any part (Branch or BedOfChaos) that grows a new part each turn.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public interface Growable {

    /**
     * Returns this part's display name ("Bed of Chaos", "Branch").
     *
     * @return The name of this part for description.
     */
    String getPartName();

    /**
     * Returns the list of all parts (branches and leaves) grown by this part.
     *
     * @return the list of parts
     */
    List<TreeStructure> getParts();

    /**
     * Performs the growth logic for this turn and returns a description
     * of what was grown.
     *
     * @return a string describing the growth result
     */
    String growThisTurn();

}
