
import java.io.*;
import java.util.*;

/**
 * The abstract {@code Player} class represents a player in the game.
 * It holds information about the player's ID, their current position on the map,
 * and whether it's their turn. It provides methods for player movement and
 * changing the water flow direction through pumps.
 */
public abstract class Player {

    /**
     * Constructor for creating a new player with a specified ID and position.
     *
     * @param playerID the unique identifier for the player
     * @param p the initial position of the player on the map
     */
    public Player (int playerID,Position p) {
        this.playerID=playerID;
        this.playerTurn=false;
        this.playerPosition=p;
    }

    /**
     *
     */
    protected int playerID;

    /**
     *
     */
    protected boolean playerTurn;

    /**
     *
     */
    protected Position playerPosition;

    /**
     * Moves the player to a new position if the conditions allow.
     * The method checks if the target position is adjacent and if the element
     * at the target position is not occupied. If both conditions are met, the player
     * can move to the new position.
     *
     * @param p the new position the player wants to move to
     */
    protected void move(Position p) {
        Main.Print("Player.move(Position p)");
        p.getElement().getNeighbourhood();
        Main.Print("Is the element next to you? 0.No 1.Yes");
        boolean result = Main.getAnswer();
        if(result) {
            if (p.getElement().canMove()) {
                Main.Print("Player position changed.");
                this.playerPosition = p;
            } else {
                Main.Print("You cannot move. Element occupied.");
            }
        } else {
            Main.Print("You cannot move. You are not next to the element.");
        }
    }

    /**
     * Changes the water flow direction of a specified pump by setting new input and output pipes.
     * The method checks if the new pipes are connected to the pump, and if so, it updates the water
     * flow direction. Otherwise, the change is not applied.
     *
     * @param pump the pump whose water flow direction is to be changed
     * @param newInputPipe the new input pipe for the pump
     * @param newOutputPipe the new output pipe for the pump
     */
    protected void changeWaterFlowDirection( Pump pump,  Pipe newInputPipe,  Pipe newOutputPipe) {
        //Main.Print("changeWaterFlowDirection");
        Main.Print("Are the 'new' pipes connected to the pump? 0.No 1.Yes");
        boolean result=Main.getAnswer();
//        if(pump.checkConnection(newInputPipe) && pump.checkConnection(newOutputPipe)) {
//            pump.setInputPipe(newInputPipe);
//            pump.setOutputPipe(newOutputPipe);
//        }
        if(result) {
            pump.setInputPipe(newInputPipe);
            pump.setOutputPipe(newOutputPipe);
            Main.Print("changeWaterFlowDirection");
            Main.Print("The waterflow direction has been changed successfully.");
        } else {
            Main.Print("The waterflow direction has not been changed.");
        }
    }
}