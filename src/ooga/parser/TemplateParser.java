package ooga.parser;

import java.time.Period;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.models.GridModel;
import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class TemplateParser {
  private JSONObject template;
  private GridParser myGridParser;
  public TemplateParser() {
  }

  public void parseTemplate (String fileName)
      throws FileNotFoundException, InvalidGridException, InvalidPieceException {
    template = new JSONObject(new JSONTokener(new FileReader(fileName)));
    String title = template.getString("title");

    System.out.println(title);
    myGridParser = new GridParser(
        template.getJSONArray("grid"),
        template.getJSONObject("pieces")
    );
  }

  public GridParser getMyGridParser(){return myGridParser;}

}
