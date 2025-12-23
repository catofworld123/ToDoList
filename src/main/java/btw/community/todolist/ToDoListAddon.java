package btw.community.todolist;

import api.AddonHandler;
import api.BTWAddon;

public class ToDoListAddon extends BTWAddon {
    private static ToDoListAddon instance;

    public ToDoListAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }
}