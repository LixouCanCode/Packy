package cc.lixou.packy;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class PackyHandler {

    private final SocketChannel channel;
    private final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    @SneakyThrows
    public PackyHandler(SocketChannel channel) {
        this.channel = channel;
    }

    @SneakyThrows
    public void handle() {
        channel.read(buffer);
        System.out.println(buffer.get());
        buffer.clear();
    }

}
