import UI.IApp;

import java.util.*;
import Entity.*;
import javax.swing.*;
import java.awt.event.*;

import DataStore.DataStore;
import Plugins.BasePlugin;

public class SystemPlugin1 implements BasePlugin {
    private Currency current = new Currency("IDR", 1d);
    private ArrayList<Currency> availableCurrencies;
    private JMenu menu;
    ButtonGroup currencyChoice;
    IApp appService;

    public SystemPlugin1() {
        this.availableCurrencies = new ArrayList<>();
        CurrencyIO io = new CurrencyIO();
        try {
            this.availableCurrencies = io.read("currency.json");

            if (this.availableCurrencies.size() <= 1) {
                this.availableCurrencies.add(this.current);
                io.write(this.availableCurrencies, "currency.json");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addKurs(Currency kurs) {
        try {
            this.availableCurrencies.add(kurs);
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(kurs.getName());
            appService.addMenuItem(appService.getMenuIndex("Currency"), menuItem);
            
            CurrencyIO io = new CurrencyIO();
            io.write(this.availableCurrencies, "currency.json");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }

    private void changeKurs(Currency newKurs) {
        ArrayList<Item> items = DataStore.getInstance().getItems().getElements();

        for (AbstractButton b : Collections.list(currencyChoice.getElements())) {
            if (b.isSelected() && !b.getText().equals(newKurs.getName())) {
                b.setSelected(false);
            }
        }

        for (Item item : items) {
            try {
                // Make New Price, depends on kurs
                Double newPrice = item.getPrice() * newKurs.getKurs() / this.current.getKurs();
                
                // Update Item
                Item newItem = new Item(item.getName(), item.getCategory(), newPrice.intValue(), item.getImageUrl(), item.getStock());
                DataStore.getInstance().updateItem(item.getId(), newItem);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        this.current = newKurs;
    }

    @Override
    public void onLoad(IApp appService, DataStore dataService) {
        this.appService = appService;
        menu = new JMenu("Currency");

        JMenuItem addKursItem = new JMenuItem("Tambah Mata Uang");
        addKursItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appService.addTab("Tambah Mata Uang", new AddKursPage(SystemPlugin1.this));
            }
        });

        menu.add(addKursItem);
        menu.addSeparator();

        currencyChoice = new ButtonGroup();

        for (Currency cur : this.availableCurrencies) {
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(cur.getName());

            if (cur.getName().equals("IDR")) {
                menuItem.setSelected(true);
            }

            menuItem.addActionListener(e -> {
                this.changeKurs(cur);
            });

            if (cur.equals(this.current)) {
                menuItem.setSelected(true);
            }

            currencyChoice.add(menuItem);
            menu.add(menuItem);
        }

        // menu.add(menuItem);
        appService.addMenu(menu);
    }
}