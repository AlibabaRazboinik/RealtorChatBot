package org.example.parser.connector;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupConnector implements Connector {

    @Override
    public Document get(String url) throws IOException {
        String userAgent = new RandomUserAgent().getRandomUserAgent();

        Connection.Response response =  Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent(userAgent)
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();

        return response.parse();
    }
}
