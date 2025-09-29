package game.items.givables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * GivableItem is an interface that declares the giveItem() method
 *
 * @author Chin Xin Qing
 * @version 1.0.0
 */
public interface GivableItem {
    /**
     * A method to give the item to NPC
     * @param npc the npc who receive the item
     * @param giver the actor that gives the item
     * @param gameMap the game map that the actor currently at
     * @return the cure result as a string
     */
    String giveItem(Actor npc, Actor giver, GameMap gameMap);
}
