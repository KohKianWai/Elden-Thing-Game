package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Plantable;

/**
 * PlantAction class is a class that represents a plant action
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class PlantAction extends Action {

    private Plantable plantable;

    /**
     * A constructor for PlantAction class
     * @param plantable the object that can be planted
     */
    public PlantAction(Plantable plantable){
        this.plantable = plantable;
    }

    /**
     * A method to execute the plant action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of planting as a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return plantable.plant(actor, map);
    }

    /**
     * A method to show the description of the plant action on the menu
     * @param actor The actor performing the action.
     * @return the description of the plant action as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " plants the " + plantable + " on the soil";
    }
}
