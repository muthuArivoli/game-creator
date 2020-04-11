package ooga.parser;

import ooga.exceptions.InvalidGridException;
import org.json.JSONArray;

public class GridParser {
  private JSONArray myGridJSON;
  public GridParser (JSONArray gridJSON) throws InvalidGridException {
    this.myGridJSON = gridJSON;

    parseGrid();
  }

  private void parseGrid () throws InvalidGridException {

  }


}
