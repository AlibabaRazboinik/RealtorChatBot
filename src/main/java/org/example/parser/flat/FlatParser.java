package org.example.parser.flat;

import org.example.core.Flat;
import org.example.parser.ParseException;

import java.io.IOException;

public interface FlatParser {
    public Flat parse(String url, String city) throws IOException, ParseException;
}
