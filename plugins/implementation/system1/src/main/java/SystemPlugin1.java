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
    private ButtonGroup currencyChoice;
    private IApp appService;
    private DataStore dataService;

    public SystemPlugin1() {
        this.availableCurrencies = new ArrayList<>();
    }

    public void addKurs(Currency kurs) {
        try {
            for (Currency cur : this.availableCurrencies) {
                if (cur.getName().equals(kurs.getName())) {
                    JOptionPane.showMessageDialog(null, "Mata Uang Sudah Ada", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            this.availableCurrencies.add(kurs);
            
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(kurs.getName());
            this.currencyChoice.add(menuItem);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeKurs(kurs);
                }
            });
            appService.addMenuItem(appService.getMenuIndex("Currency"), menuItem);
            
            CurrencyIO io = new CurrencyIO();
            io.write(this.availableCurrencies, dataService.getDirPath() + "/currency.json");
            JOptionPane.showMessageDialog(null, "Kurs berhasil ditambahkan", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                Double newPrice = item.getPrice() *  this.current.getKurs()  / newKurs.getKurs();
                
                // Update Item
                Item newItem = new Item(item.getName(), item.getCategory(), newPrice.intValue(), item.getImageUrl(), item.getStock());
                DataStore.getInstance().updateItem(item.getId(), newItem);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        JOptionPane.showMessageDialog(null, "Kurs Berhasil Diubah");
        this.current = newKurs;
    }

    @Override
    public void onLoad(IApp appService, DataStore dataService) {
        this.appService = appService;
        this.dataService = dataService;

        CurrencyIO io = new CurrencyIO();
        try {
            this.availableCurrencies = io.read(dataService.getDirPath() + "/currency.json");

            if (this.availableCurrencies.size() == 0) {
                this.availableCurrencies.add(this.current);
                io.write(this.availableCurrencies, dataService.getDirPath() + "/currency.json");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

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

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeKurs(cur);
                }
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