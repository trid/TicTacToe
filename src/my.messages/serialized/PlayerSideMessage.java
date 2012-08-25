package my.messages.serialized;

/**
 * Created with IntelliJ IDEA.
 * User: TriD
 * Date: 25.08.12
 * Time: 1:19
 */
public class PlayerSideMessage implements Message {
    private FieldType playerType;

    public PlayerSideMessage(FieldType playerType){
        this.playerType = playerType;
    }

    @Override
    public GameMessageType getType() {
        return GameMessageType.PLAYER_SIDE;
    }

    public FieldType getPlayerType(){
        return playerType;
    }
}
