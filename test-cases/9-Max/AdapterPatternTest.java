package com.example.adapter;


// Test class to demonstrate the Adapter pattern
public class AdapterPatternTest {
    public static void main(String[] args) {
        // Create the adapter
        CarReservationService adapter = new LegacyCarReservationAdapter();


        // Create the client with the adapter
        ReservationClient client = new ReservationClient(adapter);


        // Test Case 1: Simple reservation
        String result1 = client.makeReservation("CUST001", "CAR101");
        System.out.println("Test 1: " + result1);


        // Test Case 2: Different customer and car
        String result2 = client.makeReservation("CUST002", "CAR202");
        System.out.println("Test 2: " + result2);


        // Test Case 3: Edge case with null (for robustness)
        try {
            String result3 = client.makeReservation(null, "CAR303");
            System.out.println("Test 3: " + result3);
        } catch (Exception e) {
            System.out.println("Test 3: Failed as expected - " + e.getMessage());
        }
    }
}
