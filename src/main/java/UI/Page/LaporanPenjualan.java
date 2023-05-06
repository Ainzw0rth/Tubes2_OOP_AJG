package UI.Page;
import javax.swing.*;
import java.awt.*;

import Entity.Item;

public class LaporanPenjualan extends JPanel {

    public LaporanPenjualan() {
        // TITLE PANEL
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        // titlePanel.setBackground(Color.white);
        titlePanel.setBounds(20, 20, 300, 60);

        // TITLE LABEL
        JLabel titleLabel = new JLabel("Laporan Penjualan");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        titleLabel.setBounds(0, 6, 600, 60);

        // ITEM PANEL
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(null);
        itemPanel.setBounds(30,90,510,30);

        // ITEM LABEL
        JLabel itemLabel = new JLabel("Item :");
        itemLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        itemLabel.setBounds(0, 6, 600, 30);

        // LOOP ITEM
        Item[] itemList = {};
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(Color.white);

        for (Item item : itemList) {
            JPanel panelItem = new JPanel();
            panelItem.setBackground(Color.white);
            panelItem.setMaximumSize(new Dimension(1000, 30));

            JPanel itemNames = new JPanel();
            itemNames.setBounds(15, 0, 80, 20);
            itemNames.setBackground(Color.white);
            itemNames.setLayout(null);

            JPanel itemQuantity = new JPanel();
            itemQuantity.setBounds(110, 0, 80, 20);
            itemQuantity.setBackground(Color.white);
            itemQuantity.setLayout(null);

            JPanel itemPrices = new JPanel();
            itemPrices.setBounds(210, 0, 80, 20);
            itemPrices.setBackground(Color.white);
            itemPrices.setLayout(null);

            JLabel nameLabel = new JLabel(item.getName());
            nameLabel.setHorizontalAlignment(JLabel.LEFT); // align text to left
            nameLabel.setBounds(0, 0, 120, 20);
            itemNames.add(nameLabel, BorderLayout.WEST); // add name label to left side of panel

            JLabel quantityLabel = new JLabel(item.getName());
            quantityLabel.setHorizontalAlignment(JLabel.LEFT); // align text to left
            quantityLabel.setBounds(0, 0, 120, 20);
            itemQuantity.add(quantityLabel, BorderLayout.WEST); // add name label to left side of panel

            JLabel priceLabel = new JLabel("Rp " + item.getPrice());
            priceLabel.setHorizontalAlignment(JLabel.LEFT); // align text to right
            priceLabel.setBounds(0, 0, 120, 20);
            itemPrices.add(priceLabel, BorderLayout.EAST);
        }

        // HISTORY SCROLL
        JScrollPane historyScrollPane = new JScrollPane(itemPanel);
        historyScrollPane.setBackground(Color.white);
        historyScrollPane.setLayout(null);
        historyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        historyScrollPane.setBounds(600, 0, 600, 720);
        historyScrollPane.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));

        // TOTAL PANEL
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(null);
        totalPanel.setBounds(60,150,510,30);
        totalPanel.setBackground(Color.white);

        // TOTAL FIELD
        JLabel totalLabel = new JLabel("Total Pemasukan");
        totalLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        totalLabel.setBounds(0,6,300,30);

        // TOTAL NUMBER PANEL
        JPanel totalNumPanel = new JPanel();
        totalNumPanel.setLayout(null);
        totalNumPanel.setBounds(60,190,510,30);
        totalNumPanel.setBackground(Color.white);

        // TOTAL NUMBER LABEL
        JLabel totalNumLabel = new JLabel("Rp48.000,00");
        totalNumLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        totalNumLabel.setBounds(0,6,300,30);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(20,260,300,80);
        buttonPanel.setBackground(Color.white);

        // BUTTON SUBMIT
        JButton buttonSubmit = new JButton("Print Laporan Penjualan");
        buttonSubmit.setFocusPainted(false);
        buttonSubmit.setFont(new Font("Poppins", Font.BOLD, 16));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setBounds(80, 6,100, 60);
        buttonSubmit.setBackground(new Color(0x36459A));

        // // ADDING TO ITS PANEL
        titlePanel.add(titleLabel);
        itemPanel.add(itemLabel);
        totalPanel.add(totalLabel);
        buttonPanel.add(buttonSubmit);
        totalNumPanel.add(totalNumLabel);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));
        panel.setBackground(Color.white);
        panel.setBounds(600, 0, 600, 720);
        panel.setLayout(null);
        panel.add(totalPanel);
        panel.add(totalNumPanel);
        panel.add(buttonPanel);

        // // HISTORY SCROLL
        // JScrollPane historyScrollPane = new JScrollPane(panel);
        // historyScrollPane.setBackground(Color.white);
        // historyScrollPane.setLayout(null);
        // historyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // historyScrollPane.setBounds(600, 0, 600, 720);
        // historyScrollPane.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));

        // FRAME
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1200, 720);
        this.add(titlePanel);
        this.add(panel);
        this.add(itemPanel);
        this.add(itemsPanel);
        // this.add(memberTextPanel);
        // this.add(buttonPanel);
        this.add(historyScrollPane);
    }

    // public static void main(String[] args) {
    //     new LaporanPenjualan();
    // }
}
