package ooga.parser;

import java.time.Period;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class TemplateParser {
  private JSONObject template;

  public TemplateParser(String fileName) throws FileNotFoundException {
     this.template = new JSONObject(new JSONTokener(new FileReader(fileName)));
  }

  public void parseTemplate () {
    String title = template.getString("title");
    GridParser myGridParser = new GridParser(
        template.getJSONArray("grid"),
        template.getJSONObject("pieces")
    );

    try {
      myGridParser.parseGrid();
    } catch (InvalidGridException | InvalidPieceException e) {
      System.out.println(e.getMessage());
    }
  }

}
