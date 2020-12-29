package org.example.parser;

public class ProxyServer {
    private String url;
    private int port;

    public void setUrl(String url){
        this.url = url;
    }

    public void setPort(int port){
        this.port = port;
    }


    public String getUrl() {
        return url;
    }

    public int getPort() {
        return port;
    }


}
