package com.example.factory;


public class WheelchairAccessibleTransport implements SharableCar {
    @Override
    public String getFeatures() {
        return "Wheelchair ramp, extra space";
    }


    @Override
    public double calculateFee(double distance) {
        return distance * 2.5; // $2.5 per km
    }
}

