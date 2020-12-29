package org.example.agent;

import org.example.parser.connector.RandomUserAgent;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupParse {
    public Document getJsoupDocument(String url) throws IOException {
        RandomUserAgent userAgent = new RandomUserAgent();

        Connection.Response response =  Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent(userAgent.getRandomUserAgent())
                .referrer("http://www.google.com")
                .timeout(12000)
                .followRedirects(true)
                .execute();
        return response.parse();

    }
}
