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

public class ChartPlugin1Page extends JPanel {
    public ChartPlugin1Page(IApp appService, DataStore dataService) {
        this.setLayout(null);
        this.setSize(1200, 720);

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

        JFreeChart chart = ChartFactory.createLineChart("Pemasukan per bulan", "Bulan", "Total Pemasukan", dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(10, 100, 560, 520);
        chartPanel.setBackground(Color.white);

        // BAR CHART
        ArrayList<FixedBill> fixedBills = dataService.getFixedBills().getElements();
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        HashMap<String, Integer> categories = new HashMap<>();

        for (FixedBill fb : fixedBills) {
            for (Item item : fb.getItems()) {
                String category = item.getCategory();
                if (categories.containsKey(category)) {
                    categories.put(category, categories.get(category) + item.getStock());
                } else {
                    categories.put(category, item.getStock());
                }
            }
        }

        for (Map.Entry<String, Integer> entry : categories.entrySet()) {
            barDataset.addValue(entry.getValue(), "Jumlah Terjual", entry.getKey());
        }

        JFreeChart barChart = ChartFactory.createBarChart("Jumlah Terjual per Kategori", "Kategori Barang", "Jumlah Terjual", barDataset);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setBounds(580, 100, 560, 520);
        barChartPanel.setBackground(Color.white);

        this.add(titlePanel);
        this.add(chartPanel);
        this.add(barChartPanel);
    }
}