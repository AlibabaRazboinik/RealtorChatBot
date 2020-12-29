package org.example.parsing;

import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.example.parser.ParseException;
import org.example.parser.connector.Connector;
import org.example.parser.feed.AvitoFeedParser;
import org.example.parser.feed.CianFeedParser;
import org.example.parser.feed.FeedParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



public class FeedTest extends TestCase {

    private static InputStream getResourceStream(String path) throws IOException {
        ClassLoader loader = FeedTest.class.getClassLoader();
        InputStream stream = loader.getResourceAsStream(path);
        if (stream == null) {
            throw new FileNotFoundException("File \"" + path + "\" not found");
        }

        return stream;
    }

    private static List<String> loadExpectedUrls(String platformName)  throws IOException {
        List<String> expectedUrls = new ArrayList<>();
        try (InputStream stream = getResourceStream("feed/" + platformName + "Urls.txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line;
            while ((line = reader.readLine()) != null) {
                expectedUrls.add(line.strip());
            }

            return expectedUrls;
        }
    }

    private static void testParse(String platformNameAsUrl, FeedParser parser) throws IOException, ParseException {
        List<String> expectedUrls = loadExpectedUrls(platformNameAsUrl);
        List<String> parsedUrls = parser.parse(platformNameAsUrl);
        Assert.assertEquals(expectedUrls, parsedUrls);
    }

    public void testAvitoParse() throws IOException, ParseException {
        Connector connector = new FeedTestConnector("avito");
        FeedParser parser = new AvitoFeedParser(connector);
        testParse("avito", parser);
    }

    public void testCianParse() throws IOException, ParseException {
        Connector connector = new FeedTestConnector("cian");
        FeedParser parser = new CianFeedParser(connector);
        testParse("cian", parser);
    }

}