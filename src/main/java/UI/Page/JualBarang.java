package UI.Page;
import javax.swing.*;

import DataStore.DataStore;
import Entity.Bill;
import Entity.Item;
import Utils.Collections.Observer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JualBarang extends JPanel {
    private JScrollPane scrollPane;
    JPanel stockPanel;
    private JPanel itemPanel;
    JScrollPane stockScrollPane;
    private Bill x;
    private DataStore d;

    public JualBarang() {
        try {
            x = new Bill(-1);
            d = DataStore.getInstance();
            d.startNewBill(x);

            // FILTER/SORT panel
            JPanel filterPanel = new JPanel();
            filterPanel.setLayout(null);
            filterPanel.setBackground(Color.white);
            filterPanel.setBounds(0, 0, 800, 50);
    
            // min harga
            JLabel minPriceLabel = new JLabel("Min. Price");
            minPriceLabel.setFont(new Font("Poppins", Font.BOLD, 12));
            minPriceLabel.setBounds(190, 6, 60, 40);
    
            // min harga nominal
            JLabel minPriceLabelNumber = new JLabel("Rp " + "0");
            minPriceLabelNumber.setFont(new Font("Poppins", Font.BOLD, 12));
            minPriceLabelNumber.setBounds(260, 6, 130, 40);
    
            // max harga
            JLabel maxPriceLabel = new JLabel("Max. Price");
            maxPriceLabel.setFont(new Font("Poppins", Font.BOLD, 12));
            maxPriceLabel.setBounds(400, 6, 70, 40);
    
            // max harga nominal
            JLabel maxPriceLabelNumber = new JLabel("Rp " + "1000000");
            maxPriceLabelNumber.setFont(new Font("Poppins", Font.BOLD, 12));
            maxPriceLabelNumber.setBounds(470, 6, 130, 40);
            
            // category dropdown
            String[] categories = {"Category"};
            JComboBox<String> kategori = new JComboBox<>(categories);
            kategori.setBounds(620, 10, 150, 30);
    
            // input searching
            JTextField searchField = new JTextField();
            searchField.setBounds(20, 10, 150, 30);
    
            filterPanel.add(searchField);
            filterPanel.add(minPriceLabel);
            filterPanel.add(minPriceLabelNumber);
            filterPanel.add(maxPriceLabel);
            filterPanel.add(maxPriceLabelNumber);
            filterPanel.add(kategori);
    
            // STOCK PANEL
            stockPanel = new JPanel();
            stockPanel.setLayout(new FlowLayout());
            stockPanel.setBackground(Color.white);
            
            // nanti traverse list, terus visualize satu satu
            int ctr = 0;
            
            for (Item item: d.getItems().getElements()) {
                ctr++;
                ImageIcon image1 = new ImageIcon(item.getImageUrl()); // path nanti diganti dengan image yang sesuai
                Image scaledImage = image1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                image1 = new ImageIcon(scaledImage);
                JButton button1 = new JButton(image1);
                button1.setBackground(new Color(0, 0, 0, 0));
                button1.setOpaque(false);
                button1.setBorderPainted(false);
                stockPanel.add(button1);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x.tambah(item);
                        rerenderBills();
                    }
                });   
            }
    
            ctr = ctr / 5;            
    
            Dimension panelSize = new Dimension(802, (ctr+1)*116);
            stockPanel.setPreferredSize(panelSize);
            
            this.stockScrollPane = new JScrollPane(stockPanel);
            stockScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            stockScrollPane.setBounds(0, 50, 800, 590);
            stockScrollPane.setBorder(BorderFactory.createEmptyBorder());
    
            // BILL PANEL
            // label
            JLabel billLabel = new JLabel("BILL");
            billLabel.setFont(new Font("Poppins", Font.BOLD, 24));
            billLabel.setBounds(15, 10, 50, 40);
    
            // daftar belanjaan
            // Item[] itemList = {new Item("Ayam", 12000, "../../../resources/images/icon.jpg", 5), new Item("Bebek", 15000, "../../../resources/images/icon.jpg", 7)};
            itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setBackground(Color.white);
            
            ctr = 0;
            for (Item item : x.getItems()) {
                ctr++;
                JPanel panelItem = new JPanel();
                panelItem.setBackground(Color.white);
                panelItem.setMaximumSize(new Dimension(1000, 90));
    
                JPanel itemNames = new JPanel();
                itemNames.setBounds(15, 0, 120, 20);
                itemNames.setBackground(Color.white);
                itemNames.setLayout(null);
                
                JPanel itemPrices = new JPanel();
                itemPrices.setBounds(210, 0, 120, 20);
                itemPrices.setBackground(Color.white);
                itemPrices.setLayout(null);
                
                JLabel nameLabel = new JLabel(item.getName());
                nameLabel.setHorizontalAlignment(JLabel.LEFT); // align text to left
                nameLabel.setBounds(0, 0, 120, 20);
                itemNames.add(nameLabel, BorderLayout.WEST); // add name label to left side of panel
    
                JLabel priceLabel = new JLabel("Rp " + item.getPrice());
                priceLabel.setHorizontalAlignment(JLabel.LEFT); // align text to right
                priceLabel.setBounds(0, 0, 120, 20);
                itemPrices.add(priceLabel, BorderLayout.EAST);

                JPanel addminPanel = new JPanel();
                addminPanel.setBounds(210, 30, 71, 19);
                addminPanel.setBackground(new Color(0xD9D9D9));
                
                panelItem.add(itemNames);
                panelItem.add(itemPrices);
                panelItem.add(addminPanel);
                panelItem.setLayout(null);
                itemPanel.add(panelItem);
            }

            Dimension billPanelSize = new Dimension(350, (ctr)*80);
            itemPanel.setPreferredSize(billPanelSize);
    
            scrollPane = new JScrollPane(itemPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(15, 60, 370, 270);
            scrollPane.setBackground(Color.white);
            scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x36459A)));
    
            // checkout button
            JButton checkoutButton = new JButton("CHECKOUT");
            checkoutButton.setFocusPainted(false);
            checkoutButton.setFont(new Font("Poppins", Font.BOLD, 32));
            checkoutButton.setForeground(Color.white);
            checkoutButton.setBounds(30, 530, 329, 79);
            checkoutButton.setBackground(new Color(0x36459A));
    
            // member dropdown
            String[] items = {"Pilih nama member"};
            JComboBox<String> dropdown = new JComboBox<>(items);
            dropdown.setBounds(15, 420, 150, 30);
    
            // subtotal label
            JLabel subtotalLabel = new JLabel("Subtotal");
            subtotalLabel.setFont(new Font("Poppins", Font.BOLD, 16));
            subtotalLabel.setBounds(15, 340, 100, 40);
            
            // subtotal nominal
            JLabel subtotalLabelNumber = new JLabel("RP 27.000");
            subtotalLabelNumber.setFont(new Font("Poppins", Font.PLAIN, 16));
            subtotalLabelNumber.setBounds(230, 340, 100, 40);
    
            // diskon label
            JLabel discountLabel = new JLabel("Diskon");
            discountLabel.setFont(new Font("Poppins", Font.BOLD, 16));
            discountLabel.setBounds(15, 360, 100, 40);
    
            // diskon nominal
            JLabel discountLabelNumbers = new JLabel("RP 2.700");
            discountLabelNumbers.setFont(new Font("Poppins", Font.PLAIN, 16));
            discountLabelNumbers.setBounds(230, 360, 100, 40);
    
            // total price
            JLabel totalLabel = new JLabel("Total:");
            totalLabel.setFont(new Font("Poppins", Font.BOLD, 16));
            totalLabel.setBounds(230, 400, 70, 40);
    
            // nominal total price
            JLabel totalLabelNumbers = new JLabel("Rp 25.000");
            totalLabelNumbers.setFont(new Font("Poppins", Font.PLAIN, 16));
            totalLabelNumbers.setBounds(230, 420, 100, 40);
    
            // tombol save bill
            JButton saveBillButton = new JButton("Save Bill");
            saveBillButton.setFocusPainted(false);
            saveBillButton.setFont(new Font("Poppins", Font.BOLD, 16));
            saveBillButton.setForeground(Color.black);
            saveBillButton.setBounds(1, 470,199, 48);
            saveBillButton.setBackground(new Color(0xEBEBEB));
    
            // tombol print bill
            JButton printBillButton = new JButton("Print Bill");
            printBillButton.setFocusPainted(false);
            printBillButton.setFont(new Font("Poppins", Font.BOLD, 16));
            printBillButton.setForeground(Color.black);
            printBillButton.setBounds(199, 470,199, 48);
            printBillButton.setBackground(new Color(0xEBEBEB));
    
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(0x36459A)));
            panel.setBackground(Color.white);
            panel.setBounds(800, 0, 400, 620);
            panel.setLayout(null);
            panel.add(scrollPane);
            panel.add(totalLabel);
            panel.add(totalLabelNumbers);
            panel.add(subtotalLabel);
            panel.add(subtotalLabelNumber);
            panel.add(discountLabel);
            panel.add(discountLabelNumbers);
            panel.add(checkoutButton);
            panel.add(saveBillButton);
            panel.add(printBillButton);
            panel.add(billLabel);
            panel.add(dropdown);
    
            // frame pagenya
            // JFrame frame = new JFrame();
            this.setLayout(null);
            this.setVisible(true);
            this.setSize(1200, 720);
            this.add(filterPanel);
            this.add(panel);
            this.add(stockScrollPane);
            // frame.add(backgroundPanel);
            x.tambah(new Item("tes", "tes", 1, 10));

            d.getItems().addObserver(
                new Observer() {
                    @Override
                    public void update() {
                        rerenderItems();
                    }   
                }
            );
            
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    public void rerenderItems() {
        this.stockPanel.removeAll();
        stockPanel = new JPanel();
        stockPanel.setLayout(new FlowLayout());
        stockPanel.setBackground(Color.white);
        
        // nanti traverse list, terus visualize satu satu
        int ctr = 0;
        
        for (Item item: d.getItems().getElements()) {
            ctr++;
            ImageIcon image1 = new ImageIcon(item.getImageUrl()); // path nanti diganti dengan image yang sesuai
            Image scaledImage = image1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            image1 = new ImageIcon(scaledImage);
            JButton button1 = new JButton(image1);
            button1.setBackground(new Color(0, 0, 0, 0));
            button1.setOpaque(false);
            button1.setBorderPainted(false);
            stockPanel.add(button1);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    x.tambah(item);
                }
            });   
        }

        ctr = ctr / 5;            

        Dimension panelSize = new Dimension(802, (ctr+1)*116);
        stockPanel.setPreferredSize(panelSize);
        stockScrollPane.setViewportView(stockPanel);
    }

    public void rerenderBills () {
        itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.white);

        int ctr = 0;
        for (Item item : x.getItems()) {
            ctr++;
            JPanel panelItem = new JPanel();
            panelItem.setBackground(Color.white);
            panelItem.setMaximumSize(new Dimension(1000, 90));

            JPanel itemNames = new JPanel();
            itemNames.setBounds(15, 0, 120, 20);
            itemNames.setBackground(Color.white);
            itemNames.setLayout(null);
            
            JPanel itemPrices = new JPanel();
            itemPrices.setBounds(210, 0, 120, 20);
            itemPrices.setBackground(Color.white);
            itemPrices.setLayout(null);
            
            JLabel nameLabel = new JLabel(item.getName());
            nameLabel.setHorizontalAlignment(JLabel.LEFT); // align text to left
            nameLabel.setBounds(0, 0, 120, 20);
            itemNames.add(nameLabel, BorderLayout.WEST); // add name label to left side of panel

            JLabel priceLabel = new JLabel("Rp " + item.getPrice());
            priceLabel.setHorizontalAlignment(JLabel.LEFT); // align text to right
            priceLabel.setBounds(25, 0, 120, 20);
            itemPrices.add(priceLabel, BorderLayout.EAST);

            // tombol untuk menambah/mengurangi stok
            JPanel addminPanel = new JPanel();
            addminPanel.setLayout(null);
            addminPanel.setBounds(235, 30, 90, 40);
            addminPanel.setBackground(new Color(0xD9D9D9));


            JButton min = new JButton("");
            min.setBackground(new Color(0xEDEDED));
            min.setLayout(null);
            min.setBounds(0, 0, 25, 25);

            JButton plus = new JButton("+");
            plus.setFont(new Font("Poppins", Font.PLAIN, 12));
            plus.setBackground(new Color(0xEDEDED));
            plus.setLayout(null);
            plus.setBounds(50, 0, 40, 20);

            addminPanel.add(min);
            addminPanel.add(plus);
            
            panelItem.add(itemNames);
            panelItem.add(itemPrices);
            panelItem.add(addminPanel);
            panelItem.setLayout(null);
            itemPanel.add(panelItem);
        }

        Dimension billPanelSize = new Dimension(350, (ctr)*80);
        itemPanel.setPreferredSize(billPanelSize);
        scrollPane.setViewportView(itemPanel);
    }

    public static void main(String[] args) throws Exception {  
        new JualBarang();  
    } 
}   
