package Plugins.Example;

import UI.IApp;

import javax.swing.*;
import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import DataStore.DataStore;
import Plugins.BasePlugin;

class ChartPlugin2 extends JFrame implements BasePlugin {
    public ChartPlugin2() { }

    @Override
    public void onLoad(IApp appService, DataStore dataService) {
        JMenu menu = new JMenu(null, false);
        appService.addMenu(menu);

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
}
