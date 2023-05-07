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
        JMenu menu = new JMenu("ChartPlugin1");
        JMenuItem menuItem = new JMenuItem("Statistik Toko");

        menuItem.addActionListener(e -> {
            appService.addTab("Statistik Toko", new ChartPlugin1Page(appService, dataService));
        });

        menu.add(menuItem);
        appService.addMenu(menu);
    }
}
