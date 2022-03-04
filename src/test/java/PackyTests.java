import cc.lixou.packy.Packy;
import cc.lixou.packy.PackyServer;
import org.junit.jupiter.api.Test;

public class PackyTests {

    @Test
    public void createTestServer() {
        PackyServer server = Packy.createServer(25565);
    }

}
