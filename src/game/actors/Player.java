package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.FancyMessage;
import game.enums.Ability;
import game.weapons.BareFist;
import game.enums.Status;

/**
 * Class representing the Player.
 * @author Adrian Kristanto
 */
public class Player extends Actor {

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.MOVABLE);
        this.addCapability(Ability.FOLLOWABLE);
        this.setIntrinsicWeapon(new BareFist());
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
    }

    /**
     * A method to select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * A static method to display the death message when the player dies
     */
    public static void displayDeathMessage(){
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * A method to represent all the attributes of a player in String format
     * @return all attributes of a player as String
     */
    @Override
    public String toString(){
        return super.toString() + " Stamina: (" +
            this.getAttribute(BaseActorAttributes.STAMINA) + "/" +
            this.getAttributeMaximum(BaseActorAttributes.STAMINA) +
            ") " +
            "Runes: ($" +
            this.getBalance() + ")";
    }
}
