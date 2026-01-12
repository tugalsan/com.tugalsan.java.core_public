package com.tugalsan.java.core.jetty.embedded.server;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpProxy;
import org.eclipse.jetty.client.dynamic.HttpClientTransportDynamic;
import org.eclipse.jetty.http2.client.HTTP2Client;
import org.eclipse.jetty.http3.client.HTTP3Client;
import org.eclipse.jetty.io.ClientConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class Client {

    public static void main(String[] args) throws Exception {

    }

    //https://www.eclipse.org/jetty/documentation/jetty-11/programming_guide.php
    public void websocketClient2() throws Exception {
        var httpClient = new HttpClient();
        httpClient.getProxyConfiguration().getProxies().add(new HttpProxy("localhost", 8888));
//        httpClient.getProxyConfiguration().addProxy(new HttpProxy("localhost", 8888));
        var webSocketClient = new WebSocketClient(httpClient);
        webSocketClient.setMaxTextMessageSize(8 * 1024);
        webSocketClient.start();
    }

    //https://www.eclipse.org/jetty/documentation/jetty-11/programming_guide.php
    public void websocketClient1() throws Exception {
        var webSocketClient = new WebSocketClient();
        webSocketClient.setMaxTextMessageSize(8 * 1024);
        webSocketClient.start();
    }

    //https://www.eclipse.org/jetty/documentation/jetty-11/programming_guide.php
    public void http3Client() throws Exception {
        var http3Client = new HTTP3Client();
        http3Client.getHTTP3Configuration().setStreamIdleTimeout(15000);
        http3Client.start();
    }

    //https://www.eclipse.org/jetty/documentation/jetty-11/programming_guide.php
    public void http2Client() throws Exception {
        var http2Client = new HTTP2Client();
        http2Client.setStreamIdleTimeout(15000);
        http2Client.start();
    }

    //https://www.eclipse.org/jetty/documentation/jetty-11/programming_guide.php
    public void httpsClient() throws Exception {
        var sslContextFactory = new SslContextFactory.Client();
        sslContextFactory.setEndpointIdentificationAlgorithm(null);
        var clientConnector = new ClientConnector();
        clientConnector.setSslContextFactory(sslContextFactory);
        var httpClient = new HttpClient(new HttpClientTransportDynamic(clientConnector));
        httpClient.start();
    }
}
