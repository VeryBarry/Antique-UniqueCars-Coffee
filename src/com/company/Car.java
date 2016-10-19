package com.company;

/**
 * Created by VeryBarry on 10/19/16.
 */
public class Car {
    int id;
    String make;
    String model;
    int year;
    String color;
    int userId;

    public Car() {
    }

    public Car(int id, String make, String model, int year, String color, int userId) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getUserId() {
        return userId;
    }
}
