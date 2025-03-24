/**
 * The {@code ActiveElement} class is a subclass of {@link Element}.
 * It represents an active element on the game map that can be interacted with by the player.
 * This class defines the behavior for active elements, such as movement and fixing.
 * The `ActiveElement` class overrides the abstract methods from the {@code Element} class
 * to provide specific functionality for active elements.
 */
public class ActiveElement extends Element {

    /**
     * Default constructor for creating an instance of {@code ActiveElement}.
     * Initializes the element with the given position and type.
     *
     * @param p the position of the active element
     * @param ElementType the type identifier of the element
     */
    public ActiveElement(Position p, int ElementType) {
        super(p, ElementType);
    }

    /**
     * Determines if the player can move on this active element.
     * Since this is an active element, the player is allowed to move on it.
     *
     * @return {@code true} indicating that the player can move on the active element
     */
    @Override
    public boolean canMove() {
        System.out.println("Player can move on active element");
        return true;
    }

    /**
     * Determines if the active element can be fixed.
     * In this case, the active element cannot be fixed, so it returns {@code false}.
     *
     * @return {@code false} indicating that the active element cannot be fixed
     */
    @Override
    public boolean canFix() {
        return false;
    }

    /**
     * Determines if the active element is broken.
     * Since the active element is always functional, it returns {@code false}.
     *
     * @return {@code false} indicating that the active element is not broken
     */
    @Override
    public boolean isBroken() {
        return false;
    }

    /**
     * Sets the broken status of the active element.
     * This method does nothing as the active element cannot be broken.
     *
     * @param isBroken the status to set (true for broken, false for not broken)
     */
    @Override
    public void setIsBroken(boolean isBroken) {
        // Active elements cannot be broken, so no action is performed.
    }
}
