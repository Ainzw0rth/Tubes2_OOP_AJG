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
        JMenu menu = new JMenu("ChartPlugin2");
        JMenuItem menuItem = new JMenuItem("Statistik Toko");

        menuItem.addActionListener(e -> {
            appService.addTab("Statistik Toko", new ChartPlugin2Page(appService, dataService));
        });
        
        menu.add(menuItem);
        appService.addMenu(menu);

    }
}