package com.example.threeGrant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        if (cityName == null || country == null) {
            throw new IllegalArgumentException("City name and country cannot be null");
        }
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

// Test class
class DecoratorTest {
    @Test
    public void testBasicCarService() {
        CarService service = new BasicCarService();
        assertEquals("Basic Car Service", service.getDescription());
        assertEquals(5.0, service.getCost(), 0.001);
    }

    @Test
    public void testCarGoDecorator() {
        CarService service = new CarGoDecorator(new BasicCarService());
        assertEquals("Basic Car Service, with CarGo (Hatchback)", service.getDescription());
        assertEquals(7.0, service.getCost(), 0.001); // 5.0 + 2.0
    }

    @Test
    public void testCarEATSDecorator() {
        CarService service = new CarEATSDecorator(new BasicCarService());
        assertEquals("Basic Car Service, with CarEATS (Food Delivery)", service.getDescription());
        assertEquals(8.5, service.getCost(), 0.001); // 5.0 + 3.5
    }

    @Test
    public void testMultipleDecorators() {
        CarService service = new CarEATSDecorator(new CarGoDecorator(new BasicCarService()));
        assertEquals("Basic Car Service, with CarGo (Hatchback), with CarEATS (Food Delivery)",
                service.getDescription());
        assertEquals(10.5, service.getCost(), 0.001); // 5.0 + 2.0 + 3.5
    }

    @Test
    public void testCityMumbai() {
        City mumbai = new City("Mumbai", "India");
        mumbai.configureServices();
        String expected = "Mumbai, India: Basic Car Service, with CarGo (Hatchback), with CarEATS (Food Delivery) (Cost: $10.5)";
        assertEquals(expected, mumbai.getAvailableServices());
    }

    @Test
    public void testCityTokyo() {
        City tokyo = new City("Tokyo", "Japan");
        tokyo.configureServices();
        String expected = "Tokyo, Japan: Basic Car Service (Cost: $5.0)";
        assertEquals(expected, tokyo.getAvailableServices());
    }

    @Test
    public void testCityNewYork() {
        City newYork = new City("New York", "USA");
        newYork.configureServices();
        String expected = "New York, USA: Basic Car Service, with CarEATS (Food Delivery) (Cost: $8.5)";
        assertEquals(expected, newYork.getAvailableServices());
    }

    @Test
    public void testNullCity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new City(null, "USA");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new City("New York", null);
        });
    }
}
