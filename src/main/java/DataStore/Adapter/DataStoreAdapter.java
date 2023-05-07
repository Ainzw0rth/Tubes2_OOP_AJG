package DataStore.Adapter;

import java.util.*;

import DataStore.DataStore;
import Entity.*;

public interface DataStoreAdapter {

    // /**
    //  * Read all datastore from file
    //  * @param d DataStore instance
    //  */
    // public void read(DataStore d) throws Exception;

    /**
     * delete file 
     * @param className 
     */
    public void deleteOther(String className);
    
    public void setDirPath(String dir);

    /**
     * Read customers data from file
     * @return ArrayList of Customer
     */
    public ArrayList<Customer> readCustomers() throws Exception;

    /**
     * Write customers data to file
     * @param customers ArrayList of Customer
     */
    public void writeCustomers(ArrayList<Customer> customers) throws Exception;

    /**
     * Read items data from file
     * @return ArrayList of Item
     */
    public ArrayList<Item> readItems() throws Exception;

    /**
     * Write items data to file
     * @param items ArrayList of Item
     */
    public void writeItems(ArrayList<Item> items) throws Exception;

    /**
     * Read members data from file
     * @return ArrayList of Member
     */
    public ArrayList<Member> readMembers() throws Exception;

    /**
     * write members data to file
     * @param members ArrayList of Member
     */
    public void writeMembers(ArrayList<Member> members) throws Exception;

    /**
     * Read bills data from file
     * @return ArrayList of Bill
     */
    public ArrayList<Bill> readBills() throws Exception;

    /**
     * Write bills data to file
     * @param bills ArrayList of Bill
     */
    public void writeBills(ArrayList<Bill> bills) throws Exception;


    /**
     * Read fixed bills data from file
     * @return ArrayList of FixedBill
     */
    public ArrayList<FixedBill> readFixedBills() throws Exception;

    /**
     * Write fixed bills data to file
     * @param fixedBills ArrayList of FixedBill
     */
    public void writeFixedBills(ArrayList<FixedBill> fixedBills) throws Exception;

        /**
     * Read fixed bills data from file
     * @return ArrayList of PluginPaths
     */
    public ArrayList<String> readPluginPaths() throws Exception;

    /**
     * Write fixed bills data to file
     * @param pluginPaths ArrayList of FixedBill
     */
    public void writePluginPaths(ArrayList<String> pluginPaths) throws Exception;

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
