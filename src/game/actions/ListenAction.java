package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.people.monologues.CanSpeakMonologue;


/**
 * An action that allows an actor to listen to another actor who can speak monologues.
 * When executed, it returns a line of dialogue from the speaker directed at the listener.
 *
 * @author Harbir Singh
 * @version 1.0.0
 * Modified by: Koh Kian Wai
 */
public class ListenAction extends Action {

    private CanSpeakMonologue speaker;

    /**
     * Constructor.
     *
     * @param speaker the actor who will speak when this action is executed
     */
    public ListenAction(CanSpeakMonologue speaker){
        this.speaker = speaker;
    }


    /**
     * Executes the listen action where the speaker produces a monologue directed at the given actor.
     *
     * @param actor the actor performing the listen action (the listener)
     * @param map the current game map
     * @return a string describing what the speaker says to the listener
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String words = speaker.speakTo(actor, map);
        return speaker + " says: \"" + words + "\"";
    }


    /**
     * Describes this action in the menu, showing which actor listens to whom.
     *
     * @param actor the actor performing the listen action
     * @return a string describing the action for display in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + speaker;
    }
}
