package game.grounds;

import game.items.Carrot;

/**
 * A class that represents a MatureCarrot.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class MatureCarrot extends MaturePlant {

    /**
     * A constructor of MatureCarrot class
     */
    public MatureCarrot(){
        super('C', "Mature Carrot", 20, new Carrot());
    }
}
