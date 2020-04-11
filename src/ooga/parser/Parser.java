package ooga.parser;

import java.io.FileNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.Iterator;

public class Parser {
  public Parser (String fileName) throws FileNotFoundException {
       JSONObject temp = new JSONObject(new FileReader(fileName));
       
  }

}
