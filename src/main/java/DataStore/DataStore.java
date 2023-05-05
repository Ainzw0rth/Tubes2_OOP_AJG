package DataStore;

import java.io.IOException;
import java.util.*;

import DataStore.Adapter.*;
import Entity.*;
import lombok.*;

public class DataStore {
    @Setter private ArrayList<Customer> customers;
    @Setter private ArrayList<Item> items;
    @Setter private ArrayList<Member> members;
    @Setter private ArrayList<Bill> bills;
    @Setter private ArrayList<FixedBill> fixedBills;

    // @WaitForImplement
    // @Setter private ArrayList<String> pluginPaths;

    final String RED = "\033[0;31m";     // RED
    final String RESET = "\033[0m";  // Text Reset

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
        this.members = new ArrayList<Member>();
        this.bills = new ArrayList<Bill>();
        this.fixedBills = new ArrayList<FixedBill>();

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
            printError("Fail to load data", e);
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
     * Get customers from file
     * @return ArrayList of Customer
     */
    public ArrayList<Customer> getCustomers() throws IOException{
        try {
            setCustomers(this.adapter.readCustomers());
            return this.customers;
        } catch (IOException e) {
            printError("Fail to get customers", e);
            throw e;
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
            printError("Fail to add customer", e);
            this.customers.remove(customer);
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
            printError("Fail to generate customer id", e);
            throw e;
        }

        if (this.customers.size() == 0) {
            return 1;
        }

        return this.customers.get(this.customers.size()-1).getId()+1;
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
            printError("Fail to get items", e);
            throw e;
        }
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
            printError("Fail to add item", e);
            this.items.remove(item);
            throw e;
        }
    }

    /**
     * Remove item from items and write to file
     * @param item_id
     */
    public void removeItem(Integer item_id) throws IOException{
        try {
            for (Item item : this.items) {
                if (item.getId() == item_id) {
                    this.items.remove(item);
                    break;
                }
            }
            this.adapter.writeItems(items);
        } catch (IOException e) {
            printError("Fail to remove item", e);
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
            printError("Fail to generate item id", e);
            throw e;
        }

        if (this.items.size() == 0) {
            return 1;
        }

        return this.items.get(this.items.size()-1).getId()+1;
    }

    /**
     * Get members from file
     * @return ArrayList of Member
     */
    public ArrayList<Member> getMembers() throws IOException {
        try {
            setMembers(this.adapter.readMembers());
            return this.members;
        } catch (IOException e) {
            printError("Fail to get members", e);
            throw e;
        }
    }

    /**
     * Add member to members and write to file
     * @param member
     */
    public void addMember(Member member) throws IOException {
        try {
            this.members.add(member);
            this.adapter.writeMembers(members);
        } catch (IOException e) {
            printError("Fail to add member", e);
            this.members.remove(member);
            throw e;
        }
    }

    /**
     * Update member to members and write to file
     * @param member_id 
     * @param member
     */
    public void updateMember(Integer member_id, Member member) throws IOException {
        try {
            for (Member m : this.members) {
                if (m.getId() == member_id) {
                    this.members.remove(m);
                    this.members.add(member);
                    break;
                }
            }
            this.adapter.writeMembers(members);
        } catch (IOException e) {
            printError("Fail to update member", e);
            throw e;
        }
    }

    private void printError(String prompt, Exception e) {
        System.out.println(new StringBuilder().append(RED)
            .append(prompt).append(RESET).append(": ")
            .append(e.getMessage()).append("\n").toString());
    }
}
