import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddKursPage extends JPanel {
    private JTextField currencyField;
    private JTextField kursField;

    public AddKursPage(SystemPlugin1 plugin) {
        


        // this.setLayout(null);
        // this.setSize(1200, 720);

        // JPanel leftPanel = new JPanel();
        // leftPanel.setLayout(null);
        // leftPanel.setBounds(20, 20, 1200, 720);

        // currencyField = new JTextField(30); // to currencyPanel
        // kursField = new JTextField(30); // to kursPanel

        // // **** currency PANEL *** //
        // JPanel currencyPanel = new JPanel();
        // currencyPanel.setLayout(null);
        // currencyPanel.setBounds(0, 30, 400, 300); // to left panel

        // // status label
        // JLabel currencyLabel = new JLabel("Mata Uang");
        // currencyLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        // currencyLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        // currencyLabel.setBounds(0, 0, 400, 40); // to currencyPanel

        // // text field
        // JPanel fieldPanel = new JPanel();
        // fieldPanel.setLayout(null);
        // fieldPanel.setBounds(0, 0, 400, 50); // to currencyPanel

        // currencyField.setToolTipText("Enter the currency");
        // currencyField.setFont(new Font("Poppins", Font.PLAIN, 14));
        // currencyField.setBounds(0, 50, 400, 40); // to fieldPanel

        // fieldPanel.add(currencyField);

        // currencyPanel.add(currencyLabel);
        // currencyPanel.add(fieldPanel);

        // // *** kurs PANEL *** /
        // JPanel kursPanel = new JPanel();
        // kursPanel.setLayout(null);
        // kursPanel.setBounds(0, 300, 400, 300);

        // // kurs label
        // JLabel kursLabel = new JLabel("Kurs");
        // kursLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        // kursLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        // kursLabel.setBounds(0, 0, 400, 40); // to kursPanel

        // // Text Field
        // JPanel kursFieldPanel = new JPanel();
        // // JTextField kursField = new JTextField(25);
        // kursField.setToolTipText("Enter the kurs");
        // kursField.setFont(new Font("Poppins", Font.PLAIN, 14));
        // kursField.setBounds(0, 200, 200, 500);

        // kursFieldPanel.add(kursField);

        // kursPanel.add(kursLabel);
        // kursPanel.add(kursFieldPanel);
        // JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // submitPanel.setBounds(0, 300, 200, 100);

        // JButton submitButton = new JButton("Tambahkan");
        // submitButton.setFont(new Font("Poppins", Font.BOLD, 14));
        // submitButton.setForeground(Color.white);
        // submitButton.setBackground(new Color(0x36459A));
        
        // submitButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         String currency = currencyField.getText();
        //         if (kursField.getText().equals("")) {
        //             JOptionPane.showMessageDialog(null, "Please fill all the fields");
        //             return;
        //         }
        //         Double kurs = Double.parseDouble(kursField.getText());
        //         if (currency.equals("") || kurs.equals("")) {
        //             JOptionPane.showMessageDialog(null, "Please fill all the fields");
        //         } else {
        //             try {
        //                 plugin.addKurs(new Currency(currency, kurs));
        //                 JOptionPane.showMessageDialog(null, "Kurs berhasil ditambahkan");
        //             } catch (Exception ex) {
        //                 JOptionPane.showMessageDialog(null, "Kurs gagal ditambahkan");
        //             }
        //         }
        //     }
        // });
        // submitPanel.add(Box.createRigidArea(new Dimension(70, 0)));
        // submitPanel.add(submitButton);
        // submitPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // leftPanel.add(currencyPanel);
        // leftPanel.add(kursPanel);
        // leftPanel.add(submitPanel);
        // this.add(leftPanel);
    }
}