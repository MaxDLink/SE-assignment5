package com.example.factory;


public class LuxuryBlackCarFactory extends SharableCarFactory {
    @Override
    public SharableCar createCar() {
        return new LuxuryBlackCar();
    }

    @Override
    public String getCarType() {
        return "Luxury";
    }
}
