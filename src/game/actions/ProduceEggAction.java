package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.eggs.Egg;

/**
 * A class that represents a produce egg action
 *
 * @author Lee Hou Wen
 * @version 1.0.0
 * Modified by: Koh Kian Wai
 */
public class ProduceEggAction extends Action {

    private Egg egg;

    /**
     * Constructor.
     *
     * @param egg the Egg object to be produced.
     */
    public ProduceEggAction(Egg egg) {
        this.egg = egg;
    }

    /**
     * A method to execute the produce egg action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of producing an egg as a String.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).addItem(egg);
        return menuDescription(actor);
    }

    /**
     * A method to show the description of the produce egg action on the menu.
     * @param actor The actor performing the action.
     * @return the description of the produce egg action as a String.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " lays an egg!";
    }
}
