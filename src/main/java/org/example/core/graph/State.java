package org.example.core.graph;

public class State {
    private final String scenarioName;

    public State(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public String getScenarioName() {
        return this.scenarioName;
    }
}
