package UI.Page;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.text.NumberFormatter;

import Entity.*;
import Utils.Collections.Observer;
import DataStore.DataStore;

public class UpdateBarang extends JPanel {

    // GUI Components
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField imageLocField;
    private JSpinner stockSpinner;
    private JFormattedTextField priceField;
    private JPanel dropdownPanel;

    public UpdateBarang() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Update Inventori Barang");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        titlePanel.add(titleLabel);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 20, 5, 20);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        JLabel nameLabel = new JLabel("Nama");
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        panel.add(nameLabel, c);
        c.gridy = 1;
        nameField = new JTextField(25);
        nameField.setFont(new Font("Poppins", Font.PLAIN, 14));
        panel.add(nameField, c);

        c.gridy = 2;
        JLabel categoryLabel = new JLabel("Kategori");
        categoryLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        panel.add(categoryLabel, c);
        c.gridy = 3;
        categoryField = new JTextField(25);
        categoryField.setFont(new Font("Poppins", Font.PLAIN, 14));
        panel.add(categoryField, c);

        c.gridy = 4;
        JLabel imageLabel = new JLabel("Gambar");
        imageLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        panel.add(imageLabel, c);

        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        imageLocField = new JTextField(15);
        imageLocField.setFont(new Font("Poppins", Font.PLAIN, 14));
        imageLocField.setEditable(false);
        panel.add(imageLocField, c);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 5;
        c2.anchor = GridBagConstraints.EAST;
        c2.insets = new Insets(5, 20, 5, 20);
        JButton chooseImageButton = new JButton("Choose File");
        chooseImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseImage();
            }
        });
        panel.add(chooseImageButton, c2);

        c.gridx = 1;
        c.gridy = 0;
        JLabel stockLabel = new JLabel("Stok");
        stockLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        panel.add(stockLabel, c);
        c.gridy = 1;
        stockSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) stockSpinner.getEditor();
        editor.getTextField().setColumns(14);
        stockSpinner.setFont(new Font("Poppins", Font.PLAIN, 14));
        panel.add(stockSpinner, c);

        // Add Price label and field to right column
        c.gridy = 2;
        JLabel priceLabel = new JLabel("Harga");
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        panel.add(priceLabel, c);
        c.gridy = 3;
        NumberFormatter formatter = new NumberFormatter();
        formatter.setMinimum(0);
        priceField = new JFormattedTextField(formatter);
        priceField.setColumns(15);
        priceField.setFont(new Font("Poppins", Font.PLAIN, 14));
        panel.add(priceField, c);

        dropdownPanel = new JPanel();

        DataStore data = DataStore.getInstance();
        
        data.getItems().addObserver(
            new Observer() {
                @Override
                public void update() {
                    ArrayList<Item> items = data.getItems().getElements();

                    String[] listItems = new String[items.size() + 1];
                    listItems[0] = "Pilih nama barang";
                    for (int i = 0; i < items.size(); i++) {
                        listItems[i + 1] = items.get(i).getName();
                    }
            
                    @SuppressWarnings("unchecked")
                    JComboBox<String> itemsDropdown = (JComboBox<String>) dropdownPanel.getComponent(0);
                    itemsDropdown.setModel(new DefaultComboBoxModel<>(listItems));
                }
            }
        );

        ArrayList<Item> items = data.getItems().getElements();

        String[] listItems = new String[items.size() + 1];
        listItems[0] = "Pilih nama barang";
        for (int i = 0; i < items.size(); i++) {
            listItems[i + 1] = items.get(i).getName();
        }

        JComboBox<String> itemsDropdown = new JComboBox<>(listItems);

        itemsDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) itemsDropdown.getSelectedItem();
                if (!selectedName.equals("Pilih nama barang")) {
                    Item selectedItem = null;
                    for (Item item : items) {
                        if (item.getName().equals(selectedName)) {
                            selectedItem = item;
                            break;
                        }
                    }
                    nameField.setText(selectedItem.getName());
                    categoryField.setText(selectedItem.getCategory());
                    stockSpinner.setValue(selectedItem.getStock());
                    priceField.setValue(selectedItem.getPrice());
                    imageLocField.setText(selectedItem.getImageUrl());
                }
            }
        });

        itemsDropdown.setRenderer(new DefaultListCellRenderer() {
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

        itemsDropdown.setPreferredSize(new Dimension(400, itemsDropdown.getPreferredSize().height));
        itemsDropdown.setFont(new Font("Poppins", Font.PLAIN, 14));
        dropdownPanel.add(itemsDropdown);

        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        c.insets = new Insets(40, 20, 0, 0);
        JButton updateButton = new JButton("Ubah");
        updateButton.setFont(new Font("Poppins", Font.BOLD, 14));
        updateButton.setForeground(Color.white);
        updateButton.setBackground(new Color(0x36459A));
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) itemsDropdown.getSelectedItem();
                if (!selectedName.equals("Pilih nama barang")) {
                    Item selectedItem = null;
                    for (Item item : items) {
                        if (item.getName().equals(selectedName)) {
                            selectedItem = item;
                            try {
                                update(selectedItem.getId());
                                System.out.println("Item has been updated successfully");
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }
        });

        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 10;
        c3.insets = new Insets(40, 0, 0, 20);
        JButton deleteButton = new JButton("Hapus");
        deleteButton.setFont(new Font("Poppins", Font.BOLD, 14));
        deleteButton.setForeground(Color.white);
        deleteButton.setBackground(new Color(0x943433));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) itemsDropdown.getSelectedItem();
                if (!selectedName.equals("Pilih nama barang")) {
                    Item selectedItem = null;
                    for (Item item : items) {
                        if (item.getName().equals(selectedName)) {
                            selectedItem = item;
                            try {
                                deleteItem(selectedItem.getId());
                                System.out.println("Item has been deleted successfully");
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            }
        });

        panel.add(deleteButton, c3);
        panel.add(updateButton, c);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(titlePanel);
        contentPanel.add(dropdownPanel);
        contentPanel.add(panel);

        this.add(contentPanel);
        setSize(1200, 720);
        setVisible(true);
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Image File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imageLocField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void update(Integer id) throws IOException {
        String name = nameField.getText();
        String category = categoryField.getText();
        String imageLoc = imageLocField.getText();
        int stock = (Integer) stockSpinner.getValue();
        int price = (Integer) priceField.getValue();

        Item updatedItem = new Item(id, name, category, price, imageLoc, stock);

        DataStore data = DataStore.getInstance();
        try {
            data.updateItem(id, updatedItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteItem(Integer id) throws IOException {
        DataStore data = DataStore.getInstance();
        try {
            data.removeItem(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UpdateBarang();
    }
}
