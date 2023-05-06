package UI;

/* Imports */
import UI.Interface.IRefreshable;
import UI.Component.AppMenu.*;
import org.jetbrains.annotations.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.*;
import UI.Page.*;

public interface BasePlugin {
    public void onLoad();
}

class PluginChart1 implements BasePlugin {
    @Override
    public void onLoad() {
        IApp app = App.getInstance();
        
        JMenu menu = new JMenu(null, false);

        app.addMenu(menu);
    }
}

class PluginChart2 implements BasePlugin {
    @Override
    public void onLoad() {
        IApp app = App.getInstance();
        
        JMenu menu = new JMenu(null, false);
        app.addMenu(menu);


    }
}

// public class PluginSistem implements BasePlugin {
//     public void onLoad() {

//     }
// }
