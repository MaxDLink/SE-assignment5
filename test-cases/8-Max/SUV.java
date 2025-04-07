package com.example.factory;

public class SUV implements SharableCar {
    @Override
    public String getFeatures() {
        return "Spacious, off-road capable";
    }

    @Override
    public double calculateFee(double distance) {
        return distance * 2.0; // $2.0 per km
    }
}
