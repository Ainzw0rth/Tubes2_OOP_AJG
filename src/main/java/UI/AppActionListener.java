package UI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jetbrains.annotations.*;

import javax.swing.*;
import java.util.*;

import UI.Page.*;
import UI.Page.Customers.TambahMember;
import UI.Page.Customers.UpdateMember;

public class AppActionListener implements ActionListener {
    private List<JButton> tabCloseButtons = new ArrayList<JButton>();
    @NotNull
    private IApp app;

    public AppActionListener(IApp app) {
        this.app = app;
    }

    /**
     * Add close button for tab
     * 
     * @param button button to be added
     */
    public void addTabCloseButton(JButton button) {
        tabCloseButtons.add(button);
    }

    /**
     * Remove close button for tab
     * 
     * @param button button to be removed
     */
    public void removeTabCloseButton(int index) {
        tabCloseButtons.remove(index);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (tabCloseButtons.contains(e.getSource())) {
            /* Close tab when close button is clicked */
            int index = tabCloseButtons.indexOf(e.getSource());
            app.removeTab(index);
        } else if (e.getSource() instanceof JMenuItem) {
            /* Open a new tab when menu item is clicked */
            JMenuItem item = (JMenuItem) e.getSource();
            String title = item.getText();

            /* Action mapping */
            if (title.equals("Main Menu")) {
                // Main Menu
                app.addTab("Main Menu", new MainMenu());
            } else if (title.equals("Tambah Member")) {
                // Menu Tambah Member
                app.addTab("Tambah Member", new TambahMember());
            } else if (title.equals("Update Member")) {
                // Menu Update Member
                app.addTab("Update Member", new UpdateMember());
            } else if (title.equals("Riwayat Transaksi Member")) {
                // Menu Riwayat Transaksi Member
                app.addTab("Riwayat Transaksi Member", new HistoriTransaksi());
            } else if (title.equals("Jual Barang")) {
                app.addTab("Jual Barang", new JLabel("Jual Barang"));
            } else if (title.equals("Tambah Barang")) {
                app.addTab("Tambah Barang", new TambahBarang());
            } else if (title.equals("Update Barang")) {
                app.addTab("Update Barang", new UpdateBarang());
            } else if (title.equals("Laporan Penjualan")) {
                app.addTab("Laporan Penjualan", new JLabel("Laporan Penjualan"));
            } else {
                System.out.println("Menu tidak dikenali");
            }
        } else {
            System.out.println("Event tidak dikenali");
        }
    }
}