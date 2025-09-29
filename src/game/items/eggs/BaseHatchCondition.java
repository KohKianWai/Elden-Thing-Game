package game.items.eggs;

import game.behaviours.PriorityBehaviourSelector;
import game.behaviours.RandomBehaviourSelector;
import game.actors.creatures.Creature;
import game.actors.creatures.GoldenBeetle;
import game.actors.creatures.OmenSheep;
import game.actors.creatures.SpiritGoat;
import game.enums.CreatureType;

/**
 * BaseHatchCondition class is a base class that represents a generic hatch condition.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public abstract class BaseHatchCondition implements HatchCondition{

    private CreatureType hatchCreature;

    /**
     * Constructor.
     *
     * @param hatchCreature the type of creature that will be created upon hatching.
     */
    public BaseHatchCondition(CreatureType hatchCreature){
        this.hatchCreature = hatchCreature;
    }

    /**
     * Creates and returns a new creature object base on the type of the creature given.
     *
     * @return a new appropriate creature object, or null if the type is unsupported.
     */
    public Creature createCreature(){
        switch (hatchCreature) {
            case SPIRITGOAT:
                return new SpiritGoat(new PriorityBehaviourSelector());
            case RANDOMSPIRITGOAT:
                return new SpiritGoat(new RandomBehaviourSelector());

            case OMENSHEEP:
                return new OmenSheep(new PriorityBehaviourSelector());
            case RANDOMOMENSHEEP:
                return new OmenSheep(new RandomBehaviourSelector());

            case GOLDENBEETLE:
                return new GoldenBeetle(new PriorityBehaviourSelector());
            case RANDOMGOLDENBEETLE:
                return new GoldenBeetle(new RandomBehaviourSelector());

            default:
                return null;
        }
    }
}
