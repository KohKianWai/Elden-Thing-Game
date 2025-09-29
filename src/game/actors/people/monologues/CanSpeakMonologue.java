package game.actors.people.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for actors who can speak monologues.
 * Provides a method to speak to another actor in a given game map context.
 * Implementing classes should provide their own logic for producing a monologue
 * line directed at the specified actor.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public interface CanSpeakMonologue {
    /**
     * Produces a monologue line directed at the given actor within the specified game map.
     *
     * @param actor the actor to speak to
     * @param map the game map where the interaction occurs
     * @return a string containing the spoken monologue line
     */
    String speakTo(Actor actor, GameMap map);
}
