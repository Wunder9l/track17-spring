package track.messenger.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by artem on 11.04.17.
 */
public class ServerListener {
//    Selector selector = Selector.open();

//channel.configureBlocking(false);

//    SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Parameter: <Port>");
        } else {
            int port = Integer.parseInt(args[0]);
//            ServerSocketChannel serverSocketChannel = new ServerSocketChannel()

        }
    }

    private void connectionHandling() {

    }
}
