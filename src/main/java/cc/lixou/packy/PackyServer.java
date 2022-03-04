package cc.lixou.packy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PackyServer {

    private final ServerSocket serverSocket;

    public PackyServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        Socket socket = serverSocket.accept();
        System.out.println("New client connected");
    }

}
