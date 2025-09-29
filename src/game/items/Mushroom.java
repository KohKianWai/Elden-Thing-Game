package game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.PoisonEffect;

/**
 * A class that represents a Mushroom.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class Mushroom extends PlantItem {

    /**
     * A constructor of Mushroom class
     */
    public Mushroom(){
        super("Mushroom", 'M', true);
    }

    /**
     * A method to consume a mushroom.
     * @param actor an actor who consume the mushroom.
     * @param map the game map where the actor at.
     * @return the result of consuming a mushroom.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        actor.addStatusEffect(new PoisonEffect(5, 5));
        return actor + " ate " + this + " and gained a Poison Effect!";
    }
}
