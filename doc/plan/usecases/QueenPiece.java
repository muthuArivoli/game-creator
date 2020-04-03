import java.util.*;

/**
 *Specifies the procedural implementation of creating a Queen Piece
 */
public class QueenPiece {

  /**
   * Default constructor
   */
  public QueenPiece() {
    Game = new GameController;
    Parser = new ParserController;
    Parser.loadGameFile("GameFile");
    Parser.createPieces();
  }

}