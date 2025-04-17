package com.example.sevenNico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

interface ChatMediator {
    void sendMessage(String msg, Rider sender);

    void addUser(Rider user);
}

class RiderChatMediator implements ChatMediator {
    private List<Rider> riders = new ArrayList<>();

    @Override
    public void addUser(Rider rider) {
        if (rider == null) {
            throw new IllegalArgumentException("Rider cannot be null");
        }
        this.riders.add(rider);
    }

    @Override
    public void sendMessage(String msg, Rider sender) {
        if (msg == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        if (sender == null) {
            throw new IllegalArgumentException("Sender cannot be null");
        }
        for (Rider rider : riders) {
            // message should not be received by the user sending it
            if (rider != sender) {
                rider.receive(msg);
            }
        }
    }

    public List<Rider> getRiders() {
        return new ArrayList<>(riders);
    }
}

class Rider {
    private String name;
    private ChatMediator mediator;
    private List<String> receivedMessages = new ArrayList<>();

    public Rider(String name, ChatMediator mediator) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Rider name cannot be null or empty");
        }
        if (mediator == null) {
            throw new IllegalArgumentException("Mediator cannot be null");
        }
        this.name = name;
        this.mediator = mediator;
    }

    public void send(String message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        receivedMessages.add(message);
    }

    public List<String> getReceivedMessages() {
        return new ArrayList<>(receivedMessages);
    }

    public String getName() {
        return name;
    }
}

// Test class for the Mediator Pattern implementation
class MediatorPatternTest {
    @Test
    void testSingleMessage() {
        RiderChatMediator mediator = new RiderChatMediator();
        Rider john = new Rider("John", mediator);
        Rider jane = new Rider("Jane", mediator);
        
        mediator.addUser(john);
        mediator.addUser(jane);
        
        john.send("Hi there!");
        
        assertEquals(0, john.getReceivedMessages().size());
        assertEquals(1, jane.getReceivedMessages().size());
        assertEquals("Hi there!", jane.getReceivedMessages().get(0));
    }

    @Test
    void testMultipleMessages() {
        RiderChatMediator mediator = new RiderChatMediator();
        Rider john = new Rider("John", mediator);
        Rider jane = new Rider("Jane", mediator);
        
        mediator.addUser(john);
        mediator.addUser(jane);
        
        john.send("Hi there!");
        jane.send("Hey!");
        
        assertEquals(1, john.getReceivedMessages().size());
        assertEquals(1, jane.getReceivedMessages().size());
        assertEquals("Hey!", john.getReceivedMessages().get(0));
        assertEquals("Hi there!", jane.getReceivedMessages().get(0));
    }

    @Test
    void testMultipleUsers() {
        RiderChatMediator mediator = new RiderChatMediator();
        Rider john = new Rider("John", mediator);
        Rider jane = new Rider("Jane", mediator);
        Rider bob = new Rider("Bob", mediator);
        
        mediator.addUser(john);
        mediator.addUser(jane);
        mediator.addUser(bob);
        
        john.send("Hello everyone!");
        
        assertEquals(0, john.getReceivedMessages().size());
        assertEquals(1, jane.getReceivedMessages().size());
        assertEquals(1, bob.getReceivedMessages().size());
        assertEquals("Hello everyone!", jane.getReceivedMessages().get(0));
        assertEquals("Hello everyone!", bob.getReceivedMessages().get(0));
    }

    @Test
    void testNullRiderRegistration() {
        RiderChatMediator mediator = new RiderChatMediator();
        assertThrows(IllegalArgumentException.class, () -> mediator.addUser(null));
    }

    @Test
    void testNullMessage() {
        RiderChatMediator mediator = new RiderChatMediator();
        Rider john = new Rider("John", mediator);
        Rider jane = new Rider("Jane", mediator);
        
        mediator.addUser(john);
        mediator.addUser(jane);
        
        assertThrows(IllegalArgumentException.class, () -> john.send(null));
    }

    @Test
    void testEmptyRiderName() {
        RiderChatMediator mediator = new RiderChatMediator();
        assertThrows(IllegalArgumentException.class, () -> new Rider("", mediator));
        assertThrows(IllegalArgumentException.class, () -> new Rider(null, mediator));
    }

    @Test
    void testNullMediator() {
        assertThrows(IllegalArgumentException.class, () -> new Rider("John", null));
    }

    @Test
    void testMessageOrder() {
        RiderChatMediator mediator = new RiderChatMediator();
        Rider john = new Rider("John", mediator);
        Rider jane = new Rider("Jane", mediator);
        
        mediator.addUser(john);
        mediator.addUser(jane);
        
        john.send("First");
        john.send("Second");
        john.send("Third");
        
        assertEquals(3, jane.getReceivedMessages().size());
        assertEquals("First", jane.getReceivedMessages().get(0));
        assertEquals("Second", jane.getReceivedMessages().get(1));
        assertEquals("Third", jane.getReceivedMessages().get(2));
    }
}
