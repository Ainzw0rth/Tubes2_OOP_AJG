package UI.Page;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class PenyimpananData extends JPanel {
    private String selectedExt;
    private String selectedDir;

    public PenyimpananData(){
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        initExtension();
        initLocation();
    } 
    
    private void initExtension() {
        JPanel extensionPanel = new JPanel();
        extensionPanel.setLayout(new BoxLayout(extensionPanel, BoxLayout.PAGE_AXIS));
        this.add(extensionPanel);

        // Title
        JLabel title = new JLabel("Format Penyimpanan");
        title.setBounds(50,40,300,50);
        title.setFont(new Font("Poppins", Font.BOLD, 35));
        extensionPanel.add(title);

        // Dropdown
        JPanel extPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] extList = { ".JSON", ".XML", ".OBJ" };
        JComboBox<String> extDropdown = new JComboBox<>(extList);
        extDropdown.setPreferredSize(new Dimension(200, 30));
        extDropdown.setFont(new Font("Poppins", Font.PLAIN, 14));
        extPanel.add(Box.createRigidArea(new Dimension(70, 0)));
        extPanel.add(extDropdown);
        this.selectedExt = (String) extDropdown.getSelectedItem();
        extensionPanel.add(extPanel);
    }

    private void initLocation(){
        JPanel locPanel = new JPanel();
        locPanel.setLayout(new BoxLayout(locPanel, BoxLayout.PAGE_AXIS));
        this.add(locPanel);

        // Title
        JLabel locLabel = new JLabel("Lokasi Penyimpanan");
        locLabel.setBounds(50,40,300,50);
        locLabel.setFont(new Font("Poppins", Font.BOLD, 35));
        locPanel.add(locLabel);

        // Location
        JPanel choosePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        locPanel.add(choosePanel);

        // location field
        JTextField dbLocField = new JTextField(15);
        dbLocField.setFont(new Font("Poppins", Font.PLAIN, 14));
        dbLocField.setEditable(false); // disable direct text editing
        dbLocField.setText(this.selectedDir);
        choosePanel.add(dbLocField);

        // directory chooser
        JButton chooseButton = new JButton("Choose Location");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseLoc();
            }
        });
        choosePanel.add(chooseButton);
    }

    private void chooseLoc() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Database Location");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.selectedDir = selectedFile.getAbsolutePath();
        }
    }

    public static void main(String[] args) {
        
    }
}
