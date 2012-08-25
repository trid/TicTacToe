package my.messages.serialized;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetXOMessage implements Message {
    private int x;
    private int y;
    private FieldType markType;

    public SetXOMessage(int x, int y, FieldType markType) {
        this.x = x;
        this.y = y;
        this.markType = markType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public GameMessageType getType() {
        return GameMessageType.XO_MESSAGE;
    }

    public FieldType getMarkType() {
        return markType;
    }
}
