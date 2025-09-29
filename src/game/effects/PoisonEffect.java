package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents a PoisonEffect.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class PoisonEffect extends StatusEffect {

    private int effectLastingTime;
    private int effectCountdownTimer;
    private int hitPointsDecreasePerTick;

    /**
     * A constructor for PoisonEffect class.
     * @param effectLastingTime         the lasting time of the poison effect.
     * @param hitPointsDecreasePerTick  the hitpoints that will be decreased for each turn.
     */
    public PoisonEffect(int effectLastingTime, int hitPointsDecreasePerTick){
        super("Poison Effect");
        this.effectLastingTime = effectLastingTime;
        this.effectCountdownTimer = 0;
        this.hitPointsDecreasePerTick = hitPointsDecreasePerTick;
    }

    /**
     * Inform a poison effect of the passage of time.
     *
     * @param location  the location where the actor with the poison effect is currently standing.
     * @param actor     the actor holding the poison effect.
     */
    @Override
    public void tick(Location location, Actor actor){
        if(effectCountdownTimer == effectLastingTime){
            actor.removeStatusEffect(this);
        } else {
            actor.hurt(hitPointsDecreasePerTick);
            if(!actor.isConscious()){
                actor.unconscious(location.map());
            }
        }

        effectCountdownTimer += 1;
    }
}
