package com.example.twoGrant;

enum ServiceType {
    CARPOOL, CARX, CARBLACK
}

// Strategy interface
interface PricingStrategy {
    double calculatePrice(double distance, double duration);
}

// Concrete strategies
class CarPoolPricingStrategy implements PricingStrategy {
    public double calculatePrice(double distance, double duration) {
        if (distance < 0 || duration < 0) {
            throw new IllegalArgumentException("Distance and duration must be non-negative");
        }
        return 2.0 + (distance * 0.5) + (duration * 0.1);
    }
}

class CarXPricingStrategy implements PricingStrategy {
    public double calculatePrice(double distance, double duration) {
        if (distance < 0 || duration < 0) {
            throw new IllegalArgumentException("Distance and duration must be non-negative");
        }
        return 5.0 + (distance * 1.0) + (duration * 0.2);
    }
}

class CarBlackPricingStrategy implements PricingStrategy {
    public double calculatePrice(double distance, double duration) {
        if (distance < 0 || duration < 0) {
            throw new IllegalArgumentException("Distance and duration must be non-negative");
        }
        return 10.0 + (distance * 2.0) + (duration * 0.5);
    }
}

// Abstract RideService
abstract class RideService {
    protected String serviceName;
    protected PricingStrategy pricingStrategy;

    public double calculateFare(double distance, double duration) {
        return pricingStrategy.calculatePrice(distance, duration);
    }

    public String getServiceName() {
        return serviceName;
    }
}

// Concrete services
class CarPoolService extends RideService {
    public CarPoolService() {
        this.pricingStrategy = new CarPoolPricingStrategy();
        this.serviceName = "carPOOL";
    }
}

class CarXService extends RideService {
    public CarXService() {
        this.pricingStrategy = new CarXPricingStrategy();
        this.serviceName = "carX";
    }
}

class CarBlackService extends RideService {
    public CarBlackService() {
        this.pricingStrategy = new CarBlackPricingStrategy();
        this.serviceName = "carBlack";
    }
}

// Factory
class ServiceFactory {
    public static RideService createService(ServiceType type) {
        if (type == null) {
            throw new IllegalArgumentException("Service type cannot be null");
        }
        switch (type) {
            case CARPOOL:
                return new CarPoolService();
            case CARX:
                return new CarXService();
            case CARBLACK:
                return new CarBlackService();
            default:
                throw new IllegalArgumentException("Unknown service type: " + type);
        }
    }
}

// Test class
public class Main {
    public static void main(String[] args) {
        testCarPoolService();
        testCarXService();
        testCarBlackService();
        testServiceFactory();
    }

    private static void testCarPoolService() {
        RideService service = new CarPoolService();
        double fare = service.calculateFare(10, 20);
        System.out.println("Testing CarPool Service:");
        System.out.println("Service Name: " + service.getServiceName());
        System.out.println("Fare for 10km, 20min: $" + fare);
        System.out.println();
    }

    private static void testCarXService() {
        RideService service = new CarXService();
        double fare = service.calculateFare(10, 20);
        System.out.println("Testing CarX Service:");
        System.out.println("Service Name: " + service.getServiceName());
        System.out.println("Fare for 10km, 20min: $" + fare);
        System.out.println();
    }

    private static void testCarBlackService() {
        RideService service = new CarBlackService();
        double fare = service.calculateFare(10, 20);
        System.out.println("Testing CarBlack Service:");
        System.out.println("Service Name: " + service.getServiceName());
        System.out.println("Fare for 10km, 20min: $" + fare);
        System.out.println();
    }

    private static void testServiceFactory() {
        System.out.println("Testing Service Factory:");
        try {
            RideService pool = ServiceFactory.createService(ServiceType.CARPOOL);
            System.out.println("Created CarPool service successfully");

            RideService x = ServiceFactory.createService(ServiceType.CARX);
            System.out.println("Created CarX service successfully");

            RideService black = ServiceFactory.createService(ServiceType.CARBLACK);
            System.out.println("Created CarBlack service successfully");

            // Test invalid service type
            try {
                ServiceFactory.createService(null);
                System.out.println("ERROR: Should have thrown exception for null service type");
            } catch (IllegalArgumentException e) {
                System.out.println("Correctly handled invalid service type");
            }
        } catch (Exception e) {
            System.out.println("Error in service factory test: " + e.getMessage());
        }
    }
}
