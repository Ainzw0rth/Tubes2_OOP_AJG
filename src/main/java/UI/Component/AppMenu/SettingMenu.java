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
    }
}
