package com.example.factory;


public class CarSharingClient {
    private final SharableCarFactory factory;


    public CarSharingClient(SharableCarFactory factory) {
        this.factory = factory;
    }

    public String useCar(double distance) {
        SharableCar car = factory.createCar();
        return "Using car: " + car.getFeatures() + ", Fee for " + distance + "km: $" + car.calculateFee(distance);
    }
}

