package com.example.adapter;

// The legacy system with its own method
public class LegacyCarReservationSystem {
    public String bookVehicle(String userCode, String vehicleCode) {
        return String.format("Booking confirmed for user %s and vehicle %s", userCode, vehicleCode);
    }
}
