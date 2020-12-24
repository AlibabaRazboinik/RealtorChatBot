package org.example;

import junit.framework.TestCase;
import org.example.core.Request;
import org.example.core.Response;
import org.example.core.graph.scenario.LinearScenario;
import org.example.core.graph.step.LongNumberStep;
import org.example.core.graph.step.Step;

import java.util.ArrayList;
import java.util.List;

public class LinearScenarioTest extends TestCase {

    protected LinearScenario testingScenario;
    protected Request request;
    protected Response response;


    @Override
    protected void setUp() {
        LongNumberStep testingStep1 = new LongNumberStep("SUCCESS", "SUCKSASS", "max_price");
        LongNumberStep testingStep2 = new LongNumberStep("SUCCESS", "SUCKSASS", "city");

        List<Step> steps = new ArrayList<>();
        steps.add(testingStep1);
        steps.add(testingStep2);

        testingScenario = new LinearScenario(steps, false);
        request = new Request("1", "1");
    }

    public void testScenarioRespond() {
        response = testingScenario.respond(request);
        assertTrue(response.isSuccessful());
        assertFalse(testingScenario.isFinished());

        response = testingScenario.respond(request);
        assertTrue(response.isSuccessful());
        assertTrue(testingScenario.isFinished());

    }
}
