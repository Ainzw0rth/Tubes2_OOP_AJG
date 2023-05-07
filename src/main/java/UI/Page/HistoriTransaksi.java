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

        buttonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Integer custId = 0;

                String selectedName = (String) memberDropdown.getSelectedItem();
                if (!selectedName.equals("Pilih nama member")) {
                    Member selectedMember = null;
                    for (Member member : members) {
                        if (member.getName().equals(selectedName)) {
                            selectedMember = member;
                            custId = selectedMember.getId();
                            break;
                        }
                    }
                }

                DataStore data = DataStore.getInstance();

                try {
                    ArrayList<FixedBill> fixedBills = data.getFixedBillsByCustId(custId);
                    JPanel fixedBillPanel = new JPanel();
                    fixedBillPanel.setBackground(Color.white);

                    for (int i = 0; i < fixedBills.size(); i++) {

                        // DATE PANEL
                        JPanel datePanel = new JPanel();
                        datePanel.setBounds(20, i*50, 400, 100);

                        // DATE LABEL
                        JLabel dateLabel = new JLabel(fixedBills.get(i).getDate());
                        dateLabel.setFont(new Font("Poppins", Font.BOLD, 14));
                        datePanel.add(dateLabel);

                        // ITEM PANEL
                        JPanel itemPanel = new JPanel();
                        itemPanel.setLayout(new GridBagLayout());

                        // LOOP ALL ITEM
                        for (int j = 0; j < fixedBills.get(i).getItems().size(); j++) {
                            GridBagConstraints gbc = new GridBagConstraints();
                            gbc.gridx = 0;
                            gbc.gridy = j;
                            gbc.anchor = GridBagConstraints.WEST;
                            gbc.weightx = 1;
                            gbc.insets = new Insets(5, 5, 5, 5);

                            // ITEM NAME LABEL
                            JLabel nameLabel = new JLabel(fixedBills.get(i).getItems().get(j).getName());
                            nameLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                            itemPanel.add(nameLabel, gbc);

                            System.out.println(fixedBills.get(i).getItems().get(j).getName());

                            gbc.gridx = 1;

                            // ITEM QTY LABEL
                            JLabel qtyLabel = new JLabel(fixedBills.get(i).getItems().get(j).getStock() + "x");
                            qtyLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                            itemPanel.add(qtyLabel, gbc);

                            gbc.gridx = 2;

                            // ITEM PRICE LABEL
                            JLabel priceLabel = new JLabel(fixedBills.get(i).getItems().get(j).getPrice() + "");
                            priceLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                            itemPanel.add(priceLabel, gbc);
                        }

                        panel.add(datePanel);
                        panel.add(itemPanel);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

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
        // historyScrollPane.setBackground(Color.white);
        historyScrollPane.setLayout(null);
        historyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        historyScrollPane.setBounds(600, 0, 600, 720);
        historyScrollPane.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));
        historyScrollPane.add(panel);

        // FRAME
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1200, 720);
        this.add(titlePanel);
        this.add(memberPanel);
        this.add(memberTextPanel);
        this.add(buttonPanel);
        this.add(imagePanel);
        this.add(historyScrollPane);
    }

    public static void main(String[] args) {
        new HistoriTransaksi();
    }
}
