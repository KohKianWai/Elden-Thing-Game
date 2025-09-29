package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Bloodrose;
import game.items.seeds.Seed;

/**
 * BloodroseSeed is a class that represents a bloodrose seed
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class BloodroseSeed extends Seed {
    private final int plantHurtPoints = 5;

    /**
     * A constructor of BloodroseSeed class
     */
    public BloodroseSeed(){
        super("Bloodrose Seed", '*', true, 75);
    }

    /**
     * A method to plant a bloodrose seed
     * @param actor an actor who plant
     * @param gameMap the game map where the actor at
     * @return the result of planting a bloodrose seed as a String
     */
    @Override
    public String plant(Actor actor, GameMap gameMap) {
        String result = super.plant(actor, gameMap);

        // if actor does not have enough energy to plant
        if(!result.isBlank()){
            return result;
        }

        // after planting, remove the item from inventory and change the group to bloodrose
        actor.removeItemFromInventory(this);
        gameMap.locationOf(actor).setGround(new Bloodrose());
        actor.hurt(this.plantHurtPoints);

        // if the actor is unconscious while planting the seed
        if(!actor.isConscious()){
            actor.unconscious(gameMap);
        }
        return actor + " has planted the " + this;
    }

}
