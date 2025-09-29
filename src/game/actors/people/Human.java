package game.actors.people;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

import java.util.Map;
import java.util.TreeMap;

/**
 * An abstract class that represents Human
 *
 * @author Chin Xin Qing
 * @version 1.0.0
 */
public abstract class Human extends Actor {
    /**
     * A map of behaviours prioritized by integer keys.
     * Behaviours are executed in ascending key order.
     */
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();
    /**
     * The constructor of the Human class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Human(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addAttribute(NPCUniqueAttributeEnum.AFFECTION, new NPCUniqueAttribute(30));
    }

    /**
     * A method to return a new collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(this, direction));

        return actions;
    }

    /**
     * Called each turn to determine the action to perform.
     * Checks behaviours in priority order and returns the first valid action.
     * If none are valid, returns DoNothingAction.
     *
     * @param actions the list of possible actions
     * @param lastAction the action performed last turn
     * @param map the game map
     * @param display the display object for output
     * @return the chosen Action to perform this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * A method to represent all the attributes of a NPC in String format
     * @return all attributes of a NPC as String
     */
    @Override
    public String toString(){
        String ret = super.toString();
        ret += " Affection Point: (" + this.getAttribute(NPCUniqueAttributeEnum.AFFECTION) + "/"
                + this.getAttributeMaximum(NPCUniqueAttributeEnum.AFFECTION) + ")";
        return ret;
    }
}
