package DataStore.Adapter;

import java.util.*;

import DataStore.DataStore;
import Entity.*;
import java.io.*;

public class AdapterOBJ implements DataStoreAdapter {

    final String RED = "\033[0;31m";     // RED
    final String RESET = "\033[0m";  // Text Reset

    public void read(DataStore d) throws Exception {
        d.setCustomers(this.readCustomers());
        d.setItems(this.readItems());
        d.setMembers(this.readMembers());
        d.setBills(this.readBills());
        d.setFixedBills(this.readFixedBills());

        // @WaitForImplement
        // d.setPluginPaths(this.readPluginPaths());
    }
    
    public ArrayList<Customer> readCustomers() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("database/OBJ/customers.obj"));
            
            @SuppressWarnings("unchecked")
            ArrayList<Customer> customers = (ArrayList<Customer>) in.readObject();
            
            in.close();
            return customers;
        } catch (Exception e) {
            Exception _err = new Exception("Cannot read customers from file: " + e.getMessage() + "\n");
            printError("Fail to read from customers.obj", _err);
            throw _err;
        }
    }

    public void writeCustomers(ArrayList<Customer> customers) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database/OBJ/customers.obj"));
            out.writeObject(customers);
            out.close();
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write customers to file: " + e.getMessage() + "\n");
            printError("Fail to write to customers.obj", _err);
            throw _err;
        }
    }

    public ArrayList<Item> readItems() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("database/OBJ/items.obj"));
            
            @SuppressWarnings("unchecked")
            ArrayList<Item> items = (ArrayList<Item>) in.readObject();
            
            in.close();
            return items;
        } catch (Exception e) {
            Exception _err = new Exception("Cannot read items from file: " + e.getMessage() + "\n");
            printError("Fail to read from items.obj", _err);
            throw _err;
        }
    }

    public void writeItems(ArrayList<Item> items) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database/OBJ/items.obj"));
            out.writeObject(items);
            out.close();
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write items to file: " + e.getMessage() + "\n");
            printError("Fail to write to items.obj", _err);
            throw _err;
        }
    }


    public ArrayList<Member> readMembers() throws Exception {
        // TODO: implement
        return null;
    }

    public void writeMembers(ArrayList<Member> members) throws Exception {
        // TODO: implement
    }

    public ArrayList<Bill> readBills() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("database/OBJ/bills.obj"));
            
            @SuppressWarnings("unchecked")
            ArrayList<Bill> bills = (ArrayList<Bill>) in.readObject();
            
            in.close();
            return bills;
        } catch (Exception e) {
            Exception _err = new Exception("Cannot read bills from file: " + e.getMessage() + "\n");
            printError("Fail to read from bills.obj", _err);
            throw _err;
        }
    }

    public void writeBills(ArrayList<Bill> bills) throws Exception {
        
    }

    public ArrayList<FixedBill> readFixedBills() throws Exception {
        // TODO: implement
        return null;
    }
    
    public void writeFixedBills(ArrayList<FixedBill> fixedBills) throws Exception {
        // TODO: implement
    }

    private void printError(String prompt, Exception e) {
        System.out.println(new StringBuilder().append(RED)
            .append(prompt).append(RESET).append(": ")
            .append(e.getMessage()).append("\n").toString());
    }
}
