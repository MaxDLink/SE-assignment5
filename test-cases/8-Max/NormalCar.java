package com.example.factory;

public class NormalCar implements SharableCar {
    @Override
    public String getFeatures() {
        return "Basic seating, standard comfort";
    }

    @Override
    public double calculateFee(double distance) {
        return distance * 1.5; // $1.5 per km
    }
}
