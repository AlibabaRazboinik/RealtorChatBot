package org.example.parsing;

import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.example.parser.ParseException;
import org.example.parser.connector.Connector;
import org.example.parser.flat.AvitoFlatParser;
import org.example.parser.flat.CianFlatParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FlatParamTest extends TestCase {

    protected AvitoFlatParser avitoParser;
    protected CianFlatParser cianParser;

    private static InputStream getResourceStream(String path) throws IOException {
        ClassLoader loader = FeedTest.class.getClassLoader();
        InputStream stream = loader.getResourceAsStream(path);
        if (stream == null) {
            throw new FileNotFoundException("File \"" + path + "\" not found");
        }

        return stream;
    }

    private static Document getHtmlDocument(String platformName) throws IOException {
        try (InputStream stream = getResourceStream("flat/" + platformName + ".html")) {
            String htmlLine = IOUtils.toString(stream, StandardCharsets.UTF_8);
            return Jsoup.parse(htmlLine);
        }
    }

    @Override
    protected void setUp() {
        Connector connector = new EmptyConnector();
        avitoParser = new AvitoFlatParser(connector);
        cianParser = new CianFlatParser(connector);
    }

    public void testAvitoAddress() throws IOException, ParseException {
        String testingAvitoAddress = avitoParser.parseAddress(getHtmlDocument("avito1RoomFlat"));
        String expectedAvitoAddress = "Свердловская область, Екатеринбург, ул. Викулова, 63к2";

        Assert.assertEquals(expectedAvitoAddress, testingAvitoAddress);
    }

    public void testCianAddress() throws IOException, ParseException {
        String testingCianAddress = cianParser.parseAddress(getHtmlDocument("cian1RoomFlat"));
        String expectedCianAddress =
                "Свердловская область, Екатеринбург, р-н Железнодорожный, мкр. Завокзальный, " +
                        "Космонавтов 11 жилой комплекс, к4.1";
        Assert.assertEquals(testingCianAddress, expectedCianAddress);
    }

    public void testAvitoRoomsAmountInStudio() throws IOException, ParseException {
        Long testingCianStudioFlat =
                cianParser.parseRoomsAmount(getHtmlDocument("cianStudiaFlat"));
        Long expectedCianStudioFlat = 0L;

        Assert.assertSame(testingCianStudioFlat, expectedCianStudioFlat);
    }

    public void testCianRoomsAmountInStudio() throws IOException, ParseException {
        Long testingCianStudioFlat =
                cianParser.parseRoomsAmount(getHtmlDocument("cianStudiaFlat"));
        Long expectedCianStudioFlat = 0L;

        Assert.assertSame(testingCianStudioFlat, expectedCianStudioFlat);
    }

    public void testAvitoRoomsAmountInFreeSpace() throws IOException, ParseException {
        Long testingAvitoFreeSpaceFlat =
                avitoParser.parseRoomsAmount(getHtmlDocument("avitoFreeSpaceFlat"));
        Long expectedAvitoFreeSpaceFlat = -1L;

        Assert.assertSame(testingAvitoFreeSpaceFlat, expectedAvitoFreeSpaceFlat);
    }

    public void testCianRoomsAmountInFreeSpace() throws IOException, ParseException {
        Long testingCianFreeSpaceFlat =
                cianParser.parseRoomsAmount(getHtmlDocument("cianFreeSpaceFlat"));
        Long expectedCianFreeSpaceFlat = -1L;

        Assert.assertSame(testingCianFreeSpaceFlat, expectedCianFreeSpaceFlat);
    }

    public void testAvitoRoomsAmount() throws IOException, ParseException {
        Long testingAvitoRoomFlat =
                avitoParser.parseRoomsAmount(getHtmlDocument("avito1RoomFlat"));
        Long expectedAvitoRoomFlat = 1L;

        Assert.assertSame(testingAvitoRoomFlat, expectedAvitoRoomFlat);
    }

    public void testCianRoomsAmount() throws IOException, ParseException {
        Long testingCianRoomFlat =
                cianParser.parseRoomsAmount(getHtmlDocument("cian1RoomFlat"));
        Long expectedCianRoomFlat = 1L;

        Assert.assertSame(testingCianRoomFlat, expectedCianRoomFlat);
    }

    public void testAvitoSquareParam() throws IOException, ParseException{
        Double testingAvitoSquare =
                avitoParser.parseSquare(getHtmlDocument("avito2RoomFlat"));
        Double expectedAvitoSquare = 45.2;

        Assert.assertEquals(testingAvitoSquare, expectedAvitoSquare);
    }

    public void testCianSquareParam() throws IOException, ParseException{
        Double testingCianSquare =
                cianParser.parseSquare(getHtmlDocument("cian2RoomFlat"));
        Double expectedCianSquare = 48.3;

        Assert.assertEquals(testingCianSquare, expectedCianSquare);
    }

    public void testAvitoFloorParam() throws IOException, ParseException{
        Long testingAvitoFloor =
                avitoParser.parseFlatFloor(getHtmlDocument("avito2RoomFlat"));
        Long expectedAvitoFloor = 3L;

        Assert.assertEquals(testingAvitoFloor, expectedAvitoFloor);
    }

    public void testCianFloorParam() throws IOException, ParseException{
        Long testingCianFloor =
                cianParser.parseFlatFloor(getHtmlDocument("cian2RoomFlat"));
        Long expectedCianFloor = 8L;

        Assert.assertEquals(testingCianFloor, expectedCianFloor);
    }

    public void testAvitoTotalFloorsParam() throws IOException, ParseException{
        Long testingAvitoTotalFloors =
                avitoParser.parseTotalHouseFloors(getHtmlDocument("avito2RoomFlat"));
        Long expectedAvitoTotalFloors = 5L;

        Assert.assertEquals(testingAvitoTotalFloors, expectedAvitoTotalFloors);
    }

    public void testCianTotalFloorsParam() throws IOException, ParseException{
        Long testingCianTotalFloors =
                cianParser.parseTotalHouseFloors(getHtmlDocument("cian2RoomFlat"));
        Long expectedCianTotalFloors = 12L;

        Assert.assertEquals(testingCianTotalFloors, expectedCianTotalFloors);
    }

    public void testAvitoPriceParam() throws IOException, ParseException{
        Long testingAvitoPrice =
                avitoParser.parsePrice(getHtmlDocument("avito2RoomFlat"));
        Long expectedAvitoPrice = 2650000L;

        Assert.assertEquals(testingAvitoPrice, expectedAvitoPrice);
    }

    public void testCianPriceParam() throws IOException{
        Long testingCianPrice =
                cianParser.parsePrice(getHtmlDocument("cian2RoomFlat"));
        Long expectedCianPrice = 3650000L;

        Assert.assertEquals(testingCianPrice, expectedCianPrice);
    }
}