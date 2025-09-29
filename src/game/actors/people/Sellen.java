package game.actors.people;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.*;
import game.actors.creatures.GoldenBeetle;
import game.actors.creatures.OmenSheep;
import game.actors.people.monologues.CanSpeakMonologue;
import game.actors.people.monologues.Monologue;
import game.actors.people.monologues.MonologueContext;
import game.actors.people.monologues.conditions.DefaultMonologueCondition;
import game.behaviours.PriorityBehaviourSelector;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;
import game.weapons.*;
import game.weapons.effects.*;

import java.util.ArrayList;

/**
 * Represents the NPC 'Sellen', a MonologueSpeaker with wander behaviour.
 * Provides specific monologues reflecting Sellen's character and adds MOVABLE capability.
 *
 * @author Harbir Singh
 * @version 1.0.0
 * Modified by: Koh Kian Wai & Wai Chung Teng & Chin Xin Qing
 */
public class Sellen extends Human implements CanSpeakMonologue {

    private final ArrayList<Purchasable> purchasableList = new ArrayList<Purchasable>();

    private Monologue monologue = new Monologue();
    /**
     * Constructs a Sellen NPC with predefined hit points, capabilities, behaviours, and monologues.
     */
    public Sellen(){
        super("Sellen", 's', 150);
        this.addCapability(Ability.MOVABLE);
        behaviours.put(999, new WanderBehaviour());
        getMonologue().addToAllMonologues(new DefaultMonologueCondition(
            "The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever."
        ));
        getMonologue().addToAllMonologues(new DefaultMonologueCondition(
            "You sense it too, don’t you? The Glintstone hums, even now."
        ));
    }

    /**
     * A method to return a new collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the other Actor
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new ListenAction(this));
        int affectionLevel = this.getAttribute(NPCUniqueAttributeEnum.AFFECTION);
        // clear sell list
        this.purchasableList.clear();
        //only sell to otherActor if affection level is higher than 5
        if(isWillingToSell(affectionLevel)){
            double discountRate = this.getDiscountRate(affectionLevel);
            sellBroadSword(discountRate);
            sellKatana(discountRate, map);
            sellDragonSlayerGreatSword(discountRate);
        }

        //add purchase action option
        for (Purchasable purchasable : this.purchasableList){
            actions.add(new PurchaseAction(purchasable, this));
        }
        return actions;
    }

    /**
     * A method to handle sell broadsword logic.
     * @param discountRate the discount rate for the broadsword based on affection level.
     */
    public void sellBroadSword(double discountRate){
        int broadSwordPrice = (int) (100 * discountRate);
        // add purchase effect for broadSword
        ArrayList<PurchaseEffect> broadSwordPurchaseEffect= new ArrayList<PurchaseEffect>();
        broadSwordPurchaseEffect.add(new RunesModifierEffect(-broadSwordPrice));
        broadSwordPurchaseEffect.add(new MaximumAttributesModifierEffect(BaseActorAttributes.HEALTH,
                ActorAttributeOperations.INCREASE, 20));
        this.purchasableList.add(new Broadsword(broadSwordPurchaseEffect, broadSwordPrice));
    }

    /**
     * A method to handle sell sellKatana logic.
     * @param discountRate the discount rate for the item based on affection level.
     */
    public void sellKatana(double discountRate, GameMap map){
        int katanaPrice = (int) (500 * discountRate);
        //add purchase effect for katana
        ArrayList<PurchaseEffect> katanaPurchaseEffect= new ArrayList<PurchaseEffect>();
        katanaPurchaseEffect.add(new RunesModifierEffect(-katanaPrice));
        katanaPurchaseEffect.add(new SpawnCreatureEffect(new OmenSheep(new PriorityBehaviourSelector()), map.locationOf(this)));
        katanaPurchaseEffect.add(new AttributesModifierEffect(BaseActorAttributes.HEALTH,
                ActorAttributeOperations.INCREASE, 10)
        );
        katanaPurchaseEffect.add(new MaximumAttributesModifierEffect(BaseActorAttributes.STAMINA,
                ActorAttributeOperations.INCREASE, 20)
        );
        this.purchasableList.add(new Katana(katanaPurchaseEffect, katanaPrice));
    }

    /**
     * A method to handle sellDragonSlayerGreatSword logic.
     * @param discountRate the discount rate for the item based on affection level.
     */
    public void sellDragonSlayerGreatSword(double discountRate){
        int dragonSlayerGreatSwordPrice = (int) (1500 * discountRate);
        //add purchase effect for dragonslayer greatsword
        ArrayList<PurchaseEffect> dragonSlayerPurchaseEffect= new ArrayList<PurchaseEffect>();
        dragonSlayerPurchaseEffect.add(new RunesModifierEffect(-dragonSlayerGreatSwordPrice));
        dragonSlayerPurchaseEffect.add(new SpawnCreatureEffect(new GoldenBeetle(new PriorityBehaviourSelector())));
        this.purchasableList.add(new DragonslayerGreatsword(dragonSlayerPurchaseEffect, dragonSlayerGreatSwordPrice));
    }

    /**
     * A method to get discount rate based on affection level
     * @param affectionLevel the affection level of a Human
     * @return the discount rate
     */
    public double getDiscountRate(int affectionLevel){
        if(affectionLevel == 100){
            return 0.0;
        }else if(affectionLevel < 10) {
            return 1.5;
        }else if(affectionLevel > 50){
            return 0.5;
        }else{
            return 1.0;
        }
    }

    /**
     * A method to check if the Human is willing to sell an item
     * @param affectionLevel the affection level of a NPCHuman
     * @return boolean indicating if the Human is willing to sell an item
     */
    public boolean isWillingToSell(int affectionLevel){
        return affectionLevel >= 5;
    }


    /**
     * Gets the Monologue instance associated with this speaker.
     *
     * @return the Monologue object
     */
    public Monologue getMonologue() {
        return monologue;
    }


    /**
     * Returns a monologue string to speak to a given actor on the specified map.
     *
     * @param actor the actor being spoken to
     * @param map the game map where the interaction takes place
     * @return a monologue string
     */
    @Override
    public String speakTo(Actor actor, GameMap map) {
        return getMonologue().getRandom(new MonologueContext(this, actor, map));
    }

}