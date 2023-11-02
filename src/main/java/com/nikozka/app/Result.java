package com.nikozka.app;

public class Result {
    private final int number;
    private final String street;
    private final String city;
    private final int total;

    public Result(int number, String street, String city, int total) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.total = total;
    }

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getTotal() {
        return total;
    }
}

