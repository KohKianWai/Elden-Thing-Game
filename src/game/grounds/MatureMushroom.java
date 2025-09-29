package game.grounds;

import game.items.Mushroom;

/**
 * A class that represents a MatureMushroom.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class MatureMushroom extends MaturePlant {

    /**
     * A constructor of MatureMushroom class
     */
    public MatureMushroom(){
        super('M', "Mature Mushroom", 10, new Mushroom());
    }
}
