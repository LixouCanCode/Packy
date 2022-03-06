import cc.lixou.packy.Packet;
import cc.lixou.packy.PackyBuffer;

public class TestPacket implements Packet {

    @Override
    public void write(PackyBuffer buffer) {
        buffer.writeBoolean(false);
        buffer.writeInt(123);
        buffer.writeString("Test");
        buffer.writeByte((byte) 4);
        buffer.writeChar('c');
    }

    @Override
    public void read(PackyBuffer buffer) {

    }

}