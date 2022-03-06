import cc.lixou.packy.Packy;
import cc.lixou.packy.PackyServer;

public class PackyTests {

    public static void main(String[] args) {
        PackyServer server = Packy.createServer(25565);
        server.sendPacket(new TestPacket());
    }

}
