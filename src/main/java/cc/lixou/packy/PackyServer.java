package cc.lixou.packy;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PackyServer {

    @Getter
    private final int port;
    private final Selector selector;
    private final ServerSocketChannel serverSocket;

    @Getter private final List<PackyWorker> workers;
    @Getter private final AtomicInteger workerSize = new AtomicInteger(0);

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public PackyServer(int port) throws IOException {
        this.port = port;
        this.workers = new ArrayList<>();
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() throws IOException {
        for (PackyWorker worker : workers) {
            worker.start();
        }
        //outputStream = new ObjectOutputStream(socket.getOutputStream());
        //inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @SneakyThrows
    public void createWorker(int amount) {
        for (int i = 0; i < amount; i++) {
            workers.add(new PackyWorker(this));
        }
    }

    public void sendPacket(Packet packet) {
        PackyBuffer buffer = new PackyBuffer(null);
        packet.write(buffer);
        byte[] result = buffer.result();
        buffer.close();
        packet.read(new PackyBuffer(result));
        // TODO: Send Bytes
    }

}
