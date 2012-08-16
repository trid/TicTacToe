package my.messages.serialized;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Message extends Serializable {
    GameMessageType getType();
}
