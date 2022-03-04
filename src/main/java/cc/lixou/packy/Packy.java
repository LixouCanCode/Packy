package cc.lixou.packy;

import java.io.IOException;

public final class Packy {

    public static PackyServer createServer(int port) {
        PackyServer server = null;
        try {
            server = new PackyServer(port);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return server;
    }

}