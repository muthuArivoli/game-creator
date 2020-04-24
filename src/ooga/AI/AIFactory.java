package ooga.AI;

import ooga.models.GridModel;

import java.lang.reflect.InvocationTargetException;

public class AIFactory {
    private static final String PACKAGE = "ooga.AI.";

    public AIFactory() {

    }

    public AI getAIClass(String aiString, GridModel myGridModel, int currentPlayer) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class c = Class.forName(PACKAGE + aiString);
        AI newAI = (AI) c.getDeclaredConstructor(GridModel.class, int.class).newInstance(myGridModel, currentPlayer);
        return newAI;
    }
}
