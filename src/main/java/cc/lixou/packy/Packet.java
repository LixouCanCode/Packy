package cc.lixou.packy;

public interface Packet {

    void write(PackyBuffer buffer);

    PackyBuffer read(PackyBuffer buffer);

}
