import java.util.*;

/**
 *Specifies the procedural implementation of loading a new Game
 */
public class NewGame {

  /**
   * Default constructor
   */
  public NewGame() {
    Game = new GameController;
    Parser = new ParserController;
    Parser.loadGameFile("GameFile");
  }

}