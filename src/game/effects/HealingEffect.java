package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents a HealingEffect.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class HealingEffect extends StatusEffect {
    private int effectLastingTime;
    private int effectCountdownTimer;
    private int hitPointsIncreasePerTick;

    /**
     * A constructor for HealingEffect class.
     * @param effectLastingTime         the lasting time of the healing effect.
     * @param hitPointsIncreasePerTick  the hitpoints that will be healed for each turn.
     */
    public HealingEffect(int effectLastingTime, int hitPointsIncreasePerTick){
        super("Healing Effect");
        this.effectLastingTime = effectLastingTime;
        this.effectCountdownTimer = 0;
        this.hitPointsIncreasePerTick = hitPointsIncreasePerTick;
    }

    /**
     * Inform a healing effect of the passage of time.
     *
     * @param location  the location where the actor with the healing effect is currently standing.
     * @param actor     the actor holding the healing effect.
     */
    @Override
    public void tick(Location location, Actor actor){
        if(effectCountdownTimer == effectLastingTime){
            actor.removeStatusEffect(this);
        } else {
            actor.heal(hitPointsIncreasePerTick);
        }

        effectCountdownTimer += 1;
    }
}
