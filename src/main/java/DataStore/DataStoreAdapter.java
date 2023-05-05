package DataStore;

import java.io.IOException;
import java.util.*;
import Entity.*;

public interface DataStoreAdapter {
    /**
     * Read all datastore from file
     * @param d DataStore instance
     */
    public void read(DataStore d) throws IOException;

    /**
     * Write customers data to file
     * @param customers ArrayList of Customer
     */
    public void writeCustomers(ArrayList<Customer> customers) throws IOException;

    /**
     * Read customers data from file
     * @return ArrayList of Customer
     */
    public ArrayList<Customer> readCustomers() throws IOException;

    /**
     * Write items data to file
     * @param items ArrayList of Item
     */
    public void writeItems(ArrayList<Item> items) throws IOException;

    /**
     * Read items data from file
     * @return ArrayList of Item
     */
    public ArrayList<Item> readItems() throws IOException;
}
