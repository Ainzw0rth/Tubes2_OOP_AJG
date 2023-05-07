package Plugins.Example;

import UI.IApp;

import javax.swing.*;
import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYSeries;

import DataStore.DataStore;
import Plugins.BasePlugin;


class PluginChart1 implements BasePlugin {
    public PluginChart1() { }

    @Override
    public void onLoad(IApp appService, DataStore dataService) {
        JPanel page = new JPanel();
        page.setSize(1200, 654);

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
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Data");
        series.add(1, 3);
        series.add(2, 5);
        series.add(3, 4);
        series.add(4, 7);
        series.add(5, 6);
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart("Line Chart", "X Axis Label", "Y Axis Label", dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(20, 100, 570, 520);
        chartPanel.setBackground(Color.white);

        // BAR CHART
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        barDataset.addValue(1.0, "Bar Data", "Anjing");
        barDataset.addValue(2.0, "Bar Data", "Kucing");
        barDataset.addValue(3.0, "Bar Data", "Burung");
        barDataset.addValue(4.0, "Bar Data", "Babi");
        barDataset.addValue(5.0, "Bar Data", "Monyet");

        JFreeChart barChart = ChartFactory.createBarChart("Bar Chart", "Category", "Value", barDataset);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setBounds(600, 100, 570, 520);
        barChartPanel.setBackground(Color.white);

        page.add(titlePanel);
        page.add(chartPanel);
        page.add(barChartPanel);

        JMenu menu = new JMenu("Chart Plugin 1");
        JMenuItem menuItem = new JMenuItem("Line And Bar Chart");
        menuItem.addActionListener(e -> {
            appService.addTab("Line And Bar Chart", page);
        });

        appService.addMenu(menu);
    }
}