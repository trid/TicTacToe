package my.messages.serialized;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Answer implements Message {
    private FieldAnswerType answerType;
    private int x;
    private int y;

    public Answer(FieldAnswerType answerType, int x, int y) {
        this.answerType = answerType;
        this.x = x;
        this.y = y;
    }

    @Override
    public GameMessageType getType() {
        return GameMessageType.ANSWER_MESSAGE;
    }

    public FieldAnswerType getAnswerType() {
        return answerType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
