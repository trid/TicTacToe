package my.server.tests;

import junit.framework.TestCase;
import my.messages.serialized.FieldAnswerType;
import my.messages.serialized.FieldType;
import my.server.FieldStatus;
import my.server.GameProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameProcessorTest extends TestCase {
    GameProcessor instance = GameProcessor.getInstance();

    public void setUp() throws Exception {

    }

    public void testGetCurrentPlayer() throws Exception {
        instance.flush();
        assertEquals(instance.getCurrentPlayer(), FieldType.X);
        instance.putMark(0, 0, FieldType.X);
        assertEquals(instance.getCurrentPlayer(), FieldType.O);
    }

    public void testPutMark() throws Exception {
        instance.flush();
        assertEquals(FieldAnswerType.EMPTY_FIELD_SETTING, instance.putMark(0, 0, FieldType.EMPTY));
        assertEquals(FieldAnswerType.NOT_YOUR_TURN, instance.putMark(0, 0, FieldType.O));
        assertEquals(FieldAnswerType.ACCEPTED, instance.putMark(0, 0, FieldType.X));
        assertEquals(FieldAnswerType.ALREADY_SET, instance.putMark(0, 0, FieldType.O));
        assertEquals(FieldStatus.GAME_GOING, instance.getStatus());
        //Let us play with ourselves to get winning position
        instance.putMark(0, 1, FieldType.O);
        instance.putMark(1, 0, FieldType.X);
        instance.putMark(1, 1, FieldType.O);
        instance.putMark(2, 0, FieldType.X);
        assertEquals(FieldStatus.WIN_X, instance.getStatus());
        assertEquals(FieldAnswerType.GAME_ALREADY_OVER, instance.putMark(0, 0, FieldType.O));
        //Somewhere here may be needed neutral result... But when i just think about writing it...
    }
}
