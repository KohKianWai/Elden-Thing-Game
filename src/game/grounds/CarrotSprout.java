package game.grounds;

/**
 * A class that represents a CarrotSprout.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class CarrotSprout extends Sprout{

    /**
     * A constructor of CarrotSprout class
     */
    public CarrotSprout() {
        super('c', "Carrot Sprout", 5, new MatureCarrot());
    }
}
