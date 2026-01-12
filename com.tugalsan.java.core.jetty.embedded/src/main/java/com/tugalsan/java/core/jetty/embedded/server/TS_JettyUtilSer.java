package com.tugalsan.java.core.jetty.embedded.server;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.ThreadPool;

public class TS_JettyUtilSer {

    public static Server createServer(int port, Path keyPath, String keyPass) {
//        var threadPool = new QueuedThreadPool();
//        threadPool.setVirtualThreadsExecutor(Executors.newVirtualThreadPerTaskExecutor());
        var server = new Server(new ThreadPool() {
            private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

            @Override
            public void join() throws InterruptedException {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            }

            @Override
            public void execute(Runnable command) {
                executorService.submit(command);
            }

            @Override
            public int getThreads() {
                return 1;
            }

            @Override
            public int getIdleThreads() {
                return 1;
            }

            @Override
            public boolean isLowOnThreads() {
                return false;
            }
        });
        addConnector(server, port, keyPath, keyPass);
        return server;
    }

    private static void addConnector(Server server, int port, Path keyPath, String keyPass) {
        var cfgHttps = createConfigurationHttps();
        var facHttps11 = createConFactoryHttp11(cfgHttps);
        var connectorHttps = new ServerConnector(
                server,
                createConFactorySsl(keyPath, keyPass, facHttps11),
                createConFactoryHttpAlpn(facHttps11),
                createConFactoryHttp20(cfgHttps),
                facHttps11
        );
        connectorHttps.setPort(port);
        server.addConnector(connectorHttps);
    }

    private static HttpConfiguration createConfigurationHttps() {
        var cfgHttps = new HttpConfiguration();
        cfgHttps.addCustomizer(new SecureRequestCustomizer());
        return cfgHttps;
    }

    private static HttpConnectionFactory createConFactoryHttp11(HttpConfiguration cfgHttps) {
        return new HttpConnectionFactory(cfgHttps);
    }

    private static SslConnectionFactory createConFactorySsl(Path keyPath, String keyPass, HttpConnectionFactory facHttps11) {
        var sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(keyPath.toAbsolutePath().toString());
        sslContextFactory.setKeyStorePassword(keyPass);
        return new SslConnectionFactory(sslContextFactory, facHttps11.getProtocol());
    }

    private static ALPNServerConnectionFactory createConFactoryHttpAlpn(HttpConnectionFactory facHttps11) {
        var alpn = new ALPNServerConnectionFactory();
        alpn.setDefaultProtocol(facHttps11.getProtocol());
        return alpn;
    }

    private static HTTP2ServerConnectionFactory createConFactoryHttp20(HttpConfiguration cfgHttps) {
        return new HTTP2ServerConnectionFactory(cfgHttps);
    }
}
