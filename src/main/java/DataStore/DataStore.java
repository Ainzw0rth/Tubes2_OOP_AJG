package DataStore;

import java.io.IOException;
import java.util.*;
import Entity.*;
import lombok.*;

public class DataStore {
    @Setter private ArrayList<Customer> customers;
    @Setter private ArrayList<Item> items;
    // private ArrayList<Bill> bills;
    // private ArrayList<FixedBill> fixedBills;

    private DataStoreAdapter adapter;

    public enum FileStoreExt {
        JSON, 
        XML,
        OBJ
    } 
    
    private static DataStore instance;
    private DataStore() {
        this.customers = new ArrayList<Customer>();
        this.items = new ArrayList<Item>();

        this.adapter = new DataStoreAdapterJSON();
    }

    public static DataStore getInstance() {
        if (DataStore.instance == null) {
            DataStore.instance = new DataStore();
        }
        return DataStore.instance;
    }

    /**
     * Read all datastore from file
     */
    public void loadData() throws IOException{
        try {
            this.adapter.read(this);
        } catch (IOException e) {
            System.out.println("Fail to load data: " + e.getMessage() + "\n");
            throw e;
        }
    }

    /**
     * Change file extension to write/read
     * @param ext
     */
    public void changeExt(FileStoreExt ext) {
        switch (ext) {
            case JSON:
                this.adapter = new DataStoreAdapterJSON();
                break;
            case XML:
                this.adapter = new DataStoreAdapterXML();
                break;
            case OBJ:
                this.adapter = new DataStoreAdapterOBJ();
                break;
            default:
                this.adapter = new DataStoreAdapterJSON();
                break;
        }
    }

    /**
     * Add customer to customers and write to file
     * @param customer
     */
    public void addCustomer(Customer customer) throws IOException{
        try {
            this.customers.add(customer);
            this.adapter.writeCustomers(customers);
        } catch (IOException e) {
            System.out.println("Fail to add customer: " + e.getMessage() + "\n");
            this.customers.remove(customer);
            throw e;
        }
    }

    /**
     * Get customers from file
     * @return ArrayList of Customer
     */
    public ArrayList<Customer> getCustomers() throws IOException{
        try {
            setCustomers(this.adapter.readCustomers());
            return this.customers;
        } catch (IOException e) {
            System.out.println("Fail to get customers: " + e.getMessage() + "\n");
            throw e;
        }
    }
    
    /**
     * Generate customer id
     * @return Integer item id
     */
    public Integer generateCustomerId() throws IOException {
        try {
            setCustomers(this.adapter.readCustomers());
        } catch (IOException e){
            System.out.println("Fail to generate customer id (read failure): " + e.getMessage() + "\n");
            throw e;
        }

        if (this.customers.size() == 0) {
            return 1;
        }

        return this.customers.get(this.customers.size()-1).getId()+1;
    }

    /**
     * Add item to items and write to file
     * @param item
     */
    public void addItem(Item item) throws IOException{
        try {
            this.items.add(item);
            this.adapter.writeItems(items);
        } catch (IOException e) {
            System.out.println("Fail to add item: " + e.getMessage() + "\n");
            this.items.remove(item);
            throw e;
        }
    }

    /**
     * Get items from file
     * @return ArrayList of Item
     */
    public ArrayList<Item> getItems() throws IOException{
        try {
            setItems(this.adapter.readItems());
            return this.items;
        } catch (IOException e) {
            System.out.println("Fail to get items: " + e.getMessage() + "\n");
            throw e;
        }
    }

    /**
     * Generate item id
     * @return Integer item id
     */
    public Integer generateItemId() throws IOException {
        try {
            setItems(this.adapter.readItems());
        } catch (IOException e) {
            System.out.println("Fail to generate item id (read failure): " + e.getMessage() + "\n");
            throw e;
        }

        if (this.items.size() == 0) {
            return 1;
        }

        return this.items.get(this.items.size()-1).getId()+1;
    }
}
