package UI.Component.AppMenu;

import javax.swing.*;
import java.awt.event.ActionListener;
import org.jetbrains.annotations.*;

public class SettingMenu extends JMenu {
    @Nullable private ActionListener actionListener;
    
    public SettingMenu(ActionListener actionListener) {
        super("Setting");
        this.actionListener = actionListener;
        initMenuItems();
    }

    private void initMenuItems() {
        JMenuItem item = new JMenuItem("Main Menu");
        item.addActionListener(actionListener);
        add(item);

        JMenuItem item2 = new JMenuItem("Import");
        item2.addActionListener(actionListener);
        add(item2);
        
        JMenuItem dataStore = new JMenuItem("Penyimpanan Data");
        dataStore.addActionListener(actionListener);
        add(dataStore);
    }
}
