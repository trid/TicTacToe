package my.server.networking;

import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerController {
    private static PlayerController instance = new PlayerController();

    private Player p1;
    private Player p2;

    private Socket p1Socket;
    private Socket p2Socket;

    private PlayerController() {}

    public PlayerController getInstance(){
        return instance;
    }
}
