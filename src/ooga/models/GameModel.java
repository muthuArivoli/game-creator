package ooga.models;

public class GameModel {
  private GridModel myGridModel;

  public GameModel () {
    this.myGridModel = new GridModel();

  }

  public GridModel getMyGridModel() {
    return myGridModel;
  }
}
