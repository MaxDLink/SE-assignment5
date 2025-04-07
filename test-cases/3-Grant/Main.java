// Component Interface
interface CarService {
    String getDescription();

    double getCost();
}

// Concrete Component
class BasicCarService implements CarService {
    private final double baseRate = 5.0;

    public String getDescription() {
        return "Basic Car Service";
    }

    public double getCost() {
        return baseRate;
    }
}

// Abstract Decorator
abstract class CarServiceDecorator implements CarService {
    protected CarService decoratedCarService;

    public CarServiceDecorator(CarService carService) {
        this.decoratedCarService = carService;
    }

    public String getDescription() {
        return decoratedCarService.getDescription();
    }

    public double getCost() {
        return decoratedCarService.getCost();
    }
}

// Concrete Decorator 1
class CarGoDecorator extends CarServiceDecorator {
    private final double additionalCost = 2.0;

    public CarGoDecorator(CarService carService) {
        super(carService);
    }

    public String getDescription() {
        return super.getDescription() + ", with CarGo (Hatchback)";
    }

    public double getCost() {
        return super.getCost() + additionalCost;
    }
}

// Concrete Decorator 2
class CarEATSDecorator extends CarServiceDecorator {
    private final double deliveryFee = 3.5;

    public CarEATSDecorator(CarService carService) {
        super(carService);
    }

    public String getDescription() {
        return super.getDescription() + ", with CarEATS (Food Delivery)";
    }

    public double getCost() {
        return super.getCost() + deliveryFee;
    }
}

// Context Class
class City {
    private String cityName, country;
    private CarService carService;

    public City(String cityName, String country) {
        this.cityName = cityName;
        this.country = country;
    }

    public void configureServices() {
        carService = new BasicCarService();

        if (country.equals("India")) {
            carService = new CarGoDecorator(carService);
        }

        if (cityName.equals("Mumbai") || cityName.equals("New York")) {
            carService = new CarEATSDecorator(carService);
        }
    }

    public String getAvailableServices() {
        return cityName + ", " + country + ": " + carService.getDescription() +
                " (Cost: $" + carService.getCost() + ")";
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        City mumbai = new City("Mumbai", "India");
        mumbai.configureServices();
        System.out.println(mumbai.getAvailableServices());

        City tokyo = new City("Tokyo", "Japan");
        tokyo.configureServices();
        System.out.println(tokyo.getAvailableServices());

        City newYork = new City("New York", "USA");
        newYork.configureServices();
        System.out.println(newYork.getAvailableServices());
    }
}
