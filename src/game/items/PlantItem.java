package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;

/**
 * An abstract class that represents a PlantItem.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public abstract class PlantItem extends Item implements Consumable {

    /**
     * Constructor.
     *
     * @param name        the name of the plant item.
     * @param displayChar the character to display for this item.
     * @param portable    whether the plant item can be picked up and carried.
     */
    public PlantItem(String name, char displayChar, boolean portable){
        super(name, displayChar, portable);
    }

    /**
     * Returns a list of allowable actions for the actor that carrying this plant item.
     *
     * @param owner the actor who is carrying this item.
     * @param map   the map the actor is on.
     * @return a list containing the allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }
}
