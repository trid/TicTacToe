package my.client;

import my.messages.serialized.ChatMessage;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/21/12
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UICallBack {
    void receiveChatMessage(ChatMessage message);
}
