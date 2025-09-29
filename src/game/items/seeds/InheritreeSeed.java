package game.items.seeds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;
import game.grounds.Inheritree;
import game.grounds.Soil;
import game.items.seeds.Seed;

/**
 * InheritreeSeed is a class that represents an inheritree seed
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class InheritreeSeed extends Seed {

    /**
     * A constructor for InheritreeSeed class
     */
    public InheritreeSeed(){
        super("Inheritree Seed", '*', true, 25);
    }

    /**
     * A method to plant an inheritree seed
     * @param actor an actor who plant
     * @param gameMap the game map where the actor at
     * @return the result of planting an inheritree seed
     */
    @Override
    public String plant(Actor actor, GameMap gameMap) {
        String result = super.plant(actor, gameMap);

        // if actor does not have enough energy to plant
        if(!result.isBlank()){
            return result;
        }

        actor.removeItemFromInventory(this);
        gameMap.locationOf(actor).setGround(new Inheritree());
        // cure the surrounding cursed ground
        for(Exit exit: gameMap.locationOf(actor).getExits()){
            Ground destinationGround = exit.getDestination().getGround();
            if(destinationGround.hasCapability(Status.CURSED)){
                destinationGround.removeCapability(Status.CURSED);
                exit.getDestination().setGround(new Soil());
            }
        }

        return actor + " has planted the " + this;
    }

}
