package UI.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Entity.*;
import DataStore.DataStore;

public class TambahMember extends JPanel {
    private JTextField nameField = new JTextField(25);
    private JTextField phoneField = new JTextField(25);

    public TambahMember() {
        setLayout(null);
        initTitlePanel();
        initImagePanel();
        initUtilitesPanel();
    }

    private void initImagePanel(){
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBounds(452, 0, 767, 665);
        
        ImageIcon elon = new ImageIcon(getClass().getResource("/images/tambah-member/elon.png"));
        JLabel elonImage = new JLabel(elon);
        
        elonImage.setHorizontalAlignment(JLabel.LEFT);
        elonImage.setVerticalAlignment(JLabel.BOTTOM);  
        elonImage.setBounds(0, 0, 767, 665);    
        
        leftPanel.add(elonImage);
        this.add(leftPanel);
    }

    private void initTitlePanel(){
        JPanel titlePanel = new JPanel(null);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
        titlePanel.setBounds(0, 0, 600, 190);
        this.add(titlePanel);
        this.setComponentZOrder(titlePanel, 0);

        // PAGE TITLE
        JLabel titleLabel = new JLabel("Daftar Membership BNMOStore");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(100, 75, 0, 0));
        titlePanel.add(titleLabel);
    }

    private void initUtilitesPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBounds(0, 190, 433, 465);
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // *** STATUS PANEL *** //
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));

        // status label
        JLabel statusLabel = new JLabel("Status");
        statusLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 7, 260));
        

        // dropdown
        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] statusList = { "Member (Reguler)", "VIP" };
        JComboBox<String> statusDropdown = new JComboBox<>(statusList);
        statusDropdown.setPreferredSize(new Dimension(200, 30));
        statusDropdown.setFont(new Font("Poppins", Font.PLAIN, 14));
        dropdownPanel.add(Box.createRigidArea(new Dimension(70, 0)));
        dropdownPanel.add(statusDropdown);
        
        statusPanel.add(statusLabel);
        statusPanel.add(dropdownPanel);

        // **** NAME PANEL *** //
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.PAGE_AXIS));

        // status label
        JLabel nameLabel = new JLabel("Nama");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 38, 0, 0));
        nameLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        // text field
        JPanel fieldPanel = new JPanel();
        nameField.setToolTipText("Enter your name");
        nameField.setFont(new Font("Poppins", Font.PLAIN, 14));
        fieldPanel.add(nameField);
        
        namePanel.add(nameLabel);
        namePanel.add(fieldPanel);
        
        // *** PHONE PANEL *** /
        JPanel phonePanel = new JPanel();
        phonePanel.setLayout(new BoxLayout(phonePanel, BoxLayout.PAGE_AXIS));
        
        // phone label
        JLabel phoneLabel = new JLabel("Nomor Telepon");
        phoneLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        phoneLabel.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        
        // Text Field
        JPanel phoneFieldPanel = new JPanel();
        // JTextField phoneField = new JTextField(25);
        phoneField.setToolTipText("Enter your phone");
        phoneField.setFont(new Font("Poppins", Font.PLAIN, 14));
        phoneFieldPanel.add(phoneField);

        phonePanel.add(phoneLabel);
        phonePanel.add(phoneFieldPanel);

        // REGIS BUTTON
        JPanel regisPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton regisButton = new JButton("Daftarkan");
        regisButton.setFont(new Font("Poppins", Font.BOLD, 14));
        regisButton.setForeground(Color.white);
        regisButton.setBackground(new Color(0x36459A));
        regisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedStatus = (String) statusDropdown.getSelectedItem();
                try {
                    Boolean isMember;
                    if (selectedStatus.equals("Member (Reguler)")) {
                        isMember = true;
                    } else {
                        isMember = false;
                    }
                    regisMember(isMember);
                    System.out.println("Member has been added successfully");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        regisPanel.add(Box.createRigidArea(new Dimension(70, 0)));
        regisPanel.add(regisButton);

        // ADD COMPONENTS TO PANEL
        leftPanel.add(statusPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(namePanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(phonePanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(regisPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 200)));
        this.add(leftPanel);
    }

    private void regisMember(Boolean isMember) throws IOException {
        String name = nameField.getText();
        String phone = phoneField.getText();

        DataStore data = DataStore.getInstance();

        try {
            if (isMember) {
                Member member = new Member(1, name, phone, true, null);
                data.addMember(member);
            } else {
                VIP vip = new VIP(1, name, phone, true, null);
                data.addMember(vip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        new TambahMember();
    }
}