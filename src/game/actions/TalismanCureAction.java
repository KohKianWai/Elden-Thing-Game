package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.TalismanCurable;

/**
 * TalismanCureAction is a class that represent a cure action by using talisman
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class TalismanCureAction extends Action {

    private TalismanCurable talismanCurable;

    /**
     * A constructor for TalismanCureAction class
     * @param talismanCurable the object that can be cured by talisman
     */
    public TalismanCureAction(TalismanCurable talismanCurable){
        this.talismanCurable = talismanCurable;
    }

    /**
     * A method to execute the cure action by using talisman
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of curing as a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return talismanCurable.talismanCure(actor, map);
    }

    /**
     * A method to show the description of cure action on the menu
     * @param actor The actor performing the action.
     * @return the description of the cure action as a String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " cure " + talismanCurable;
    }
}
