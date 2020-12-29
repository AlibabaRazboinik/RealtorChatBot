package org.example.parser.connector;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface Connector {
    public Document get(String url) throws IOException;
}
