package game.actors.people;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actors.people.monologues.CanSpeakMonologue;
import game.actors.people.monologues.Monologue;
import game.actors.people.monologues.MonologueContext;
import game.actors.people.monologues.conditions.DefaultMonologueCondition;
import game.actors.people.monologues.conditions.LowHpMonologueCondition;
import game.behaviours.attack.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.behaviours.attack.AttackCondition;
import game.behaviours.attack.TargetAboveHpCondition;
import game.behaviours.attack.TargetHasCapabilityCondition;
import game.enums.Ability;
import game.enums.Status;
import game.weapons.BareFist;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the NPC 'Guts', a hostile MonologueSpeaker with attack and wander behaviours.
 * Guts attacks targets who have more than a threshold of hit points and are movable.
 * Includes monologues for aggressive shouts and remarks when low on hit points.
 *
 * @author Harbir Singh
 * @version 1.0.0
 * Modified by: Koh Kian Wai & Wai Chung Teng
 */
public class Guts extends Human implements CanSpeakMonologue {

    private Monologue monologue = new Monologue();
    private final int attackHitPointsThreshold = 50;
    private final int hitPointsThreshold = 50;

    /**
     * Constructs Guts with 500 hit points, bare fists as intrinsic weapon,
     * hostile to player and movable capabilities, attack behaviour targeting
     * movable actors above a hit point threshold, and wander behaviour.
     * Adds aggressive and low HP monologues.
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.setIntrinsicWeapon(new BareFist());
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.addCapability(Ability.MOVABLE);

        List<AttackCondition> attackConditions = new ArrayList<>();
        attackConditions.add(new TargetAboveHpCondition(attackHitPointsThreshold));
        attackConditions.add(new TargetHasCapabilityCondition(Ability.MOVABLE));
        behaviours.put(0, new AttackBehaviour(attackConditions));
        behaviours.put(999, new WanderBehaviour());

        getMonologue().addToAllMonologues(new DefaultMonologueCondition("RAAAAGH!"));
        getMonologue().addToAllMonologues(new DefaultMonologueCondition("I’LL CRUSH YOU ALL!"));
        getMonologue().addToAllMonologues(new LowHpMonologueCondition(hitPointsThreshold,
            "WEAK! TOO WEAK TO FIGHT ME!"));
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

    /**
     * Returns a list of allowable actions other actors can perform on this actor.
     * Adds ListenAction and AttackAction to the default actions.
     *
     * @param otherActor the other actor interacting with this actor
     * @param direction the direction of the other actor relative to this actor
     * @param map the game map
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new ListenAction(this));
        return actions;
    }
}
