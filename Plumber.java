/**
 * Plumber class that extends player and has plumber functions
 */

public class Plumber extends Player {
    private Element inventory;
    /**
     * Constructor for creating a new plumber with a specific player ID.
     * The plumber starts at position (0,0).
     *
     * @param playerID the unique identifier for the player
     */
    public Plumber(int playerID) {
        super(playerID,new Position(0,0));
    }
    /**
     * Allows the plumber to pick up a pipe or pump if they are at a cistern.
     */
    public void pickingUpPipeOrPump(){
        Main.Print("pickingUpPipeOrPump()");
        Main.Print("Are you at the cistern? 0.No 1.Yes");
        boolean result= Main.getAnswer();
        if(result){
            Main.Print("Is there any pump or pipe at the cistern? 0.No 1.Yes");
            result = Main.getAnswer();
            if(result)
            {
                this.setInventory(new Pipe(new Position(0,0),false,false,null,null));
            } else {
                Main.Print("No element to pick up :(");
            }
        }
        else {
            Main.Print("Can't pick up here :(");
        }
    }
    /**
     * Retrieves the plumber's inventory.
     *
     * @return the element currently in the inventory
     */
    public Element getInventory() {
        Main.Print("getInventory()");
        return inventory;
    }

    /**
     * Places an element from the plumber's inventory onto the map.
     * Checks if the position is empty and allows placement.
     *
     * @param e the element to place on the map
     */
    public void placeElement(Element e) {
        Main.Print("placeElement(Element e)");
        this.getInventory();
        Main.Print("Do you have a pipe or a pump in the inventory? 0.No 1.Yes");
        boolean result = Main.getAnswer();
        if(result) {
            Main.Print("Can you place the element in that position (i.e. the position is empty)? 0.No 1.Yes");
            result = Main.getAnswer();
            if(result) {
                GameSystem.getInstance().placeElementOnMap(e,e.getPosition());
                this.clearInventory();
                Main.Print("The element was placed successfully");
            } else {
                Main.Print("You cannot place the element. The position is occupied.");
            }
        } else {
            Main.Print("Your inventory is empty.");
        }
    }
    /**
     * Fixes an element if it is a broken pump or pipe.
     * The plumber checks if the element is broken and attempts to fix it.
     *
     * @param e the element to fix
     */

    public void fixElement(Element e) {

        //Main.Print("checking if element is fixable i.e. if it is pump or pipe");
//        if(e.canFix()){
//            Main.Print("checking if the element is broken");
//            if(e.isBroken())
//            {
//                Main.Print("element is borken can be finxed");
//                e.setIsBroken(false);
//                Main.Print("Fixed element");
//            }
//            else {
//                Main.Print("element is not broken");
//            }
//        }
//        else{
//            Main.Print("non fixable element");
//        }
        Main.Print("Checking if the element is fixable, i.e. it is pump or pipe... 0.No 1.Yes");
        boolean result= Main.getAnswer();
        if(result){
            Main.Print("Checking if the element is broken... 0.No 1.Yes");
             result= Main.getAnswer();
            if(result)
            {
                Main.Print("The element is broken.");
                e.setIsBroken(false);
                Main.Print("fixElement(Element e) ...");
                Main.Print("Fixed.");
            }
            else {
                Main.Print("The element is not broken. Nothing to fix.");
            }
        }
        else{
            Main.Print("Non fixable element.");
        }
    }
    /**
     * Sets an element in the plumber's inventory.
     *
     * @param e the element to store in the inventory
     */
    public void setInventory(Element e) {
        Main.Print("setInventory(Element e)");
        this.inventory = e;
    }
    /**
     * Clears the plumber's inventory by setting it to null.
     */
    public void clearInventory() { Main.Print("clearInventory()"); this.inventory = null; }
    /**
     * Inserts a pipe into a pump if there is free space and if the plumber has a pipe in their inventory.
     *
     * @param pump the pump into which the pipe is to be inserted
     */
    public void insertPipeIntoPump(Pump pump){
        Main.Print("insertPipeIntoPump(Pump pump)");
        Main.Print("Is there any free space? 0.No 1.Yes");
        if(Main.getAnswer()) {
            Main.Print("Do you have pipe in the inventory? 0.No 1.Yes");
            if(Main.getAnswer()) {
                this.clearInventory();
                pump.addPipe((Pipe) this.getInventory());
                Main.Print("The pipe was successfully inserted.");
            } else {
                Main.Print("The pipe cannot be inserted.");
            }
        }
        else{
            Main.Print("The pipe cannot be inserted.");
        }

    }
}