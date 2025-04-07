package com.example.adapter;


// Adapts the legacy system to the new interface
public class LegacyCarReservationAdapter implements CarReservationService {
    private final LegacyCarReservationSystem legacySystem;


    public LegacyCarReservationAdapter() {
        this.legacySystem = new LegacyCarReservationSystem();
    }


    @Override
    public String reserveCar(String customerId, String carId) {
        return legacySystem.bookVehicle(customerId, carId); // Adapts the call
    }
}
