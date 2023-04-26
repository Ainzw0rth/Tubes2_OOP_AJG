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
        kiriPanel.setLayout(new BoxLayout(kiriPanel, BoxLayout.PAGE_AXIS));

        // Membuat label judul
        titleLabel = new JLabel("Riwayat Transaksi Member");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        kiriPanel.add(titleLabel);

        // Membuat text field untuk nama member
        namaMemberField = new JTextField(20);
        namaMemberField.setMaximumSize(new Dimension(250, 30));
        namaMemberField.setAlignmentX(Component.CENTER_ALIGNMENT);
        kiriPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        kiriPanel.add(namaMemberField);

        // Membuat tombol submit
        tampilkanButton = new JButton("Tampilkan riwayat");
        tampilkanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        kiriPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        kiriPanel.add(tampilkanButton);

        // Menambahkan panel ke frame
        getContentPane().add(kiriPanel, BorderLayout.WEST);

        // Membuat panel untuk kolom kanan
        kananPanel = new JPanel();
        kananPanel.setLayout(new BoxLayout(kananPanel, BoxLayout.PAGE_AXIS));

        // Membuat label untuk judul riwayat transaksi
        JLabel riwayatLabel = new JLabel("Riwayat Transaksi");
        riwayatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        kananPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        kananPanel.add(riwayatLabel);

        // Membuat text area untuk menampilkan riwayat transaksi
        riwayatTextArea = new JTextArea(10, 30);
        riwayatTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(riwayatTextArea);
        kananPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        kananPanel.add(scrollPane);

        // Menambahkan panel kanan ke frame
        getContentPane().add(kananPanel, BorderLayout.EAST);

        // Menampilkan frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        HistoriTransaksi ui = new HistoriTransaksi();
    }
}
