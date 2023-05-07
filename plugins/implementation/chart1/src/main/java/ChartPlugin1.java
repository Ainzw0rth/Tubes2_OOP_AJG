import UI.IApp;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import DataStore.DataStore;
import Entity.FixedBill;
import Entity.Item;
import Plugins.BasePlugin;


public class ChartPlugin1 implements BasePlugin {
    public ChartPlugin1() { }

    @Override
    public void onLoad(IApp appService, DataStore dataService) {
        JPanel page = new JPanel();

        // TITLE PANEL
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(450, 0, 300, 50);

        // TITLE LABEL
        JLabel titleLabel = new JLabel("Line And Bar Chart");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        titleLabel.setBounds(0, 6, 300, 40);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        titlePanel.add(titleLabel);

        // LINE CHART
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

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
            dataset.addValue(monthValue[i], "Total Pemasukan", String.valueOf(i + 1));
        }

        JFreeChart chart = ChartFactory.createLineChart("Line Chart", "Bulan", "Total Pemasukan", dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(20, 100, 560, 520);
        chartPanel.setBackground(Color.white);

        // BAR CHART
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        ArrayList<Item> items = dataService.getItems().getElements();

        for (Item item : items) {
            barDataset.addValue(item.getStock(), "Stock", item.getName());
        }

        JFreeChart barChart = ChartFactory.createBarChart("Bar Chart", "Nama Barang", "Stock", barDataset);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setBounds(20, 600, 560, 520);
        barChartPanel.setBackground(Color.white);

        page.add(titlePanel);
        page.add(chartPanel);
        page.add(barChartPanel);

        JMenu menu = new JMenu("ChartPlugin1");
        JMenuItem menuItem = new JMenuItem("Statistik Toko");

        JScrollPane scrollPane = new JScrollPane(page);

        menuItem.addActionListener(e -> {
            appService.addTab("Statistik Toko", scrollPane);
        });
        
        menu.add(menuItem);
        appService.addMenu(menu);
    }
}