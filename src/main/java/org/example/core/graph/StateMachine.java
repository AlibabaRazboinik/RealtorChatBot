package org.example.core.graph;

import org.example.core.Request;
import org.example.core.Response;
import org.example.core.graph.scenario.Scenario;

import java.util.HashMap;
import java.util.Map;

public class StateMachine {
    private final Map<String, Scenario> scenarios;
    private final Map<String, String> nextScenario;
    private final Map<String, State> currentStates;
    private final State startState;
    private final Response startResponse;

    public StateMachine(
            Map<String, Scenario> scenarios,
            Map<String, String> nextScenario,
            State startState,
            Response startResponse
    ) {
        this.scenarios = scenarios;
        this.nextScenario = nextScenario;
        this.currentStates = new HashMap<>();
        this.startState = startState;
        this.startResponse = startResponse;
    }

    public Response getResponse(Request request) {
        String userId = request.getUserId();
        if (!this.currentStates.containsKey(userId)) {
            this.currentStates.put(userId, this.startState);
            return startResponse;
        }

        State currentState = this.currentStates.get(userId);
        Scenario currentScenario = this.scenarios.get(currentState.getScenarioName());
        if (currentScenario == null) {
            throw new NullPointerException("currentScenario is null");
        }

        Response response = currentScenario.respond(request);

        if (currentScenario.isFinished()) {
            String currentScenarioName = currentState.getScenarioName();
            String nextScenarioName = this.nextScenario.get(currentScenarioName);
            this.currentStates.put(userId, new State(nextScenarioName));
        }

        return response;
    }
}
