package org.example.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Flat {
    private Long price;
    private Double square;
    private String address;
    private Long roomsAmount;
    private Long flatFloor;
    private Long totalHouseFloors;
    private String city;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((price == null) ? 0 : price.hashCode())
                + ((square == null) ? 0 : square.hashCode())
                + ((address == null) ? 0 : address.hashCode())
                + ((roomsAmount == null) ? 0 : roomsAmount.hashCode())
                + ((flatFloor == null) ? 0 : flatFloor.hashCode())
                + ((totalHouseFloors == null) ? 0 : totalHouseFloors.hashCode())
                + ((city == null) ? 0 : city.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Flat other = (Flat) obj;

        if ((price == null && other.price != null) ||
                (square == null && other.square != null) ||
                (address == null && other.address != null) ||
                (roomsAmount == null && other.roomsAmount != null) ||
                (flatFloor == null && other.flatFloor != null) ||
                (totalHouseFloors == null && other.totalHouseFloors != null) ||
                (city == null && other.city != null)
        ) {
            return false;
        }

        return (price == null || price.equals(other.price)) &&
                (square == null || square.equals(other.square)) &&
                (roomsAmount == null || roomsAmount.equals(other.roomsAmount)) &&
                (flatFloor == null || flatFloor.equals(other.flatFloor)) &&
                (totalHouseFloors == null || totalHouseFloors.equals(other.totalHouseFloors)) &&
                (address == null || address.equals(other.address)) &&
                (city == null || city.equals(other.city));
    }

    @JsonSetter("price")
    public void setPrice(Long price) {
        this.price = price;
    }

    @JsonSetter("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonSetter("square")
    public void setSquare(Double square) {
        this.square = square;
    }

    @JsonSetter("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonSetter("roomsAmount")
    public void setRoomsAmount(Long roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    @JsonSetter("flatFloor")
    public void setFlatFloor(Long flatFloor) {
        this.flatFloor = flatFloor;
    }

    @JsonSetter("totalHouseFloors")
    public void setTotalHouseFloors(Long totalHouseFloors) {
        this.totalHouseFloors = totalHouseFloors;
    }

    public Long getPrice() {
        return price;
    }

    public String getCity() {
        return city;
    }

    public Double getSquare() {
        return square;
    }

    public String getAddress() {
        return address;
    }

    public Long getFlatFloor() {
        return flatFloor;
    }

    public Long getTotalHouseFloors() {
        return totalHouseFloors;
    }

    public Long getRoomsAmount() {
        return roomsAmount;
    }

    @JsonIgnore
    public boolean isStudio() {
        return roomsAmount == 0;
    }

    @JsonIgnore
    public boolean isOpenPlanApartment() {
        return roomsAmount == -1;
    }
}
