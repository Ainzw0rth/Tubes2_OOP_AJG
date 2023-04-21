package UI.Component.AppMenu;

import javax.swing.*;
import java.awt.event.ActionListener;
import org.jetbrains.annotations.*;

public class CustomerMenu extends JMenu {
    @Nullable private ActionListener actionListener;
    
    public CustomerMenu(ActionListener actionListener) {
        super("Customer");
        this.actionListener = actionListener;
        initMenuItems();
    }

    private void initMenuItems() {
        JMenuItem item = new JMenuItem("Tambah Member");
        item.addActionListener(actionListener);
        add(item);

        item = new JMenuItem("Update Member");
        item.addActionListener(actionListener);
        add(item);

        item = new JMenuItem("Riwayat Transaksi Member");
        item.addActionListener(actionListener);
        add(item);
    }
}
