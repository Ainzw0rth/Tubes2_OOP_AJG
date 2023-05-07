package UI.Page;

import javax.swing.*;

import DataStore.DataStore;
import Utils.Collections.Observer;
import Entity.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HistoriTransaksi extends JPanel {
    public HistoriTransaksi() {
        // TITLE PANEL
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        // titlePanel.setBackground(Color.white);
        titlePanel.setBounds(80, 20, 510, 50);

        // TITLE LABEL
        JLabel titleLabel = new JLabel("Riwayat Transaksi Member");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setBounds(0, 6, 600, 30);

        // MEMBER PANEL
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(null);
        memberPanel.setBounds(80, 60, 510, 30);

        // MEMBER LABEL
        JLabel memberLabel = new JLabel("Member");
        memberLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        memberLabel.setBounds(0, 6, 600, 30);

        // MEMBER TEXT PANEL
        JPanel memberTextPanel = new JPanel();
        memberTextPanel.setLayout(null);
        memberTextPanel.setBounds(80, 90, 510, 30);

        JPanel memberDropdownPanel = new JPanel();

        DataStore data = DataStore.getInstance();

        data.getMembers().addObserver(
                new Observer() {
                    @Override
                    public void update() {
                        ArrayList<Member> members = data.getMembers().getElements();

                        String[] memberList = new String[members.size() + 1];
                        memberList[0] = "Pilih nama member";
                        for (int i = 0; i < members.size(); i++) {
                            memberList[i + 1] = members.get(i).getName();
                        }

                        @SuppressWarnings("unchecked")
                        JComboBox<String> memberDropdown = (JComboBox<String>) memberDropdownPanel.getComponent(0);
                        memberDropdown.setModel(new DefaultComboBoxModel<>(memberList));
                    }
                });

        ArrayList<Member> members = data.getMembers().getElements();

        String[] memberList = new String[members.size() + 1];
        memberList[0] = "Pilih nama member";
        for (int i = 0; i < members.size(); i++) {
            memberList[i + 1] = members.get(i).getName();
        }

        JComboBox<String> memberDropdown = new JComboBox<>(memberList);

        memberDropdown.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == 0) {
                    setEnabled(false);
                    setFont(getFont().deriveFont(Font.ITALIC));
                } else {
                    setEnabled(true);
                    setFont(getFont().deriveFont(Font.PLAIN));
                }
                return this;
            }
        });

        // MEMBER TEXT FIELD
        // JTextField memberTextArea = new JTextField();
        memberDropdown.setBounds(0, 6, 300, 30);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(-132, 140, 600, 80);

        // BUTTON SUBMIT
        JButton buttonSubmit = new JButton("Tampilkan Riwayat");
        buttonSubmit.setFocusPainted(false);
        buttonSubmit.setFont(new Font("Poppins", Font.BOLD, 16));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setBounds(80, 6, 201, 60);
        buttonSubmit.setBackground(new Color(0x36459A));

        // HISTORY PANEL
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));
        panel.setBackground(Color.white);
        panel.setBounds(600, 0, 600, 720);
        panel.setLayout(null);

        Item barang1 = new Item(1, "lala", "kskakaks", 10000, "asjjsadjajd", 10);
        Item barang2 = new Item(1, "lala", "kskakaks", 10000, "asjjsadjajd", 10);
        LinkedList<Item> listBar = new LinkedList<Item>();
        listBar.add(barang1);
        listBar.add(barang2);
        FixedBill fix1 = new FixedBill(1, 40000, listBar, 1, "10 April 2023");
        FixedBill fix2 = new FixedBill(1, 40000, listBar, 1, "11 April 2025");
        ArrayList<FixedBill> bills = new ArrayList<>();
        bills.add(fix1);
        bills.add(fix2);

        for (int i = 0; i < bills.size(); i++) {

            // DATE PANEL
            JPanel datePanel = new JPanel();
            datePanel.setBounds(20, i*50, 400, 100);

            // DATE LABEL
            JLabel dateLabel = new JLabel(bills.get(i).getDate());
            dateLabel.setFont(new Font("Poppins", Font.BOLD, 14));
            datePanel.add(dateLabel);

            System.out.println("a");

            // ITEM PANEL
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new GridBagLayout());

            // LOOP ALL ITEM
            for (int j = 0; j < bills.get(i).getItems().size(); j++) {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = j;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.weightx = 1;
                gbc.insets = new Insets(5, 5, 5, 5);

                // ITEM NAME LABEL
                JLabel nameLabel = new JLabel(bills.get(i).getItems().get(j).getName());
                nameLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                itemPanel.add(nameLabel, gbc);

                System.out.println(bills.get(i).getItems().get(j).getName());

                gbc.gridx = 1;

                // ITEM QTY LABEL
                JLabel qtyLabel = new JLabel(bills.get(i).getItems().get(j).getStock() + "x");
                qtyLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                itemPanel.add(qtyLabel, gbc);

                gbc.gridx = 2;

                // ITEM PRICE LABEL
                JLabel priceLabel = new JLabel(bills.get(i).getItems().get(j).getPrice() + "");
                priceLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                itemPanel.add(priceLabel, gbc);
            }
            panel.add(datePanel);
            panel.add(itemPanel);
        }

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(0, 220, 600, 396);

        ImageIcon mrbeast = new ImageIcon(getClass().getResource("/images/riwayat/mrBeast.png"));
        JLabel mrbeastImage = new JLabel(mrbeast);
        mrbeastImage.setHorizontalAlignment(JLabel.LEFT);
        mrbeastImage.setVerticalAlignment(JLabel.BOTTOM);
        mrbeastImage.setBounds(0, 0, 511, 396);

        // ADDING TO ITS PANEL
        titlePanel.add(titleLabel);
        memberPanel.add(memberLabel);
        memberTextPanel.add(memberDropdown);
        buttonPanel.add(buttonSubmit);
        imagePanel.add(mrbeastImage);


        // HISTORY SCROLL
        JScrollPane historyScrollPane = new JScrollPane(panel);
        historyScrollPane.setBackground(Color.white);
        historyScrollPane.setLayout(null);
        historyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        historyScrollPane.setBounds(600, 0, 600, 720);
        historyScrollPane.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));
        // historyScrollPane.add(panel);

        // FRAME
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1200, 720);
        this.add(titlePanel);
        this.add(memberPanel);
        this.add(memberTextPanel);
        this.add(buttonPanel);
        this.add(imagePanel);
        this.add(panel);
        this.add(historyScrollPane);
    }

    public static void main(String[] args) {
        new HistoriTransaksi();
    }
}
