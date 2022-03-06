package cc.lixou.packy;

public interface Packet {

    void write(PackyBuffer buffer);

    void read(PackyBuffer buffer);

}
