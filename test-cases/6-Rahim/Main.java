// Observer interface defining the update method
public interface Observer {
    void update(String coupon);
}


// Subject interface (optional in this example, but useful for clarity)
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}


// CouponNotifier acts as the subject that notifies registered observers
public class CouponNotifier implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String coupon;


    @Override
    public void registerObserver(Observer observer) {
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
        this.coupon = coupon;
        notifyObservers();
    }
}


// Rider class implements Observer to receive coupon notifications
public class Rider implements Observer {
    private String name;


    public Rider(String name) {
        this.name = name;
    }


    @Override
    public void update(String coupon) {
        System.out.println("Rider " + name + " received coupon: " + coupon);
    }
}


// Client code to test the coupon push-mode notification system
public class CouponClient {
    public static void main(String[] args) {
        // Create the coupon notifier (subject)
        CouponNotifier notifier = new CouponNotifier();
        
        // Create riders (observers)
        Rider rider1 = new Rider("Alice");
        Rider rider2 = new Rider("Bob");
        
        // Register riders to receive coupon updates
        notifier.registerObserver(rider1);
        notifier.registerObserver(rider2);
        
        // Set a new coupon; all registered riders will be notified automatically
        notifier.setCoupon("DISCOUNT2025");
    }
}

