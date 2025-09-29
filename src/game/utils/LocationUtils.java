package game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import java.util.List;


/**
 * LocationUtils is a class that represents Location Utility function
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class LocationUtils {

    /**
     * A method to check if surrounding contain entity with certain capability
     * @param location the location to check
     * @param capability the capability to check
     * @return true if there's an entity with certain capability nearby, false otherwise
     */
    public static boolean isCurrentOrSurroundingEntityHasCapability(Location location, Enum<?> capability){
        // check current location ground and surrounding actor
        if(location.getGround().hasCapability(capability) || getSurroundingActorWithCapability(location, capability) != null){
            return true;
        }

        // check current location actor
        Actor currentLocationActor = location.getActor();
        if(currentLocationActor != null && currentLocationActor.hasCapability(capability)){
            return true;
        }

        // check current location items
        for(Item item: location.getItems()){
            if(item.hasCapability(capability)){
                return true;
            }
        }

        // check surrounding location ground and items
        for(Exit exit: location.getExits()){
            Location destination = exit.getDestination();
            List<Item> items = destination.getItems();
            // check every item in the exit
            for(Item item: items){
                if(item.hasCapability(capability)){
                    return true;
                }
            }
            if(destination.getGround().hasCapability(capability)){
                return true;
            }
        }

        return false;
    }

    /**
     * A method to get surrounding actor with certain capability
     * @param location the location to check
     * @param capability the capability to check
     * @return nearby actor with certain capability, if no such actor found, return null
     */
    public static Actor getSurroundingActorWithCapability(Location location, Enum<?> capability){
        //check every exit for actor with certain capability
        for(Exit exit: location.getExits()){
            Location destination = exit.getDestination();
            Actor actor = destination.getActor();
            if(actor != null && actor.hasCapability(capability)){
                return actor;
            }
        }
        return null;
    }
}
