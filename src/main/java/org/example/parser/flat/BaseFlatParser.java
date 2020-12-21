package org.example.parser.flat;

import org.example.core.Flat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.example.parser.ParseException;

import java.io.IOException;

public abstract class BaseFlatParser implements FlatParser {

    public Flat parse(String url) throws IOException, ParseException {
        Document doc = Jsoup.connect(url).get();
        Flat flat = new Flat();

        Long price = parsePrice(doc);
        flat.setPrice(price);

        Double square = parseSquare(doc);
        flat.setSquare(square);

        String address = parseAddress(doc);
        flat.setAddress(address);

        Long roomsAmount = parseRoomsAmount(doc);
        flat.setRoomsAmount(roomsAmount);

        Long flatFloor = parseFlatFloor(doc);
        flat.setFlatFloor(flatFloor);

        Long totalHouseFloors = parseTotalHouseFloors(doc);
        flat.setTotalHouseFloors(totalHouseFloors);

        return flat;
    }

    protected abstract Long parsePrice(Document doc) throws ParseException;
    protected abstract Double parseSquare(Document doc) throws ParseException;
    protected abstract String parseAddress(Document doc) throws ParseException;
    protected abstract Long parseRoomsAmount(Document doc) throws ParseException;
    protected abstract Long parseFlatFloor(Document doc) throws ParseException;
    protected abstract Long parseTotalHouseFloors(Document doc) throws ParseException;

}
