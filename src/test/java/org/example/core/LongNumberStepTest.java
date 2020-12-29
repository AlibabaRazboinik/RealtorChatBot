package org.example.core;

import junit.framework.TestCase;
import org.example.core.graph.step.LongNumberStep;

import java.util.HashMap;

public class LongNumberStepTest extends TestCase {
    protected LongNumberStep testingPriceStep, testingPriceStepRespondNull;
    protected Request priceRequestCorrect;
    protected Request priceRequestIncorrect;
    protected HashMap<String, Object> options;

    @Override
    protected void setUp() {
        testingPriceStep = new LongNumberStep("SUCCESS", "SUCKSASS", "PRICE");
        testingPriceStepRespondNull = new LongNumberStep("SUCKSASS", "PRICE");
        priceRequestCorrect = new Request("1", "1");
        priceRequestIncorrect = new Request("Один?", "user");

        options = new HashMap<String, Object>();
        options.put("My string", new Object());
    }

    public void testSuccessRespond () {
        Response answer = testingPriceStep.respond(priceRequestCorrect, options);
        Response answerNull = testingPriceStepRespondNull.respond(priceRequestCorrect, options);

        assertTrue(answer.isSuccessful());
        assertEquals(answer.getMessageText(), "SUCCESS");

        assertNull(answerNull);
    }

    public void testFailRespond() {
        Response answer = testingPriceStep.respond(priceRequestIncorrect, options);
        Response answerNull = testingPriceStepRespondNull.respond(priceRequestIncorrect, options);

        assertFalse(answer.isSuccessful());
        assertEquals(answer.getMessageText(), "SUCKSASS");

        assertFalse(answerNull.isSuccessful());
        assertEquals(answerNull.getMessageText(), "SUCKSASS");
    }
}