package DataStore.Adapter;

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
// import DataStore.DataStore;
import Entity.*;
import java.io.*;
import lombok.Setter;

public class AdapterOBJ implements DataStoreAdapter {

    final String RED = "\033[0;31m";     // RED
    final String RESET = "\033[0m";  // Text Reset
    @Setter private String dirPath;
    // public void read(DataStore d) throws Exception {
    //     d.setCustomers(this.readCustomers());
    //     d.setItems(this.readItems());
    //     d.setMembers(this.readMembers());
    //     d.setBills(this.readBills());
    //     d.setFixedBills(this.readFixedBills());

    //     // @WaitForImplement
    //     // d.setPluginPaths(this.readPluginPaths());
    // }

    public AdapterOBJ(String dirPath){
        this.dirPath = dirPath;
    }

    

    public void deleteOther(String className) {
        String filePath = dirPath + "/" + className;
        try {
            // Check if the file exists
            Path path = Paths.get(filePath + ".xml");
            if (Files.exists(path)) {
                // Delete the file
                Files.delete(path);   
            } 
            path = Paths.get(filePath + ".json");
            if (Files.exists(path)){
                Files.delete(path);
            }
        } catch (Exception e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
    }
    
    public ArrayList<Customer> readCustomers() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(dirPath + "/customers.ser"));
            
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
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dirPath + "/customers.ser"));
            out.writeObject(customers);
            out.close();
            deleteOther("customers");
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write customers to file: " + e.getMessage() + "\n");
            printError("Fail to write to customers.ser", _err);
            throw _err;
        }
    }

    public ArrayList<Item> readItems() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(dirPath + "/items.ser"));
            
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
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dirPath + "/items.ser"));
            out.writeObject(items);
            out.close();
            deleteOther("items");
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write items to file: " + e.getMessage() + "\n");
            printError("Fail to write to items.ser", _err);
            throw _err;
        }
    }


    public ArrayList<Member> readMembers() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(dirPath + "/members.ser"));
            
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
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dirPath + "/members.ser"));
        
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
            deleteOther("members");
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write members to file: " + e.getMessage() + "\n");
            printError("Fail to write to members.ser", _err);
            throw _err;
        }
    }

    public ArrayList<Bill> readBills() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(dirPath + "/bills.ser"));
            
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
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dirPath + "/bills.ser"));
            out.writeObject(bills);
            out.close();
            deleteOther("bills");
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write bills to file: " + e.getMessage() + "\n");
            printError("Fail to write to bills.ser", _err);
            throw _err;
        }
    }

    public ArrayList<FixedBill> readFixedBills() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(dirPath + "/fixed_bills.ser"));
            
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
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dirPath + "/fixed_bills.ser"));
            out.writeObject(fixedBills);
            out.close();
            deleteOther("fixed_bills");
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write fixedBills to file: " + e.getMessage() + "\n");
            printError("Fail to write to fixedBills.ser", _err);
            throw _err;
        }
    }

    public ArrayList<String> readPluginPaths() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(dirPath + "/plugins.ser"));
            
            @SuppressWarnings("unchecked")
            ArrayList<String> pluginPaths = (ArrayList<String>) in.readObject();
            
            in.close();
            return pluginPaths;
        } catch (Exception e) {
            Exception _err = new Exception("Cannot read pluginPaths from file: " + e.getMessage() + "\n");
            printError("Fail to read from pluginPaths.ser", _err);
            throw _err;
        }
    }


    public void writePluginPaths(ArrayList<String> pluginPaths) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dirPath + "/plugins.ser"));
            out.writeObject(pluginPaths);
            out.close();
            deleteOther("plugins");
        } catch (Exception e) {
            Exception _err = new Exception("Cannot write pluginPaths to file: " + e.getMessage() + "\n");
            printError("Fail to write to pluginPaths.ser", _err);
            throw _err;
        }
    }

    private void printError(String prompt, Exception e) {
        System.out.println(new StringBuilder().append(RED)
            .append(prompt).append(RESET).append(": ")
            .append(e.getMessage()).append("\n").toString());
    }


    public void deleteAll() {
        deleteOther("customers");
    //     delete("items");
    //     delete("members");
    //     delete("bills");
    //     delete("fixed_bills");
    }
}
