package my.server;

import my.messages.serialized.FieldAnswerType;
import my.messages.serialized.FieldType;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameProcessor {
    private static GameProcessor instance = new GameProcessor();
    private FieldType currentPlayer = FieldType.X;
    private Field field = new Field();

    private GameProcessor(){}

    public static GameProcessor getInstance() {
        return instance;
    }

    public FieldType getCurrentPlayer() {
        return currentPlayer;
    }

    public FieldAnswerType putMark(int x, int y, FieldType type){
        FieldAnswerType answer = field.putXO(x, y, type);
        if (answer == FieldAnswerType.ACCEPTED){
            if (currentPlayer == FieldType.X)
                currentPlayer = FieldType.O;
            else
                currentPlayer = FieldType.X;
        }
        return answer;
    }

    public void flush(){
        currentPlayer = FieldType.X;
        field = new Field();
    }

    public FieldStatus getStatus(){
        return field.fieldStatus();
    }
}
