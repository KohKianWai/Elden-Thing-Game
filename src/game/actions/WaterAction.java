package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Waterable;

/**
 * A class that represents a WaterAction.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class WaterAction extends Action {

    private Waterable waterable;

    /**
     * A constructor for WaterAction class.
     * @param waterable the waterable ground.
     */
    public WaterAction(Waterable waterable){
        this.waterable = waterable;
    }

    /**
     * A method to execute the water action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of execution as a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return waterable.water(actor, map);
    }

    /**
     * A method to show the description of the water action on the menu
     * @param actor The actor performing the action.
     * @return the description of the water action as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " water the " + waterable;
    }
}
