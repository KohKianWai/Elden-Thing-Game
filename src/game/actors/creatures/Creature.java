package game.actors.creatures;

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
 * Creature class is a base class that represents a generic Creature
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public abstract class Creature extends Actor {

    /**
     * behaviours attribute is a tree map that stores all the behaviours of a NPC
     */
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private int produceEggCounter; //counter for the production of egg
    private int produceEggTurn;

    /**
     * A constructor for Creature class
     * @param name the name of the creature
     * @param displayChar the display character of the creature
     * @param hitPoints the health of the creature
     */
    public Creature(String name, char displayChar, int hitPoints, int produceEggTurn){
        super(name, displayChar, hitPoints);
        this.produceEggCounter = 0;
        this.produceEggTurn = produceEggTurn;
    }

    /**
     * A method that select and return an action to perform on the current turn.
     * Cite: Created by FIT2099 Teaching Team in demo.huntsman.HuntsmanSpider playTurn() method
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
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
//
//        //For Guts attack on the creature
//        if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER) && this.getAttribute(BaseActorAttributes.HEALTH)>50){
//            actions.add(new AttackAction(this, direction));
//        }

        return actions;
    }

    /**
     * Sets the current value of the egg production counter.
     *
     * @param produceEggCounter the number of turns passed.
     */
    public void setProduceEggCounter(int produceEggCounter){
        this.produceEggCounter = produceEggCounter;
    }

    /**
     * Returns the current value of the egg production counter.
     *
     * @return the number of turns passed.
     */
    public int getProduceEggCounter(){
        return this.produceEggCounter;
    }

    /**
     * Reutruns the required number of turns for producing an egg.
     *
     * @return the number of turns for producing an egg.
     */
    public int getProduceEggTurn(){
        return this.produceEggTurn;
    }
}
