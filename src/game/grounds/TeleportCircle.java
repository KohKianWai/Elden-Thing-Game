package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;

import game.actions.TeleportAction;
import java.util.ArrayList;
import java.util.List;

/**
 * A ground type representing a teleportation circle.
 * Use this to teleport actors to any of its configured destinations.
 *
 * @author Harbir Singh
 * @version 1.0.0
 */
public class TeleportCircle extends Ground {

    private final List<Location> destinations = new ArrayList<>();

    /**
     * Creates a TeleportCircle.
     */
    public TeleportCircle(){
        super('A', "TeleportationCircle");
    }

    /**
     * Adds a new teleport destination to this circle.
     *
     * @param destination the map location to which actors can teleport
     */
    public void addDestination(Location destination){
        destinations.add(destination);
    }

    /**
     * Returns the list of teleport actions available to the actor,
     * one for each configured destination.
     *
     * @param actor     the actor standing on this ground
     * @param location  the current location of this teleport circle
     * @param direction the direction of the actor relative to this ground
     * @return an ActionList containing a TeleportAction for each destination
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        for (Location target : destinations){
            GameMap destinationMap = target.map();
            int x = target.x();
            int y = target.y();

            actions.add(new TeleportAction(destinationMap, x, y));
        }
        return actions;
    }



}
