package ooga.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.util.Pair;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.models.GridModel;
import ooga.parser.TemplateParser;
import ooga.piece.Coordinate;
import ooga.piece.Piece;
import ooga.piece.movement.Movement;

public class GameController {
  private TemplateParser myTemplateParser;
  private GridModel myGridModel;
  private int playerTurn = 1;
  private Piece selectedPiece;

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

  public List<Coordinate> pieceSelected (Piece piece, int x, int y) {
    this.selectedPiece = piece;

    Set <Coordinate> allPossibleMoves = new HashSet<>();
    for (Movement movement: piece.getNormalAnyMovements()) {
      allPossibleMoves.addAll(movement.validMoves(new Coordinate(x, y)));
    }
    Set <Coordinate> allValidMoves = validateMoves(removeMovesOutOfBounds(allPossibleMoves));

    return new ArrayList (allValidMoves);
  }

  private Set<Coordinate> validateMoves (Set<Coordinate> indicesToCheck) {
    for (Coordinate c: indicesToCheck) {
      if (!selectedPiece.isCanJump()) {

      }
    }
  }

  private Set<Coordinate> removeMovesOutOfBounds (Set <Coordinate> allPossibleIndices) {
    Set <Coordinate> ret = new HashSet<>();
    Coordinate bounds = myGridModel.getDimensions();
    for (Coordinate c: allPossibleIndices) {
      if(c.getXpos() >= 0 && c.getXpos() < bounds.getXpos()
          && c.getYpos() >= 0 && c.getYpos() < bounds.getYpos()){
        ret.add(c);
      }
    }
    return ret;
  }

  public void moveSelectedPiece (int x, int y) {

  }

  public GridModel getGridModel() {
    return myGridModel;
  }

  public String getGameName() {return myTemplateParser.getGameName();}
}
