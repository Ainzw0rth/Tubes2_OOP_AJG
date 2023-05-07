package DataStore.Adapter;

import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// import DataStore.DataStore;
import Entity.*;
import DataStore.Adapter.TypeAdapter.*;

public class AdapterJSON implements DataStoreAdapter {
    private Gson gson;
    final String RED = "\033[0;31m";     // RED
    final String RESET = "\033[0m";  // Text Reset

    private String dirPath;

    public AdapterJSON(String dirPath) {
        this.dirPath = dirPath;
        this.gson = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(Member.class, new JsonMemberSerializer())
            .registerTypeAdapter(Member.class, new JsonMemberDeserializer())
            .registerTypeAdapter(VIP.class, new JsonMemberSerializer())
            .create();
    }

    // public void read(DataStore d) throws IOException{
    //     d.setCustomers(this.readCustomers());
    //     d.setItems(this.readItems());
    //     d.setMembers(this.readMembers());
    //     d.setBills(this.readBills());
    //     d.setFixedBills(this.readFixedBills());

    //     // @WaitForImplement
    //     // d.setPluginPaths(this.readPluginPaths());
    // }
    
    public void delete(String className) {
        String filePath = dirPath + "/" + className;
        try {
            // Check if the file exists
            Path path = Paths.get(filePath + ".xml");
            if (Files.exists(path)) {
                // Delete the file
                Files.delete(path);   
            } 
            path = Paths.get(filePath + ".ser");
            if (Files.exists(path)){
                Files.delete(path);
            }
        } catch (Exception e) {
            System.out.println("Error deleting file: " + e.getMessage());
        }
    }

    public void isValid(String pathString) throws IOException {
        Path path = Paths.get(pathString);
        if (!Files.exists(path)){
            throw new IOException("File not found!", null);
        } 
    }


    public ArrayList<Customer> readCustomers() throws IOException{
        try {
            isValid(dirPath + "/customers.json");
            FileReader reader = new FileReader(dirPath + "/customers.json");
            ArrayList<Customer> customers = this.gson.fromJson(reader, new TypeToken<ArrayList<Customer>>(){}.getType());
            reader.close();
            return customers == null ? new ArrayList<Customer>() : customers;
        } catch (Exception e) {
            printError("Fail to read from customers.json", e);
            return new ArrayList<Customer>();
            // throw e;
        }
    }

    public void writeCustomers(ArrayList<Customer> customers) throws IOException{
        try {
            FileWriter writer = new FileWriter(dirPath + "/customers.json");
            String json = this.gson.toJson(customers);
            writer.write(json);
            writer.close();
            delete("customers");
        } catch (IOException e) {
            printError("Fail to write to customers.json", e);
            throw e;
        }
    }

    public ArrayList<Item> readItems() throws IOException{
        try {
            isValid(dirPath + "/items.json");
            FileReader reader = new FileReader(dirPath + "/items.json");
            ArrayList<Item> items = this.gson.fromJson(reader, new TypeToken<ArrayList<Item>>(){}.getType());
            reader.close();
            return items == null ? new ArrayList<Item>() : items;
        } catch (Exception e) {
            printError("Fail to read from items.json", e);
            return new ArrayList<Item>();
        }
    }

    public void writeItems(ArrayList<Item> items) throws IOException{
        try {
            FileWriter writer = new FileWriter(dirPath + "/items.json");
            String json = this.gson.toJson(items);
            writer.write(json);
            writer.close();
            delete("items");
        } catch (IOException e) {
            printError("Fail to write to items.json", e);
            throw e;
        }
    }

    public ArrayList<Member> readMembers() throws IOException {
        try {
            isValid(dirPath + "/members.json");
            FileReader reader = new FileReader(dirPath + "/members.json");
            ArrayList<Member> members = this.gson.fromJson(reader, new TypeToken<ArrayList<Member>>(){}.getType());
            reader.close();
            return members == null ? new ArrayList<Member>() : members;
        } catch (IOException e) {
            printError("Fail to read from members.json", e);
            return new ArrayList<Member>();
        }
    }
    
    public void writeMembers(ArrayList<Member> members) throws IOException {
        try {
            FileWriter writer = new FileWriter(dirPath + "/members.json");
            String json = this.gson.toJson(members);
            writer.write(json);
            writer.close();
            delete("members");
        } catch (IOException e) {
            printError("Fail to write to members.json", e);
            throw e;
        }
    }

    public ArrayList<Bill> readBills() throws IOException {
        try {
            isValid(dirPath + "/bills.json");
            FileReader reader = new FileReader(dirPath + "/bills.json");
            ArrayList<Bill> bills = this.gson.fromJson(reader, new TypeToken<ArrayList<Bill>>(){}.getType());
            reader.close();
            return bills == null ? new ArrayList<Bill>() : bills;
        } catch (IOException e) {
            printError("Fail to read from bills.json", e);
            return new ArrayList<Bill>();
        }
    }

    public void writeBills(ArrayList<Bill> bills) throws IOException {
        try {
            FileWriter writer = new FileWriter(dirPath + "/bills.json");
            String json = this.gson.toJson(bills);
            writer.write(json);
            writer.close();
            delete("bills");
        } catch (IOException e) {
            printError("Fail to write to bills.json", e);
            throw e;
        }
    }
    
    public ArrayList<FixedBill> readFixedBills() throws IOException {
        try {
            isValid(dirPath + "/fixed_bills.json");
            FileReader reader = new FileReader(dirPath + "/fixed_bills.json");
            ArrayList<FixedBill> fixedBills = this.gson.fromJson(reader, new TypeToken<ArrayList<FixedBill>>(){}.getType());
            reader.close();
            return fixedBills == null ? new ArrayList<FixedBill>() : fixedBills;
        } catch (IOException e) {
            printError("Fail to read from fixed_bills.json", e);
            return new ArrayList<FixedBill>();
        }
    }

    public void writeFixedBills(ArrayList<FixedBill> fixedBills) throws IOException {
        try {
            FileWriter writer = new FileWriter(dirPath + "/fixed_bills.json");
            String json = this.gson.toJson(fixedBills);
            writer.write(json);
            writer.close();
            delete("fixed_bills");
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
