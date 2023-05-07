package UI.Page;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Plugins.CustomClassLoader;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import DataStore.DataStore;

public class PluginImport extends JPanel {
    String name = "-";
    String extension = "-";
    String selectedPath = "";

    JFileChooser fileChooser;
    DataStore data;

    public PluginImport(){
        this.data = DataStore.getInstance();
        this.setLayout(null);
        init();
    }

    public void init() {
        JLabel title = new JLabel("Plugins");
        title.setBounds(100,40,300,50);
        title.setFont(new Font("Poppins", Font.BOLD, 35));
        this.add(title);
        
        // asumsi nanti daftar plugins yang telah ada dalam bentuk list of string
        // String[] daftarPlugs = {new String("C:/Users/louis/Downloads/Projects/tubes2-ajg/src/main/resources/images/tes.jar"),  new String("C:/Users/louis/Downloads/Projects/tubes2-ajg/src/main/resources/images/tes.jar")};
        String[] daftarPlugs = data.getPluginPaths().toArray(new String[0]);
        
        JPanel plugsPanel = new JPanel();
        plugsPanel.setBounds(0,0,550,320);
        plugsPanel.setLayout(new GridLayout(0, 1));
        plugsPanel.setBackground(Color.white);
        
        for (String plug : daftarPlugs) {
            JButton plugButton = new JButton(plug);
            plugButton.setHorizontalAlignment(JLabel.LEFT);
            plugButton.setFont(new Font("Poppins", Font.PLAIN, 18));
            plugButton.setPreferredSize(new Dimension(300, 35));
            plugButton.setForeground(Color.black);
            plugButton.setBackground(Color.WHITE);
            plugButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedPath = plug;
                    System.out.println(selectedPath);
                    plugButton.setBackground(Color.gray);
                }
            });
            
            plugsPanel.add(plugButton);
        }
        plugsPanel.add(Box.createVerticalGlue());
        plugsPanel.add(Box.createVerticalGlue());
        plugsPanel.add(Box.createVerticalGlue());
        plugsPanel.add(Box.createVerticalGlue());
        plugsPanel.add(Box.createVerticalGlue());
        plugsPanel.add(Box.createVerticalGlue());


        JScrollPane scrollPane = new JScrollPane(plugsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // scrollPane.setLayout(null);
        scrollPane.setBounds(100, 110, 550, 320);
        scrollPane.setBackground(Color.white);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x36459A)));
        this.add(scrollPane);
        
        // removebutton
        JButton removeButton = new JButton("Remove");
        removeButton.setFocusPainted(false);
        removeButton.setFont(new Font("Poppins", Font.BOLD, 15));
        removeButton.setForeground(Color.white);
        removeButton.setBounds(100, 500, 100, 40);
        removeButton.setBackground(new Color(0x36459A));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedPath != "") {
                    try {
                        data.removePluginPath(selectedPath);
                    } catch (Exception err) {
                        System.out.println("gagal menghapus plugin");
                    }
                } else {
                    System.out.println("tidak ada plugin yang dipilih");
                }
            }
        });
        this.add(removeButton);
        
        // SELECT FILE
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
                    try {
                        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        String path = file.getAbsolutePath();
                        CustomClassLoader loader = new CustomClassLoader(path);
                        loader.load();
                        data.addPluginPath(path);

                    } catch (Exception err) {
                        System.out.println("Gagal menambahkan plugin");
                    }
                }
            }  
        });
        this.add(selectFile);
        

        ImageIcon sepuh = new ImageIcon(getClass().getResource("/images/plugin/sepuh.png"));
        Image scaledImage = sepuh.getImage().getScaledInstance(463, 555, Image.SCALE_DEFAULT);
        sepuh = new ImageIcon(scaledImage);
        JLabel sepuhIMG = new JLabel(sepuh);
        sepuhIMG.setBounds(650, 0, 600, 700);
        this.add(sepuhIMG);
    }
}