package UI.Page;
import javax.swing.*;
import java.awt.*;

public class HistoriTransaksi extends JFrame {
    private JLabel titleLabel;
    private JTextField namaMemberField;
    private JButton tampilkanButton;

    // Variabel untuk panel kanan
    private JPanel kananPanel;
    private JTextArea riwayatTextArea;

    public HistoriTransaksi() {
        // Membuat judul frame
        super("Histori Transaksi");

        // Membuat panel untuk kolom kiri
        JPanel kiriPanel = new JPanel();
        kiriPanel.setLayout(new GridBagLayout());

        // Membuat label judul
        JPanel titlePanel = new JPanel();
        titleLabel = new JLabel("Riwayat Transaksi Member");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 40));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(80, 80, 0, 0));
        titlePanel.add(titleLabel);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 20, 5, 20);
        kiriPanel.add(titlePanel, c);

        // Membuat label member
        JPanel memberPanel = new JPanel();
        JLabel memberLabel = new JLabel("Member");
        memberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        memberLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        memberPanel.setBorder(BorderFactory.createEmptyBorder(10, 83, 0, 0));
        memberPanel.add(memberLabel);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(10, 20, 5, 20);
        kiriPanel.add(memberPanel, c);

        // Membuat text field untuk nama member
        namaMemberField = new JTextField(20);
        namaMemberField.setMaximumSize(new Dimension(250, 30));
        namaMemberField.setAlignmentX(Component.CENTER_ALIGNMENT);
        namaMemberField.setBorder(BorderFactory.createEmptyBorder(10, 60, 0, 0));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10, 0, 5, 20);
        kiriPanel.add(namaMemberField, c);

        // Membuat tombol submit
        tampilkanButton = new JButton("Tampilkan riwayat");
        tampilkanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10, 0, 5, 20);
        kiriPanel.add(tampilkanButton, c);

        // Menambahkan panel ke frame
        getContentPane().add(kiriPanel, BorderLayout.WEST);

        // Menambahkan garis pemisah
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setPreferredSize(new Dimension(1, Integer.MAX_VALUE));
        getContentPane().add(separator, BorderLayout.CENTER);

        // Membuat panel untuk kolom kanan
        kananPanel = new JPanel();
        kananPanel.setLayout(new BoxLayout(kananPanel, BoxLayout.PAGE_AXIS));

        // Membuat text area untuk menampilkan riwayat transaksi
        riwayatTextArea = new JTextArea(100, 50);
        riwayatTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(riwayatTextArea);
        kananPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        kananPanel.add(scrollPane);

        // Menambahkan panel kanan ke frame
        getContentPane().add(kananPanel, BorderLayout.EAST);

        // Menampilkan frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1200, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HistoriTransaksi();
    }
}
