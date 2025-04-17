package com.example.adapter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test class to demonstrate the Adapter pattern
public class AdapterPatternTest {
    
    @Test
    public void testSimpleReservation() {
        // Create the adapter and client
        CarReservationService adapter = new LegacyCarReservationAdapter();
        ReservationClient client = new ReservationClient(adapter);

        // Test simple reservation
        String result = client.makeReservation("CUST001", "CAR101");
        assertNotNull(result, "Reservation result should not be null");
        assertTrue(result.contains("CUST001"), "Result should contain customer ID");
        assertTrue(result.contains("CAR101"), "Result should contain car ID");
    }

    @Test
    public void testDifferentCustomerAndCar() {
        // Create the adapter and client
        CarReservationService adapter = new LegacyCarReservationAdapter();
        ReservationClient client = new ReservationClient(adapter);

        // Test with different customer and car
        String result = client.makeReservation("CUST002", "CAR202");
        assertNotNull(result, "Reservation result should not be null");
        assertTrue(result.contains("CUST002"), "Result should contain new customer ID");
        assertTrue(result.contains("CAR202"), "Result should contain new car ID");
    }

    @Test
    public void testNullCustomerId() {
        // Create the adapter and client
        CarReservationService adapter = new LegacyCarReservationAdapter();
        ReservationClient client = new ReservationClient(adapter);

        // Test with null customer ID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            client.makeReservation(null, "CAR303");
        });
        
        assertTrue(exception.getMessage().contains("Customer ID"), 
                  "Exception message should mention customer ID");
    }

    @Test
    public void testNullCarId() {
        // Create the adapter and client
        CarReservationService adapter = new LegacyCarReservationAdapter();
        ReservationClient client = new ReservationClient(adapter);

        // Test with null car ID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            client.makeReservation("CUST004", null);
        });
        
        assertTrue(exception.getMessage().contains("Car ID"), 
                  "Exception message should mention car ID");
    }

    @Test
    public void testEmptyCustomerId() {
        // Create the adapter and client
        CarReservationService adapter = new LegacyCarReservationAdapter();
        ReservationClient client = new ReservationClient(adapter);

        // Test with empty customer ID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            client.makeReservation("", "CAR505");
        });
        
        assertTrue(exception.getMessage().contains("Customer ID"), 
                  "Exception message should mention customer ID");
    }
}
