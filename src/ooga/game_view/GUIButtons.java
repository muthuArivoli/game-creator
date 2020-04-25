package ooga.game_view;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * ButtonController.java Creates all the Buttons and Pop-Ups in the Game environment
 */
public class GUIButtons {

  private static final String SUPPORTED_LANGUAGES = "src/ooga/resources/languages/LanguageList.txt";
  public static final int BUTTONWIDTH = 250;
  public static final int BUTTONHEIGHT = 60;
  public static final int VSPACING = 10;
  public static final int BUTTONPREFHEIGHT = 350;

  private ResourceBundle myResources;
  private List<String> languages;
  private boolean newGamePressed;
  private boolean restartGamePressed;
  private boolean settingsPressed;
  private boolean rulesPressed;
  private String languagePressed;
  private VBox myButtons;

  /**
   * Constructor that sets Resource Bundle and initializes all initial states of buttons
   * <p>
   * Button states are initially False; ComboBox states have a defined initial String
   *
   * @param language the current language passed in from ParserController
   * @throws FileNotFoundException in case the File does not exist
   */
  public GUIButtons(String language) throws FileNotFoundException {
    myResources = ResourceBundle.getBundle(language);
    this.languages = text2Regex(new File(SUPPORTED_LANGUAGES));
    this.newGamePressed = false;
    this.restartGamePressed = false;
    this.settingsPressed = false;
    this.rulesPressed = false;
    this.languagePressed = myResources.getString("LanguageButton");
    renderButtons();
  }

  /**
   * @return the JavaFX HBox that contains all the buttons
   */
  public VBox getVBox() {
    return myButtons;
  }

  /**
   * @return the pressed state (String) of the Language ComboBox
   */
  public String getLanguageStatus() {
    return languagePressed;
  }

  /**
   * @return the pressed state (boolean) of the New Game Button
   */
  public boolean getNewGameStatus() {
    return newGamePressed;
  }

  /**
   * Unpresses the New Game Button
   */
  public void setNewGamePressedOff() {
    newGamePressed = false;
  }

  /**
   * @return the pressed state (boolean) of the restart Game Button
   */
  public boolean getRestartGameStatus() {
    return restartGamePressed;
  }

  /**
   * Unpresses the restart game Button
   */
  public void setRestartGamePressedOff() {
    restartGamePressed = false;
  }

  /**
   * @return the pressed state (boolean) of the settings Button
   */
  public boolean getSettingsStatus() {
    return settingsPressed;
  }

  /**
   * Unpresses the Settings Button
   */
  public void setSettingsPressedOff() {
    settingsPressed = false;
  }

  /**
   * @return the pressed state (boolean) of the Rules button
   */
  public boolean getRulesStatus() {
    return rulesPressed;
  }

  /**
   * Unpresses the Rules Button
   */
  public void setRulesPressedOff() {
    rulesPressed = false;
  }

  /**
   * Creates and initializes all Buttons based on Regex Values
   */
  private void renderButtons() {
    myButtons = new VBox();
    Button newGameButton = makeButton("NewGame", event -> newGamePressed = true);
    Button restartGameButton = makeButton("RestartGame", event -> restartGamePressed = true);
    Button settingsButton = makeButton("Settings", event -> settingsPressed = true);
    Button rulesButton = makeButton("Rules", event -> rulesPressed = true);
    ComboBox langMenu = makeDropDown("LanguageButton", languages);
    myButtons.setPrefSize(BUTTONWIDTH+30, BUTTONPREFHEIGHT);
    myButtons.setPadding(new Insets(30));
    myButtons.setSpacing(VSPACING);

    formatButton(newGameButton);
    formatButton(restartGameButton);
    formatButton(settingsButton);
    formatButton(rulesButton);
    formatBox(langMenu);
    myButtons.getChildren()
        .addAll(newGameButton, restartGameButton, settingsButton, rulesButton, langMenu);
  }

  private Button makeButton(String key, EventHandler e) {
    Button tempButton = new Button(myResources.getString(key));
    tempButton.setMaxWidth(Double.MAX_VALUE);
    tempButton.setOnAction(e);
    return tempButton;
  }

  private ComboBox makeDropDown(String key, List<String> options) {
    ComboBox tempMenu = new ComboBox();
    tempMenu.getItems().addAll(options);
    tempMenu.setValue(myResources.getString(key));
    if (key.equals("LanguageButton")) {
      tempMenu.setOnAction(event -> languagePressed = (String) tempMenu.getValue());
    }
    return tempMenu;
  }

  private void formatButton(Button tempButton) {tempButton.setPrefSize(BUTTONWIDTH, BUTTONHEIGHT);}

  private void formatBox(ComboBox tempMenu) {
    tempMenu.setPrefSize(BUTTONWIDTH,BUTTONHEIGHT);
  }

  private List<String> text2Regex(File dataFile) throws FileNotFoundException {
    List<String> ret = new ArrayList<>();
    Scanner scanner = new Scanner(dataFile);
    while (scanner.hasNextLine()) {
      ret.add(myResources.getString(scanner.nextLine()));
    }
    return ret;
  }
}