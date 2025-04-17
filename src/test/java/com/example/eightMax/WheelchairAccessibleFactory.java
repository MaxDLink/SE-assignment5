package com.example.factory;


public class WheelchairAccessibleFactory extends SharableCarFactory {
    @Override
    public SharableCar createCar() {
        return new WheelchairAccessibleTransport();
    }

    @Override
    public String getCarType() {
        return "Wheelchair";
    }
}

