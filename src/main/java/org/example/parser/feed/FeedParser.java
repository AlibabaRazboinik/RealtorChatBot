package org.example.parser.feed;

import org.example.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface FeedParser {
    public List<String> parse(String url) throws IOException, ParseException;
}