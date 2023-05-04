package UI.Page.Customers;

import javax.swing.*;
import java.awt.*;

public class UpdateMember extends JPanel {

    public UpdateMember() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        // PAGE TITLE
        JLabel titleLabel = new JLabel("Update Membership BNMOStore");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));


        // MEMBER PANEL
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.PAGE_AXIS));

            // member label
            JLabel memberLabel = new JLabel("Member");
            memberLabel.setFont(new Font("Poppins", Font.PLAIN, 16));

            // dropdown
            JPanel memberDropdownPanel = new JPanel();
            String[] memberList = {"Pilih nama member"};
            JComboBox<String> memberDropdown = new JComboBox<>(memberList);
            memberDropdown.setPreferredSize(new Dimension(300, 20));
            memberDropdown.setFont(new Font("Poppins", Font.PLAIN, 14));
            memberDropdownPanel.add(memberDropdown);

        memberPanel.add(memberLabel);
        memberPanel.add(memberDropdownPanel);

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

        // wrap status and member panel to
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
        wrapper.add(memberPanel);
        wrapper.add(statusPanel);

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
        JButton regisButton = new JButton("Update");
        regisButton.setFont(new Font("Poppins", Font.BOLD, 14));
        regisButton.setForeground(Color.white);
        regisButton.setBackground(new Color(0x36459A));

        // ADD COMPONENTS TO PANEL
        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(wrapper);
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
        new UpdateMember();
    }
}


