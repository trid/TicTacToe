package my.client;

import my.messages.serialized.*;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    private static Client instance = new Client();
    private FieldType playerSide = null;

    private Client(){}
    private ClientController clientController;

    private String playerName;
    private UICallBack callBack;

    private Thread listener;

    public void setPlayerSide(FieldType playerSide) {
        this.playerSide = playerSide;
    }

    public void createClientController(String addr, String name){
        clientController = new ClientController(addr, name);
        playerName = name;
        listener = new Thread(clientController);
        listener.start();
        sendWhoAmI();
    }

    private void sendWhoAmI() {
        WhoAmIMessage message = new WhoAmIMessage();
        clientController.sendMessage(message);
    }

    public static Client getClient(){
        return instance;
    }

    public void sendChatMessage(String text) {
        ChatMessage message = new ChatMessage(playerName, text);
        clientController.sendMessage(message);
    }

    public void setCallBack(UICallBack callBack){
        this.callBack = callBack;
    }

    public void receiveChatMessage(ChatMessage message) {
        if (callBack != null)
            callBack.receiveChatMessage(message);
    }

    public FieldType getPlayerSide() {
        return playerSide;
    }

    public void sendSetMessage(int x, int y) {
        SetXOMessage message = new SetXOMessage(x, y, playerSide);
        clientController.sendMessage(message);
    }

    public void receiveAnswer(Answer message) {
        callBack.receiveAnswer(message);
    }

    public void receiveSetMark(SetXOMessage message) {
        callBack.setMark(message.getX(), message.getY(), message.getMarkType());
    }

    public void gameOver(GameOverMessage message) {
        switch (message.getResult()) {
            case LOSE:
                callBack.resultLose();
                break;
            case WIN:
                callBack.resultWin();
                break;
            case FRIENDSHIP:
                callBack.resultFriendship();
                break;
        }
    }
}
