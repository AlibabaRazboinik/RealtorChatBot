package org.example.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class JsoupProxy {
    public static Document get(String url) throws IOException {
        InetSocketAddress ipAddress = new InetSocketAddress("161.202.226.194", 8123);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, ipAddress);

        return Jsoup.connect(url).proxy(proxy).get();
    }
}
