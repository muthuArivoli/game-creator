package ooga.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.models.GridModel;
import ooga.parser.TemplateParser;
import ooga.piece.Piece;

public class GameController {
  TemplateParser myTemplateParser;
  GridModel myGridModel;

  public GameController () {
    this.myGridModel = new GridModel();
    this.myTemplateParser = new TemplateParser(myGridModel);
  }

  public void parseFile (String fileName) {
    try {
      myTemplateParser.parseTemplate(fileName);
    } catch (FileNotFoundException | InvalidGridException | InvalidPieceException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Pair<Integer, Integer>> pieceSelected (Piece piece, int x, int y) {
    return new ArrayList<Pair<Integer, Integer>>;
  }

  public void moveSelectedPiece (int x, int y) {

  }

  public GridModel getGridModel() {
    return myGridModel;
  }

  public String getGameName() {return myTemplateParser.getGameName();}
}
