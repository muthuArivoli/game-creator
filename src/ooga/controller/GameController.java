package ooga.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    List <Coordinate> allPossibleMoves = new ArrayList<>();
    for (Movement movement: piece.getNormalAnyMovements()) {
      allPossibleMoves.addAll(movement.validMoves(new Coordinate(x, y)));
    }
    List <Coordinate> allValidMoves = validateMoves(allPossibleMoves);

    return allPossibleMoves;
  }

  private List<Coordinate> validateMoves (List<Coordinate> allPossibleMoves) {
    List<Coordinate> allValidMoves = new ArrayList<>();
    for (Coordinate c: allPossibleMoves) {
//      if (!selectedPiece.isCanJump() && myGridModel.getPiece(c).)
      checkOpposingPieceExists();
    }
  }

  public void moveSelectedPiece (int x, int y) {

  }

  public GridModel getGridModel() {
    return myGridModel;
  }

  public String getGameName() {return myTemplateParser.getGameName();}
}
