package UI.Page;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.text.NumberFormatter;

public class TambahBarang extends JPanel {
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField imageLocField;
    private JSpinner stockSpinner;
    private JFormattedTextField priceField;

    public TambahBarang() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Tambah Inventori Barang");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        titlePanel.add(titleLabel);

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
        c2.insets = new Insets(5, 20, 5, 20);
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

        // Add button
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        c.insets = new Insets(40, 20, 0, 0);
        JButton addButton = new JButton("Tambah Barang");
        addButton.setFont(new Font("Poppins", Font.BOLD, 14));
        addButton.setForeground(Color.white);
        addButton.setBackground(new Color(0x36459A));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        panel.add(addButton, c);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(titlePanel, BorderLayout.PAGE_START);
        contentPanel.add(panel, BorderLayout.CENTER);

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

    private void addItem() {
        String name = nameField.getText();
        String category = categoryField.getText();
        String imageLoc = imageLocField.getText();
        int stock = (Integer) stockSpinner.getValue();
        int price = (Integer) priceField.getValue();

        System.out.println("Nama: " + name);
        System.out.println("Kategori: " + category);
        System.out.println("Lokasi Gambar: " + imageLoc);
        System.out.println("Stok: " + stock);
        System.out.println("Harga: " + price);
    }

    public static void main(String[] args) {
        new TambahBarang();
    }
}
