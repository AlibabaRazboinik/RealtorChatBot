package org.example.core.graph.scenario;

import org.example.core.Request;
import org.example.core.Response;
import org.example.core.graph.step.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinearScenario implements Scenario {
    private final List<Step> steps;
    private final boolean isLoopedForever;
    private final Map<String, Object> options;
    private int curStepIndex;
    private boolean isFinished;

    public LinearScenario(List<Step> steps, boolean isLoopedForever) {
        if (steps.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("empty steps container");
        }

        this.steps = steps;
        this.isLoopedForever = isLoopedForever;
        curStepIndex = 0;
        isFinished = false;
        options = new HashMap<>();
    }

    @Override
    public Response respond(Request request) {
        Step currentStep = steps.get(curStepIndex);
        if (currentStep == null) {
            throw new NullPointerException("currentStep is null");
        }

        Response response = currentStep.respond(request, options);

        if (curStepIndex == steps.size() - 1) {
            if (isLoopedForever) {
                isFinished = false;
                curStepIndex = 0;
            } else {
                isFinished = true;
            }
        }

        if (response.isSuccessful()) {
            curStepIndex += 1;
        }

        return response;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
