package com.example.sixRahim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

// Observer interface defining the update method
interface Observer {
    void update(String coupon);
}

// Subject interface (optional in this example, but useful for clarity)
interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

// CouponNotifier acts as the subject that notifies registered observers
class CouponNotifier implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String coupon;

    @Override
    public void registerObserver(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(coupon);
        }
    }

    // When a new coupon is set, notify all observers
    public void setCoupon(String coupon) {
        if (coupon == null) {
            throw new IllegalArgumentException("Coupon cannot be null");
        }
        this.coupon = coupon;
        notifyObservers();
    }
}

// Rider class implements Observer to receive coupon notifications
class Rider implements Observer {
    private String name;
    private List<String> receivedCoupons = new ArrayList<>();

    public Rider(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Rider name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public void update(String coupon) {
        receivedCoupons.add(coupon);
    }

    public List<String> getReceivedCoupons() {
        return new ArrayList<>(receivedCoupons);
    }

    public String getName() {
        return name;
    }
}

// Test class for the Observer Pattern implementation
class ObserverPatternTest {
    @Test
    void testRegisterObserver() {
        CouponNotifier notifier = new CouponNotifier();
        Rider rider = new Rider("Alice");

        notifier.registerObserver(rider);
        notifier.setCoupon("DISCOUNT2025");

        assertEquals(1, rider.getReceivedCoupons().size());
        assertEquals("DISCOUNT2025", rider.getReceivedCoupons().get(0));
    }

    @Test
    void testRemoveObserver() {
        CouponNotifier notifier = new CouponNotifier();
        Rider rider = new Rider("Alice");

        notifier.registerObserver(rider);
        notifier.setCoupon("FIRST_COUPON");
        notifier.removeObserver(rider);
        notifier.setCoupon("SECOND_COUPON");

        assertEquals(1, rider.getReceivedCoupons().size());
        assertEquals("FIRST_COUPON", rider.getReceivedCoupons().get(0));
    }

    @Test
    void testMultipleObservers() {
        CouponNotifier notifier = new CouponNotifier();
        Rider rider1 = new Rider("Alice");
        Rider rider2 = new Rider("Bob");

        notifier.registerObserver(rider1);
        notifier.registerObserver(rider2);
        notifier.setCoupon("DISCOUNT2025");

        assertEquals(1, rider1.getReceivedCoupons().size());
        assertEquals(1, rider2.getReceivedCoupons().size());
        assertEquals("DISCOUNT2025", rider1.getReceivedCoupons().get(0));
        assertEquals("DISCOUNT2025", rider2.getReceivedCoupons().get(0));
    }

    @Test
    void testMultipleCoupons() {
        CouponNotifier notifier = new CouponNotifier();
        Rider rider = new Rider("Alice");

        notifier.registerObserver(rider);
        notifier.setCoupon("FIRST_COUPON");
        notifier.setCoupon("SECOND_COUPON");

        assertEquals(2, rider.getReceivedCoupons().size());
        assertEquals("FIRST_COUPON", rider.getReceivedCoupons().get(0));
        assertEquals("SECOND_COUPON", rider.getReceivedCoupons().get(1));
    }

    @Test
    void testNullObserverRegistration() {
        CouponNotifier notifier = new CouponNotifier();
        assertThrows(IllegalArgumentException.class, () -> notifier.registerObserver(null));
    }

    @Test
    void testNullCoupon() {
        CouponNotifier notifier = new CouponNotifier();
        Rider rider = new Rider("Alice");
        notifier.registerObserver(rider);

        assertThrows(IllegalArgumentException.class, () -> notifier.setCoupon(null));
    }

    @Test
    void testEmptyRiderName() {
        assertThrows(IllegalArgumentException.class, () -> new Rider(""));
        assertThrows(IllegalArgumentException.class, () -> new Rider(null));
    }

    @Test
    void testObserverOrder() {
        CouponNotifier notifier = new CouponNotifier();
        List<String> notificationOrder = new ArrayList<>();

        Observer observer1 = coupon -> notificationOrder.add("Observer1");
        Observer observer2 = coupon -> notificationOrder.add("Observer2");

        notifier.registerObserver(observer1);
        notifier.registerObserver(observer2);
        notifier.setCoupon("TEST_COUPON");

        assertEquals(2, notificationOrder.size());
        assertEquals("Observer1", notificationOrder.get(0));
        assertEquals("Observer2", notificationOrder.get(1));
    }
}
