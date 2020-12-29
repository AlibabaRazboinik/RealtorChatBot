package org.example.parser.pipeline;

import com.fasterxml.jackson.annotation.JsonSetter;

public class CityUrl {
    private String cityName;
    private String feedUrl;

    @JsonSetter("cityName")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @JsonSetter("feedUrl")
    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getCityName() {
        return cityName;
    }

    public String getFeedUrl() {
        return feedUrl;
    }
}
