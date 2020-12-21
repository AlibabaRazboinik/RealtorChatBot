package org.example.parser.pipeline;

import org.example.core.JsonParser;
import org.example.parser.ParseException;
import org.example.core.Flat;
import org.example.parser.flat.FlatParser;
import org.example.parser.offerfeed.FeedParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParserPipeLine {
    public static List<Flat> parse(String platformName, FeedParser feedParser, FlatParser flatParser) throws IOException, ParseException, InterruptedException {
        JsonParser<CityUrl> deserializer = new JsonParser<>();
        List<CityUrl> cityUrls = deserializer.deserialize(platformName + ".json");
        List<Flat> flats = new ArrayList<>();

        for (CityUrl cityUrl : cityUrls) {
            List<String> advertisementUrls = feedParser.parse(cityUrl.getFeedUrl());
            for (String url : advertisementUrls) {
                Flat flat = flatParser.parse(url);
                flat.setCity(cityUrl.getCityName());
                flats.add(flat);

                TimeUnit.SECONDS.sleep(1);
            }
        }

        return flats;
    }
}
