package game.actors.bedofchaos;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.BoCAttackBehaviour;
import game.behaviours.GrowBehaviour;

import java.util.*;

/**
 * BedOfChaos is a powerful boss that remains stationary and grows new branches and leaves when not in combat.
 * It has 25 base damage and starts with 1000 hit points.
 * The boss cannot wander and will only attack or grow each turn depending on whether any actor is nearby.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public class BedOfChaos extends Actor implements GrowthAttacker, Growable {

    /**
     * The list of growable parts (specifically branches) that this boss has grown.
     * Used to propagate growth logic each turn.
     */
    private List<Growable> growablePart = new ArrayList<>();

    /**
     * The list of all parts (branches and leaves) that this boss has grown.
     * Used to calculate total damage and apply healing.
     */
    private final List<TreeStructure> parts = new ArrayList<>();

    /**
     * The map of behaviours that control what actions this boss takes.
     * Priority is based on the key (lower numbers have higher priority).
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor for BedOfChaos.
     * Initializes the Bed of Chaos with name "Bed of Chaos", display character 'T', and 1000 HP.
     * Default behaviours are put(attack and grow).
     */
    public BedOfChaos() {
        super("Bed of Chaos", 'T', 1000);
        behaviours.put(0, new BoCAttackBehaviour(this));
        behaviours.put(1, new GrowBehaviour(this));
    }

    /**
     * Performs the growth logic for this turn.
     * Randomly grows either a branch or a leaf, and applies growth recursively.
     * Also applies healing from all leaves grown.
     *
     * @return a string describing the growth results
     */
    @Override
    public String growThisTurn() {
        String growthMessage = "";
        Random random = new Random();
        if (random.nextBoolean()) {
            Branch newBranch = new Branch();
            growablePart.add(newBranch);
            parts.add(newBranch);
            growthMessage += getPartName() + " grows a new branch\n";
        } else {
            parts.add(new Leaf());
            growthMessage += getPartName() + " grows a new leaf\n";
        }

        for (Growable part : growablePart) {
            growthMessage += part.growThisTurn();
        }

        applyHealingFromLeaves();
        growthMessage += "Bed of Chaos is healed by its leaves ";

        return growthMessage;
    }

    /**
     * Performs a growth-based attack against a target in the specified direction.
     * Calculates total damage (base damage plus accumulated damage from grown parts).
     *
     * @param target the actor to attack
     * @param direction the direction of the target
     * @param map the game map
     * @return a string describing the result of the attack
     */
    @Override
    public String growthAttack(Actor target, String direction, GameMap map) {
        int totalDamage = getBaseDamage() + getAccumulatedDamage();
        double hitChance = Math.random();

        String attackMessage = this + " attacks " + target + " at " + direction + ".";
        if (hitChance < 0.75) {
            target.hurt(totalDamage);
            String damageMessage = this + " hits " + target + " for " + totalDamage + " damage!";
            String unconsciousMessage = !target.isConscious() ? "\n" + target.unconscious(this, map) : "";
            return attackMessage + " " + damageMessage + unconsciousMessage;
        } else {
            return attackMessage + " It misses the target!";
        }
    }

    /**
     * Returns the base damage by the Bed of Chaos (25 damage).
     *
     * @return the base damage
     */
    public int getBaseDamage() {
        return 25;
    }

    /**
     * Calculates the total accumulated damage from all grown parts.
     *
     * @return the total damage contributed by branches and leaves
     */
    public int getAccumulatedDamage() {
        int total = 0;
        for (TreeStructure part : parts) {
            total += part.getAccumulatedDamage();
        }
        return total;
    }

    /**
     * Applies healing effects from all leaves grown by the Bed of Chaos.
     */
    public void applyHealingFromLeaves() {
        for (TreeStructure part : parts) {
            part.applyHealingFromLeaves(this);
        }
    }

    /**
     * Returns the name of this part.
     *
     * @return "Bed of Chaos"
     */
    @Override
    public String getPartName() {
        return "Bed of Chaos";
    }

    /**
     * Returns the list of all parts (branches and leaves) grown by the Bed of Chaos.
     *
     * @return the list of parts
     */
    @Override
    public List<TreeStructure> getParts(){
        return parts;
    }

    /**
     * Chooses an action for the Bed of Chaos each turn.
     * The first valid behaviour in the behaviour map is executed.
     *
     * @param actions possible actions
     * @param lastAction the last action performed
     * @param map the game map
     * @param display the display
     * @return an action to perform this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }

        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that another Actor can do to the Bed of Chaos.
     *
     * @param otherActor the Actor that might be performing an action on the Bed of Chaos
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an empty collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(this, direction));

        return actions;
    }
}
