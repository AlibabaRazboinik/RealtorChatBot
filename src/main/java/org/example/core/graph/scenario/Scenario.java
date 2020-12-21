package org.example.core.graph.scenario;

import org.example.core.Request;
import org.example.core.Response;
import org.example.core.graph.State;

public interface Scenario {

    public Response respond(Request request);
    public boolean isFinished();
}
