package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that represents a StrongEffect.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class StrongEffect extends StatusEffect {
    private int effectLastingTime;
    private int effectCountdownTimer;
    private int staminaIncreasePerTick;

    /**
     * A constructor for StrongEffect class.
     * @param effectLastingTime       the lasting time of the strong effect.
     * @param staminaIncreasePerTick  the stamina that will be increased for each turn.
     */
    public StrongEffect(int effectLastingTime, int staminaIncreasePerTick){
        super("Strong Effect");
        this.effectLastingTime = effectLastingTime;
        this.effectCountdownTimer = 0;
        this.staminaIncreasePerTick = staminaIncreasePerTick;
    }

    /**
     * Inform a strong effect of the passage of time.
     *
     * @param location  the location where the actor with the strong effect is currently standing.
     * @param actor     the actor holding the strong effect.
     */
    @Override
    public void tick(Location location, Actor actor){
        if(effectCountdownTimer == effectLastingTime){
            actor.removeStatusEffect(this);
        } else {
            actor.modifyAttribute(
                BaseActorAttributes.STAMINA,
                ActorAttributeOperations.INCREASE,
                staminaIncreasePerTick
            );
        }

        effectCountdownTimer += 1;
    }
}
