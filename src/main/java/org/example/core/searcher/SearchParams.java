package org.example.core.searcher;

import java.util.Map;

public class SearchParams {
    private final Long maxPrice;
    private final String city;
    private final Long roomsAmount;

    public SearchParams() {
        this.maxPrice = null;
        this.roomsAmount = null;
        this.city = null;
    }

    public SearchParams(Map<String, Object> optionsValues) {
        Object object;

        object = optionsValues.get(Option.MAX_PRICE.getName());
        maxPrice = (Long) object;

        object = optionsValues.get(Option.CITY.getName());
        city = (String) object;

        object = optionsValues.get(Option.ROOMS_COUNT.getName());
        roomsAmount = (Long) object;
    }

    public Long getMaxPrice() {
        return this.maxPrice;
    }

    public String getCity() {
        return this.city;
    }

    public Long getRoomsAmount() {
        return this.roomsAmount;
    }
}