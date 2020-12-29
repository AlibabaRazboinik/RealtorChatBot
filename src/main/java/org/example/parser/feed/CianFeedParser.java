package org.example.parser.feed;

import org.example.parser.connector.Connector;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.example.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CianFeedParser implements FeedParser {
    private final Connector connector;

    public CianFeedParser(Connector connector) {
        this.connector = connector;
    }

    @Override
    public List<String> parse(String url) throws IOException, ParseException {
        Document doc = connector.get(url);
        Elements tagLinks = doc.select("div[data-name=\"LinkArea \"]");
        if (tagLinks.isEmpty()) {
            throw new ParseException("Tag links is empty.");
        }

        List<String> links = new ArrayList<>();
        for (Element el : tagLinks) {

            String link = el.selectFirst("a").attr("href");
            if (link != null && link.length() != 0)
                links.add(link);
        }

        return links;
    }
}