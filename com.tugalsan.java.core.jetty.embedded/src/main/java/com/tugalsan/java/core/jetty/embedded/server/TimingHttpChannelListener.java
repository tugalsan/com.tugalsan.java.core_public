package com.tugalsan.java.core.jetty.embedded.server;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.eclipse.jetty.server.HttpChannel;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.util.NanoTime;

class TimingHttpChannelListener implements HttpChannel.Listener {

    private final ConcurrentMap<Request, Long> times = new ConcurrentHashMap<>();

    @Override
    public void onRequestBegin(Request request) {
        times.put(request, NanoTime.now());
    }

    @Override
    public void onComplete(Request request) {
        var begin = times.remove(request);
        var delay = NanoTime.since(begin);
        System.out.println("Request %s took %d ns".formatted(request.toString(), delay));
    }
}
