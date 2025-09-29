package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.Creature;

/**
 * A class that represents a produce offspring action
 *
 * @author Lee Hou Wen
 * @version 1.0.0
 * Modified by: Koh Kian Wai
 */
public class ProduceOffspringAction extends Action {

    private Location offspringLocation;

    private Creature offspring;

    /**
     * Constructor.
     *
     * @param offspringLocation the location of the offspring that will be produced.
     * @param offspring the creature object to be produced.
     */
    public ProduceOffspringAction(Location offspringLocation, Creature offspring) {
        this.offspringLocation = offspringLocation;
        this.offspring = offspring;
    }

    /**
     * A method to execute the produce offspring action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of producing an offspring as a String.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //the actor here is the offspring added
        map.addActor(offspring, offspringLocation);
        return "A new " + offspring + " is spawned by " + actor + "!";
    }

    /**
     * A method to show the description of the produce offspring action on the menu.
     *
     * @param actor The actor performing the action.
     * @return the description of the produce offspring action as a String.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Spawn a new " + offspring;
    }
}
