package UI.Page;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Entity.Item;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jetbrains.annotations.*;
import java.io.File;
import java.io.FileFilter;

public class Import extends JPanel {
    String name = "-";
    String extension = "-";
    String path = "";
    JLabel filename2;
    JLabel filepath2;
    JFileChooser fileChooser;
    JPanel selectedPanel;

    public Import() {
        JLabel label = new JLabel("Plugins");
        label.setBounds(50,40,300,50);
        label.setFont(new Font("Poppins", Font.BOLD, 35));
        
        // bagian info selected file, awalnya null
        selectedPanel = new JPanel();
        selectedPanel.setLayout(null);
        selectedPanel.setBounds(50, 450, 400, 100);
        //selectedPanel.setBackground(Color.black);

        // asumsi nanti daftar plugins yang telah ada dalam bentuk list of string
        String[] daftarPlugs = {new String("C:/Users/louis/Downloads/Projects/tubes2-ajg/src/main/resources/images/tes.jar"),  new String("C:/Users/louis/Downloads/Projects/tubes2-ajg/src/main/resources/images/tes.jar")};
        JPanel plugsPanel = new JPanel();
        plugsPanel.setLayout(new BoxLayout(plugsPanel, BoxLayout.Y_AXIS));
        plugsPanel.setBackground(Color.white);
        for (String plug : daftarPlugs) {
            JPanel plugPan = new JPanel();
            plugPan.setBackground(Color.white);
            plugPan.setMaximumSize(new Dimension(1000, 30));
            plugPan.setLayout(null);

            JLabel plugLabel = new JLabel(plug);
            plugLabel.setHorizontalAlignment(JLabel.LEFT);
            plugLabel.setBounds(0, 0, 1000, 20);
            
            plugPan.add(plugLabel);
            plugsPanel.add(plugPan);
        }

        JScrollPane scrollPane = new JScrollPane(plugsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 110, 500, 320);
        scrollPane.setBackground(Color.white);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x36459A)));

        // nama file
        JLabel filename = new JLabel("File Name : ");
        filename.setFont(new Font("Poppins", Font.BOLD, 20));
        filename.setBounds(0, 0, 120, 20);
        filename.setForeground(Color.black);

        filename2 = new JLabel(name);
        filename2.setFont(new Font("Poppins", Font.BOLD, 12));
        filename2.setBounds(130, 0, 220, 20);
        filename2.setForeground(Color.black);

        JLabel filepath = new JLabel("File Path : ");
        filepath.setFont(new Font("Poppins", Font.BOLD, 20));
        filepath.setBounds(0, 30, 120, 20);
        filepath.setForeground(Color.black);

        filepath2 = new JLabel(extension);
        filepath2.setFont(new Font("Poppins", Font.BOLD, 12));
        filepath2.setBounds(130, 30, 220, 20);
        filepath2.setForeground(Color.black);

        ImageIcon sepuh = new ImageIcon(getClass().getResource("/images/plugin/sepuh.png"));
        Image scaledImage = sepuh.getImage().getScaledInstance(463, 555, Image.SCALE_DEFAULT);
        sepuh = new ImageIcon(scaledImage);
        JLabel sepuhIMG = new JLabel(sepuh);
        sepuhIMG.setBounds(650, 0, 600, 700);

        JButton selectFile;
        selectFile = new JButton("Select File", null);
        selectFile.setLayout(null);
        selectFile.setFont(new Font("Poppins", Font.BOLD, 20));
        selectFile.setBounds(780, 200, 180, 30);
        selectFile.setFocusPainted(false);
        selectFile.setBackground(new Color(0xEBEBEB));
        selectFile.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(".")); 
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Jar files", "jar");
                fileChooser.setFileFilter(filter);
                int response = fileChooser.showOpenDialog(null);
                if(response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    name = file.getName();
                    path = file.getAbsolutePath();
                    filename2.setText(name);
                    filepath2.setText(path);
                    selectedPanel.repaint();
                }
            }  
        });  
        
        // save button
        JButton saveButton = new JButton("Tambah");
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("Poppins", Font.BOLD, 15));
        saveButton.setForeground(Color.white);
        saveButton.setBounds(50, 530, 100, 40);
        saveButton.setBackground(new Color(0x36459A));

        selectedPanel.add(filename);
        selectedPanel.add(filename2);
        selectedPanel.add(filepath);
        selectedPanel.add(filepath2);
        
        this.add(saveButton);
        this.add(label);
        this.add(selectFile);
        this.add(sepuhIMG);
        this.add(selectedPanel);
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Import();  
    }
}