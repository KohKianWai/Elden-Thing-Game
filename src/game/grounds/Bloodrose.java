package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;

/**
 * Bloodrose class is a class that represents a bloodrose
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class Bloodrose extends Ground {

    private final int hurtPoints = 10;

    /**
     * A constructor of Bloodrose class
     */
    public Bloodrose(){
        super('w', "Bloodrose");
    }

    /**
     * A method that simulates the passage of time and updates the object's state accordingly
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        // for each surrounding tiles
        for(Exit exit: location.getExits()){
            Location destination = exit.getDestination();
            // hurt the surrounding actor
            if(destination.containsAnActor()){
                Actor actor = destination.getActor();
                actor.hurt(this.hurtPoints);
                // if the actor is unconscious
                if(!actor.isConscious()){
                    actor.unconscious(location.map());
                }
            }
        }
    }

}
