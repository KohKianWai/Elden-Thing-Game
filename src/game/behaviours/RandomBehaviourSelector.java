package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.SelectorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A selector that picks one behaviour at random each turn.
 * The chosen behaviour is executed if it returns a valid action.
 *
 * @author Harbir Singh
 * @version 1.0.0
 */
public class RandomBehaviourSelector implements BehaviourSelector {

    private final Random random = new Random();

    /**
     * Chooses a random behaviour from the map and returns its action.
     *
     * @param actor the actor whose turn it is
     * @param behaviours a map of priority to behaviour instances
     * @param map the current game map
     * @return the Action from the randomly selected behaviour, or null if none apply
     */
    @Override
    public Action chooseAction(Actor actor, Map<Integer, Behaviour> behaviours, GameMap map) {
        List<Behaviour> list = new ArrayList<>(behaviours.values());

        if (list.isEmpty()) {
            return null;
        }

        Behaviour chosen = list.get(random.nextInt(list.size()));
        return chosen.getAction(actor, map);
    }

    /**
     * Returns the type of this selector.
     *
     * @return SelectorType.RANDOM
     */
    @Override
    public SelectorType getSelectorType() {
        return SelectorType.RANDOM;
    }

}
