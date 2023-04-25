package UI.Page;
import javax.swing.*;

import javax.swing.*;
import java.awt.*;

public class LaporanPenjualan extends JFrame {

    public LaporanPenjualan() {
        // set judul frame
        super("Laporan Penjualan");

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
        kiriPanel.add(judulLabel, BorderLayout.NORTH);

        // tambahkan detail penjualan ke panel kiri
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new GridLayout(0, 3));
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
        kiriPanel.add(scrollPane, BorderLayout.CENTER);

        // tambahkan panel kiri ke dalam frame
        add(kiriPanel, BorderLayout.WEST);

        // tambahkan komponen ke panel kanan
        JPanel kananPanel = new JPanel();
        kananPanel.setLayout(new BorderLayout());

        // tambahkan label Total Pemasukan ke panel kanan
        JLabel totalLabel = new JLabel("Total Pemasukan:");
        kananPanel.add(totalLabel, BorderLayout.NORTH);

        // tambahkan jumlah total harga ke panel kanan
        JLabel hargaLabel = new JLabel("Rp24000");
        kananPanel.add(hargaLabel, BorderLayout.CENTER);

        // tambahkan tombol print ke panel kanan
        JButton printButton = new JButton("Print Laporan Penjualan");
        kananPanel.add(printButton, BorderLayout.SOUTH);

        // tambahkan panel kanan ke dalam frame
        add(kananPanel, BorderLayout.EAST);

        // tampilkan frame
        setVisible(true);
    }

    // public static void main(String[] args) {
    //     LaporanPenjualan lapPenjualan = new LaporanPenjualan();
    // }
}
