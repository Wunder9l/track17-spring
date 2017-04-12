package track.messenger.server;

import track.messenger.utils.Message;
import track.messenger.utils.MessageBuffer;

import java.util.Queue;

/**
 * Created by jjenkov on 22-10-2015.
 * Modified by artem on 12.04.2017.
 */
public class WriteProxy {

    private MessageBuffer messageBuffer = null;
    private Queue writeQueue = null;

    public WriteProxy(MessageBuffer messageBuffer, Queue writeQueue) {
        this.messageBuffer = messageBuffer;
        this.writeQueue = writeQueue;
    }

    public Message getMessage() {
        return this.messageBuffer.getMessage();
    }

    public boolean enqueue(Message message) {
        return this.writeQueue.offer(message);
    }

}
