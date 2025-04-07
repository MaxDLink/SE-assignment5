// The interface used by your system for processing payments
public interface PaymentHandler {
    void processPayment(PaymentRequest request);
}

// Class representing a payment request in your system's native format (e.g.,
// credit card details)
public class PaymentRequest {
    private String creditCardNumber;
    private double amount;

    public PaymentRequest(String creditCardNumber, double amount) {
        this.creditCardNumber = creditCardNumber;
        this.amount = amount;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public double getAmount() {
        return amount;
    }
}

// Class representing the data format required by PayPal
public class PayPalData {
    private String email;
    private String transactionAmount;

    public PayPalData(String email, String transactionAmount) {
        this.email = email;
        this.transactionAmount = transactionAmount;
    }

    public String getEmail() {
        return email;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }
}

// Simulated PayPal service with its own interface
public class PayPalService {
    public void processPayment(PayPalData data) {
        System.out.println("Processing payment through PayPal:");
        System.out.println("Email: " + data.getEmail());
        System.out.println("Amount: " + data.getTransactionAmount());
    }
}

// Adapter that converts PaymentRequest to PayPalData and calls the PayPal
// service
public class PayPalAdapter implements PaymentHandler {
    private PayPalService payPalService;

    public PayPalAdapter(PayPalService service) {
        this.payPalService = service;
    }

    @Override
    public void processPayment(PaymentRequest request) {
        // Convert the PaymentRequest into PayPalData format
        PayPalData data = convertRequestToPayPalData(request);
        // Delegate to the PayPal service
        payPalService.processPayment(data);
    }

    // Conversion logic (could involve more complex mapping in a real scenario)
    private PayPalData convertRequestToPayPalData(PaymentRequest request) {
        // For demonstration: generate a dummy email from the credit card number
        String email = request.getCreditCardNumber() + "@payments.com";
        // Format the transaction amount as a string with two decimals
        String transactionAmount = String.format("%.2f", request.getAmount());
        return new PayPalData(email, transactionAmount);
    }
}

// Client code to test the implementation
public class PaymentClient {
    public static void main(String[] args) {
        // Create an instance of the PayPal service
        PayPalService payPalService = new PayPalService();
        // Create an adapter to handle payment requests for PayPal
        PaymentHandler paymentHandler = new PayPalAdapter(payPalService);
        // Create a PaymentRequest with credit card data
        PaymentRequest request = new PaymentRequest("1234-5678-9012-3456", 99.99);
        // Process the payment using the adapter
        paymentHandler.processPayment(request);
    }
}
