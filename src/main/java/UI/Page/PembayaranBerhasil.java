package UI.Page;
import javax.swing.*;

import UI.App;
import UI.IApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PembayaranBerhasil extends JPanel {
    public PembayaranBerhasil (String member) {
        JPanel pane = new JPanel();
        pane.setLayout(null);
        pane.setBounds(0, 0, 1280, 650);

        ImageIcon subaru = new ImageIcon(getClass().getResource("/images/transaksi_berhasil/subaru.png"));
        Image scaledImage = subaru.getImage().getScaledInstance(414, 315, Image.SCALE_DEFAULT);
        subaru = new ImageIcon(scaledImage);
        JLabel subaruIMG = new JLabel(subaru);
        subaruIMG.setLayout(null);
        subaruIMG.setBounds(340, 50, 414, 315);  
        pane.add(subaruIMG);

        JLabel transaksiLabel = new JLabel("Transaksi Berhasil");
        transaksiLabel.setFont(new Font("Poppins", Font.BOLD, 60));
        transaksiLabel.setBounds(290, 410, 600, 60);
        pane.add(transaksiLabel);

        if (!member.equals("Pilih nama member")) {

        } else {
            JButton addMember = new JButton("Tambahkan sebagai member +");
            addMember.setFont(new Font("Poppins", Font.BOLD, 16));
            addMember.setBounds(380, 490, 380, 40);
            addMember.setBackground(new Color(0x36459A));
            addMember.setForeground(Color.white);
            addMember.setFocusPainted(false);
            addMember.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    IApp app = App.getInstance();
                    app.addTab("Tambah Member", new TambahMember());
                }                
            });
            pane.add(addMember);

        }

        this.add(pane);
        this.setLayout(null);
        this.setVisible(true);
    }

    
}