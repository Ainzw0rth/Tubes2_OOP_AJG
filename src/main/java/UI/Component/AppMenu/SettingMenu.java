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
        JMenuItem item2 = new JMenuItem("Import");
        item.addActionListener(actionListener);
        item2.addActionListener(actionListener);
        add(item);
        add(item2);
    }
}
