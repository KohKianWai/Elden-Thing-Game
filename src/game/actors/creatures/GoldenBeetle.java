package game.actors.creatures;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.BehaviourSelector;
import game.actions.ConsumeAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.ProduceEggBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;
import game.items.Consumable;
import game.items.eggs.GoldenBeetleEgg;
import game.utils.LocationUtils;

/**
 * Golden Beetle is a concrete class that represents an golden beetle
 *
 * @author Chin Xin Qing
 * @version 1.1.0
 * Modified by: Harbir Singh
 */
public class GoldenBeetle extends Creature implements Consumable {
    private final int restoreHealthPoint = 15;
    private final int soldPrice = 1000;

    private final BehaviourSelector selector;

    /**
     * Constructs a Golden Beetle with the given behaviour selector.
     *
     * @param selector the BehaviourSelector implementation that determines
     *                 how the beetle chooses its behaviours
     */
    public GoldenBeetle(BehaviourSelector selector){
        super("Golden Beetle", 'b', 25, 5);
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
        // update produce egg counter, if counter reach produceEggTurn, lay an egg
        setProduceEggCounter(getProduceEggCounter() + 1);

        behaviours.put(0, new ProduceEggBehaviour(new GoldenBeetleEgg(selector), getProduceEggCounter(), getProduceEggTurn()));

        Actor followableActor = LocationUtils.getSurroundingActorWithCapability(map.locationOf(this), Ability.FOLLOWABLE);
        // if the beetle is not following a target
        if(!behaviours.containsKey(998)){
            behaviours.put(998, new FollowBehaviour(followableActor));
        }
        // if the beetle's follow target is unconscious follow new actor
        else if (behaviours.containsKey(998) && behaviours.get(998).getAction(this, map) == null){
            behaviours.put(998, new FollowBehaviour(followableActor));
        }


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
        actions.add(new ConsumeAction(this));
        return actions;
    }


    /**
     * A method to consume GoldenBeetle
     * @param actor an actor who perform the consume action
     * @param map the game map where the actor at
     * @return the result of consuming as a String
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        map.removeActor(this);  //remove the beetle from the map
        actor.addBalance(soldPrice);    //add runes to wallet
        actor.heal(restoreHealthPoint); //heal actor
        return actor + " ate " + this;
    }
}

