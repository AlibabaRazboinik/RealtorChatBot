package org.example.core.searcher;

import java.util.Map;

public class SearchParams {
    private Long maxPrice;
    private String city;
    private Long roomsCount;

    public SearchParams() {
        this.maxPrice = null;
        this.roomsCount = null;
        this.city = null;
    }

    public SearchParams(Map<String, Object> optionsValues) {
        Object object;

        object = optionsValues.get(Option.MAX_PRICE.getName());
        maxPrice = (Long) object;

        object = optionsValues.get(Option.CITY.getName());
        city = (String) object;

        object = optionsValues.get(Option.ROOMS_COUNT.getName());
        roomsCount = (Long) object;
    }

    public Long getMaxPrice() {
        return this.maxPrice;
    }

    public String getCity() {
        return this.city;
    }

    public Long getRoomsCount() {
        return this.roomsCount;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRoomsCount(Long roomsCount) {
        this.roomsCount = roomsCount;
    }

}