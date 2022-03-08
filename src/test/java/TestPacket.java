import cc.lixou.packy.Packet;
import cc.lixou.packy.PackyBuffer;

public class TestPacket implements Packet {

    @Override
    public void write(PackyBuffer buffer) {
        buffer.writeBoolean(false).
                writeInt(123).
                writeString("Test").
                writeByte((byte) 4).
                writeChar('c');
    }

    @Override
    public void read(PackyBuffer buffer) {
        System.out.println(buffer.readInt());
        System.out.println(buffer.readInt());
        System.out.println(buffer.readString());
        System.out.println(buffer.readByte());
        System.out.println(buffer.readChar());

    }

}
