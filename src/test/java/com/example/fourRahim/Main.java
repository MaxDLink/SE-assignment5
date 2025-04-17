package com.example.fourRahim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// The interface used by your system for processing payments
interface PaymentHandler {
    void processPayment(PaymentRequest request);
}

// Class representing a payment request in your system's native format
class PaymentRequest {
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
class PayPalData {
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
class PayPalService {
    public void processPayment(PayPalData data) {
        System.out.println("Processing payment through PayPal:");
        System.out.println("Email: " + data.getEmail());
        System.out.println("Amount: " + data.getTransactionAmount());
    }
}

// Adapter that converts PaymentRequest to PayPalData and calls the PayPal
// service
class PayPalAdapter implements PaymentHandler {
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
    PayPalData convertRequestToPayPalData(PaymentRequest request) {
        // For demonstration: generate a dummy email from the credit card number
        String email = request.getCreditCardNumber() + "@payments.com";
        // Format the transaction amount as a string with two decimals
        String transactionAmount = String.format("%.2f", request.getAmount());
        return new PayPalData(email, transactionAmount);
    }
}

// Client code to test the implementation
class PaymentClient {
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

// Test class
class PaymentAdapterTest {
    @Test
    public void testPaymentRequestCreation() {
        PaymentRequest request = new PaymentRequest("1234-5678-9012-3456", 99.99);
        assertEquals("1234-5678-9012-3456", request.getCreditCardNumber());
        assertEquals(99.99, request.getAmount(), 0.001);
    }

    @Test
    public void testPayPalDataCreation() {
        PayPalData data = new PayPalData("test@payments.com", "100.00");
        assertEquals("test@payments.com", data.getEmail());
        assertEquals("100.00", data.getTransactionAmount());
    }

    @Test
    public void testPayPalAdapterConversion() {
        PayPalService payPalService = new PayPalService();
        PayPalAdapter adapter = new PayPalAdapter(payPalService);
        PaymentRequest request = new PaymentRequest("1234-5678-9012-3456", 99.99);

        // Test the conversion logic
        PayPalData data = adapter.convertRequestToPayPalData(request);
        assertEquals("1234-5678-9012-3456@payments.com", data.getEmail());
        assertEquals("99.99", data.getTransactionAmount());
    }

    @Test
    public void testPayPalAdapterProcessPayment() {
        PayPalService payPalService = new PayPalService();
        PayPalAdapter adapter = new PayPalAdapter(payPalService);
        PaymentRequest request = new PaymentRequest("1234-5678-9012-3456", 99.99);

        // This test will verify that the adapter correctly processes the payment
        // through the PayPal service
        adapter.processPayment(request);
        // Note: In a real test, you might want to capture and verify the output
        // or use a mock for PayPalService
    }

    @Test
    public void testNullPaymentRequest() {
        PayPalService payPalService = new PayPalService();
        PayPalAdapter adapter = new PayPalAdapter(payPalService);

        assertThrows(NullPointerException.class, () -> {
            adapter.processPayment(null);
        });
    }

    @Test
    public void testInvalidAmount() {
        PayPalService payPalService = new PayPalService();
        PayPalAdapter adapter = new PayPalAdapter(payPalService);
        PaymentRequest request = new PaymentRequest("1234-5678-9012-3456", -99.99);

        // Test that negative amounts are handled correctly
        PayPalData data = adapter.convertRequestToPayPalData(request);
        assertEquals("-99.99", data.getTransactionAmount());
    }

    @Test
    public void testEmptyCreditCardNumber() {
        PayPalService payPalService = new PayPalService();
        PayPalAdapter adapter = new PayPalAdapter(payPalService);
        PaymentRequest request = new PaymentRequest("", 99.99);

        PayPalData data = adapter.convertRequestToPayPalData(request);
        assertEquals("@payments.com", data.getEmail());
    }
}
