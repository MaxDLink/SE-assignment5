// Context: FareCalculator that delegates to a PricingStrategy
package com.example.fiveRahim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Context: FareCalculator that delegates to a PricingStrategy
class FareCalculator {
    private PricingStrategy pricingStrategy;

    // Constructor to set an initial strategy
    FareCalculator(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    // Setter to change strategy dynamically at runtime
    void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    // Calculate fare by delegating to the current strategy
    double calculateFare(RideRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("RideRequest cannot be null");
        }
        if (pricingStrategy == null) {
            throw new IllegalStateException("PricingStrategy not set");
        }
        return pricingStrategy.calculatePrice(request);
    }
}

// Strategy interface defining the pricing method
interface PricingStrategy {
    double calculatePrice(RideRequest request);
}

// Concrete strategy for normal pricing
class NormalPricing implements PricingStrategy {
    @Override
    public double calculatePrice(RideRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("RideRequest cannot be null");
        }
        // Basic fare calculation: base fare + distance * rate (e.g., 1.0)
        return request.getBaseFare() + (request.getDistance() * 1.0);
    }
}

// Concrete strategy for surge pricing (increases fare when demand > supply)
class SurgePricing implements PricingStrategy {
    @Override
    public double calculatePrice(RideRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("RideRequest cannot be null");
        }
        double multiplier = (request.getDemandFactor() > request.getSupplyFactor()) ? 1.5 : 1.0;
        return (request.getBaseFare() + request.getDistance()) * multiplier;
    }
}

// Data class representing the details of a ride request
class RideRequest {
    private double distance;
    private double baseFare;
    private double demandFactor;
    private double supplyFactor;

    RideRequest(double distance, double baseFare, double demandFactor, double supplyFactor) {
        if (distance < 0 || baseFare < 0 || demandFactor < 0 || supplyFactor < 0) {
            throw new IllegalArgumentException("All parameters must be non-negative");
        }
        this.distance = distance;
        this.baseFare = baseFare;
        this.demandFactor = demandFactor;
        this.supplyFactor = supplyFactor;
    }

    double getDistance() {
        return distance;
    }

    double getBaseFare() {
        return baseFare;
    }

    double getDemandFactor() {
        return demandFactor;
    }

    double getSupplyFactor() {
        return supplyFactor;
    }
}

// Test class for the Strategy Pattern implementation
class StrategyPatternTest {
    @Test
    void testNormalPricing() {
        RideRequest request = new RideRequest(10.0, 5.0, 1.0, 1.0);
        PricingStrategy strategy = new NormalPricing();
        FareCalculator calculator = new FareCalculator(strategy);

        // Expected: baseFare + (distance * 1.0) = 5.0 + (10.0 * 1.0) = 15.0
        assertEquals(15.0, calculator.calculateFare(request), 0.001);
    }

    @Test
    void testSurgePricingWithHighDemand() {
        RideRequest request = new RideRequest(10.0, 5.0, 2.0, 1.0);
        PricingStrategy strategy = new SurgePricing();
        FareCalculator calculator = new FareCalculator(strategy);

        // Expected: (baseFare + distance) * 1.5 = (5.0 + 10.0) * 1.5 = 22.5
        assertEquals(22.5, calculator.calculateFare(request), 0.001);
    }

    @Test
    void testSurgePricingWithLowDemand() {
        RideRequest request = new RideRequest(10.0, 5.0, 1.0, 2.0);
        PricingStrategy strategy = new SurgePricing();
        FareCalculator calculator = new FareCalculator(strategy);

        // Expected: (baseFare + distance) * 1.0 = (5.0 + 10.0) * 1.0 = 15.0
        assertEquals(15.0, calculator.calculateFare(request), 0.001);
    }

    @Test
    void testStrategySwitch() {
        RideRequest request = new RideRequest(10.0, 5.0, 1.0, 1.0);
        FareCalculator calculator = new FareCalculator(new NormalPricing());

        // Test with normal pricing
        assertEquals(15.0, calculator.calculateFare(request), 0.001);

        // Switch to surge pricing and test again
        calculator.setPricingStrategy(new SurgePricing());
        assertEquals(15.0, calculator.calculateFare(request), 0.001);
    }

    @Test
    void testNullStrategy() {
        RideRequest request = new RideRequest(10.0, 5.0, 1.0, 1.0);
        FareCalculator calculator = new FareCalculator(new NormalPricing());

        assertThrows(IllegalStateException.class, () -> {
            calculator.setPricingStrategy(null);
            calculator.calculateFare(request);
        });
    }

    @Test
    void testNullRequest() {
        FareCalculator calculator = new FareCalculator(new NormalPricing());
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateFare(null));
    }

    @Test
    void testNegativeValues() {
        assertThrows(IllegalArgumentException.class, () -> new RideRequest(-10.0, 5.0, 1.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new RideRequest(10.0, -5.0, 1.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new RideRequest(10.0, 5.0, -1.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new RideRequest(10.0, 5.0, 1.0, -1.0));
    }

    @Test
    void testZeroValues() {
        RideRequest request = new RideRequest(0.0, 0.0, 0.0, 0.0);
        FareCalculator calculator = new FareCalculator(new NormalPricing());
        assertEquals(0.0, calculator.calculateFare(request), 0.001);
    }
}

// Client code to test the dynamic pricing model
public class PricingClient {
    public static void main(String[] args) {
        // Create a ride request under normal conditions (low demand)
        RideRequest normalRequest = new RideRequest(10.0, 2.0, 1.0, 1.5);
        // Create a ride request under surge conditions (high demand)
        RideRequest surgeRequest = new RideRequest(10.0, 2.0, 2.0, 1.0);

        // Use NormalPricing strategy initially
        FareCalculator calculator = new FareCalculator(new NormalPricing());
        double normalFare = calculator.calculateFare(normalRequest);
        System.out.println("Normal pricing fare: " + normalFare);

        // Switch to SurgePricing strategy for high demand conditions
        calculator.setPricingStrategy(new SurgePricing());
        double surgeFare = calculator.calculateFare(surgeRequest);
        System.out.println("Surge pricing fare: " + surgeFare);
    }
}
