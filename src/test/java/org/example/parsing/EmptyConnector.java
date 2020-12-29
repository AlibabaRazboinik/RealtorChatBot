package org.example.parsing;

import org.apache.commons.io.IOUtils;
import org.example.parser.connector.Connector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class EmptyConnector implements Connector {

    @Override
    public Document get(String url) throws IOException {
        return null;
    }
}
