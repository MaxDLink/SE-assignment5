package com.example.adapter;


// Adapts the legacy system to the new interface
public class LegacyCarReservationAdapter implements CarReservationService {
    private final LegacyCarReservationSystem legacySystem;


    public LegacyCarReservationAdapter() {
        this.legacySystem = new LegacyCarReservationSystem();
    }


    @Override
    public String reserveCar(String customerId, String carId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        if (carId == null || carId.trim().isEmpty()) {
            throw new IllegalArgumentException("Car ID cannot be null or empty");
        }
        return legacySystem.bookVehicle(customerId, carId); // Adapts the call
    }
}
