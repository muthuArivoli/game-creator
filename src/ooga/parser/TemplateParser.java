package ooga.parser;

import java.lang.reflect.InvocationTargetException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import ooga.exceptions.InvalidGridException;
import ooga.exceptions.InvalidPieceException;
import ooga.goals.Goal;
import ooga.goals.GoalFactory;
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
  private GoalFactory factory;

  public TemplateParser(GridModel gridModel, GameModel myGameModel) {
    this.myGridModel = gridModel;
    this.myGameModel = myGameModel;
    this.factory = new GoalFactory();
  }

  public void parseTemplate (String fileName)
      throws FileNotFoundException, InvalidGridException, InvalidPieceException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    template = new JSONObject(new JSONTokener(new FileReader(fileName)));
    String title = template.getString("title");

    myGameModel.setCanPlace(template.getBoolean("canPlace"));
    myGameModel.setPieceJSON(template.getJSONObject("pieces"));

    JSONArray goalsJSON = template.getJSONArray("goals");
    for (int i = 0; i < goalsJSON.length(); i++) {
      JSONObject goalJSON = goalsJSON.getJSONObject(i);
      Iterator<String> keys = goalJSON.keys();
      while(keys.hasNext()) {
        String key = keys.next();

        Object goalObj = goalJSON.get(key);
        if(goalObj instanceof Integer){
          myGameModel.addGoal(factory.getGoal(key, goalJSON.getInt(key)));;
        } else {
          JSONArray goalArray = goalJSON.getJSONArray(key);
          List<String> targets = new ArrayList<>();
          for(int j = 0; j < goalArray.length(); j++) {
            targets.add(goalArray.getString(j));
          }
          myGameModel.addGoal(factory.getMultiGoal(key, targets));;
        }
      }
    }

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
