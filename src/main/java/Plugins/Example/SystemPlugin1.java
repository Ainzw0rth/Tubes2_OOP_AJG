package Plugins.Example;

import UI.IApp;

import java.util.*;
import Entity.*;

import DataStore.DataStore;
import Plugins.BasePlugin;

class SystemPlugin1 implements BasePlugin {
    private String mataUang;
    private float kurs;
    private DataStore data;

    public SystemPlugin1 (String mu, float kurs, DataStore dataInput) {
        this.mataUang = mu;
        this.kurs = kurs;
        this.data = dataInput;
    }

    public void onLoad(IApp appService, DataStore dataService) {
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
