package com.example.factory;


public class SUVFactory extends SharableCarFactory {
    @Override
    public SharableCar createCar() {
        return new SUV();
    }
}

