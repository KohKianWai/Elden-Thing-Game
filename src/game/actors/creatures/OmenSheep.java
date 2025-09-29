package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.BehaviourSelector;
import game.actions.TalismanCureAction;
import game.behaviours.ProduceEggBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;
import game.grounds.Inheritree;
import game.items.TalismanCurable;
import game.items.eggs.OmenSheepEgg;

/**
 * OmenSheep is a class that represents an omen sheep
 *
 * @author Koh Kian Wai
 * @version 1.1.0
 * Modified by: Lee Hou Wen & Harbir Singh
 */
public class OmenSheep extends Creature implements TalismanCurable {

    private final BehaviourSelector selector;
    private int counter = 0;
    private int disappearTime = 15;


    /**
     * Constructs an Omen Sheep with the given behaviour selector.
     *
     * @param selector the BehaviourSelector that determines how this sheep
     *                 chooses its behaviours
     */
    public OmenSheep(BehaviourSelector selector){
        super("Omen Sheep", 'm', 75, 7);
        this.addCapability(Ability.MOVABLE);
        behaviours.put(999, new WanderBehaviour());

        this.selector = selector;
    }

    /**
     * A method that select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        counter += 1;
        if(counter == disappearTime){
            this.unconscious(map);
            return new DoNothingAction();
        }
        // update counter
        setProduceEggCounter(getProduceEggCounter() + 1);
        //add produce egg behaviour
        behaviours.put(0, new ProduceEggBehaviour(new OmenSheepEgg(selector), getProduceEggCounter(), getProduceEggTurn()));

        Action chosen = selector.chooseAction(this, behaviours, map);
        return (chosen != null) ? chosen : new DoNothingAction();
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
        actions.add(super.allowableActions(otherActor, direction, map));
        if(otherActor.hasCapability(Ability.CURE_ENTITY)){
            actions.add(new TalismanCureAction(this));
        }
        return actions;
    }

    /**
     * A method to cure the omen sheep by a talisman
     * @param actor the actor who uses the talisman to cure
     * @param gameMap the game map that the actor currently at
     * @return the result of curing as a String
     */
    @Override
    public String talismanCure(Actor actor, GameMap gameMap){
        Location omenSheepLocation = gameMap.locationOf(this);
        // loop through the surrounding of the omen sheep and grow the inheritree
        for(Exit exit: omenSheepLocation.getExits()){
            if(exit.getDestination().getGround().hasCapability(Ability.GROWABLE)){
                exit.getDestination().setGround(new Inheritree());
            }
        }
        return this + " is cured! Inheritree grows on surroundings!";
    }

    /**
     * A method to represent all the attributes of a creature in String format
     * @return all the attributes of a creature as a String
     */
    @Override
    public String toString(){
        return super.toString() + " Countdown Timer: (" +
                this.counter + "/" + this.disappearTime + ")";
    }
}
