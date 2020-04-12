package ooga.piece.movement;

import java.lang.reflect.InvocationTargetException;
import ooga.piece.movement.Movement;

/**
 * Responsible for generating the corresponding class
 * within movement package
 */
public class MovementFactory {
  private static final String PACKAGE = "piece.movement.";

  /**
   * Takes in a String which corresponds to the movement
   * @param command the String representation of the command from Resources file
   * @return the Movement object constructed from the string
   */
  public Movement getMovement(String movementString) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class c = Class.forName(PACKAGE + movementString);
    Movement movement = (Movement) c.getConstructor().newInstance();
    return movement;
  }

}
