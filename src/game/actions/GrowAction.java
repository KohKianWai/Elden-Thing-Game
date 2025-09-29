package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.bedofchaos.*;

import java.util.Random;

/**
 * GrowAction represents the action for a TreeStructure (either the Bed of Chaos itself or a Branch) to grow a new part.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public class GrowAction extends Action {

    /**
     * The part that will perform the growth.
     */
    private final Growable growable;

    /**
     * Random number generator for deciding the growth result.
     */
    private final Random random = new Random();

    /**
     * Constructor for GrowAction.
     *
     * @param growable the TreeStructure(Branch) or the Bed Of Chaos itself, that will perform the growth
     */
    public GrowAction(Growable growable) {
        this.growable = growable;
    }

    /**
     * Executes the growth action, adding a new Branch or Leaf to the TreeStructure by calling
     * the grow method from growable objects.
     *
     * @param actor The actor performing the action.
     * @param map   The map containing the actor.
     * @return a description of the growth result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return growable.growThisTurn();
    }


    /**
     * Provides a description of the growth action for the in-game menu.
     *
     * @param actor The actor performing the action.
     * @return a string description.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " grows a new part.";
    }
}
