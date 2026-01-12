package com.tugalsan.java.core.servlet.local.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.thread;
import module com.tugalsan.java.core.union;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class TS_SLocal {

    private TS_SLocal() {

    }

    final private static TS_Log d = TS_Log.of(true, TS_SLocal.class);

    public static void main(String... s) {
        var jobIsServer = false;
        var killTrigger = TS_ThreadSyncTrigger.of("main");
        var socketFile = Path.of("d:\\%s.socket".formatted(TS_SLocal.class.getName()));
        if (jobIsServer) {
            var server = runServer(killTrigger, socketFile, receivedText -> {
                d.ci("main", "server", receivedText);
            }, t -> {
                d.ct("main.server.onExcuse", t);
            });
            if (server.isExcuse()) {
                d.ct("main.server", server.excuse());
            }
        } else {
            var client = runClient(killTrigger, socketFile, "'Msg from client!");
            if (client.isExcuse()) {
                d.ct("main.client", client.excuse());
            } else {
                d.ci("main", "client", "sent successful");
            }
        }
    }

    private static TGS_UnionExcuseVoid runClient(TS_ThreadSyncTrigger threadKiller, Path socketFile, String msg) {
        return TGS_FuncMTCUtils.call(() -> {
            var socketAddress = UnixDomainSocketAddress.of(socketFile);
            var openedChannel = SocketChannel.open(StandardProtocolFamily.UNIX);
            openedChannel.connect(socketAddress);
            return write(threadKiller, openedChannel, msg);
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    private static TGS_UnionExcuseVoid runServer(TS_ThreadSyncTrigger threadKiller, Path socketFile, TGS_FuncMTU_In1<String> receivedText, TGS_FuncMTU_In1<Throwable> onExcuse) {
        return TGS_FuncMTCUtils.call(() -> {
            Files.deleteIfExists(socketFile);
            var socketAddress = UnixDomainSocketAddress.of(socketFile);
            var serverChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX);
            serverChannel.bind(socketAddress);
            d.ci("runServer", "waiting...!");
            var acceptedChannel = serverChannel.accept();
            d.ci("runServer", "accepted!");
            while (threadKiller.hasNotTriggered()) {
                var op = read(threadKiller, acceptedChannel);
                if (op.isExcuse()) {
                    onExcuse.run(op.excuse());
                    continue;
                }
                receivedText.run(op.value());
                TS_ThreadSyncWait.milliseconds100();
                d.ci("runServer", "waiting for new...!");
                acceptedChannel = serverChannel.accept();
                d.ci("runServer", "new accepted!");
            }
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    private static TGS_UnionExcuseVoid write(TS_ThreadSyncTrigger threadKiller, SocketChannel openedChannel, String msg) {
        return TGS_FuncMTCUtils.call(() -> {
            var buffer = ByteBuffer.allocate(1024);
            buffer.clear();
            buffer.put(msg.getBytes());
            buffer.flip();
            while (buffer.hasRemaining() && threadKiller.hasNotTriggered()) {
                openedChannel.write(buffer);
            }
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

    @Deprecated //I DONT UNDERSTANT, WHERE WHILE
    private static TGS_UnionExcuse<String> read(TS_ThreadSyncTrigger threadKiller, SocketChannel channel) {
        return TGS_FuncMTCUtils.call(() -> {
            var buffer = ByteBuffer.allocate(1024);
            var bytesRead = channel.read(buffer);
            if (bytesRead < 0) {
                return TGS_UnionExcuse.of("");
            }
            var bytes = new byte[bytesRead];
            buffer.flip();
            buffer.get(bytes);
            var message = new String(bytes);
            return TGS_UnionExcuse.of(message);
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }
}
