package com.example.adapter;


// The client that uses the reservation service
public class ReservationClient {
    private final CarReservationService reservationService;


    public ReservationClient(CarReservationService reservationService) {
        this.reservationService = reservationService;
    }


    public String makeReservation(String customerId, String carId) {
        return reservationService.reserveCar(customerId, carId);
    }
}
