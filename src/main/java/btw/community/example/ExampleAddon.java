package btw.community.example;

import btw.AddonHandler;
import btw.BTWAddon;

public class ExampleAddon extends BTWAddon {
    private static ExampleAddon instance;

    private ExampleAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }
}