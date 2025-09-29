package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * TeleportAction moves an actor from their current map and location
 * to a specified coordinate on another or the same map. If the target
 * location is occupied, the teleport fails.
 *
 * @author Harbir Singh
 * @version 1.0.0
 */
public class TeleportAction extends Action {

    private final GameMap destinationMap;
    private final int destX;
    private final int destY;


    /**
     * Creates a TeleportAction to the given map coordinates.
     *
     * @param destinationMap the map to teleport to
     * @param destX the x-coordinate on the destination map
     * @param destY the y-coordinate on the destination map
     */
    public TeleportAction(GameMap destinationMap, int destX, int destY) {
        this.destinationMap = destinationMap;
        this.destX = destX;
        this.destY = destY;
    }


    /**
     * Executes the teleport. Removes the actor from their current map
     * and adds them to the destination map at (destX, destY), unless
     * that tile is occupied.
     *
     * @param actor the actor performing the teleport
     * @param map the current map of the actor
     * @return a message indicating success or failure of the teleport
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location targetLocation = destinationMap.at(destX, destY);

        if (targetLocation.containsAnActor()) {
            return "Teleport failed: " +destinationMap + " (" + destX + "," + destY + ") is occupied";
        }

        // Remove the actor from their current map/tile
        map.removeActor(actor);

        targetLocation.addActor(actor);

        return actor + " teleported to " + destinationMap + " (" + destX + "," + destY + ")";
    }


    /**
     * Describes this action in the menu.
     *
     * @param actor the actor who can perform this action
     * @return menu text showing where the actor will teleport
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " uses the Teleportation Circle to " + destinationMap + " (" + destX + "," + destY + ")";
    }

}
