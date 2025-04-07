interface ChatMediator {
    void sendMessage(String msg, Rider sender);

    void addUser(Rider user);
}

class RiderChatMediator implements ChatMediator {
    private List<Rider> riders = new ArrayList<>();

    @Override
    public void addUser(Rider rider) {
        this.riders.add(rider);
    }

    @Override
    public void sendMessage(String msg, Rider sender) {
        for (Rider rider : riders) {
            // message should not be received by the user sending it
            if (rider != sender) {
                rider.receive(msg);
            }
        }
    }
}

class Rider {
    private String name;
    private ChatMediator mediator;

    public Rider(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        System.out.println(this.name + " received: " + message);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        ChatMediator mediator = new RiderChatMediator();
        Rider john = new Rider("John", mediator);
        Rider jane = new Rider("Jane", mediator);
        mediator.addUser(john);
        mediator.addUser(jane);

        john.send("Hi there!");
        jane.send("Hey!");
    }
}
