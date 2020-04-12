package ooga;

import java.io.FileNotFoundException;
import ooga.controller.GameController;
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
//        GameGuiController frameWork = new GameGuiController(args);

        GameController myGameController = new GameController();
        myGameController.parseFile("./src/ooga/resources/gameFiles/chess.json");

    }

}
