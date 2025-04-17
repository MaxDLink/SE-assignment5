package com.example.factory;


public class LuxuryBlackCar implements SharableCar {
    @Override
    public String getFeatures() {
        return "Leather seats, premium service";
    }


    @Override
    public double calculateFee(double distance) {
        return distance * 3.0; // $3.0 per km
    }
}

