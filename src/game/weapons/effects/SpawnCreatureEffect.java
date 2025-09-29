package game.weapons.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.Creature;

/**
 * A class that handles purchase effects related to spawning creature.
 *
 * @author Koh Kian Wai
 * @version 1.0.0
 */
public class SpawnCreatureEffect implements PurchaseEffect{

    private Creature spawnCreature;
    private Location sellerLocation;

    /**
     * Constructor of SpawnCreatureEffect. Handle situation when creature spawn around buyer
     *
     * @param spawnCreature the creature to spawn
     */
    public SpawnCreatureEffect(Creature spawnCreature){
        this.spawnCreature = spawnCreature;
        this.sellerLocation = null;
    }

    /**
     * Constructor of SpawnCreatureEffect. Handle situation when creature spawn around seller
     *
     * @param spawnCreature the creature to spawn
     * @param sellerLocation the location of the seller
     */
    public SpawnCreatureEffect(Creature spawnCreature, Location sellerLocation){
        this.spawnCreature = spawnCreature;
        this.sellerLocation = sellerLocation;
    }

    /**
     * Apply the purchase effect of SpawnCreatureEffect
     * @param buyer the actor who buys the weapon
     * @param map the game map that the buyer is currently at
     */
    @Override
    public void applyEffect(Actor buyer, GameMap map) {
        Location actorLocation;
        // if sellerLocation is not null, spawn creature around seller, else spawn around buyer
        if(sellerLocation != null){
            actorLocation = sellerLocation;
        } else {
            actorLocation = map.locationOf(buyer);
        }
        // spawn at the surrounding first empty place
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(spawnCreature)) {
                map.addActor(spawnCreature, destination);
                break;
            }
        }
    }
}

