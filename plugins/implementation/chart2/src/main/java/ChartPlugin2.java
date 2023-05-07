import UI.IApp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import DataStore.DataStore;
import Entity.FixedBill;
import Plugins.BasePlugin;

public class ChartPlugin2 implements BasePlugin {
    public ChartPlugin2() { }

    @Override
    public void onLoad(IApp appService, DataStore dataService) {
        JPanel page = new JPanel();

        // TITLE PANEL
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(470, 0, 200, 50);

        // TITLE LABEL
        JLabel titleLabel = new JLabel("Pie Chart");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setBounds(0, 6, 200, 40);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        titlePanel.add(titleLabel);

        // CHART DATA
        DefaultPieDataset dataset = new DefaultPieDataset();

        Integer[] monthValue = new Integer[12];
        for (int i = 0; i < 12; i++) {
            monthValue[i] = 0;
        }
    
        ArrayList<FixedBill> bills = dataService.getFixedBills().getElements();
        for (FixedBill bill : bills) {
            String[] date = bill.getDate().split("-");
            int month = Integer.parseInt(date[1]);
            monthValue[month - 1] += bill.getTotalPrice();
        }

        for (int i = 0; i < 12; i++) {
            dataset.setValue("Bulan " + (i + 1), monthValue[i]);
        }
        JFreeChart chart = ChartFactory.createPieChart("Penjualan Toko", dataset, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(20, 100, 1140, 520);
        chartPanel.setBackground(Color.white);

        page.add(titlePanel);
        page.add(chartPanel);

        JMenu menu = new JMenu("ChartPlugin2");
        JMenuItem menuItem = new JMenuItem("Statistik Toko");

        menuItem.addActionListener(e -> {
            appService.addTab("Statistik Toko", page);
        });
        
        menu.add(menuItem);
        appService.addMenu(menu);

    }
}
