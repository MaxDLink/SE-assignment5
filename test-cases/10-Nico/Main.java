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


// Client code
ServiceFeature service = new BasicService();
ServiceFeature decoratedService = new NewFeatureDecorator(service);
decoratedService.operate();
