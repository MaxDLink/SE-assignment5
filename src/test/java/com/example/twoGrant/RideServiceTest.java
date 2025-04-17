package com.example.twoGrant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RideServiceTest {
    
    @Test
    public void testCarPoolPricingStrategy() {
        PricingStrategy strategy = new CarPoolPricingStrategy();
        // Test with 10km and 20 minutes
        double price = strategy.calculatePrice(10, 20);
        // 2.0 + (10 * 0.5) + (20 * 0.1) = 2.0 + 5.0 + 2.0 = 9.0
        assertEquals(9.0, price, 0.001);
    }

    @Test
    public void testCarXPricingStrategy() {
        PricingStrategy strategy = new CarXPricingStrategy();
        // Test with 10km and 20 minutes
        double price = strategy.calculatePrice(10, 20);
        // 5.0 + (10 * 1.0) + (20 * 0.2) = 5.0 + 10.0 + 4.0 = 19.0
        assertEquals(19.0, price, 0.001);
    }

    @Test
    public void testCarBlackPricingStrategy() {
        PricingStrategy strategy = new CarBlackPricingStrategy();
        // Test with 10km and 20 minutes
        double price = strategy.calculatePrice(10, 20);
        // 10.0 + (10 * 2.0) + (20 * 0.5) = 10.0 + 20.0 + 10.0 = 40.0
        assertEquals(40.0, price, 0.001);
    }

    @Test
    public void testCarPoolService() {
        RideService service = new CarPoolService();
        assertEquals("carPOOL", service.getServiceName());
        double fare = service.calculateFare(10, 20);
        assertEquals(9.0, fare, 0.001);
    }

    @Test
    public void testCarXService() {
        RideService service = new CarXService();
        assertEquals("carX", service.getServiceName());
        double fare = service.calculateFare(10, 20);
        assertEquals(19.0, fare, 0.001);
    }

    @Test
    public void testCarBlackService() {
        RideService service = new CarBlackService();
        assertEquals("carBlack", service.getServiceName());
        double fare = service.calculateFare(10, 20);
        assertEquals(40.0, fare, 0.001);
    }

    @Test
    public void testServiceFactory() {
        // Test creating CarPool service
        RideService pool = ServiceFactory.createService(ServiceType.CARPOOL);
        assertTrue(pool instanceof CarPoolService);
        assertEquals("carPOOL", pool.getServiceName());

        // Test creating CarX service
        RideService x = ServiceFactory.createService(ServiceType.CARX);
        assertTrue(x instanceof CarXService);
        assertEquals("carX", x.getServiceName());

        // Test creating CarBlack service
        RideService black = ServiceFactory.createService(ServiceType.CARBLACK);
        assertTrue(black instanceof CarBlackService);
        assertEquals("carBlack", black.getServiceName());
    }

    @Test
    public void testServiceFactoryWithNullType() {
        assertThrows(IllegalArgumentException.class, () -> {
            ServiceFactory.createService(null);
        });
    }

    @Test
    public void testZeroDistanceAndDuration() {
        PricingStrategy poolStrategy = new CarPoolPricingStrategy();
        PricingStrategy xStrategy = new CarXPricingStrategy();
        PricingStrategy blackStrategy = new CarBlackPricingStrategy();

        // Test with zero distance and duration
        assertEquals(2.0, poolStrategy.calculatePrice(0, 0), 0.001);
        assertEquals(5.0, xStrategy.calculatePrice(0, 0), 0.001);
        assertEquals(10.0, blackStrategy.calculatePrice(0, 0), 0.001);
    }

    @Test
    public void testNegativeValues() {
        PricingStrategy strategy = new CarPoolPricingStrategy();
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(-10, 20);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            strategy.calculatePrice(10, -20);
        });
    }
} 