package game.grounds;

/**
 * A class that represents a LettuceSprout.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class LettuceSprout extends Sprout {

    /**
     * A constructor of LettuceSprout class
     */
    public LettuceSprout(){
        super('l', "Lettuce Sprout", 6, new MatureLettuce());
    }
}
