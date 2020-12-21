package org.example.parser.offerfeed;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.example.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CianFeedParser implements FeedParser {
    @Override
    public List<String> parse(String url) throws IOException, ParseException {
        Document doc = Jsoup.connect(url).get();
        Elements tagLinks = doc.select("a[target=\"_blank\"]");

        if (tagLinks.isEmpty()) {
            throw new ParseException("Tag links is empty.");
        }

        List<String> links = new ArrayList<>();
        for (Element el : tagLinks) {
            String rowLink = el.attr("href");
            if (rowLink.contains("kupit") && rowLink.contains("ulica")) {
                links.add(rowLink);
            }
        }

        return links;

    }
}
