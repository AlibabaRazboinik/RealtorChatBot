package org.example.core.graph.step;

import org.example.core.Request;
import org.example.core.Response;

import java.util.Map;

public interface Step {

    public Response respond(Request request, Map<String, Object> options);

}
