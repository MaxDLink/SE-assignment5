package com.example.factory;


public class NormalCarFactory extends SharableCarFactory {
    @Override
    public SharableCar createCar() {
        return new NormalCar();
    }
}
