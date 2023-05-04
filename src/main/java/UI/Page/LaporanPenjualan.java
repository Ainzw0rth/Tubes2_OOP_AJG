package UI.Page;
import javax.swing.*;
import java.awt.*;

public class LaporanPenjualan extends JPanel {

    public LaporanPenjualan() {
        // // set judul frame
        // super("Laporan Penjualan");

        // set ukuran frame
        setSize(600, 400);

        // set layout frame
        setLayout(new BorderLayout());

        // tambahkan komponen ke panel kiri
        JPanel kiriPanel = new JPanel();
        kiriPanel.setLayout(new BorderLayout());

        // tambahkan judul page ke panel kiri
        JLabel judulLabel = new JLabel("Laporan Penjualan");
        judulLabel.setFont(new Font("Arial", Font.BOLD, 24));
        judulLabel.setBorder(BorderFactory.createEmptyBorder(80, 100, 0, 0));
        kiriPanel.add(judulLabel, BorderLayout.NORTH);

        // tambahkan detail penjualan ke panel kiri
        JPanel detailPanel = new JPanel();
        detailPanel.setBorder((BorderFactory.createEmptyBorder(20, 20, 0, 0)));
        detailPanel.setLayout(new GridLayout(0, 3, 60, 10));
        detailPanel.add(new JLabel("Nama Item"));
        detailPanel.add(new JLabel("Jumlah"));
        detailPanel.add(new JLabel("Harga"));
        // contoh detail penjualan
        detailPanel.add(new JLabel("Ayam"));
        detailPanel.add(new JLabel("2x"));
        detailPanel.add(new JLabel("Rp12000"));
        detailPanel.add(new JLabel("Bebek"));
        detailPanel.add(new JLabel("2x"));
        detailPanel.add(new JLabel("Rp12000"));
        // tambahkan detail panel ke dalam JScrollPane
        JScrollPane scrollPane = new JScrollPane(detailPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 83, 0, 0));
        kiriPanel.add(scrollPane);

        // tambahkan panel kiri ke dalam frame
        add(kiriPanel, BorderLayout.WEST);

        // tambahkan komponen ke panel kanan
        JPanel kananPanel = new JPanel();
        kananPanel.setLayout(new BoxLayout(kananPanel, BoxLayout.Y_AXIS));

        // tambahkan label Total Pemasukan ke panel kanan
        JLabel totalLabel = new JLabel("Total Pemasukan:");
        totalLabel.setBorder(BorderFactory.createEmptyBorder(135, 80, 5, 480));
        kananPanel.add(totalLabel, BorderLayout.NORTH);

        // tambahkan jumlah total harga ke panel kanan
        JLabel hargaLabel = new JLabel("Rp24000");
        hargaLabel.setBorder(BorderFactory.createEmptyBorder(5, 80, 5, 480));
        kananPanel.add(hargaLabel);

        // tambahkan tombol print ke panel kanan
        JButton printButton = new JButton("Print Laporan Penjualan");
        printButton.setBorder(BorderFactory.createEmptyBorder(5, 80, 5, 80));
        printButton.setPreferredSize(new Dimension(150, 30));
        kananPanel.add(printButton, BorderLayout.SOUTH);

        // tambahkan panel kanan ke dalam frame
        add(kananPanel, BorderLayout.EAST);

        // tampilkan frame
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // pack();
        setSize(1200, 720);
        // setLocationRelativeTo(null);
        setVisible(true);
    }

    // public static void main(String[] args) {
    //     new LaporanPenjualan();
    // }
}
