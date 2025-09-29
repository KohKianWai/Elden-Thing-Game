package game.grounds;


/**
 * A class that represents a MushroomSprout.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class MushroomSprout extends Sprout {

    /**
     * A constructor of MushroomSprout class
     */
    public MushroomSprout(){
        super('m', "Mushroom Sprout", 3, new MatureMushroom());
    }
}
