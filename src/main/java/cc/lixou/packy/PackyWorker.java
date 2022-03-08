package cc.lixou.packy;

import lombok.Getter;

import java.io.IOException;
import java.nio.channels.Selector;

public class PackyWorker extends Thread {

    private final Selector selector;
    private final int ID;
    @Getter
    private final PackyServer server;

    public PackyWorker(PackyServer server) throws IOException {
        this.server = server;
        this.ID = server.getWorkerSize().getAndIncrement();
        this.selector = Selector.open();
    }

    @Override
    public void run() {
        System.out.println("[Worker-" + ID + "] Starting..");
    }

}
