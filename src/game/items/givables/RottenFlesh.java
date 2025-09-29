package game.items.givables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.GiveAction;
import game.actors.people.NPCUniqueAttributeEnum;
import game.items.Consumable;

/**
 * RottenFlesh is a class that represents a RottenFlesh
 *
 * @author Chin Xin Qing
 * @version 1.0.0
 */
public class RottenFlesh extends Item implements GivableItem, Consumable {
    private final int affectionValue = 25;
    private final int decreaseHealth = 20;
    private final int decreaseStamina = 20;

    /**
     * A constructor of RottenFlesh class
     */
    public RottenFlesh(){
        super("Rotten Flesh", '/', true);
    }

    /**
     * A method to give the item to NPC
     *
     * @param npc   the npc who receive the item
     * @param gameMap the game map that the actor currently at
     * @return the cure result as a string
     */
    @Override
    public String giveItem(Actor npc, Actor giver, GameMap gameMap) {
        giver.removeItemFromInventory(this);
        npc.modifyAttribute(NPCUniqueAttributeEnum.AFFECTION, ActorAttributeOperations.DECREASE, affectionValue);
        return npc + " received a " + this + " and decreased " + affectionValue + " affection points!";
    }

    /**
     * List of allowable actions that the item allows its owner do to other actor.
     * Example #1: a weapon can return an attacking action to the other actor.
     * Example #2: if the weapon has a special ability, it can return an action to use the special ability.
     * Example #3: a food can return an action to feed the other actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = super.allowableActions(otherActor, location);
        if(otherActor.hasAttribute(NPCUniqueAttributeEnum.AFFECTION)){
            actions.add(new GiveAction(this, otherActor));
        }
        return actions;
    }

    /**
     * List of allowable actions that the item can perform to its owner
     * or to the current map while being carried by an actor
     * Example #1: a healing item can have a special skill that can increase the current actor's hitpoints.
     *
     * @param owner the actor that owns the item
     * @param map the map where the actor is performing the action on
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * A method to consume a consumable
     *
     * @param actor an actor who perform the consume action
     * @param map   the game map where the actor at
     * @return the result of consuming as a String
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, decreaseHealth);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, decreaseStamina);
        if (!actor.isConscious()){
            actor.unconscious(map);
        }
        return actor + " ate " + this;
    }
}
