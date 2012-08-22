package my.client;

import my.messages.serialized.ChatMessage;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    private static Client instance = new Client();

    private Client(){}
    private ClientController clientController;

    private String playerName;
    private UICallBack callBack;

    public void createClientController(String addr, String name){
        clientController = new ClientController(addr, name);
        playerName = name;
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
        callBack.receiveChatMessage(message);
    }
}
