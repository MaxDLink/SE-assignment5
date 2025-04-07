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
        return 2.0 + (distance * 0.5) + (duration * 0.1);
    }
}


class CarXPricingStrategy implements PricingStrategy {
    public double calculatePrice(double distance, double duration) {
        return 5.0 + (distance * 1.0) + (duration * 0.2);
    }
}


class CarBlackPricingStrategy implements PricingStrategy {
    public double calculatePrice(double distance, double duration) {
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
        return switch (type) {
            case CARPOOL -> new CarPoolService();
            case CARX    -> new CarXService();
            case CARBLACK-> new CarBlackService();
        };
    }
}


// Client code
public class Main {
    public static void main(String[] args) {
        RideService pool = ServiceFactory.createService(ServiceType.CARPOOL);
        System.out.println(pool.getServiceName() + " fare: $" + pool.calculateFare(10, 20));


        RideService black = ServiceFactory.createService(ServiceType.CARBLACK);
        System.out.println(black.getServiceName() + " fare: $" + black.calculateFare(10, 20));
    }
}


// Factory
class ServiceFactory:
    static method createService(type: ServiceType): RideService
        switch(type):
            case CARPOOL: return new CarPoolService()
            case CARX: return new CarXService()
            case CARBLACK: return new CarBlackService()
            default: throw Error("Unknown service type")


// Client code
function main():
    // Client uses factory to get service with appropriate strategy
    poolService = ServiceFactory.createService(CARPOOL)
    print(poolService.getServiceName() + " fare: $" + poolService.calculateFare(10, 20))
    
    blackService = ServiceFactory.createService(CARBLACK)
    print(blackService.getServiceName() + " fare: $" + blackService.calculateFare(10, 20))
