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
        setLayout(new BorderLayout()); // set the layout of the main panel to BorderLayout

        // create a panel to hold the left-side components
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(500, 720));
        leftPanel.setBackground(Color.white);

        // TITLE PANEL
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setPreferredSize(new Dimension(510, 50));

        // TITLE LABEL
        JLabel titleLabel = new JLabel("Riwayat Transaksi Member");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setBounds(0, 6, 510, 30);

        // MEMBER PANEL
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(null);
        memberPanel.setPreferredSize(new Dimension(510, 30));

        // MEMBER LABEL
        JLabel memberLabel = new JLabel("Member");
        memberLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        memberLabel.setBounds(0, 6, 510, 30);

        // MEMBER TEXT PANEL
        JPanel memberTextPanel = new JPanel();
        memberTextPanel.setLayout(null);
        memberTextPanel.setPreferredSize(new Dimension(510, 30));

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

        // MEMBER TEXT FIELD
        memberDropdown.setBounds(0, 6, 300, 30);

        // create the history panel
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS)); // use a vertical box layout
        historyPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));
        historyPanel.setBackground(Color.white);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(600, 80));

        // BUTTON SUBMIT
        JButton buttonSubmit = new JButton("Tampilkan Riwayat");
        buttonSubmit.setFocusPainted(false);
        buttonSubmit.setFont(new Font("Poppins", Font.BOLD, 16));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setBounds(80, 6, 201, 60);
        buttonSubmit.setBackground(new Color(0x36459A));

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

                    for (FixedBill bill : fixedBills) {

                        // create the date panel
                        JPanel datePanel = new JPanel();
                        datePanel.setPreferredSize(new Dimension(400, 30));
                        datePanel.setBackground(Color.lightGray);

                        // add the date label to the date panel
                        JLabel dateLabel = new JLabel(bill.getDate());
                        dateLabel.setFont(new Font("Poppins", Font.BOLD, 14));
                        datePanel.add(dateLabel);

                        // create the item panel
                        JPanel itemPanel = new JPanel(new GridBagLayout());
                        itemPanel.setBackground(Color.white);

                        // add the item name, quantity and price labels to the item panel
                        for (Item item : bill.getItems()) {
                            GridBagConstraints gbc = new GridBagConstraints();
                            gbc.gridx = 0;
                            gbc.gridy = GridBagConstraints.RELATIVE;
                            gbc.anchor = GridBagConstraints.WEST;
                            gbc.weightx = 1;
                            gbc.insets = new Insets(5, 5, 5, 5);

                            JLabel nameLabel = new JLabel(item.getName());
                            nameLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                            itemPanel.add(nameLabel, gbc);

                            JLabel qtyLabel = new JLabel(item.getStock() + "x");
                            qtyLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                            gbc.gridx = 1;
                            itemPanel.add(qtyLabel, gbc);

                            JLabel priceLabel = new JLabel("$" + item.getPrice());
                            priceLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                            gbc.gridx = 2;
                            itemPanel.add(priceLabel, gbc);
                        }

                        // create a panel to hold the date panel and item panel
                        JPanel billPanel = new JPanel();
                        billPanel.setLayout(new BoxLayout(billPanel, BoxLayout.Y_AXIS));
                        billPanel.add(datePanel);
                        billPanel.add(itemPanel);

                        // calculate the total price of the bill
                        double total = bill.getItems().stream().mapToDouble(Item::getPrice).sum();
                        JLabel totalLabel = new JLabel("Total: Rp" + total);
                        totalLabel.setFont(new Font("Poppins", Font.BOLD, 14));
                        billPanel.add(totalLabel);

                        // add the bill panel to the history panel
                        historyPanel.add(billPanel);

                        // add some spacing between the bills
                        historyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        // add the components to the left-side panel
        titlePanel.add(titleLabel);
        memberPanel.add(memberLabel);
        memberTextPanel.add(memberDropdown);
        buttonPanel.add(buttonSubmit);
        leftPanel.add(titlePanel);
        leftPanel.add(memberPanel);
        leftPanel.add(memberTextPanel);
        leftPanel.add(buttonPanel);

        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(600, 396));

        ImageIcon mrbeast = new ImageIcon(getClass().getResource("/images/riwayat/mrBeast.png"));
        JLabel mrbeastImage = new JLabel(mrbeast);
        mrbeastImage.setHorizontalAlignment(JLabel.LEFT);
        mrbeastImage.setVerticalAlignment(JLabel.BOTTOM);
        mrbeastImage.setPreferredSize(new Dimension(511, 396));
        imagePanel.add(mrbeastImage);
        leftPanel.add(imagePanel);

        // create a scroll pane for the history panel
        JScrollPane scrollPane = new JScrollPane(historyPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(600, 600));

        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        new HistoriTransaksi();
    }
}
