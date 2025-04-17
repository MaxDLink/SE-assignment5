package com.example.tenNico;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

interface ServiceFeature {
    void operate();
}

class BasicService implements ServiceFeature {
    public void operate() {
        System.out.println("Performing basic service operations.");
    }
}

abstract class FeatureDecorator implements ServiceFeature {
    protected ServiceFeature decoratedFeature;

    public FeatureDecorator(ServiceFeature feature) {
        if (feature == null) {
            throw new NullPointerException("Feature cannot be null");
        }
        this.decoratedFeature = feature;
    }

    public void operate() {
        decoratedFeature.operate();
    }
}

class NewFeatureDecorator extends FeatureDecorator {
    public NewFeatureDecorator(ServiceFeature feature) {
        super(feature);
    }

    public void operate() {
        super.operate();
        addFeature();
    }

    private void addFeature() {
        System.out.println("Adding new feature.");
    }
}

// Test class for the Decorator Pattern implementation
class DecoratorPatternTest {
    @Test
    void testBasicServiceOperation() {
        ServiceFeature basicService = new BasicService();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        basicService.operate();

        assertEquals("Performing basic service operations.\n", outContent.toString());
    }

    @Test
    void testDecoratedServiceOperation() {
        ServiceFeature basicService = new BasicService();
        ServiceFeature decoratedService = new NewFeatureDecorator(basicService);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        decoratedService.operate();

        assertEquals("Performing basic service operations.\nAdding new feature.\n", outContent.toString());
    }

    @Test
    void testNullFeatureInDecorator() {
        assertThrows(NullPointerException.class, () -> new NewFeatureDecorator(null));
    }

    @Test
    void testMultipleDecorators() {
        ServiceFeature basicService = new BasicService();
        ServiceFeature decoratedService = new NewFeatureDecorator(basicService);
        ServiceFeature doubleDecoratedService = new NewFeatureDecorator(decoratedService);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        doubleDecoratedService.operate();

        assertEquals("Performing basic service operations.\nAdding new feature.\nAdding new feature.\n",
                outContent.toString());
    }

    @Test
    void testFeatureDecoratorAbstractClass() {
        ServiceFeature basicService = new BasicService();
        FeatureDecorator decorator = new NewFeatureDecorator(basicService);
        assertTrue(decorator instanceof ServiceFeature);
    }

    @Test
    void testBasicServiceInstanceOf() {
        ServiceFeature service = new BasicService();
        assertTrue(service instanceof ServiceFeature);
        assertFalse(service instanceof FeatureDecorator);
    }

    @Test
    void testNewFeatureDecoratorInstanceOf() {
        ServiceFeature basicService = new BasicService();
        ServiceFeature decoratedService = new NewFeatureDecorator(basicService);
        assertTrue(decoratedService instanceof ServiceFeature);
        assertTrue(decoratedService instanceof NewFeatureDecorator);
    }
}
