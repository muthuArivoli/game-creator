package ooga.goals;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Responsible for generating the corresponding class
 * which represents the "goal" which will end the game
 */
public class GoalFactory {
  private static final String PACKAGE = "ooga.goals.";

  /**
   * Takes in a String which corresponds to the goal
   * @param goalString the String representation of the ending game goal
   * @return the Movement object constructed from the string
   */
  public Goal getGoal(String goalString, int target) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class c = Class.forName(PACKAGE + goalString);
    Goal goal = (Goal) c.getDeclaredConstructor(int.class).newInstance(target);
    return goal;
  }

  public Goal getMultiGoal(String goalString, List<String> targets)
      throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class c = Class.forName(PACKAGE + goalString);
    Goal goal = (Goal) c.getDeclaredConstructor(List.class).newInstance(targets);
    return goal;
  }
}
