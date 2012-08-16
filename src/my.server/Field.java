package my.server;

import my.messages.serialized.FieldAnswerType;
import my.messages.serialized.FieldType;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Field {
    FieldType[][] cells = new FieldType[3][3];
    int turnsMade = 0;
    boolean finished = false;

    public Field(){
        for (int i = 0; i < 3; i++){
            for (int k = 0; k < 3; k++){
                cells[i][k] = FieldType.EMPTY;
            }
        }
    }

    public synchronized FieldAnswerType putXO(int x, int y, FieldType fieldType){
        if (fieldType == FieldType.EMPTY) return FieldAnswerType.EMPTY_FIELD_SETTING;
        if (finished) return FieldAnswerType.GAME_ALREADY_OVER;
        if (cells[x][y] != FieldType.EMPTY) return FieldAnswerType.ALREADY_SET;

        GameProcessor server = GameProcessor.getInstance();
        FieldType player = server.getCurrentPlayer();

        if (player == fieldType){
            cells[x][y] = fieldType;
            turnsMade++;
        }
        else {
            return FieldAnswerType.NOT_YOUR_TURN;
        }

        return FieldAnswerType.ACCEPTED;
    }

    public FieldStatus fieldStatus(){
        FieldStatus winner = checkWinner();
        if (winner != null){
            finished = true;
            return winner;
        }
        if (turnsMade == 9){
            finished = true;
            return FieldStatus.FRIENDSHIP;
        }
        return FieldStatus.GAME_GOING;
    }

    //Terrible way to check who won. Can't see better (sad).
    private FieldStatus checkWinner() {
        for (int i = 0; i < 3; i++)
        {
            FieldType[] row = cells[i];
            if (row[0] == row[1] && row[1] == row[2]){
                if (row[0] == FieldType.X) return FieldStatus.WIN_X;
                else return FieldStatus.WIN_Y;
            }
        }
        for (int i = 0; i < 3; i++)
        {
            //I'm sad. I can't take columns just like rows :(
            if (cells[0][i] == cells[1][i] && cells[1][i] == cells[2][i]){
                if (cells[i][0] == FieldType.X) return FieldStatus.WIN_X;
                else return FieldStatus.WIN_Y;
            }
        }
        //Not too cool diagonal comparison. Do we really may need loop here? (Yes, we need, if just there will be more cells
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]){
            if (cells[0][0] == FieldType.X) return FieldStatus.WIN_X;
            else return FieldStatus.WIN_Y;
        }
        if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]){
            if (cells[0][0] == FieldType.X) return FieldStatus.WIN_X;
            else return FieldStatus.WIN_Y;
        }
        //Every next comparison is bigger then previous. WTF?!

        return null;
    }
}
