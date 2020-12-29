package org.example.parser.pipeline;

import org.example.core.FlatJsonMarshaller;
import org.example.parser.ParseException;
import org.example.core.Flat;
import org.example.parser.feed.FeedParser;
import org.example.parser.flat.FlatParser;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParserPipeLine {

    private static InputStream getResourceStream(String path) throws IOException {
        ClassLoader loader = ParserPipeLine.class.getClassLoader();
        InputStream stream = loader.getResourceAsStream(path);
        if (stream == null) {
            throw new FileNotFoundException("File \"" + path + "\" not found");
        }

        return stream;
    }

    public static List<Flat> parse(String platformName, FeedParser feedParser, FlatParser flatParser) throws IOException, ParseException, InterruptedException {
        List<CityUrl> cityUrls = CityUrlJsonMarshaller.deserialize(platformName + ".json");
        List<Flat> flats = new ArrayList<>();

        for (CityUrl cityUrl : cityUrls) {
            List<String> advertisementUrls = feedParser.parse(cityUrl.getFeedUrl());
            for (String url : advertisementUrls) {
                Flat flat = flatParser.parse(url, cityUrl.getCityName());
                flats.add(flat);
                FlatJsonMarshaller.serialize(flats, "parsedFlats.json");

                TimeUnit.SECONDS.sleep(1);
            }
        }

        return flats;
    }
}
