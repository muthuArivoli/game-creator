package ooga.parser;

import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class Parser {
  public Parser (String fileName) throws FileNotFoundException {
    JSONObject template = new JSONObject(new JSONTokener(new FileReader(fileName)));
    String title = template.getString("title");
    JSONArray pieces = template.getJSONArray("pieces");

    for (int i = 0; i < pieces.length(); i++) {
      System.out.println(pieces.get(i));
    }
  }

}
