import java.util.ArrayList;
import java.util.List;

/**
 * Pump class extends Active Element has unique functions and features.
 */
public class Pump extends ActiveElement {
    private Pipe inputPipe;
    private Pipe outputPipe;
    private boolean isTankFull;
    private boolean isBroken;

    private List<Pipe> connected;

    /**
     * Default constructor to initialize the pump with its position.
     *
     * @param p Position of the pump
     */
    public Pump(Position p) {
        super(p,1);
        isBroken=false;
        this.connected= new ArrayList<>();
    }
    /**
     * Checks for pipes connected to the pump by inspecting the four cardinal directions.
     */
    public void checkForPipes(){
        Main.Print("Pump.CheckForPipes");
        GameSystem gameSystem=GameSystem.getInstance();
        Element check1=gameSystem.FindElementByPosition(getPosition().getRow()-1, getPosition().getColumn());
        Element check2=gameSystem.FindElementByPosition(getPosition().getRow()+1, getPosition().getColumn());
        Element check3=gameSystem.FindElementByPosition(getPosition().getRow(),getPosition().getColumn()+1);
        Element check4=gameSystem.FindElementByPosition( getPosition().getRow(),getPosition().getColumn()-1);
        //if it is pipe
        if(check1!=null && check1.getElementType()==0){
            this.connected.add((Pipe)check1);
            connected.getLast().ConnectToPump(this);
        }
        if(check2!=null && check2.getElementType()==0){
            this.connected.add((Pipe)check2);
            connected.getLast().ConnectToPump(this);
        }
        if(check3!=null && check3.getElementType()==0){
            this.connected.add((Pipe)check3);
            connected.getLast().ConnectToPump(this);
        }
        if(check4!=null && check4.getElementType()==0){
            this.connected.add((Pipe)check4);
            connected.getLast().ConnectToPump(this);
        }

    }

    /**
     * Checks if a given pipe is connected to the pump.
     *
     * @param p The pipe to check
     * @return true if the pipe is connected to the pump, false otherwise
     */
    public boolean checkConnection(Pipe p)
    {
        Main.Print("checkConnection");
        return this.connected.contains(p);
    }
    @Override
    public boolean isBroken() { Main.Print("Pump.isBroken()"); return false; }
    public boolean getTankFull() { return isTankFull; }
    /**
     * Sets the input pipe for the pump.
     *
     * @param newInput The new input pipe
     */
    public void setInputPipe(Pipe newInput) { Main.Print("SetInputPipe");  this.inputPipe = newInput; }
    /**
     * Sets the output pipe for the pump.
     *
     * @param newOutput The new out pipe
     */
    public void setOutputPipe(Pipe newOutput) { Main.Print("SetOutputPipe"); this.outputPipe = newOutput; }

    /**
     * Adds a pipe to the list of connected pipes.
     *
     * @param p The pipe to be added
     */
    public void addPipe(Pipe p){
            this.connected.add(p);
            Main.Print("addPipe(Pipe p)");
    }

    @Override
    public boolean canFix() {
        Main.Print("Pump.canFix()");
        return true;
    }

    @Override
    public void setIsBroken(boolean isBroken) {
        Main.Print("Pump.setIsBroken(boolean isBroken)");
        this.isBroken=isBroken;
    }
}