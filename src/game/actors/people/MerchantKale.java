package game.actors.people;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actions.PurchaseAction;
import game.actors.people.monologues.CanSpeakMonologue;
import game.actors.people.monologues.Monologue;
import game.actors.people.monologues.MonologueContext;
import game.actors.people.monologues.conditions.DefaultMonologueCondition;
import game.actors.people.monologues.conditions.EmptyInventoryMonologueCondition;
import game.actors.people.monologues.conditions.LowRunesMonologueCondition;
import game.actors.people.monologues.conditions.ProximityMonologueCondition;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;
import game.enums.Status;
import game.weapons.*;
import game.weapons.effects.*;

import java.util.ArrayList;

/**
 * Represents the NPC 'Merchant Kale', a MonologueSpeaker who wanders and speaks
 * conditional monologues depending on the player's state and surroundings.
 * Monologues include default sayings, remarks on low runes, empty inventory,
 * and proximity to cursed entities.
 *
 * @author Harbir Singh
 * @version 1.0.0
 * Modified by: Koh Kian Wai & Wai Chung Teng & Chin Xin Qing
 */
public class MerchantKale extends Human implements CanSpeakMonologue {
    private Monologue monologue = new Monologue();
    private final int runesThreshold = 500;
    private final ArrayList<Purchasable> purchasableList = new ArrayList<Purchasable>();

    /**
     * Constructs a MerchantKale with predefined hit points, capabilities, behaviours, and monologues.
     */
    public MerchantKale() {
        super("Merchant Kale", 'k', 200);
        this.addCapability(Ability.MOVABLE);
        behaviours.put(999, new WanderBehaviour());

        getMonologue().addToAllMonologues(new DefaultMonologueCondition(
            "A merchant’s life is a lonely one. But the roads… they whisper secrets to those who listen."
        ));
        getMonologue().addToAllMonologues(new LowRunesMonologueCondition(this.runesThreshold,
            "Ah, hard times, I see. Keep your head low and your blade sharp."));
        getMonologue().addToAllMonologues(new EmptyInventoryMonologueCondition(
            "Not a scrap to your name? Even a farmer should carry a trinket or two."));
        getMonologue().addToAllMonologues(new ProximityMonologueCondition(Status.CURSED,
            "Rest by the flame when you can, friend. These lands will wear you thin."));
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
        this.purchasableList.clear();
        //only sell to otherActor if affection level is higher than 5
        if(isWillingToSell(affectionLevel)){
            double discountRate = this.getDiscountRate(affectionLevel);
            sellBroadSword(discountRate);
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
     * @param discountRate the discount rate for the item based on affection level.
     */
    public void sellBroadSword(double discountRate){
        int broadSwordPrice = (int) (150 * discountRate);
        // add purchase effect for broadSword
        ArrayList<PurchaseEffect> broadSwordPurchaseEffect= new ArrayList<PurchaseEffect>();
        broadSwordPurchaseEffect.add(new RunesModifierEffect(-broadSwordPrice));
        broadSwordPurchaseEffect.add(new MaximumAttributesModifierEffect(BaseActorAttributes.STAMINA,
                ActorAttributeOperations.INCREASE, 30));
        this.purchasableList.add(new Broadsword(broadSwordPurchaseEffect, broadSwordPrice));
    }

    /**
     * A method to handle sellDragonSlayerGreatSword logic.
     * @param discountRate the discount rate for the item based on affection level.
     */
    public void sellDragonSlayerGreatSword(double discountRate){
        int dragonSlayerGreatSwordPrice = (int) (1700 * discountRate);
        //add purchase effect for dragonslayer greatsword
        ArrayList<PurchaseEffect> dragonSlayerPurchaseEffect= new ArrayList<PurchaseEffect>();
        dragonSlayerPurchaseEffect.add(new RunesModifierEffect(-dragonSlayerGreatSwordPrice));
        dragonSlayerPurchaseEffect.add(new AttributesModifierEffect(BaseActorAttributes.STAMINA,
                ActorAttributeOperations.INCREASE, 20));
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
        }else if(affectionLevel < 20) {
            return 1.5;
        }else if(affectionLevel > 80){
            return 0.5;
        }else{
            return 1.0;
        }
    }

    /**
     * A method to check if the Human is willing to sell an item
     * @param affectionLevel the affection level of a Human
     * @return boolean indicating if the Human is willing to sell an item
     */
    public boolean isWillingToSell(int affectionLevel){
        return affectionLevel >= 10;
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
