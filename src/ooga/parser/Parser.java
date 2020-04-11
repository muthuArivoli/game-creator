package ooga.parser;

import java.time.Period;
import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class Parser {
  private JSONObject template;

  public Parser (String fileName) throws FileNotFoundException {
     this.template = new JSONObject(new JSONTokener(new FileReader(fileName)));
  }

  public void parseTemplate () {
    String title = template.getString("title");

    PieceParser myPieceParser = new PieceParser(template.getJSONArray("pieces"));
    GridParser myGridParser = new GridParser(template.getJSONArray("grid"));
  }


}
