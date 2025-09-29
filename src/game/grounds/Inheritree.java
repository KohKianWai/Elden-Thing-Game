package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * Inheritree is a class that represents an inheritree
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class Inheritree extends Ground {

    private final int healPoints = 5;
    private final int restoreStaminaPoints = 5;

    /**
     * A constructor for Inheritree class
     */
    public Inheritree(){
        super('t', "Inheritree");
        this.addCapability(Status.BLESSED);
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
            // heal the surrounding actor
            if(destination.containsAnActor()){
                Actor actor = destination.getActor();
                actor.heal(this.healPoints);
                // if the actor has the stamina attribute, restore their stamina
                if(actor.hasAttribute(BaseActorAttributes.STAMINA)){
                    actor.modifyAttribute(
                        BaseActorAttributes.STAMINA,
                        ActorAttributeOperations.INCREASE,
                        restoreStaminaPoints);
                }
            }
        }
    }
}
