import UI.IApp;

import java.util.*;
import Entity.*;
import javax.swing.*;

import DataStore.DataStore;
import Plugins.BasePlugin;

public class SystemPlugin1 implements BasePlugin {
    private HashMap<String, Float> kurs;
    private String currentKurs = "IDR";

    public SystemPlugin1() {
        this.kurs = new HashMap<String, Float>() {
            {
                put("IDR", (float) 1);
                put("USD", (float) 14675);
                put("MYR", (float) 3701.1);
                put("EUR", (float) 16171.1);
            }
        };
    }

    private void changeKurs(String newKurs) {
        this.currentKurs = newKurs;

        ArrayList<Item> items = DataStore.getInstance().getItems().getElements();
        for (Item item : items) {
            try {
                // Make New Price, depends on kurs
                Float newPrice = item.getPrice().floatValue() / (this.kurs.get(this.currentKurs));

                // Update Item
                Item newItem = new Item(item.getName(), item.getCategory(), newPrice.intValue(), item.getImageUrl(), item.getStock());
                DataStore.getInstance().updateItem(item.getId(), newItem);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void onLoad(IApp appService, DataStore dataService) {
        JMenu menu = new JMenu("Currency");

        ButtonGroup group = new ButtonGroup();

        for (String key : this.kurs.keySet()) {
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(key);

            menuItem.addActionListener(e -> {
                this.changeKurs(key);
            });

            if (key.equals(this.currentKurs)) {
                menuItem.setSelected(true);
            }

            group.add(menuItem);
            menu.add(menuItem);
        }

        // menu.add(menuItem);
        appService.addMenu(menu);
    }
}
