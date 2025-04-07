interface AppInterface {
    void operation();
}

class IOSApp implements AppInterface {
    private UserInterface userInterface;

    public IOSApp(UserInterface ui) {
        this.userInterface = ui;
    }

    public void operation() {
        userInterface.interact();
    }
}

interface UserInterface {
    void interact();
}

class SmartphoneUserInterface implements UserInterface {
    public void interact() {
        System.out.println("Interaction through smartphone app.");
    }
}

class WebApp implements AppInterface {
    private UserInterface userInterface;

    public WebApp(UserInterface ui) {
        this.userInterface = ui;
    }

    public void operation() {
        userInterface.interact();
    }
}

interface UserInterface {
    void interact();
}

class WebUserInterface implements UserInterface {
    public void interact() {
        System.out.println("Interaction through smartphone app.");
    }
}
//extend with android app etc.


// Client code
UserInterface uiPhone = new SmartphoneUserInterface();
AppInterface PhoneApp = new IOSApp(uiPhone);
UserInterface uiWeb = new WebUserInterface();
AppInterface webAppEx = new WebApp(uiWeb);
PhoneApp.operation();
webAppEx.operation();
