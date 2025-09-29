package game.actors.people.monologues;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * MonologueContext holds the context information necessary to evaluate monologue conditions,
 * including the speaker, the listener, and the game map.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class MonologueContext {

    private Actor speaker;
    private Actor listener;
    private GameMap gameMap;

    /**
     * Constructs a MonologueContext with the specified speaker, listener, and game map.
     *
     * @param speaker the actor who is speaking
     * @param listener the actor who is listening
     * @param gameMap the current game map where the interaction occurs
     */
    public MonologueContext(Actor speaker, Actor listener, GameMap gameMap){
        this.speaker = speaker;
        this.listener = listener;
        this.gameMap = gameMap;
    }

    /**
     * Returns the actor that is speaking.
     *
     * @return the speaker actor
     */
    public Actor getSpeaker() {
        return speaker;
    }

    /**
     * Returns the actor who is listening.
     *
     * @return the listener actor
     */
    public Actor getListener() {
        return listener;
    }

    /**
     * Returns the game map where the interaction is happening.
     *
     * @return the current game map
     */
    public GameMap getGameMap() {
        return gameMap;
    }

}