package cc.lixou.packy;

import lombok.SneakyThrows;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class PackyHandler {

    private final SocketChannel channel;

    @SneakyThrows
    public PackyHandler(SocketChannel channel) {
        this.channel = channel;
    }

    @SneakyThrows
    public void handle() {
        ByteBuffer input = ByteBuffer.allocate(256);
        channel.read(input);
        String result = new String(input.array()).trim();
        System.out.println(result);
    }

}
