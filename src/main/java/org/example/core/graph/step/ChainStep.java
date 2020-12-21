package org.example.core.graph.step;

import org.example.core.Request;
import org.example.core.Response;

import java.util.List;
import java.util.Map;

public class ChainStep implements Step {
    private final List<Step> steps;
    private final String onFailText;

    public ChainStep(List<Step> steps, String onFailText) {
        this.steps = steps;
        this.onFailText = onFailText;
    }

    @Override
    public Response respond(Request request, Map<String, Object> options) {
        for (Step step : steps) {
            Response response = step.respond(request, options);
            if (response != null) {
                return response;
            }
        }

        return new Response(onFailText, false);
    }
}
