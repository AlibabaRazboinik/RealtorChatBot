package org.example.core;

public class Flat {
    private Long price;
    private Double square;
    private String city;
    private String area;
    private String type;
    private String address;
    private Long roomsCount;

    public Flat(Long price, Double square, String city, String area,
                String type, String address, Long roomsCount) {
        this.price = price;
        this.square = square;
        this.city = city;
        this.area = area;
        this.type = type;
        this.address = address;
        this.roomsCount = roomsCount;

    }

    public Long getPrice() {
        return this.price;
    }

    public Double getSquare() {
        return this.square;
    }

    public String getCity() {
        return this.city;
    }

    public String getArea() {
        return this.area;
    }

    public String getType() {
        return this.type;
    }

    public String getAddress() {
        return this.address;
    }

    public Long getRoomsCount() {
        return this.roomsCount;
    }

    public  StringBuilder getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------------------------------------\n");
        stringBuilder.append("Город: " + this.city + "\n");
        stringBuilder.append("Цена: " + this.price + "\n");
        stringBuilder.append("Тип: " + this.type + "\n");
        stringBuilder.append("Площадь: " + this.square + "\n");
        stringBuilder.append("Район: " + this.area + "\n");
        stringBuilder.append("Адрес: " + this.address + "\n");
        stringBuilder.append("Число комнат: " + this.roomsCount + "\n");
        stringBuilder.append("---------------------------------------\n");
        return stringBuilder;
    }
}
