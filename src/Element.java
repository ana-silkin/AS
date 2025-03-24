import java.util.*;

/**
 * Abstract class representing a map element.
 * This class serves as the base for various types of map elements in the game.
 * It provides common functionality for all elements, such as managing positions, connections, and neighbourhoods.
 * Specific element types (e.g., Pipe, Pump) should extend this class and implement its abstract methods.
 */
public abstract class Element {

    // Fields
    protected int elementType;
    protected List<Element> connectedElements;
    protected List<Element> neighbourhood;
    protected Position elementPosition;
    List<Player> onElement;

    /**
     * Default constructor initializes the element's position, element type, and lists for connected elements and neighbourhood.
     *
     * @param position the position of the element on the map
     * @param elementType the type identifier of the element
     */
    public Element(Position position, int elementType) {
        this.elementPosition = position;
        this.connectedElements = new ArrayList<>();
        this.neighbourhood = new ArrayList<>();
        this.elementType = elementType;
    }

    /**
     * Gets the list of elements connected to this element.
     *
     * @return List of connected elements
     */
    public List<Element> getConnectedElements() {
        return connectedElements;
    }

    /**
     * Gets the type identifier of the element.
     *
     * @return the element type identifier
     */
    public int getElementType() {
        return elementType;
    }

    /**
     * Gets the neighbourhood elements for this element.
     * This method may be deprecated or not needed as per the UML sequence diagram.
     *
     * @return List of neighbourhood elements
     */
    public List<Element> getNeighbourhood() {
        Main.Print("getNeighbourhood");
        return neighbourhood;
    }

    /**
     * Gets the position of the element on the map.
     *
     * @return the position of the element
     */
    public Position getPosition() {
        return elementPosition;
    }

    /**
     * Abstract method to determine if the element can move.
     * Subclasses should implement the logic for whether this element is movable.
     *
     * @return {@code true} if the element can move, {@code false} otherwise
     */
    public abstract boolean canMove();

    /**
     * Abstract method to determine if the element can be fixed.
     * Subclasses should implement the logic for whether this element can be repaired or fixed.
     *
     * @return {@code true} if the element can be fixed, {@code false} otherwise
     */
    public abstract boolean canFix();

    /**
     * Abstract method to check if the element is broken.
     * Subclasses should implement the logic for determining whether this element is broken or not.
     *
     * @return {@code true} if the element is broken, {@code false} otherwise
     */
    public abstract boolean isBroken();

    /**
     * Abstract method to set the broken status of the element.
     * Subclasses should implement the logic for setting the broken status of the element.
     *
     * @param isBroken the status to set (true for broken, false for not broken)
     */
    public abstract void setIsBroken(boolean isBroken);

    /**
     * Sets the position of the element on the map.
     *
     * @param p the new position to set for the element
     */
    public void setPosition(Position p) {
        Main.Print("setPosition");
        this.elementPosition = p;
    }
}
