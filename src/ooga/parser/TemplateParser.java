package ooga.parser;

import java.time.Period;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.models.GameModel;
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
  private GridModel myGridModel;
  private GameModel myGameModel;

  public TemplateParser(GridModel gridModel, GameModel myGameModel) {
    this.myGridModel = gridModel;
    this.myGameModel = myGameModel;
  }

  public void parseTemplate (String fileName)
      throws FileNotFoundException, InvalidGridException, InvalidPieceException {
    template = new JSONObject(new JSONTokener(new FileReader(fileName)));
    String title = template.getString("title");

    myGameModel.setCanPlace(template.getBoolean("canPlace"));
    myGameModel.setPieceJSON(template.getJSONObject("pieces"));

    myGridModel.initGrid(template.getInt("rows"), template.getInt("columns"));
    myGridParser = new GridParser(
        myGridModel,
        template.getJSONArray("grid"),
        template.getJSONObject("pieces")
    );
  }

  public String getGameName(){
    return template.getString("title");
  }

}
