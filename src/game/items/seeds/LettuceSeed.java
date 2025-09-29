package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.LettuceSprout;

/**
 * LettuceSeed is a class that represents a lettuce seed
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class LettuceSeed extends Seed{

    /**
     * A constructor of LettuceSeed class
     */
    public LettuceSeed(){
        super("Lettuce Seed", 'l', true, 0);
    }

    /**
     * A method to plant a lettuce seed
     * @param actor an actor who plant
     * @param gameMap the game map where the actor at
     * @return the result of planting a lettuce seed as a String
     */
    @Override
    public String plant(Actor actor, GameMap gameMap){
        actor.removeItemFromInventory(this);
        gameMap.locationOf(actor).setGround(new LettuceSprout());

        return actor + " has planted the " + this;
    }
}
