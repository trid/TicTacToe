package my.messages.serialized;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 12:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatMessage implements Message{
    private String playerName;
    private String message;

    @Override
    public GameMessageType getType() {
        return GameMessageType.CHAT_MESSAGE;
    }

    public ChatMessage(String playerName, String message){
        this.playerName = playerName;
        this.message = message;
    }
}
