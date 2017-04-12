package track.messenger.server;

import track.messenger.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by artem on 11.04.17.
 */
public class ServerListener {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(Constants.DEFAULT_SERVER_PORT));
        serverSocketChannel.configureBlocking(false);
        while (true) {
            SocketChannel socketChannel =
                    serverSocketChannel.accept();
            if (socketChannel != null) {
                //do something with socketChannel...
            }
            //do something with socketChannel...
        }
    }

    private void connectionHandling() {
//        Selector selector = Selector.open();
//
//        channel.configureBlocking(false);
//
//        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);


        while (true) {

            int readyChannels = selector.select();

            if (readyChannels == 0) continue;


            Set<SelectionKey> selectedKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {

                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.

                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.

                } else if (key.isReadable()) {
                    // a channel is ready for reading

                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }

                keyIterator.remove();
            }
        }

    }
}
