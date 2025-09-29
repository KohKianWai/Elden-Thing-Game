package game.actors.people.monologues;

import game.actors.people.monologues.conditions.MonologueCondition;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Monologue class manages and selects valid monologue lines for NPCs
 * based on a list of conditional rules.
 * Each monologue is tied to a {@link MonologueCondition}, and only those whose
 * conditions are met in the current {@link MonologueContext} will be considered.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class Monologue {
    private final List<MonologueCondition> allMonologues; // all monologues of an NPC
    private final Random random;

    /**
     * A constructor for the Monologue class.
     * Initializes an empty list of monologue conditions and a random number generator.
     */
    public Monologue(){
        this.allMonologues = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Adds a new monologue condition to the list of all monologues.
     *
     * @param monologueCondition the condition and associated text to be added
     */
    public void addToAllMonologues(MonologueCondition monologueCondition) {
        allMonologues.add(monologueCondition);
    }


    /**
     * Returns a random monologue text from all monologues whose conditions
     * are satisfied in the given monologue context.
     *
     * @param monologueContext the context used to evaluate monologue conditions
     * @return a randomly selected monologue text from valid monologues
     */
    public String getRandom(MonologueContext monologueContext) {
        ArrayList<String> possibleMonologues = new ArrayList<>();
        for(MonologueCondition monologueCondition: allMonologues){
            if(monologueCondition.isConditionSatisfied(monologueContext)){
                possibleMonologues.add(monologueCondition.getMonologueText());
            }
        }

        return possibleMonologues.get(random.nextInt(possibleMonologues.size()));
    }
}
