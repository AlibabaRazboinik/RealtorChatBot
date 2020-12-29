package org.example.parser.connector;

import org.example.utils.ResourceLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class RandomUserAgent {

    private final Map<String, List<String>> userAgents = new HashMap<>();
    private final Map<String, Double> userAgentFrequencies = new HashMap<>();

    private static List<String> getAgents(String path) throws IOException {
        List<String> agents = new ArrayList<>();
        try (BufferedReader reader = ResourceLoader.loadBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                agents.add(line.strip());
            }
        }
        return agents;
    }

    private void loadAgents() throws IOException {
        userAgents.put("Chrome", getAgents("user-agents/chrome-agent.txt"));
        userAgents.put("Firefox", getAgents("user-agents/firefox-agent.txt"));
        userAgents.put("Internet Explorer", getAgents("user-agents/explorer-agent.txt"));
        userAgents.put("Safari", getAgents("user-agents/safari-agent.txt"));
        userAgents.put("Opera", getAgents("user-agents/opera-agent.txt"));
    }

    public RandomUserAgent() throws IOException {
        userAgentFrequencies.put("Internet Explorer", 11.8);
        userAgentFrequencies.put("Firefox", 28.2);
        userAgentFrequencies.put("Chrome", 52.9);
        userAgentFrequencies.put("Safari", 3.9);
        userAgentFrequencies.put("Opera", 1.8);

        loadAgents();
    }

    public String getRandomUserAgent() {
        double rand = Math.random() * 100;
        String browser = null;
        double count = 0.0;
        for(Entry<String, Double> freq : userAgentFrequencies.entrySet()) {
            count += freq.getValue();
            if(rand <= count) {
                browser = freq.getKey();
                break;
            }
        }

        if(browser == null) {
            browser = "Chrome";
        }

        List<String> userAgents = this.userAgents.get(browser);
        int agentIndex = (int) Math.floor(Math.random() * userAgents.size());
        return userAgents.get(agentIndex);
    }
}
