package game.actors.people.monologues.conditions;


/**
 * BaseMonologueCondition is an abstract base class that implements the
 * MonologueCondition interface and provides shared functionality
 * such as storing and returning monologue text.
 * Concrete subclasses must implement the logic for when the monologue condition is satisfied.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public abstract class BaseMonologueCondition implements MonologueCondition {

    private String monologueText;

    /**
     * A constructor for BaseMonologueCondition class.
     *
     * @param monologueText the monologue text associated with this condition
     */
    public BaseMonologueCondition(String monologueText){
        this.monologueText = monologueText;
    }


    /**
     * A method that returns the monologue text associated with this condition.
     *
     * @return the monologue text as a String
     */
    @Override
    public String getMonologueText(){
        return this.monologueText;
    }
}
