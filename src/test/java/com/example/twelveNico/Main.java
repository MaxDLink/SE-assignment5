package com.example.twelveNico;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

interface AppInterface {
    void operation();
}

class IOSApp implements AppInterface {
    private UserInterface userInterface;

    public IOSApp(UserInterface ui) {
        if (ui == null) {
            throw new IllegalArgumentException("UserInterface cannot be null");
        }
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
        if (ui == null) {
            throw new IllegalArgumentException("UserInterface cannot be null");
        }
        this.userInterface = ui;
    }

    public void operation() {
        userInterface.interact();
    }
}

class WebUserInterface implements UserInterface {
    public void interact() {
        System.out.println("Interaction through web interface.");
    }
}

// Test class for the Bridge Pattern implementation
class BridgePatternTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testIOSAppWithSmartphoneUI() {
        UserInterface ui = new SmartphoneUserInterface();
        AppInterface app = new IOSApp(ui);
        app.operation();
        assertEquals("Interaction through smartphone app.\n", outContent.toString());
    }

    @Test
    void testWebAppWithWebUI() {
        UserInterface ui = new WebUserInterface();
        AppInterface app = new WebApp(ui);
        app.operation();
        assertEquals("Interaction through web interface.\n", outContent.toString());
    }

    @Test
    void testIOSAppWithWebUI() {
        UserInterface ui = new WebUserInterface();
        AppInterface app = new IOSApp(ui);
        app.operation();
        assertEquals("Interaction through web interface.\n", outContent.toString());
    }

    @Test
    void testWebAppWithSmartphoneUI() {
        UserInterface ui = new SmartphoneUserInterface();
        AppInterface app = new WebApp(ui);
        app.operation();
        assertEquals("Interaction through smartphone app.\n", outContent.toString());
    }

    @Test
    void testNullUserInterfaceInIOSApp() {
        assertThrows(IllegalArgumentException.class, () -> new IOSApp(null));
    }

    @Test
    void testNullUserInterfaceInWebApp() {
        assertThrows(IllegalArgumentException.class, () -> new WebApp(null));
    }

    @Test
    void testInstanceOfChecks() {
        UserInterface smartphoneUI = new SmartphoneUserInterface();
        UserInterface webUI = new WebUserInterface();
        AppInterface iosApp = new IOSApp(smartphoneUI);
        AppInterface webApp = new WebApp(webUI);

        assertTrue(smartphoneUI instanceof UserInterface);
        assertTrue(webUI instanceof UserInterface);
        assertTrue(iosApp instanceof AppInterface);
        assertTrue(webApp instanceof AppInterface);
    }
}
