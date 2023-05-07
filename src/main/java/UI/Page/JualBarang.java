package UI.Page;
import javax.swing.*;

import DataStore.DataStore;
import Entity.Bill;
import Entity.Item;
import Entity.Member;
import UI.App;
import UI.IApp;
import Utils.Collections.Observer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JualBarang extends JPanel {
    private JScrollPane scrollPane;
    JPanel stockPanel;
    private JPanel itemPanel;
    private JScrollPane stockScrollPane;
    private Bill x;
    private DataStore d;

    private int minprice = 0;
    private int maxprice = 999999999;

    
    private JLabel subtotalLabelNumber;

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
            JTextField minPriceLabelNumber = new JTextField();
            minPriceLabelNumber.setFont(new Font("Poppins", Font.BOLD, 12));
            minPriceLabelNumber.setBounds(260, 10, 130, 30);
            minPriceLabelNumber.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inp = minPriceLabelNumber.getText();
                    try {
                        int price = Integer.parseInt(inp);
                        minprice = price;
                        rerenderItems();
                    } catch (Exception a) {
                    }
                }
            });
            
            // max harga
            JLabel maxPriceLabel = new JLabel("Max. Price");
            maxPriceLabel.setFont(new Font("Poppins", Font.BOLD, 12));
            maxPriceLabel.setBounds(400, 6, 70, 40);
    
            // max harga nominal
            JTextField maxPriceLabelNumber = new JTextField();
            maxPriceLabelNumber.setFont(new Font("Poppins", Font.BOLD, 12));
            maxPriceLabelNumber.setBounds(470, 10, 130, 30);
            maxPriceLabelNumber.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inp = maxPriceLabelNumber.getText();
                    try {
                        int price = Integer.parseInt(inp);
                        maxprice = price;
                        rerenderItems();
                    } catch (Exception a) {
                    }
                }
            });
            
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
            
            // BILL PANEL
            // label
            JLabel billLabel = new JLabel("BILL");
            billLabel.setFont(new Font("Poppins", Font.BOLD, 24));
            billLabel.setBounds(15, 10, 50, 40);

            // checkout button
            JButton checkoutButton = new JButton("CHECKOUT");
            checkoutButton.setFocusPainted(false);
            checkoutButton.setFont(new Font("Poppins", Font.BOLD, 32));
            checkoutButton.setForeground(Color.white);
            checkoutButton.setBounds(30, 530, 329, 79);
            checkoutButton.setBackground(new Color(0x36459A));
            checkoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkoutItems();
                }
            });
    
            // member dropdown
            String[] items = {"Pilih nama member"};
            JComboBox<String> dropdown = new JComboBox<>(items);
            dropdown.setBounds(15, 420, 150, 30);
    
            // subtotal label
            JLabel subtotalLabel = new JLabel("Subtotal");
            subtotalLabel.setFont(new Font("Poppins", Font.BOLD, 16));
            subtotalLabel.setBounds(15, 340, 100, 40);
            
            // subtotal nominal
            subtotalLabelNumber = new JLabel("Rp " + Integer.toString(x.getTotalPrice()));
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
            // STOCK PANEL
            stockPanel = new JPanel();
            stockPanel.setLayout(new FlowLayout());
            stockPanel.setBackground(Color.white);
            
            stockPanel = new JPanel();
            this.stockScrollPane = new JScrollPane(stockPanel);
            stockScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            stockScrollPane.setBounds(0, 50, 800, 590);
            stockScrollPane.setBorder(BorderFactory.createEmptyBorder());

            rerenderItems();
    
            // daftar belanjaan
            itemPanel = new JPanel();
            scrollPane = new JScrollPane(itemPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(15, 60, 370, 270);
            scrollPane.setBackground(Color.white);
            scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x36459A)));
            rerenderBills();
            
    
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

            d.getItems().addObserver(
                new Observer() {
                    @Override
                    public void update() {
                        rerenderItems();
                        rerenderBills();
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
            if (item.getPrice() <= maxprice && item.getPrice() >= minprice) {
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
            if (item.getPrice() <= maxprice && item.getPrice() >= minprice) { 
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
    
                // tombol untuk menambah/mengurangi stok
                JPanel addminPanel = new JPanel();
                addminPanel.setLayout(null);
                addminPanel.setBounds(200, 30, 130, 20);
                addminPanel.setBackground(new Color(0xD9D9D9));
    
                JButton min = new JButton("-");
                min.setFont(new Font("Poppins", Font.PLAIN, 10));
                min.setBackground(new Color(0xEDEDED));
                min.setLayout(null);
                min.setBounds(0, 0, 40, 20);
                min.setFocusPainted(false);
                min.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x.hapus(item);
                        rerenderBills();
                    }
                });
    
                JButton plus = new JButton("+");
                plus.setFont(new Font("Poppins", Font.PLAIN, 10));
                plus.setBackground(new Color(0xEDEDED));
                plus.setLayout(null);
                plus.setBounds(90, 0, 40, 20);
                plus.setFocusPainted(false);
                plus.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x.tambah(item);
                        rerenderBills();
                    }
                });
    
                JPanel amountPanel = new JPanel(new GridBagLayout());
                amountPanel.setBounds(40, 0, 50, 20);
    
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.anchor = GridBagConstraints.CENTER;
    
                JLabel amountText = new JLabel(Integer.toString(item.getStock()));
    
                amountPanel.add(amountText, gbc);
                
                addminPanel.add(min);
                addminPanel.add(plus);
                addminPanel.add(amountPanel);
                
                panelItem.add(itemNames);
                panelItem.add(itemPrices);
                panelItem.add(addminPanel);
                panelItem.setLayout(null);
                itemPanel.add(panelItem);
            }
        }

        Dimension billPanelSize = new Dimension(350, (ctr)*70);
        itemPanel.setPreferredSize(billPanelSize);
        scrollPane.setViewportView(itemPanel);

        subtotalLabelNumber.setText("Rp " + Integer.toString(x.getTotalPrice()));
    }

    public void checkoutItems() {
        // TODO : bikin page transaksi berhasil
        IApp app = App.getInstance();
        app.addTab("Transaksi Berhasil", new JualBarang());
    }

    public static void main(String[] args) throws Exception {  
        new JualBarang();  
    } 
}   
