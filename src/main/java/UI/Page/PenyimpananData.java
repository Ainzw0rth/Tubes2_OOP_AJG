package UI.Page;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.*;

import DataStore.DataStore;


public class PenyimpananData extends JPanel {
    private String selectedExt;
    private String selectedDir;

    public PenyimpananData(){
        DataStore data = DataStore.getInstance();
        Map<String, String> config = new HashMap<>(data.getConfig());
        
        this.selectedExt = config.get("ext");
        this.selectedDir = config.get("dirPath");
        init();
    }

    private void init() {
        this.setLayout(null);
        this.setSize(1200, 720);

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel title = new JLabel("Penyimpanan Data");
        title.setBounds(100,40,300,50);
        title.setFont(new Font("Poppins", Font.BOLD, 30));
        this.add(title);

        initExtension();
        initLocation();
    } 
    
    private void initExtension() {
        JPanel extensionPanel = new JPanel();
        extensionPanel.setBounds(100, 120, 300, 100);
        extensionPanel.setLayout(null);
        this.add(extensionPanel);

        // Title
        JLabel title = new JLabel("Format Penyimpanan");
        title.setBounds(2,0,300,50);
        title.setFont(new Font("Poppins", Font.BOLD, 16));
        extensionPanel.add(title);

        // Dropdown
        JPanel extPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        extPanel.setBounds(0,50,300,50);
        String[] extList = { "JSON", "XML", "OBJ" };
        
        JComboBox<String> extDropdown = new JComboBox<>(extList);
        extDropdown.setSelectedItem(this.selectedExt);
        extDropdown.setPreferredSize(new Dimension(200, 30));
        extDropdown.setFont(new Font("Poppins", Font.PLAIN, 14));
        extDropdown.setBounds(0,0,300,50);
        extDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedExt = (String) extDropdown.getSelectedItem();
                setConfig();
            }
        });
        this.selectedExt = (String) extDropdown.getSelectedItem();
        setConfig();

        extensionPanel.add(extPanel);

        extPanel.add(extDropdown);
    }

    private void initLocation(){
        JPanel locPanel = new JPanel();
        locPanel.setLayout(null);
        locPanel.setBounds(100, 220, 600, 100);
        this.add(locPanel);
        
        // Title
        JLabel locLabel = new JLabel("Lokasi Penyimpanan");
        locLabel.setBounds(0,0,300,50);
        locLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        locPanel.add(locLabel);

        // Location
        JPanel choosePanel = new JPanel(null);
        choosePanel.setBounds(0,50,600,50);
        
        // location field
        JTextField dbLocField = new JTextField(15);
        dbLocField.setFont(new Font("Poppins", Font.PLAIN, 14));
        dbLocField.setEditable(false); // disable direct text editing
        dbLocField.setText(this.selectedDir);
        dbLocField.setBounds(0,0,300,30);
        choosePanel.add(dbLocField);
        
        // directory chooser
        JButton chooseButton = new JButton("Choose Loc");
        chooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseLoc();
                dbLocField.setText(selectedDir);
            }
        });
        chooseButton.setBounds(330,0,120,30);
        choosePanel.add(chooseButton);

        locPanel.add(choosePanel);
        
    }

    private void setConfig() {
        DataStore data = DataStore.getInstance();
        data.changeConfig(selectedDir, selectedExt);
    }

    private void chooseLoc() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Database Location");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.selectedDir = selectedFile.getAbsolutePath();
            setConfig();
        }
    }

    public static void main(String[] args) {
        
    }
}
