package UI.Page;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import Utils.Collections.Observer;
import DataStore.DataStore;
import Entity.*;

public class UpdateMember extends JPanel {
    private JTextField nameField = new JTextField(25);
    JTextField phoneField = new JTextField(25);

    public UpdateMember() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        // PAGE TITLE
        JLabel titleLabel = new JLabel("Update Membership BNMOStore");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));

        // STATUS PANEL
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.PAGE_AXIS));

        // status label
        JLabel statusLabel = new JLabel("Status");
        statusLabel.setFont(new Font("Poppins", Font.PLAIN, 16));

        // dropdown
        JPanel dropdownPanel = new JPanel();
        String[] statusList = { "Member (Reguler) ", "VIP" };
        JComboBox<String> statusDropdown = new JComboBox<>(statusList);
        statusDropdown.setPreferredSize(new Dimension(300, 20));
        statusDropdown.setFont(new Font("Poppins", Font.PLAIN, 14));
        dropdownPanel.add(statusDropdown);

        statusPanel.add(statusLabel);
        statusPanel.add(dropdownPanel);

        // MEMBER PANEL
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.PAGE_AXIS));

        // member label
        JLabel memberLabel = new JLabel("Member");
        memberLabel.setFont(new Font("Poppins", Font.PLAIN, 16));

        // dropdown
        JPanel memberDropdownPanel = new JPanel();
        DataStore data = DataStore.getInstance();

        data.getMembers().addObserver(
                new Observer() {
                    @Override
                    public void update() {
                        ArrayList<Member> members = data.getMembers().getElements();

                        String[] memberList = new String[members.size() + 1];
                        memberList[0] = "Pilih nama member";
                        for (int i = 0; i < members.size(); i++) {
                            memberList[i + 1] = members.get(i).getName();
                        }

                        @SuppressWarnings("unchecked")
                        JComboBox<String> memberDropdown = (JComboBox<String>) memberDropdownPanel.getComponent(0);
                        memberDropdown.setModel(new DefaultComboBoxModel<>(memberList));
                    }
                });

        ArrayList<Member> members = data.getMembers().getElements();

        String[] memberList = new String[members.size() + 1];
        memberList[0] = "Pilih nama member";
        for (int i = 0; i < members.size(); i++) {
            memberList[i + 1] = members.get(i).getName();
        }

        JComboBox<String> memberDropdown = new JComboBox<>(memberList);

        memberDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedName = (String) memberDropdown.getSelectedItem();
                if (!selectedName.equals("Pilih nama member")) {
                    Member selectedMember = null;
                    for (Member member : members) {
                        if (member.getName().equals(selectedName)) {
                            selectedMember = member;
                            break;
                        }
                    }
                    nameField.setText(selectedMember.getName());
                    phoneField.setText(selectedMember.getPhoneNumber());
                    if (selectedMember instanceof Member) {
                        statusDropdown.setSelectedItem("Member (Reguler)");
                    } else if (selectedMember instanceof VIP) {
                        statusDropdown.setSelectedItem("VIP");
                    }
                }
            }
        });

        memberDropdown.setRenderer(new DefaultListCellRenderer() {
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

        memberDropdown.setPreferredSize(new Dimension(300, 20));
        memberDropdown.setFont(new Font("Poppins", Font.PLAIN, 14));
        memberDropdownPanel.add(memberDropdown);

        memberPanel.add(memberLabel);
        memberPanel.add(memberDropdownPanel);

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
