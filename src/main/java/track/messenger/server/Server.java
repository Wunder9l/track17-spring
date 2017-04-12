package track.messenger.server;

import track.messenger.Constants;
import track.messenger.utils.IMessageProcessor;
import track.messenger.utils.IMessageReaderFactory;
import track.messenger.utils.MessageBuffer;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by jjenkov on 24-10-2015.
 * Modified by artem on 12.04.2017.
 */
public class Server {

    private SocketAccepter socketAccepter = null;
    private SocketProcessor socketProcessor = null;

    private int tcpPort = 0;
    private IMessageReaderFactory messageReaderFactory = null;
    private IMessageProcessor messageProcessor = null;

    /**
     * Server based on NIO-server implementation of jjenkov
     * @param tcpPort Can be 0, in that way the default port would be used
     * @param messageReaderFactory
     * @param messageProcessor
     */
    public Server(int tcpPort, IMessageReaderFactory messageReaderFactory, IMessageProcessor messageProcessor) {
        if (0 == tcpPort){
            tcpPort = Constants.DEFAULT_SERVER_PORT;
        }
        this.tcpPort = tcpPort;
        this.messageReaderFactory = messageReaderFactory;
        this.messageProcessor = messageProcessor;
    }

    public void start() throws IOException {

        Queue socketQueue = new ArrayBlockingQueue(Constants.SERVER_SOCKET_QUEUE_SIZE);

        this.socketAccepter = new SocketAccepter(tcpPort, socketQueue);


        MessageBuffer readBuffer = new MessageBuffer();
        MessageBuffer writeBuffer = new MessageBuffer();

        this.socketProcessor = new SocketProcessor(socketQueue,
                readBuffer,
                writeBuffer,
                this.messageReaderFactory,
                this.messageProcessor);

        Thread accepterThread = new Thread(this.socketAccepter);
        Thread processorThread = new Thread(this.socketProcessor);

        accepterThread.start();
        processorThread.start();
    }


}
