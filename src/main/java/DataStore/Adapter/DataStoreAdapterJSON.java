package DataStore.Adapter;

import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import DataStore.DataStore;
import Entity.*;

public class DataStoreAdapterJSON implements DataStoreAdapter {
    private Gson gson;
    final String RED = "\033[0;31m";     // RED
    final String RESET = "\033[0m";  // Text Reset

    public DataStoreAdapterJSON() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void read(DataStore d) throws IOException{
        d.setCustomers(this.readCustomers());
        d.setItems(this.readItems());
        d.setMembers(this.readMembers());
        d.setBills(this.readBills());
        d.setFixedBills(this.readFixedBills());

        // @WaitForImplement
        // d.setPluginPaths(this.readPluginPaths());
    }

    public ArrayList<Customer> readCustomers() throws IOException{
        try {
            FileReader reader = new FileReader("database/JSON/customers.json");
            ArrayList<Customer> customers = this.gson.fromJson(reader, new TypeToken<ArrayList<Customer>>(){}.getType());
            reader.close();
            return customers;
        } catch (IOException e) {
            printError("Fail to read from customers.json", e);
            throw e;
        }
    }

    public void writeCustomers(ArrayList<Customer> customers) throws IOException{
        try {
            FileWriter writer = new FileWriter("database/JSON/customers.json");
            this.gson.toJson(customers, writer);
            writer.close();
        } catch (IOException e) {
            printError("Fail to write to customers.json", e);
            throw e;
        }
    }

    public ArrayList<Item> readItems() throws IOException{
        try {
            FileReader reader = new FileReader("database/JSON/items.json");
            ArrayList<Item> items = this.gson.fromJson(reader, new TypeToken<ArrayList<Item>>(){}.getType());
            reader.close();
            return items;
        } catch (IOException e) {
            printError("Fail to read from items.json", e);
            throw e;
        }
    }

    public void writeItems(ArrayList<Item> items) throws IOException{
        try {
            FileWriter writer = new FileWriter("database/JSON/items.json");
            this.gson.toJson(items, writer);
            writer.close();
        } catch (IOException e) {
            printError("Fail to write to items.json", e);
            throw e;
        }
    }

    public ArrayList<Member> readMembers() throws IOException {
        // TODO: implement
        try {
            FileReader reader = new FileReader("database/JSON/members.json");
            ArrayList<Member> members = this.gson.fromJson(reader, new TypeToken<ArrayList<Member>>(){}.getType());
            reader.close();
            return members;
        } catch (IOException e) {
            printError("Fail to read from members.json", e);
            return new ArrayList<Member>();
        }
    }
    
    public void writeMembers(ArrayList<Member> members) throws IOException {
        try {
            FileWriter writer = new FileWriter("database/JSON/members.json");
            this.gson.toJson(members, writer);
            writer.close();
        } catch (IOException e) {
            printError("Fail to write to members.json", e);
            throw e;
        }
    }

    public ArrayList<Bill> readBills() throws IOException {
        try {
            FileReader reader = new FileReader("database/JSON/bills.json");
            ArrayList<Bill> bills = this.gson.fromJson(reader, new TypeToken<ArrayList<Bill>>(){}.getType());
            reader.close();
            return bills;
        } catch (IOException e) {
            printError("Fail to read from bills.json", e);
            throw e;
        }
    }

    public void writeBills(ArrayList<Bill> bills) throws IOException {
        // TODO: implement
        try {
            FileWriter writer = new FileWriter("database/JSON/bills.json");
            this.gson.toJson(bills, writer);
            writer.close();
        } catch (IOException e) {
            printError("Fail to write to bills.json", e);
            throw e;
        }
    }
    
    public ArrayList<FixedBill> readFixedBills() throws IOException {
        // TODO: implement
        try {
            FileReader reader = new FileReader("database/JSON/fixed_bills.json");
            ArrayList<FixedBill> fixedBills = this.gson.fromJson(reader, new TypeToken<ArrayList<FixedBill>>(){}.getType());
            reader.close();
            return fixedBills;
        } catch (IOException e) {
            printError("Fail to read from fixed_bills.json", e);
            throw e;
        }
    }

    public void writeFixedBills(ArrayList<FixedBill> fixedBills) throws IOException {
        // TODO: implement
        try {
            FileWriter writer = new FileWriter("database/JSON/fixed_bills.json");
            this.gson.toJson(fixedBills, writer);
            writer.close();
        } catch (IOException e) {
            printError("Fail to write to fixed_bills.json", e);
            throw e;
        }
    }

    private void printError(String prompt, Exception e) {
        System.out.println(new StringBuilder().append(RED)
            .append(prompt).append(RESET).append(": ")
            .append(e.getMessage()).append("\n").toString());
    }
}
