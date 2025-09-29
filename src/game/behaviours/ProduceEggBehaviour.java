package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ProduceEggAction;
import game.items.eggs.Egg;

/**
 * A class that represents the NPC's behaviour of producing an Egg.
 *
 * @author Lee Hou Wen
 * @version 1.0.0
 * Modified by: Koh Kian Wai
 */
public class ProduceEggBehaviour implements Behaviour {

    private Egg egg;
    private int produceEggCounter;
    private int produceEggTurn;

    /**
     * Constructor.
     *
     * @param egg The Egg object to be produced when this behaviour is executed.
     * @param produceEggCounter The Egg counter the creature to produce egg
     * @param produceEggTurn The turn needed for the creature to produce egg
     */
    public ProduceEggBehaviour(Egg egg, int produceEggCounter, int produceEggTurn) {
        this.egg = egg;
        this.produceEggCounter = produceEggCounter;
        this.produceEggTurn = produceEggTurn;
    }

    /**
     * Returns a ProduceEggAction to allow the creature to produce an egg.
     *
     * @param actor the creature acting the behaviour.
     * @param map the map containing the creature.
     * @return if can produce egg, an ProduceEggAction object for producing the egg. Else, null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (produceEggCounter % produceEggTurn == 0)
            return new ProduceEggAction(egg);
        else{
            return null;
        }
    }
}
