package game.actors.people;

import edu.monash.fit2099.engine.actors.attributes.ActorAttribute;

/**
 * A class that represents a NPC attribute of an NPC, such as affectionLevel.
 * Cite: Created by referecing engine.actors.attributes.BaseActorAttribute
 */
public class NPCUniqueAttribute implements ActorAttribute<Integer> {
    private final int maximumPoints = 100;
    private int points;

    /**
     * constructor of .NPCUniqueAttribute
     */
    public NPCUniqueAttribute(int initialPoints) {
        this.points = initialPoints;
    }

    /**
     * Overwrites the current points of the attribute with the given points.
     * Sets the current points to either the given points or the maximum points, whichever is lesser.
     * @param points the points to overwrite the current points of the attribute
     */
    @Override
    public void update(Integer points) {
        this.points = Math.min(points, maximumPoints);
    }

    /**
     * Increases the current points of the attribute by the given points.
     * It ensures that the new points do not exceed the maximum points by taking either the new points or the maximum points, whichever is lesser.
     * @param points the points to increase the current points of the attribute
     */
    @Override
    public void increase(Integer points) {
        this.points += points;
        this.points = Math.min(this.points, maximumPoints);
    }

    /**
     * Decreases the current points of the attribute by the given points.
     * It ensures that the new points do not fall below 0 by taking either the new points or 0, whichever is greater.
     * For example, the stamina (another example of a basic attribute) of the actor cannot be a negative value
     * @param points the points to decrease the current points of the attribute
     */
    @Override
    public void decrease(Integer points) {
        this.points -= points;
        this.points = Math.max(this.points, 0);
    }

    /**
     * Getter for the current points of the attribute.
     * @return the current points of the attribute
     */
    @Override
    public Integer get() {
        return points;
    }

    /**
     * Getter for the maximum points of the attribute.
     * @return the maximum points of the attribute
     */
    @Override
    public Integer getMaximum() {
        return maximumPoints;
    }

}

