
import java.io.*;
import java.util.*;

/**
 * Class representing a position on the map.
 */
public class Position {
    private Element element;


    /**
     *
     */
    private int row;

    /**
     *
     */
    private int column;


    /**
     * Constructor to create a position with row and column indices.
     *
     * @param x the row index
     * @param y the column index
     */
    public Position( int x,  int y) {
        Main.Print("Position.Position");
        this.row=x;
        this.column=y;
    }

    /**
     * @return the row index of the position
     */
    public int getRow() {
        Main.Print("Position.getRow");
        return row;
    }

    /**
     * @return the column index of the position
     */
    public int getColumn() {
        Main.Print("Position.getCol");
        return column;
    }
    /**
     * @return the element at this position
     */
    public Element getElement(){
        Main.Print("Element.getElement");
         return this.element;
    }
    /**
     * Sets the element at this position.
     *
     * @param element the element to place at this position
     */
    public void SetElement(Element element){
        Main.Print("Position.setElement");
        this.element=element;
    }

}