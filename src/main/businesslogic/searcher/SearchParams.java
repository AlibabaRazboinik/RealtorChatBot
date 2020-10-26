package main.businesslogic.searcher;

public class SearchParams {
    private Long maxPrice;
    private String city;
    private Integer roomsCount;

    public SearchParams() {
        this.maxPrice = null;
        this.roomsCount = null;
        this.city = null;
    }

    public Long getMaxPrice() {
        return this.maxPrice;
    }

    public String getCity() {
        return this.city;
    }

    public Integer getRoomsCount() {
        return this.roomsCount;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRoomsCount(Integer roomsCount) {
        this.roomsCount = roomsCount;
    }

}