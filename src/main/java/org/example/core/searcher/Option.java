package org.example.core.searcher;

public enum Option {
    MAX_PRICE("max_price"),
    ROOMS_COUNT("rooms_amount"),
    CITY("city");

    private final String name;

    Option(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
