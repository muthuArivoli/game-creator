import java.util.*;

/**
 *Specifies the procedural implementation of choosing a new Goal for the game
 */
public class QueenPiece {

  /**
   * Default constructor
   */
  public QueenPiece() {
    Game = new GameController;
    Parser = new ParserController;
    Parser.loadGameFile("GameFile");
    Game.addGoal(GoalName);
  }

}