package ooga.piece.movement;

import java.lang.reflect.InvocationTargetException;
import ooga.piece.movement.Movement;

/**
 * Responsible for generating the corresponding class
 * within movement package
 */
public class MovementFactory {
  private static final String PACKAGE = "ooga.piece.movement.";

  /**
   * Takes in a String which corresponds to the movement
   * @param movementString the String representation of the movement
   * @return the Movement object constructed from the string
   */
  public Movement getMovement(String movementString, int rbegin, int rend) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class c = Class.forName(PACKAGE + movementString);
    Movement movement = (Movement) c.getDeclaredConstructor(int.class).newInstance(rbegin,rend);
    return movement;
  }

}
