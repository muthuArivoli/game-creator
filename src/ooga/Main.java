package ooga;


import java.io.FileNotFoundException;
import ooga.parser.TemplateParser;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        try {
            TemplateParser myParser = new TemplateParser("./src/ooga/games/chess.json");
            myParser.parseTemplate();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }
}
