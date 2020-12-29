package org.example.parsing;

import org.apache.commons.io.IOUtils;
import org.example.parser.connector.Connector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FeedTestConnector implements Connector {
    private final Document doc;
    private final String platformNameAsUrl;

    public FeedTestConnector(String platformName) throws IOException {
        String feedHtml = loadFeedHtml(platformName);
        platformNameAsUrl = platformName;
        doc = Jsoup.parse(feedHtml);
    }

    private static InputStream getResourceStream(String path) throws IOException {
        ClassLoader loader = FeedTest.class.getClassLoader();
        InputStream stream = loader.getResourceAsStream(path);
        if (stream == null) {
            throw new FileNotFoundException("File \"" + path + "\" not found");
        }

        return stream;
    }

    private static String loadFeedHtml(String platformName) throws IOException {
        try (InputStream stream = getResourceStream("feed/" + platformName + "Feed.html")) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        }
    }

    @Override
    public Document get(String url) throws IOException {
        if (platformNameAsUrl.equals(url)) {
            return doc;
        } else {
            throw new IOException("Url \"" + url + "\n not found");
        }

    }
}
