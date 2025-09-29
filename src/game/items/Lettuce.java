package game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.HealingEffect;

/**
 * A class that represents a Lettuce.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class Lettuce extends PlantItem {

    private final int restoreHealthPoints;
    private final int restoreStaminaPoints;

    /**
     * A constructor of Lettuce class
     */
    public Lettuce(){
        super("Lettuce", 'L', true);
        this.restoreHealthPoints = 10;
        this.restoreStaminaPoints = 20;
    }

    /**
     * A method to consume a lettuce.
     * @param actor an actor who consume the lettuce.
     * @param map the game map where the actor at.
     * @return the result of consuming a lettuce.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        actor.modifyAttribute(
            BaseActorAttributes.HEALTH,
            ActorAttributeOperations.INCREASE,
            restoreHealthPoints
        );
        actor.modifyAttribute(
            BaseActorAttributes.STAMINA,
            ActorAttributeOperations.INCREASE,
            restoreStaminaPoints
        );
        actor.addStatusEffect(new HealingEffect(3, 5));

        return actor + " ate " + this + " and gained a Healing Effect!";
    }
}
