package ooga;

import java.io.FileNotFoundException;
import ooga.game_view.GameGuiController;

import java.io.FileNotFoundException;
import ooga.parser.TemplateParser;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main {

    /**
     * Start of the program.
     */
    public static void main(String[] args) throws FileNotFoundException {
        GameGuiController frameWork = new GameGuiController(args);
        try {
            TemplateParser myParser = new TemplateParser("./src/ooga/resources/gameFiles/chess.json");
            myParser.parseTemplate();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

}
