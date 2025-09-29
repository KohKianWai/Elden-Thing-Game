package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BoCAttackAction;
import game.actors.bedofchaos.GrowthAttacker;

/**
 * A behaviour for GrowthAttacker NPCs (Bed of Chaos) to perform their
 * growth-based attack on any valid target in adjacent locations.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public class BoCAttackBehaviour implements Behaviour {

    /**
     * The GrowthAttacker NPC that owns this behaviour.
     */
    private final GrowthAttacker attacker;

    /**
     * Constructor.
     *
     * @param attacker The GrowthAttacker NPC using this behaviour.
     */
    public BoCAttackBehaviour(GrowthAttacker attacker) {
        this.attacker = attacker;
    }

    /**
     * Returns a BoCAttackAction if a target is adjacent, else returns null.
     * Chooses the first valid target found.
     *
     * @param actor the actor performing the action
     * @param map   the GameMap containing the actor
     * @return a BoCAttackAction against a valid target or null if no target
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Actor target = exit.getDestination().getActor();
            if (target != null && target != actor) {
                return new BoCAttackAction(attacker, target, exit.getName());
            }
        }
        return null;
    }
}
