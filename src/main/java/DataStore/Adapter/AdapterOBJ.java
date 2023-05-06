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
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("database/OBJ/customers.ser"));
            
            @SuppressWarnings("unchecked")
            ArrayList<Customer> customers = (ArrayList<Customer>) in.readObject();
            
            in.close();
            return customers;
        } catch (Exception e) {
            Exception _err = new Exception("Cannot read customers from file: " + e.getMessage() + "\n");
            printError("Fail to read from customers.ser", _err);
            throw _err;
        }
    }

    public void writeCustomers(ArrayList<Customer> customers) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database/OBJ/customers.ser"));
            out.writeObject(customers);
            out.close();
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write customers to file: " + e.getMessage() + "\n");
            printError("Fail to write to customers.ser", _err);
            throw _err;
        }
    }

    public ArrayList<Item> readItems() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("database/OBJ/items.ser"));
            
            @SuppressWarnings("unchecked")
            ArrayList<Item> items = (ArrayList<Item>) in.readObject();
            
            in.close();
            return items;
        } catch (Exception e) {
            Exception _err = new Exception("Cannot read items from file: " + e.getMessage() + "\n");
            printError("Fail to read from items.ser", _err);
            throw _err;
        }
    }

    public void writeItems(ArrayList<Item> items) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database/OBJ/items.ser"));
            out.writeObject(items);
            out.close();
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write items to file: " + e.getMessage() + "\n");
            printError("Fail to write to items.ser", _err);
            throw _err;
        }
    }


    public ArrayList<Member> readMembers() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("database/OBJ/members.ser"));
            
            ArrayList<Member> members = new ArrayList<Member>();
            while (true) {
                String className = (String) in.readObject();
                if (className == null) break;
                if (className.equals(Member.class.getName())) {
                    members.add((Member) in.readObject());
                } else if (className.equals(VIP.class.getName())) {
                    members.add((VIP) in.readObject());
                }
            }
            
            in.close();
            return members;
        } catch (Exception e) {
            Exception _err = new Exception("Cannot read members from file: " + e.getMessage() + "\n");
            printError("Fail to read from members.ser", _err);
            throw _err;

        }
    }

    public void writeMembers(ArrayList<Member> members) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database/OBJ/members.ser"));
        
            for (Member member : members) {
                if (member instanceof VIP) {
                    out.writeObject(VIP.class.getName());
                    out.writeObject(member);
                } else {
                    out.writeObject(Member.class.getName());
                    out.writeObject(member);
                }
            }

            out.close();
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write members to file: " + e.getMessage() + "\n");
            printError("Fail to write to members.ser", _err);
            throw _err;
        }
    }

    public ArrayList<Bill> readBills() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("database/OBJ/bills.ser"));
            
            @SuppressWarnings("unchecked")
            ArrayList<Bill> bills = (ArrayList<Bill>) in.readObject();
            
            in.close();
            return bills;
        } catch (Exception e) {
            Exception _err = new Exception("Cannot read bills from file: " + e.getMessage() + "\n");
            printError("Fail to read from bills.ser", _err);
            throw _err;
        }
    }

    public void writeBills(ArrayList<Bill> bills) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database/OBJ/bills.ser"));
            out.writeObject(bills);
            out.close();
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write bills to file: " + e.getMessage() + "\n");
            printError("Fail to write to bills.ser", _err);
            throw _err;
        }
    }

    public ArrayList<FixedBill> readFixedBills() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("database/OBJ/fixedBills.ser"));
            
            @SuppressWarnings("unchecked")
            ArrayList<FixedBill> fixedBills = (ArrayList<FixedBill>) in.readObject();
            
            in.close();
            return fixedBills;
        } catch (Exception e) {
            Exception _err = new Exception("Cannot read fixedBills from file: " + e.getMessage() + "\n");
            printError("Fail to read from fixedBills.ser", _err);
            throw _err;
        }
    }
    
    public void writeFixedBills(ArrayList<FixedBill> fixedBills) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database/OBJ/fixedBills.ser"));
            out.writeObject(fixedBills);
            out.close();
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write fixedBills to file: " + e.getMessage() + "\n");
            printError("Fail to write to fixedBills.ser", _err);
            throw _err;
        }
    }

    private void printError(String prompt, Exception e) {
        System.out.println(new StringBuilder().append(RED)
            .append(prompt).append(RESET).append(": ")
            .append(e.getMessage()).append("\n").toString());
    }
}
