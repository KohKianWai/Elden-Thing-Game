package game.grounds;

import game.items.Lettuce;

/**
 * A class that represents a MatureLettuce.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class MatureLettuce extends MaturePlant {

    /**
     * A constructor of MatureLettuce class
     */
    public MatureLettuce(){
        super('L', "Mature Lettuce", 25, new Lettuce());
    }
}
