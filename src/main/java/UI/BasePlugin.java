package UI;

/* Imports */
import UI.Interface.IRefreshable;
import UI.Component.AppMenu.*;
import org.jetbrains.annotations.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.*;
import UI.Page.*;

import javax.swing.JFrame;
import javax.xml.crypto.Data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.PublicCloneable;

import DataStore.DataStore;

import org.jfree.data.xy.XYSeries;
import java.util.*;
import Entity.*;

public interface BasePlugin {
    public void onLoad();
}

class PluginChart1 extends JFrame implements BasePlugin {
    public PluginChart1() { }

    @Override
    public void onLoad() {
        IApp app = App.getInstance();

        JMenu menu = new JMenu(null, false);
        app.addMenu(menu);

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

        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1200, 720);
        this.add(titlePanel);
        this.add(chartPanel);
        this.add(barChartPanel);
    }

    public static void main(String[] args) {
        PluginChart1 plug = new PluginChart1();
        plug.onLoad();
    }
}

class PluginChart2 extends JFrame implements BasePlugin {
    public PluginChart2() { }

    @Override
    public void onLoad() {
        IApp app = App.getInstance();

        JMenu menu = new JMenu(null, false);
        app.addMenu(menu);

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
        dataset.setValue("Kucing", 42);
        dataset.setValue("Anjing", 23);
        dataset.setValue("Burung", 12);
        dataset.setValue("Ikan", 8);
        dataset.setValue("Reptil", 5);

        JFreeChart chart = ChartFactory.createPieChart("Hewan Peliharaan", dataset, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(20, 100, 1140, 520);
        chartPanel.setBackground(Color.white);

        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1200, 720);
        this.add(titlePanel);
        this.add(chartPanel);
    }

    public static void main(String[] args) {
        PluginChart2 plug = new PluginChart2();
        plug.onLoad();
    }
}

class PluginSistem1 implements BasePlugin {
    private String mataUang;
    private float kurs;
    private DataStore data;

    public PluginSistem1 (String mu, float kurs, DataStore dataInput) {
        this.mataUang = mu;
        this.kurs = kurs;
        this.data = dataInput;
    }

    public void onLoad() {
        ArrayList<Item> items = this.data.getItems().getElements();
        for (Item item : items) {
            try {
                switch (this.mataUang) {
                    case "USD":
                        this.kurs = 14675;
                    case "MYR":
                        this.kurs = (float) 3701.1;
                    case "EUR":
                        this.kurs = (float) 16171.1;
                }


                // Make New Price, depends on kurs
                Float newPrice = item.getPrice().floatValue() / (this.kurs);

                // Update Item
                Item newItem = new Item(item.getName(), item.getCategory(), newPrice.intValue(), item.getImageUrl(), item.getStock());
                this.data.updateItem(item.getId(), newItem);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
