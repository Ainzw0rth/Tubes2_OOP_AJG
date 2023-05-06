package UI.Page;
import javax.swing.*;
import java.awt.*;

import Entity.Item;

public class tes extends JFrame {

    public tes() {
        // TITLE PANEL
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        // titlePanel.setBackground(Color.white);
        titlePanel.setBounds(80, 80, 510, 50);

        // TITLE LABEL
        JLabel titleLabel = new JLabel("Riwayat Transaksi");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setBounds(0, 6, 600, 30);

        // MEMBER PANEL
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(null);
        memberPanel.setBounds(80,120,510,30);

        // MEMBER LABEL
        JLabel memberLabel = new JLabel("Member");
        memberLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        memberLabel.setBounds(0, 6, 600, 30);

        // MEMBER TEXT PANEL
        JPanel memberTextPanel = new JPanel();
        memberTextPanel.setLayout(null);
        memberTextPanel.setBounds(80,170,510,30);

        // MEMBER TEXT FIELD
        JTextField memberTextArea = new JTextField();
        memberTextArea.setBounds(0,6,300,30);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(-132,220,600,80);

        // BUTTON SUBMIT
        JButton buttonSubmit = new JButton("Tampilkan Riwayat");
        buttonSubmit.setFocusPainted(false);
        buttonSubmit.setFont(new Font("Poppins", Font.BOLD, 16));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setBounds(80, 6,201, 60);
        buttonSubmit.setBackground(new Color(0x36459A));

        // ADDING TO ITS PANEL
        titlePanel.add(titleLabel);
        memberPanel.add(memberLabel);
        memberTextPanel.add(memberTextArea);
        buttonPanel.add(buttonSubmit);

        // HISTORY PANEL
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));
        panel.setBackground(Color.white);
        panel.setBounds(600, 0, 600, 720);
        panel.setLayout(null);

        // FOR LOOP ALL ITEMS IN HISTORY
        Item[] itemList = {};
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.white);

        for (Item item : itemList) {
            JPanel panelItem = new JPanel();
            panelItem.setBackground(Color.white);
            panelItem.setMaximumSize(new Dimension(1000, 30));

            JPanel itemNames = new JPanel();
            itemNames.setBounds(15, 0, 120, 20);
            itemNames.setBackground(Color.white);
            itemNames.setLayout(null);

            JPanel itemQuantity = new JPanel();
            itemQuantity.setBounds(110, 0, 120, 20);
            itemQuantity.setBackground(Color.white);
            itemQuantity.setLayout(null);

            JPanel itemPrices = new JPanel();
            itemPrices.setBounds(210, 0, 120, 20);
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

            panelItem.add(itemNames);
            panelItem.add(itemPrices);
            panelItem.setLayout(null);
            itemPanel.add(panelItem);
        }

        // HISTORY SCROLL
        JScrollPane historyScrollPane = new JScrollPane(panel);
        historyScrollPane.setBackground(Color.white);
        historyScrollPane.setLayout(null);
        historyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        historyScrollPane.setBounds(600, 0, 600, 720);
        historyScrollPane.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));

        // FRAME
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1200, 720);
        this.add(titlePanel);
        this.add(memberPanel);
        this.add(memberTextPanel);
        this.add(buttonPanel);
        this.add(historyScrollPane);
    }

    public static void main(String[] args) {
        new tes();
    }
}
