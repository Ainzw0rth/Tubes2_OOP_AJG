import javax.swing.*;
import java.awt.*;
import UI.IApp;

import java.util.*;
import Entity.*;

import DataStore.DataStore;
import Plugins.BasePlugin;

public class SystemPlugin2 extends JPanel implements BasePlugin {
    private final Double tax;
    private final Double charge;
    private Double discount;

    public SystemPlugin2() {
        this.tax = 0.05;
        this.charge = 0.05;
        this.discount = 0.93;
    }

    public void changePrice(ArrayList<Bill> bills) {
        try {
            for (Bill bill : bills) {
                int temp = (int) (bill.getTotalPrice() * (this.tax + this.charge)) ; // tax 5%, service charge 5%

                if (bill.getTotalPrice() > 100000) {
                    Double value = temp * 0.9; // diskon 10%
                    bill.setTotalPrice(value.intValue());
                } else {
                    Double value = temp * this.discount; // diskon 5%
                    bill.setTotalPrice(value.intValue());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void changePanel(IApp appService, DataStore dataService) {
        // JPanel panel = new JPanel();
        // appService.setPanel("Jual Barang", panel);
    }

    public void onLoad(IApp appService, DataStore dataService) {
        try {
            ArrayList<Bill> bills = dataService.getBills().getElements();
            changePrice(bills);
            changePanel(appService, dataService);

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}

