package org.example.parser;


import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

public class ProxyConnector {
    private int index;
    private List<ProxyServer> proxies;

    public void setProxies(List<ProxyServer> proxies) {
        this.proxies = proxies;
    }

    private void updateIndex(){
        if(index >= proxies.size())
            index = 0;
        index += 1;
    }

    private Document getHTML() throws IOException, ParseException {
        if(proxies.size() == 0) {
            throw new ParseException("Empty proxies");
        }

        ProxyServer proxyServer = proxies.get(index);
        updateIndex();

        String url = proxies.get(index).getUrl();
        InetSocketAddress ipAddress = new InetSocketAddress(url, proxyServer.getPort());
        Proxy proxy = new Proxy(Proxy.Type.HTTP, ipAddress);

        return Jsoup.connect(url).proxy(proxy).get();
    }
}
