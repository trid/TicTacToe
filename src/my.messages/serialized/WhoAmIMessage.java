package my.messages.serialized;

/**
 * Created with IntelliJ IDEA.
 * User: TriD
 * Date: 24.08.12
 * Time: 22:37
 */
public class WhoAmIMessage implements Message {
    @Override
    public GameMessageType getType() {
        return GameMessageType.WHOAMI;
    }
}
