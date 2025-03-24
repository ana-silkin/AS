import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Pipe} class represents a pipe element on the game map.
 * It extends from {@link Element} and provides specific functionality related to pipes, including handling broken state,
 * water presence, and connection to other pipes and pumps.
 * Players can interact with pipes, including determining if they can move on a pipe, fix it, or modify its state.
 */

public class Pipe extends Element {
    private boolean isBroken;
    private boolean hasWater;
    private int leakAmount;
    private boolean occupied;
    private List<Pipe> connectedPipes;
    private List<Pump> connectedPumps;

    /**
     * Default constructor initializes lists and position.
     *
     * @param position
     */
    public Pipe(Position position, boolean isBroken, boolean hasWater,
                Pipe connectedPipe1, Pipe connectedPipe2) {
        super(position,0);
        connectedPipes= new ArrayList<>();
        this.connectedPipes.add(connectedPipe1);
        this.connectedPipes.add(connectedPipe2);
        this.isBroken=isBroken;
        this.hasWater=hasWater;
        this.connectedPipes= new ArrayList<>();
        this.connectedPumps= new ArrayList<>();
    }

    /**
     * Checks if the pipe is broken.
     *
     * @return {@code true} if the pipe is broken, {@code false} otherwise
     */
    @Override
    public boolean isBroken() { System.out.println("Pipe.isBroken()");return isBroken; }

    @Override
    public void setIsBroken(boolean isBroken) {
        Main.Print("Pipe.setIsBroken");
        this.isBroken=isBroken;
    }
    /**
     * Gets the water status of the pipe.
     *
     * @return {@code true} if the pipe has water, {@code false} otherwise
     */
    public boolean hasWater() { return hasWater; }
    /**
     * Sets the water status of the pipe.
     *
     * @param hasWater the water status to set (true for has water, false for no water)
     */
    public void setWater(boolean hasWater) { this.hasWater = hasWater; }
    /**
     * Sets the leak amount of the pipe.
     *
     * @param amount the leak amount to set
     */
    public void setLeakAmount(int amount) { this.leakAmount = amount; }
    /**
     * Sets the occupation status of the pipe.
     * A pipe can be occupied by a player, preventing movement.
     *
     * @param occupied {@code true} if the pipe is occupied, {@code false} otherwise
     */
    public void setOccupied(boolean occupied) { Main.Print("Pipe.setOccupied"); this.occupied = occupied; }

    /***
     *  this function determines if player can move on this pipe
     *  the result is true if it is not occupied at this moment and is false if it is occupied
     * @return !occupied
     */
    @Override
    public boolean canMove() {
        System.out.println("Checking if the pipe is occupied... 0.No 1.Yes");
        boolean result=!Main.getAnswer();
//        boolean result= !occupied;
//        if(result)
//        {
//            System.out.println("can move");
//            this.setOccupied(!occupied);
//        } else{
//            System.out.println("can't move");
//        }
        if(result)
        {
            System.out.println("The player can move.");
            this.setOccupied(true);
        } else{
            System.out.println("The player cannot move");
        }

        return result;
    }

    /**
     * Determines if the pipe can be fixed.
     * Since pipes can be fixed in the game, this method always returns {@code true}.
     *
     * @return {@code true} indicating the pipe can be fixed
     */
    @Override
    public boolean canFix() {
        Main.Print("Pipe.canFix()");
        return true;
    }

    /**
     * Connects this pipe to a {@link Pump}.
     * This method adds the specified pump to the list of connected pumps.
     *
     * @param pump the pump to connect to this pipe
     */
    public void ConnectToPump(Pump pump){
        this.connectedPumps.add(pump);

    }
    /**
     * Disconnects this pipe from a {@link Pump}.
     * This method removes the specified pump from the list of connected pumps.
     *
     * @param pump the pump to disconnect from this pipe
     */
    public void DisconnectFromPump(Pump pump){
        this.connectedPumps.remove(pump);
    }
}