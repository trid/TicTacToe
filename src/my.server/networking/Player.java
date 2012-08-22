package my.server.networking;

import my.messages.serialized.FieldType;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    FieldType playerSide;
    String playerName;

    public Player(FieldType playerSide, String playerName) {
        this.playerSide = playerSide;
        this.playerName = playerName;
    }

    public Player() {

    }

    public FieldType getPlayerSide() {
        return playerSide;
    }

    public String getPlayerName() {
        return playerName;
    }
}
