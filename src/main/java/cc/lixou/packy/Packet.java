package cc.lixou.packy;

public interface Packet {

    void write(PackyBuffer buffer);

    Packet read(PackyBuffer buffer);

}
