package UI.Component.AppMenu;

import javax.swing.*;
import java.awt.event.ActionListener;
import org.jetbrains.annotations.*;

public class InventoryMenu extends JMenu {
    @Nullable private ActionListener actionListener;
    
    public InventoryMenu(ActionListener actionListener) {
        super("Inventory");
        this.actionListener = actionListener;
        initMenuItems();
    }

    private void initMenuItems() {
        JMenuItem item = new JMenuItem("Jual Barang");
        item.addActionListener(actionListener);
        add(item);

        item = new JMenuItem("Tambah Barang");
        item.addActionListener(actionListener);
        add(item);

        item = new JMenuItem("Update Barang");
        item.addActionListener(actionListener);
        add(item);

        item = new JMenuItem("Laporan Penjualan");
        item.addActionListener(actionListener);
        add(item); 
    }
}