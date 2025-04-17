package com.example.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryPatternTest {
    
    @Test
    public void testNormalCarFactory() {
        SharableCarFactory normalFactory = new NormalCarFactory();
        CarSharingClient normalClient = new CarSharingClient(normalFactory);
        
        // Test car usage
        String result = normalClient.useCar(5.0);
        assertNotNull(result, "Car usage result should not be null");
        assertTrue(result.contains("Using car"), "Result should contain usage information");
        assertTrue(result.contains("$"), "Result should contain fee information");
        
        // Test car description
        String description = normalFactory.getCarDescription();
        assertNotNull(description, "Car description should not be null");
        assertTrue(description.contains("Normal"), "Description should mention Normal car");
    }

    @Test
    public void testLuxuryBlackCarFactory() {
        SharableCarFactory luxuryFactory = new LuxuryBlackCarFactory();
        CarSharingClient luxuryClient = new CarSharingClient(luxuryFactory);
        
        // Test car usage
        String result = luxuryClient.useCar(5.0);
        assertNotNull(result, "Car usage result should not be null");
        assertTrue(result.contains("Using car"), "Result should contain usage information");
        assertTrue(result.contains("$"), "Result should contain fee information");
        
        // Test car description
        String description = luxuryFactory.getCarDescription();
        assertNotNull(description, "Car description should not be null");
        assertTrue(description.contains("Luxury"), "Description should mention Luxury car");
    }

    @Test
    public void testSUVFactory() {
        SharableCarFactory suvFactory = new SUVFactory();
        CarSharingClient suvClient = new CarSharingClient(suvFactory);
        
        // Test car usage
        String result = suvClient.useCar(5.0);
        assertNotNull(result, "Car usage result should not be null");
        assertTrue(result.contains("Using car"), "Result should contain usage information");
        assertTrue(result.contains("$"), "Result should contain fee information");
        
        // Test car description
        String description = suvFactory.getCarDescription();
        assertNotNull(description, "Car description should not be null");
        assertTrue(description.contains("SUV"), "Description should mention SUV");
    }

    @Test
    public void testWheelchairAccessibleFactory() {
        SharableCarFactory wheelchairFactory = new WheelchairAccessibleFactory();
        CarSharingClient wheelchairClient = new CarSharingClient(wheelchairFactory);
        
        // Test car usage
        String result = wheelchairClient.useCar(5.0);
        assertNotNull(result, "Car usage result should not be null");
        assertTrue(result.contains("Using car"), "Result should contain usage information");
        assertTrue(result.contains("$"), "Result should contain fee information");
        
        // Test car description
        String description = wheelchairFactory.getCarDescription();
        assertNotNull(description, "Car description should not be null");
        assertTrue(description.contains("Wheelchair"), "Description should mention Wheelchair accessibility");
    }
}
