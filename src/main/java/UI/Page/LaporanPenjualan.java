package UI.Page;

import javax.swing.*;

import com.itextpdf.text.DocumentException;

import DataStore.DataStore;
import Utils.Printer;
import Entity.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LaporanPenjualan extends JPanel {

    private String printableString = "";
    private String tab = "    ";
    private Printer printer = new Printer();

    public LaporanPenjualan() {

        printableString = "LAPORAN PENJUALAN\n\n";

        setLayout(new BorderLayout()); // set the layout of the main panel to BorderLayout

        // create a panel to hold the left-side components
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(500, 720));
        leftPanel.setBackground(Color.white);

        // TITLE PANEL
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        // titlePanel.setBackground(Color.white);
        titlePanel.setPreferredSize(new Dimension(510, 50));

        // TITLE LABEL
        JLabel titleLabel = new JLabel("Laporan Penjualan");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 30));
        titleLabel.setBounds(0, 6, 510, 30);

        // create the history panel
        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS)); // use a vertical box layout
        historyPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));
        historyPanel.setBackground(Color.white);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(600, 80));

        // BUTTON SUBMIT
        JButton buttonSubmit = new JButton("Print PDF");
        buttonSubmit.setFocusPainted(false);
        buttonSubmit.setFont(new Font("Poppins", Font.BOLD, 16));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setBounds(80, 6, 201, 60);
        buttonSubmit.setBackground(new Color(0x36459A));

        historyPanel.removeAll();

        DataStore data = DataStore.getInstance();
        Double income = 0.0;
        ArrayList<FixedBill> fixedBills = data.getFixedBills().getElements();

        for (FixedBill bill : fixedBills) {

            // create the date panel
            JPanel datePanel = new JPanel();
            datePanel.setPreferredSize(new Dimension(400, 30));
            datePanel.setBackground(Color.lightGray);

            // add the date label to the date panel
            JLabel dateLabel = new JLabel(bill.getDate());
            dateLabel.setFont(new Font("Poppins", Font.BOLD, 14));
            datePanel.add(dateLabel);

            printableString += "Tanggal : " + bill.getDate();
            printableString += "\nBarang  :\n";

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

                JLabel priceLabel = new JLabel("Rp " + item.getPrice());
                priceLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
                gbc.gridx = 2;
                itemPanel.add(priceLabel, gbc);

                printableString += tab + "- " + item.getName() + " x" + item.getStock() + " (Rp "
                        + item.getPrice() + ")\n";
            }
            printableString += "Total   : Rp " + bill.getTotalPrice() + "\n\n";

            // create a panel to hold the date panel and item panel
            JPanel billPanel = new JPanel();
            billPanel.setLayout(new BoxLayout(billPanel, BoxLayout.Y_AXIS));
            billPanel.add(datePanel);
            billPanel.add(itemPanel);

            // calculate the total price of the bill
            double total = bill.getItems().stream().mapToDouble(Item::getPrice).sum();
            JLabel totalLabel = new JLabel("Total: Rp" + total);
            income += total;
            totalLabel.setFont(new Font("Poppins", Font.BOLD, 14));
            billPanel.add(totalLabel);

            // billPanel.setPreferredSize(new Dimension(400, 50));

            // add the bill panel to the history panel
            historyPanel.add(billPanel);

            // add some spacing between the bills
            historyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        printableString += "Total Pemasukan : Rp " + income + "\n\n";

        buttonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    printer.printComponentToPdf(printableString);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        // add the components to the left-side panel
        titlePanel.add(titleLabel);
        buttonPanel.add(buttonSubmit);
        leftPanel.add(titlePanel);
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

    // public static void main(String[] args) {
    // new LaporanPenjualan();
    // }
}
