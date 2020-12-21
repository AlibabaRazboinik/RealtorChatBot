package org.example.parser.flat;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.example.parser.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CianFlatParser extends BaseFlatParser {
    private final static Pattern SQUARE_REGEX = Pattern.compile("[1-9][0-9]*(,[0-9]+)?");
    private final static Pattern FLOOR_REGEX = Pattern.compile("(?<flatFloor>\\d+)\\s+из\\s+(?<totalFloors>\\d+)");
    private final static Pattern ROOMS_AMOUNT_REGEX = Pattern.compile("(?<roomsAmount>^\\d+)-");


    @Override
    protected Long parsePrice(Document doc) {
        return Long.parseLong(doc.select("span[itemprop=\"price\"]").text()
                .replace(" ", "")
                .replace("₽", ""));
    }

    @Override
    protected String parseAddress(Document doc) throws ParseException {
        Elements geo = doc.select("div[data-name=\"Geo\"]");
        String fullAddress = geo.select("span[itemprop=\"name\"]").attr("content");
        if (fullAddress.equals(""))
            throw new ParseException("Address is empty");
        String[] splitAddress = fullAddress.split(",");
        if (splitAddress.length < 6)
            throw new ParseException("Address is not matched");
        return splitAddress[splitAddress.length - 2] +
                splitAddress[splitAddress.length - 1];
    }

    @Override

    protected Long parseRoomsAmount(Document doc) throws ParseException {
        String flatKind = doc.select("h1").text().split(",")[0];
        if (flatKind.split(",")[0].equalsIgnoreCase("Студия")) {
            return Long.valueOf(0);
        }
        if (flatKind.contains("свободной планировки")) {
            return Long.valueOf(-1);
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

    private Matcher makeDescriptionMatcher(
            Document doc, String titleText, Pattern regex, String valueNotMatchedText, String elementNotFoundText
    ) throws ParseException {
        Element description = doc.select("div#description").get(0);
        Element infoBlock = description.select("div[class~=.*--info-block--.*]").get(0);
        Elements infoElements = infoBlock.select("div[class~=.*--info--.*]");

        for (Element element : infoElements) {
            Element title = element.select("div[class~=.*--info-title--.*]").get(0);
            if (!title.text().equalsIgnoreCase(titleText)) {
                continue;
            }

            Element value = element.select("div[class~=.*--info-value--.*]").get(0);

            Matcher matcher = regex.matcher(value.text());
            if (matcher.find()) {
                return matcher;
            }

            throw new ParseException(valueNotMatchedText);
        }
        throw new ParseException(elementNotFoundText);
    }

    @Override
    protected Double parseSquare(Document doc) throws ParseException {
        String titleText = "Общая";
        String elementNotFoundText = "Square element is not found";
        String valueNotMatchedText = "Square value is not matched";

        Matcher squareMatcher = makeDescriptionMatcher(doc, titleText, SQUARE_REGEX, valueNotMatchedText, elementNotFoundText);
        String squareInText = squareMatcher.group().replace(',', '.');

        return Double.parseDouble(squareInText);
    }

    @Override
    protected Long parseFlatFloor(Document doc) throws ParseException {
        String titleText = "Этаж";
        String elementNotFoundText = "Flat floor element is not found";
        String valueNotMatchedText = "Flat floor value is not matched";

        Matcher flatFloorMatcher = makeDescriptionMatcher(doc, titleText, FLOOR_REGEX, valueNotMatchedText, elementNotFoundText);
        String flatFloorInText = flatFloorMatcher.group("flatFloor");

        return Long.parseLong(flatFloorInText);
    }

    @Override
    protected Long parseTotalHouseFloors(Document doc) throws ParseException {
        String titleText = "Этаж";
        String elementNotFoundText = "Total house floors element is not found";
        String valueNotMatchedText = "Total house floors value is not matched";

        Matcher totalFloorsMatcher = makeDescriptionMatcher(doc, titleText, FLOOR_REGEX, valueNotMatchedText, elementNotFoundText);
        String totalFloorsInText = totalFloorsMatcher.group("totalFloors");

        return Long.parseLong(totalFloorsInText);
    }
}
