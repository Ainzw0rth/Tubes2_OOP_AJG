import javax.swing.*;
import java.awt.*;
import UI.IApp;

import java.util.*;
import Entity.*;

import DataStore.DataStore;
import Plugins.BasePlugin;
import Utils.Collections.Observer;

public class SystemPlugin2 extends JPanel implements BasePlugin {
    private final Double tax;
    private final Double charge;
    private Double discount;

    public SystemPlugin2() {
        this.tax = 0.05;
        this.charge = 0.05;
        this.discount = 0.93;
    }

    public void changePrice(ArrayList<Bill> bills, DataStore dataService) {
        try {

            bills = dataService.getBills();
            bills.addObserver( new Observer () {
                @Override
                public void update() {
                    for (Bill bill : bills) {
                        Double temp = (bill.getTotalPrice() * (this.tax + this.charge)) ; // tax 5%, service charge 5%

                        if (bill.getTotalPrice() > 100000) {
                            Double value = temp * 0.9; // diskon 10%
                            bill.setTotalPrice(value);
                        } else {
                            Double value = temp * this.discount; // diskon 7%
                            bill.setTotalPrice(value);
                        }
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void changePanel(IApp appService, DataStore dataService) {
        // JPanel panel = new JPanel();
        // appService.setPanel("Jual Barang", panel);
    }

    @Override
    public void onLoad(IApp appService, DataStore dataService) {
        try {
            ArrayList<Bill> bills = dataService.getBills().getElements();
            changePrice(bills);
            dataService.setBills(bills);
            changePanel(appService, dataService);

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}

