package cc.lixou.packy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class PackyServer {

    private final ServerSocket serverSocket;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public PackyServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        Socket socket = serverSocket.accept();
        //outputStream = new ObjectOutputStream(socket.getOutputStream());
        //inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendPacket(Packet packet) {
        PackyBuffer buffer = new PackyBuffer(null);
        packet.write(buffer);
        PackyBuffer buffer1 = new PackyBuffer(buffer.result());
        buffer.close();
        System.out.println(buffer1.readBoolean());
   }

}
