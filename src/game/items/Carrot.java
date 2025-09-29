package game.items;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effects.StrongEffect;

/**
 * A class that represents a Carrot.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class Carrot extends PlantItem {
    private final int restoreHealthPoints;

    /**
     * A constructor of Carrot class
     */
    public Carrot(){
        super("Carrot", 'C', true);
        this.restoreHealthPoints = 15;
    }

    /**
     * A method to consume a carrot.
     * @param actor an actor who consume the carrot.
     * @param map the game map where the actor at.
     * @return the result of consuming a carrot.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        actor.modifyAttribute(
            BaseActorAttributes.HEALTH,
            ActorAttributeOperations.INCREASE,
            restoreHealthPoints
        );
        actor.addStatusEffect(new StrongEffect(3, 5));

        return actor + " ate " + this + " and gained a Strong Effect!";
    }
}
