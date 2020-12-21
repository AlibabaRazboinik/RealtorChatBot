package org.example.parser.flat;

import org.jsoup.nodes.Document;
import org.example.parser.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvitoFlatParser extends BaseFlatParser{
    private final static Pattern SQUARE_REGEX = Pattern.compile("[1-9][0-9]*(.[0-9]+)?");
    private final static Pattern FLOOR_REGEX = Pattern.compile("(?<flatFloor>\\d+)/(?<totalFloors>\\d+)");
    private final static Pattern ROOMS_AMOUNT_REGEX = Pattern.compile("(?<roomsAmount>^\\d+)-");
    private final static Pattern PRICE_REGEX = Pattern.compile("(\\d+)");

    @Override
    protected Long parsePrice(Document doc) throws ParseException {
        String priceInText = doc.select("span.js-item-price").attr("content");
        if(priceInText.isEmpty())
            throw new ParseException("Price is empty");
        Matcher matchedPrice = PRICE_REGEX.matcher(priceInText);
        if(!matchedPrice.find())
            throw new ParseException("Price is not matched");
        return Long.parseLong(matchedPrice.group().replace(" ", ""));
    }
    private String getHeader(Document doc) throws ParseException {
        String header = doc.select("span.title-info-title-text").text();
        if (header.isEmpty())
            throw new ParseException("Advertisement header is empty");
        return header;
    }

    @Override
    protected Double parseSquare(Document doc) throws ParseException {
        String squareInText = getHeader(doc).split(",")[1];
        if (squareInText.isEmpty())
            throw new ParseException("Square is empty");
        Matcher matchedSquare = SQUARE_REGEX.matcher(squareInText);
        if (matchedSquare.find()) {
            return Double.parseDouble(matchedSquare.group());
        }
        throw new ParseException("Square is not matched");
    }

    @Override
    protected String parseAddress(Document doc) throws ParseException {
        String address = doc.select("span.item-address__string").text();
        if(address.isEmpty())
            throw new ParseException("Address is empty");
        return address;
    }

    @Override
    protected Long parseRoomsAmount(Document doc) throws ParseException {
        String flatKind = getHeader(doc).split(",")[0];
        if (flatKind.equalsIgnoreCase("Своб. планировка")) {
            return Long.valueOf(-1);
        }
        if (flatKind.equalsIgnoreCase("Квартира-студия")) {
            return Long.valueOf(0);
        }
        if (flatKind.length() == 0) {
            throw new ParseException("Rooms amount element is empty.");
        }
        Matcher roomsAmountInText = ROOMS_AMOUNT_REGEX.matcher(flatKind);
        if (roomsAmountInText.find()) {
            return Long.parseLong(roomsAmountInText.group("roomsAmount"));
        }
        throw new ParseException("Rooms amount is not matched.");
    }

    @Override
    protected Long parseFlatFloor(Document doc) throws ParseException {
        return parseFloorParams(doc, "flatFloor","Flat floor is not matched","Flat floor is empty");
    }

    @Override
    protected Long parseTotalHouseFloors(Document doc) throws ParseException {
        return parseFloorParams(doc,"totalFloors",
                "Total Floors is not matched", "Total floors is not found");
    }

    private Long parseFloorParams(
            Document doc, String groupName, String valueNotMatchedText, String valueNotFoundText)
            throws ParseException {
        String flatFloorInText = getHeader(doc).split(",")[2];
        if (flatFloorInText.isEmpty())
            throw new ParseException(valueNotFoundText);
        Matcher matchedFlatFloor = FLOOR_REGEX.matcher(flatFloorInText);
        if (matchedFlatFloor.find()) {
            return Long.parseLong(matchedFlatFloor.group(groupName));
        }
        throw new ParseException(valueNotMatchedText);

    }
}
