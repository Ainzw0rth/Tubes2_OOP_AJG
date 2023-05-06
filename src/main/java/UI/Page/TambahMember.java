package UI.Page;

import javax.swing.*;
import java.awt.*;

public class TambahMember extends JPanel {

    public TambahMember() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        // PAGE TITLE
        JLabel titleLabel = new JLabel("Daftar Membership BNMOStore");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));

        // STATUS PANEL
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.PAGE_AXIS));

        // status label
        JLabel statusLabel = new JLabel("Status");
        statusLabel.setFont(new Font("Poppins", Font.PLAIN, 16));

        // dropdown
        JPanel dropdownPanel = new JPanel();
        String[] statusList = {"Member (Reguler) ", "VIP"};
        JComboBox<String> statusDropdown = new JComboBox<>(statusList);
        statusDropdown.setPreferredSize(new Dimension(300, 20));
        statusDropdown.setFont(new Font("Poppins", Font.PLAIN, 14));
        dropdownPanel.add(statusDropdown);

        statusPanel.add(statusLabel);
        statusPanel.add(dropdownPanel);

        // NAME PANEL
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.PAGE_AXIS));

            // status label
            JLabel nameLabel = new JLabel("Nama");
            nameLabel.setFont(new Font("Poppins", Font.PLAIN, 16));

            // text field
            JPanel fieldPanel = new JPanel();
            JTextField nameField = new JTextField(25);
            nameField.setToolTipText("Enter your name");
            nameField.setFont(new Font("Poppins", Font.PLAIN, 14));
            fieldPanel.add(nameField);

        namePanel.add(nameLabel);
        namePanel.add(fieldPanel);

        // PHONE PANEL
        JPanel phonePanel = new JPanel();
        phonePanel.setLayout(new BoxLayout(phonePanel, BoxLayout.Y_AXIS));

            // phone label
            JLabel phoneLabel = new JLabel("Nomor Telepon");
            phoneLabel.setFont(new Font("Poppins", Font.PLAIN, 16));

            // Text Field
            JPanel phoneFieldPanel = new JPanel();
            JTextField phoneField = new JTextField(25);
            phoneField.setToolTipText("Enter your phone");
            phoneField.setFont(new Font("Poppins", Font.PLAIN, 14));
            phoneFieldPanel.add(phoneField);

        phonePanel.add(phoneLabel);
        phonePanel.add(phoneFieldPanel);

        // REGIS BUTTON
        JButton regisButton = new JButton("Daftarkan");
        regisButton.setFont(new Font("Poppins", Font.BOLD, 14));
        regisButton.setForeground(Color.white);
        regisButton.setBackground(new Color(0x36459A));

        // ADD COMPONENTS TO PANEL
        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(statusPanel);
        panel.add(namePanel);
        panel.add(phonePanel);
        panel.add(regisButton);
        panel.add(Box.createVerticalGlue());

        this.add(panel);
        setSize(1200, 720);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TambahMember();
    }
}


