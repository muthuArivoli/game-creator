package ooga.controller;

import java.io.FileNotFoundException;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.models.GridModel;
import ooga.parser.TemplateParser;

public class GameController {
  TemplateParser myTemplateParser;
  GridModel myGridModel;

  public GameController () {
    this.myTemplateParser = new TemplateParser();
  }

  public void parseFile (String fileName) {
    try {
      myTemplateParser.parseTemplate(fileName);
    } catch (FileNotFoundException | InvalidGridException | InvalidPieceException e) {
      System.out.println(e.getMessage());
    }

    myGridModel = myTemplateParser.getMyGridParser().getMyGridModel();
  }

  public GridModel getMyGridModel() {
    return myGridModel;
  }
}
