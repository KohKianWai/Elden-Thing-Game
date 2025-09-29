package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * Creates and registers the Limveld game map.
 *
 * @author Harbir Singh
 * @version 1.0.0
 */
public class LimveldMapInitializer {

    /**
     * Builds the Limveld map from a list of strings and adds it to the world.
     *
     * @param world the World to register the new map with
     * @param groundFactory the factory used to create ground types on the map
     * @return the created Limveld GameMap
     */
    public static GameMap initialize(World world, FancyGroundFactory groundFactory){
        List<String> limveldMap = Arrays.asList(
                ".............xxxx",
                "..............xxx",
                "................x",
                ".................",
                "................x",
                "...............xx",
                "..............xxx",
                "..............xxx",
                "..............xxx",
                ".............xxxx",
                ".............xxxx",
                "....xxx.....xxxxx",
                "....xxxx...xxxxxx"
        );

        GameMap limveld = new GameMap("Limveld", groundFactory, limveldMap);
        world.addGameMap(limveld);

        return limveld;

    }
}
