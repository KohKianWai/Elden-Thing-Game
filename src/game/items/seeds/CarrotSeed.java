package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.CarrotSprout;

/**
 * CarrotSeed is a class that represents a carrot seed
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class CarrotSeed extends Seed {

    /**
     * A constructor of CarrotSeed class
     */
    public CarrotSeed(){
        super("Carrot Seed", 'c', true, 0);
    }

    /**
     * A method to plant a carrot seed
     * @param actor an actor who plant
     * @param gameMap the game map where the actor at
     * @return the result of planting a carrot seed as a String
     */
    @Override
    public String plant(Actor actor, GameMap gameMap){
        actor.removeItemFromInventory(this);
        gameMap.locationOf(actor).setGround(new CarrotSprout());

        return actor + " has planted the " + this;
    }
}
