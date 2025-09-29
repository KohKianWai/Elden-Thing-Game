package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.MushroomSprout;

/**
 * MushroomSeed is a class that represents a mushroom seed
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class MushroomSeed extends Seed{

    /**
     * A constructor of MushroomSeed class
     */
    public MushroomSeed(){
        super("Mushroom Seed", 'm', true, 0);
    }

    /**
     * A method to plant a mushroom seed
     * @param actor an actor who plant
     * @param gameMap the game map where the actor at
     * @return the result of planting a mushroom seed as a String
     */
    @Override
    public String plant(Actor actor, GameMap gameMap){
        actor.removeItemFromInventory(this);
        gameMap.locationOf(actor).setGround(new MushroomSprout());

        return actor + " has planted the " + this;
    }
}
