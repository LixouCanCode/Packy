import cc.lixou.packy.Packy;
import cc.lixou.packy.PackyServer;

import java.io.IOException;

public class PackyTests {

    public static void main(String[] args) throws IOException {
        PackyServer server = Packy.createServer(25565);
        server.createWorker(3);
        server.start();
        //server.sendPacket(new TestPacket());
    }

}
