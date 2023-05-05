package DataStore.Adapter;

import java.io.IOException;
import java.util.*;

import DataStore.DataStore;
import Entity.*;

public interface DataStoreAdapter {
    /**
     * Read all datastore from file
     * @param d DataStore instance
     */
    public void read(DataStore d) throws IOException;
    
    /**
     * Read customers data from file
     * @return ArrayList of Customer
     */
    public ArrayList<Customer> readCustomers() throws IOException;

    /**
     * Write customers data to file
     * @param customers ArrayList of Customer
     */
    public void writeCustomers(ArrayList<Customer> customers) throws IOException;

    /**
     * Read items data from file
     * @return ArrayList of Item
     */
    public ArrayList<Item> readItems() throws IOException;

    /**
     * Write items data to file
     * @param items ArrayList of Item
     */
    public void writeItems(ArrayList<Item> items) throws IOException;

    /**
     * Read members data from file
     * @return ArrayList of Member
     */
    public ArrayList<Member> readMembers() throws IOException;

    /**
     * write members data to file
     * @param members ArrayList of Member
     */
    public void writeMembers(ArrayList<Member> members) throws IOException;

    /**
     * Read bills data from file
     * @return ArrayList of Bill
     */
    public ArrayList<Bill> readBills() throws IOException;

    /**
     * Write bills data to file
     * @param bills ArrayList of Bill
     */
    public void writeBills(ArrayList<Bill> bills) throws IOException;


    /**
     * Read fixed bills data from file
     * @return ArrayList of FixedBill
     */
    public ArrayList<FixedBill> readFixedBills() throws IOException;

    /**
     * Write fixed bills data to file
     * @param fixedBills ArrayList of FixedBill
     */
    public void writeFixedBills(ArrayList<FixedBill> fixedBills) throws IOException;

    // @WaitForImplement
    /**
     * Write Base Plugin path to file
     * @param path String
     */
    // public void writeBasePluginPath(String path) throws IOException;

    /**
     * Read Base Plugin path from file
     * @return String
     */
    // public String readBasePluginPath() throws IOException;
}