
import java.util.*;

/**
 * 
 */
public abstract class Goal {

    /**
     * Default constructor
     */
    public Goal() {
    }

    /**
     * In this genre, most ooga.goals are determined by the position of certain pieces
     * Checks the position of the given piece
     */
    public abstract void checkPiecePosition;

    /**
     * The logic to determine if the game is over
     */
    public void goalLogic;


}