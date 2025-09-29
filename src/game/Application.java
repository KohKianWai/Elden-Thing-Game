package game;


import game.actors.bedofchaos.BedOfChaos;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.creatures.GoldenBeetle;
import game.actors.creatures.OmenSheep;
import game.actors.Player;
import game.actors.creatures.SpiritGoat;
import game.actors.people.Guts;
import game.actors.people.MerchantKale;
import game.actors.people.Sellen;
import game.behaviours.BehaviourSelector;
import game.behaviours.PriorityBehaviourSelector;
import game.behaviours.RandomBehaviourSelector;
import game.grounds.Floor;
import game.grounds.Soil;
import game.grounds.TeleportCircle;
import game.grounds.Wall;
import game.grounds.Blight;
import game.items.WateringCan;
import game.items.givables.Flower;
import game.items.givables.GoldenNecklace;
import game.items.givables.RottenFlesh;
import game.items.seeds.BloodroseSeed;
import game.items.seeds.CarrotSeed;
import game.items.seeds.InheritreeSeed;
import game.items.Talisman;
import game.items.seeds.LettuceSeed;
import game.items.seeds.MushroomSeed;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The main class to setup and run the game.
 * @author Adrian Kristanto
 */
public class Application {

    /**
     * This is the main method to start the whole game
     * @param args main arguments
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Blight(),
                new Wall(), new Floor(), new Soil());

        List<String> map = Arrays.asList(
                "xxxx...xxxxxxxxxxxxxxxxxxxxxxx........xx",
                "xxx.....xxxxxxx..xxxxxxxxxxxxx.........x",
                "..........xxxx....xxxxxxxxxxxxxx.......x",
                "....xxx...........xxxxxxxxxxxxxxx.....xx",
                "...xxxxx...........xxxxxxxxxxxxxx.....xx",
                "...xxxxxxxxxx.......xxxxxxxx...xx......x",
                "....xxxxxxxxxx........xxxxxx...xxx......",
                "....xxxxxxxxxxx.........xxx....xxxx.....",
                "....xxxxxxxxxxx................xxxx.....",
                "...xxxx...xxxxxx.....#####.....xxx......",
                "...xxx....xxxxxxx....#___#.....xx.......",
                "..xxxx...xxxxxxxxx...#___#....xx........",
                "xxxxx...xxxxxxxxxx...##_##...xxx.......x",
                "xxxxx..xxxxxxxxxxx.........xxxxx......xx",
                "xxxxx..xxxxxxxxxxxx.......xxxxxx......xx");

        GameMap gameMap = new GameMap("Valley of the Inheritree", groundFactory, map);
        world.addGameMap(gameMap);

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Farmer", '@', 100, 200);

        player.addItemToInventory(new InheritreeSeed());
        player.addItemToInventory(new BloodroseSeed());
        player.addItemToInventory(new CarrotSeed());
        player.addItemToInventory(new MushroomSeed());
        player.addItemToInventory(new LettuceSeed());
        player.addItemToInventory(new GoldenNecklace());
        player.addItemToInventory(new RottenFlesh());
        player.addItemToInventory(new Flower());
        world.addPlayer(player, gameMap.at(23, 10));

        BehaviourSelector priority = new PriorityBehaviourSelector();
        BehaviourSelector random = new RandomBehaviourSelector();

        // game setup
        gameMap.at(24, 11).addItem(new Talisman());

        gameMap.at(26, 11).addActor(new SpiritGoat(priority));
        gameMap.at(19, 12).addActor(new OmenSheep(priority));
        gameMap.at(22, 13).addActor(new GoldenBeetle(priority));

        gameMap.at(26, 13).addActor(new Sellen());
        gameMap.at(21,13).addActor(new MerchantKale());

        gameMap.at(20,12).addActor(new Guts());
        gameMap.at(22, 10).addItem(new WateringCan());



        //Build Limveld
        GameMap limveld = LimveldMapInitializer.initialize(world, groundFactory);

        int gameMapGateX = 24;
        int gameMapGateY = 10;

        int limveldGateX = 2;
        int limveldGateY = 3;

        Location gameMapGateLocation = gameMap.at(gameMapGateX, gameMapGateY);
        Location limveldGateLocation = limveld.at(limveldGateX, limveldGateY);


        TeleportCircle circleA = new TeleportCircle();
        TeleportCircle circleB = new TeleportCircle();

        circleA.addDestination(limveldGateLocation);    // A will take to Limveld(2, 3)
        circleB.addDestination(gameMapGateLocation);    // B will take to GameMap(24, 10)

        gameMapGateLocation.setGround(circleA);         // A placed at GameMap(24, 10)
        limveldGateLocation.setGround(circleB);         // B placed at Limveld(2, 3)

        limveld.at(2, 3).addActor(new GoldenBeetle(random));
        limveld.at(7, 8).addActor(new OmenSheep(random));
        limveld.at(4, 2).addActor(new SpiritGoat(random));
        limveld.at(8,6).addActor(new BedOfChaos());

        world.run();
        Player.displayDeathMessage();
    }
}
