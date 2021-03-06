package cc.lixou.packy;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class PackyWorker extends Thread {

    private final Selector selector;
    private final int ID;
    private final HashMap<SocketChannel, PackyHandler> channels;
    @Getter
    private final PackyServer server;

    public PackyWorker(PackyServer server) throws IOException {
        super("PackyServer-Worker-" + server.getWorkerCount().get());
        this.ID = server.getWorkerCount().getAndIncrement();
        this.server = server;
        this.channels = new HashMap<>();
        this.selector = Selector.open();
    }

    @SneakyThrows
    public void addChannel(SocketChannel channel) {
        channels.put(channel, new PackyHandler(channel));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
        this.selector.wakeup();
    }

    @SneakyThrows
    public void disconnectChannel(SocketChannel channel) {
        System.out.println("disconnected " + channel.getRemoteAddress());
        channel.close();
        channels.remove(channel);
        this.selector.wakeup();
    }

    @Override
    public void run() {
        System.out.println("[Worker-" + ID + "] Starting..");
        while (server.isOpen()) {
            try {
                this.selector.select(selectionKey -> {
                    final SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if (!channel.isOpen()) return;
                    if (!selectionKey.isReadable()) return;
                    boolean couldHandle = channels.get(channel).handle();
                    if(!couldHandle) {
                        disconnectChannel(channel);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
