package DataStore;

import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Entity.*;

public class DataStoreAdapterJSON implements DataStoreAdapter {
    private Gson gson;

    public DataStoreAdapterJSON() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void read(DataStore d){
        d.setCustomers(this.readCustomers());
        d.setItems(this.readItems());
    }

    public void writeCustomers(ArrayList<Customer> customers){
        try {
            FileWriter writer = new FileWriter("database/JSON/customers.json");
            this.gson.toJson(customers, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Fail to write to customers.json :" + e.getMessage());
        }
    }

    public ArrayList<Customer> readCustomers() {
        try {
            FileReader reader = new FileReader("database/JSON/customers.json");
            ArrayList<Customer> customers = this.gson.fromJson(reader, new TypeToken<ArrayList<Customer>>(){}.getType());
            reader.close();
            return customers;
        } catch (IOException e) {
            System.out.println("Fail to read from customers.json :" + e.getMessage());
            return new ArrayList<Customer>();
        }
    }

    public void writeItems(ArrayList<Item> items) {
        try {
            FileWriter writer = new FileWriter("database/JSON/items.json");
            this.gson.toJson(items, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Fail to write to items.json :" + e.getMessage());
        }
    }

    public ArrayList<Item> readItems() {
        try {
            FileReader reader = new FileReader("database/JSON/items.json");
            ArrayList<Item> items = this.gson.fromJson(reader, new TypeToken<ArrayList<Item>>(){}.getType());
            reader.close();
            return items;
        } catch (IOException e) {
            System.out.println("Fail to read from items.json :" + e.getMessage());
            return new ArrayList<Item>();
        }
    }
}
