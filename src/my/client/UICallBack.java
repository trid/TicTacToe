package my.client;

import my.messages.serialized.Answer;
import my.messages.serialized.ChatMessage;
import my.messages.serialized.FieldType;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/21/12
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UICallBack {
    void receiveChatMessage(ChatMessage message);
    void receiveAnswer(Answer message);
    void setMark(int x, int y, FieldType markType);
    void resultWin();
    void resultLose();
    void resultFriendship();
}
