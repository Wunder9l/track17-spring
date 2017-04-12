package track.messenger.client;

import track.messenger.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by artem on 11.04.17.
 */
public class ConnectorToServer {

    public ConnectorToServer() throws IOException{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(Constants.DEFAULT_SERVER_ADDRESS, Constants.DEFAULT_SERVER_PORT));
    }
}
