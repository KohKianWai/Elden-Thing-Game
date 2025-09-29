package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.GrowAction;
import game.actors.bedofchaos.Growable;

/**
 * A behaviour for Growable parts (Bed of Chaos or Branch) to grow each turn.
 * This behaviour triggers their growing logic.
 *
 * @author Wai Chung Teng
 * @version 1.0.0
 */
public class GrowBehaviour implements Behaviour {

    /**
     * The growable actor (Bed of Chaos or Branch) that will perform the growth.
     */
    private final Growable growable;

    /**
     * Constructor for GrowBehaviour.
     *
     * @param growable the Growable actor (Bed of Chaos or Branch) using this behaviour.
     */
    public GrowBehaviour(Growable growable) {
        this.growable = growable;
    }

    /**
     * Returns a GrowAction for the Growable actor.
     *
     * @param actor the actor performing the action
     * @param map   the map containing the actor
     * @return a GrowAction for the actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new GrowAction(growable);
    }
}
