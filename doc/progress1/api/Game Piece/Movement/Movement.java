
import java.util.*;

/**
 * 
 */
public class Movement {

    /**
     * Default constructor
     */
    public Movement() {
    }

    /**
     * The logic specific to the movement type
     */
    public abstract void movementLogic;

    /**
     * 
     */
    public abstract void isMoveable;

    /**
     * sets the number of steps associated with the movement
     */
    public abstract void setUnits;



}