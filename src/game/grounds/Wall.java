package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class representing a wall that cannot be entered by any actor
 * @author Riordan D. Alfredo
 */
public class Wall extends Ground {

    /**
     * A constructor for Wall clas
     */
    public Wall() {
        super('#', "Wall");
    }

    /**
     * A method to check whether an actor can enter the ground
     * @param actor the Actor to check
     * @return true if an actor can enter; otherwise false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
