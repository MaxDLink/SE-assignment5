package com.example.factory;

public abstract class SharableCarFactory {
    public abstract SharableCar createCar();

    public String getCarDescription() {
        SharableCar car = createCar();
        return "Car Features: " + car.getFeatures() + ", Fee for 10km: $" + car.calculateFee(10.0);
    }
}
