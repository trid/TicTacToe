package my.client;

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

    public void createClientController(String addr, String name){
        clientController = new ClientController(addr, name);
    }

    public static Client getClient(){
        return instance;
    }
}
