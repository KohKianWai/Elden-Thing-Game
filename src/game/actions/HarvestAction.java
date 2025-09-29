package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Harvestable;

/**
 * A class that represents a HarvestAction.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class HarvestAction extends Action {

    private Harvestable harvestable;

    /**
     * A constructor for HarvestAction class.
     * @param harvestable the harvestable ground.
     */
    public HarvestAction(Harvestable harvestable){
        this.harvestable = harvestable;
    }

    /**
     * A method to execute the harvest action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of execution as a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return harvestable.harvest(actor, map);
    }

    /**
     * A method to show the description of the harvest action on the menu
     * @param actor The actor performing the action.
     * @return the description of the harvest action as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvest " + harvestable;
    }
}
