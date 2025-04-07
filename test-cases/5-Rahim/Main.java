// Context: FareCalculator that delegates to a PricingStrategy
public class FareCalculator {
    private PricingStrategy pricingStrategy;

    // Constructor to set an initial strategy
    public FareCalculator(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    // Setter to change strategy dynamically at runtime
    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    // Calculate fare by delegating to the current strategy
    public double calculateFare(RideRequest request) {
        return pricingStrategy.calculatePrice(request);
    }
}

// Strategy interface defining the pricing method
public interface PricingStrategy {
    double calculatePrice(RideRequest request);
}

// Concrete strategy for normal pricing
public class NormalPricing implements PricingStrategy {
    @Override
    public double calculatePrice(RideRequest request) {
        // Basic fare calculation: base fare + distance * rate (e.g., 1.0)
        return request.getBaseFare() + (request.getDistance() * 1.0);
    }
}

// Concrete strategy for surge pricing (increases fare when demand > supply)
public class SurgePricing implements PricingStrategy {
    @Override
    public double calculatePrice(RideRequest request) {
        double multiplier = (request.getDemandFactor() > request.getSupplyFactor()) ? 1.5 : 1.0;
        return (request.getBaseFare() + request.getDistance()) * multiplier;
    }
}

// Data class representing the details of a ride request
public class RideRequest {
    private double distance;
    private double baseFare;
    private double demandFactor;
    private double supplyFactor;

    public RideRequest(double distance, double baseFare, double demandFactor, double supplyFactor) {
        this.distance = distance;
        this.baseFare = baseFare;
        this.demandFactor = demandFactor;
        this.supplyFactor = supplyFactor;
    }

    public double getDistance() {
        return distance;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public double getDemandFactor() {
        return demandFactor;
    }

    public double getSupplyFactor() {
        return supplyFactor;
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
