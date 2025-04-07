package com.example.factory;


public class FactoryPatternTest {
    public static void main(String[] args) {
        // Test with NormalCar
        SharableCarFactory normalFactory = new NormalCarFactory();
        CarSharingClient normalClient = new CarSharingClient(normalFactory);
        System.out.println("Normal Car Test: " + normalClient.useCar(5.0));
        System.out.println(normalFactory.getCarDescription());


        // Test with LuxuryBlackCar
        SharableCarFactory luxuryFactory = new LuxuryBlackCarFactory();
        CarSharingClient luxuryClient = new CarSharingClient(luxuryFactory);
        System.out.println("Luxury Black Car Test: " + luxuryClient.useCar(5.0));
        System.out.println(luxuryFactory.getCarDescription());


        // Test with SUV
        SharableCarFactory suvFactory = new SUVFactory();
        CarSharingClient suvClient = new CarSharingClient(suvFactory);
        System.out.println("SUV Test: " + suvClient.useCar(5.0));
        System.out.println(suvFactory.getCarDescription());


        // Test with WheelchairAccessibleTransport
        SharableCarFactory wheelchairFactory = new WheelchairAccessibleFactory();
        CarSharingClient wheelchairClient = new CarSharingClient(wheelchairFactory);
        System.out.println("Wheelchair Accessible Test: " + wheelchairClient.useCar(5.0));
        System.out.println(wheelchairFactory.getCarDescription());
    }
}
