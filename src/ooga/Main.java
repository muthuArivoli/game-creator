package ooga;


import java.io.File;
import java.io.FileNotFoundException;
import ooga.parser.Parser;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        try {
            Parser myParser = new Parser("./src/ooga/games/chess.json");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }
}
