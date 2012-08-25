package my.messages.serialized;

/**
 * Created with IntelliJ IDEA.
 * User: TriD
 * Date: 25.08.12
 * Time: 20:52
 */
public class GameOverMessage implements Message {
    private GameResult result;

    public GameOverMessage(GameResult result) {
        this.result = result;
    }

    @Override
    public GameMessageType getType() {
        return GameMessageType.GAME_OVER_MESSAGE;
    }

    public GameResult getResult() {
        return result;
    }
}
