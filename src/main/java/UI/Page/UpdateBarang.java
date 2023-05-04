package UI.Page;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.text.NumberFormatter;

public class UpdateBarang extends JPanel {
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField imageLocField;
    private JSpinner stockSpinner;
    private JFormattedTextField priceField;

    public UpdateBarang() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Update Inventori Barang");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        titlePanel.add(titleLabel);

        JPanel dropdownPanel = new JPanel();
        String[] items = { "Pilih nama barang" };
        JComboBox<String> itemsDropdown = new JComboBox<>(items);
        itemsDropdown.setPreferredSize(new Dimension(400, itemsDropdown.getPreferredSize().height));
        itemsDropdown.setFont(new Font("Poppins", Font.PLAIN, 14));
        dropdownPanel.add(itemsDropdown);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 20, 5, 20);

        // Add Name label and field to left column
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

        // Add Category label and field to left column
        c.gridy = 2;
        JLabel categoryLabel = new JLabel("Kategori");
        categoryLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        panel.add(categoryLabel, c);
        c.gridy = 3;
        categoryField = new JTextField(25);
        categoryField.setFont(new Font("Poppins", Font.PLAIN, 14));
        panel.add(categoryField, c);

        // Add Image Loc label and field to left column
        c.gridy = 4;
        JLabel imageLabel = new JLabel("Gambar");
        imageLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        panel.add(imageLabel, c);

        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START; // set the anchor to the left
        imageLocField = new JTextField(15);
        imageLocField.setFont(new Font("Poppins", Font.PLAIN, 14));
        imageLocField.setEditable(false); // disable direct text editing
        panel.add(imageLocField, c);

        // create a new GridBagConstraints object for the chooseImageButton component
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 5;
        c2.anchor = GridBagConstraints.EAST;
        c2.insets = new Insets(5, 20, 5, 20); // set the insets
        JButton chooseImageButton = new JButton("Choose File");
        chooseImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseImage();
            }
        });
        panel.add(chooseImageButton, c2);

        // Add Stock label and field to right column
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
                updateItem();
            }
        });

        // create a new GridBagConstraints object for the chooseImageButton component
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 10;
        c3.insets = new Insets(40, 0, 0, 20); // set the insets
        JButton deleteButton = new JButton("Hapus");
        deleteButton.setFont(new Font("Poppins", Font.BOLD, 14));
        deleteButton.setForeground(Color.white);
        deleteButton.setBackground(new Color(0x943433));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });

        panel.add(deleteButton, c3);
        panel.add(updateButton, c);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(titlePanel);
        contentPanel.add(dropdownPanel);
        contentPanel.add(panel);

        // JPanel contentPanel = new JPanel(new BorderLayout());
        // contentPanel.add(topPanel, BorderLayout.PAGE_START);
        // contentPanel.add(panel, BorderLayout.CENTER);

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

    private void updateItem() {
        String name = nameField.getText();
        String category = categoryField.getText();
        String imageLoc = imageLocField.getText();
        int stock = (Integer) stockSpinner.getValue();
        int price = (Integer) priceField.getValue();

        System.out.println("Updated Data");
        System.out.println("Nama: " + name);
        System.out.println("Kategori: " + category);
        System.out.println("Lokasi Gambar: " + imageLoc);
        System.out.println("Stok: " + stock);
        System.out.println("Harga: " + price);
    }

    private void deleteItem() {
        String name = nameField.getText();
        String category = categoryField.getText();
        String imageLoc = imageLocField.getText();
        int stock = (Integer) stockSpinner.getValue();
        int price = (Integer) priceField.getValue();

        System.out.println("Deleted Data");
        System.out.println("Nama: " + name);
        System.out.println("Kategori: " + category);
        System.out.println("Lokasi Gambar: " + imageLoc);
        System.out.println("Stok: " + stock);
        System.out.println("Harga: " + price);
    }

    public static void main(String[] args) {
        new UpdateBarang();
    }
}
