package org.example;


import junit.framework.TestCase;
import org.example.core.Flat;

import static org.junit.Assert.assertNotEquals;

public class FlatTest extends TestCase {
    protected Flat testingFlat, copyFlat, comparableFlat, nullFlat;
    protected Integer dumbObject;

    @Override
    protected void setUp() {
        testingFlat = new Flat();
        testingFlat.setPrice(100L);
        testingFlat.setSquare(45.5);
        testingFlat.setAddress("Серова, 35");
        testingFlat.setRoomsAmount(4L);
        testingFlat.setFlatFloor(5L);
        testingFlat.setTotalHouseFloors(9L);
        testingFlat.setCity("Екатеринбург");

        comparableFlat = new Flat();
        comparableFlat.setPrice(100L);
        comparableFlat.setSquare(45.5);
        comparableFlat.setAddress("Серова, 35");
        comparableFlat.setRoomsAmount(4L);
        comparableFlat.setFlatFloor(5L);
        comparableFlat.setTotalHouseFloors(9L);
        comparableFlat.setCity("Екатеринбург");

        copyFlat = testingFlat;
    }

    public void testHash() {
        assertEquals(testingFlat.hashCode(), comparableFlat.hashCode());
    }

    public void testEquals() {
        assertEquals(testingFlat, comparableFlat);
        assertEquals(comparableFlat, testingFlat);
        assertEquals(testingFlat, copyFlat);

        assertNotEquals(testingFlat, nullFlat);
        assertNotEquals(testingFlat, dumbObject);


    }
}
