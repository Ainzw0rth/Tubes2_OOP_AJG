package UI;

import java.awt.Component;
import javax.swing.*;

/* 
 * Define an app interface/API that is accessible by all clients
 * Can be used by features or plugins to modify the application
*/
public interface IApp {
    /**
     * Get index of the currently selected tab
     * @return Index of the currently selected tab
    */
    public int getSelectedTabIndex();

    /** 
     * Add a new tab to the application 
     * @param title Title of the tab
     * @param component Component to be added to the tab
    */
    public void addTab(String title, Component component);

    /** 
     * Remove a tab from the application
     * @param index Index of the tab to be removed
    */
    public void removeTab(int index);

    /** 
     * Refresh a tab from the application
     * @param index Index of the tab to be refreshed
    */
    public void refreshTab(int index);

    /**
     * Add new menu to the application
     * @param menu Menu to be added
     */
    public void addMenu(JMenu menu);

    /**
     * Get menu index by name
     * @param name Name of the menu
     * @return Index of the menu (if found, -1 otherwise)
    */
    public int getMenuIndex(String name);

    /** 
     * Add new menu item to existing Menu 
     * @param menu Menu to add the item to
     * @param index Index of the item to be added
    */
    public void addMenuItem(int index, JMenuItem item);

    /**
     * Set visibility of App
     * @param bool visible if true, otherwise, not visible
    */
    public void setVisible(boolean bool);
}
