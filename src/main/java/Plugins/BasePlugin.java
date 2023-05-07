package Plugins;

/* Imports */
import UI.IApp;
import DataStore.DataStore;

public interface BasePlugin {
    /**
     * Method that will run when the plugin is loaded
     * @param appService
     * @param dataService
     */
    public void onLoad(IApp appService, DataStore dataService);
}