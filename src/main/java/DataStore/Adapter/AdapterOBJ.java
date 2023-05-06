package DataStore.Adapter;

import java.util.*;

import DataStore.DataStore;
import Entity.*;
import java.io.*;

public class AdapterOBJ implements DataStoreAdapter {
    public void read(DataStore d) throws IOException {
        d.setCustomers(this.readCustomers());
        d.setItems(this.readItems());
        d.setMembers(this.readMembers());
        d.setBills(this.readBills());
        d.setFixedBills(this.readFixedBills());

        // @WaitForImplement
        // d.setPluginPaths(this.readPluginPaths());
    }
    
    public ArrayList<Customer> readCustomers() throws IOException {
        // TODO: implement
        return null;
    }

    public void writeCustomers(ArrayList<Customer> customers) throws IOException {
        try {
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customers.obj"));
        }
    }

    public ArrayList<Item> readItems() throws IOException {
        // TODO: implement
        return null;
    }

    public void writeItems(ArrayList<Item> items) throws IOException {
        // TODO: implement   
    }


    public ArrayList<Member> readMembers() throws IOException {
        // TODO: implement
        return null;
    }

    public void writeMembers(ArrayList<Member> members) throws IOException {
        // TODO: implement
    }

    public ArrayList<Bill> readBills() throws IOException {
        // TODO: implement
        return null;
    }

    public void writeBills(ArrayList<Bill> bills) throws IOException {
        // TODO: implement
    }

    public ArrayList<FixedBill> readFixedBills() throws IOException {
        // TODO: implement
        return null;
    }
    
    public void writeFixedBills(ArrayList<FixedBill> fixedBills) throws IOException {
        // TODO: implement
    }

}
