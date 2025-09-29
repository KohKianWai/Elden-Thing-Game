package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.BehaviourSelector;
import game.actions.TalismanCureAction;
import game.behaviours.ProduceOffspringBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;


import game.enums.Status;
import game.items.TalismanCurable;
import game.utils.LocationUtils;

/**
 * SpiritGoat is a class that represents a spirit goat
 *
 * @author Koh Kian Wai
 * @version 1.1.0
 * Modified by: Lee Hou Wen & Harbir Singh
 */
public class SpiritGoat extends Creature implements TalismanCurable {

    private int breedCounter;
    private int breedCooldown;  // avoid offspring overflow
    private int counter = 0;
    private int disappearTime = 10;
    private final BehaviourSelector selector;

    /**
     * Constructs a Spirit Goat with the given behaviour selector.
     *
     * @param selector the BehaviourSelector implementation that determines how this goat chooses its behaviours
     */
    public SpiritGoat(BehaviourSelector selector){
        super("Spirit Goat", 'y', 50, 0);
        this.addCapability(Ability.MOVABLE);
        this.breedCounter = 0;
        this.breedCooldown = 5;
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
        //increment counter to disappear time
        counter += 1;
        if(counter == disappearTime){
            this.unconscious(map);
            return new DoNothingAction();
        }

        behaviours.put(0, new ProduceOffspringBehaviour(new SpiritGoat(selector), breedCounter, breedCooldown));
        //implemented a counter to avoid overbreeding of spiritgoat
        if (LocationUtils.isCurrentOrSurroundingEntityHasCapability(map.locationOf(this), Status.BLESSED)) {
            breedCounter += 1;
        }
        else {
            // reset the counter to 0 if the creature stepped out the blessed surrounding
            this.resetBreedCounter();
        }

        Action chosen = selector.chooseAction(this, behaviours, map);
        return (chosen != null) ? chosen : new DoNothingAction();
    }

    /**
     * A method that reset the countdown timer of a creature to 0
     */
    public void resetCounter(){
        this.counter = 0;
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
     * A method to cure the spirit goat by a talisman
     * @param actor the actor who uses the talisman to cure
     * @param gameMap the game map that the actor currently at
     * @return the result of curing as a String
     */
    @Override
    public String talismanCure(Actor actor, GameMap gameMap){
        this.resetCounter();
        return this + " is cured and countdown timer is reset! ";
    }

    public void resetBreedCounter(){
        this.breedCounter = 0;
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
