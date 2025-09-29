package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * ConsumeAction class is a class that represents a consume action.
 *
 * @author Lee Hou Wen
 * @version 1.0.0
 * Modified by: Koh Kian Wai
 */
public class ConsumeAction extends Action {

    private Consumable consumable;

    /**
     * Constructor.
     * @param consumable the consumable object that can be consumed.
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * A method to execute the consume action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of consume action as a String.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.consume(actor, map);
    }

    /**
     * A method to show the description of the consume action on the menu.
     * @param actor The actor performing the action.
     * @return the description of the consume action as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats the " + consumable;
    }
}
