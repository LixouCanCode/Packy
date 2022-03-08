package cc.lixou.packy;

import java.io.*;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class PackyServer {

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public PackyServer(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
    }

    public void start() throws IOException {
        //outputStream = new ObjectOutputStream(socket.getOutputStream());
        //inputStream = new ObjectInputStream(socket.getInputStream());
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
