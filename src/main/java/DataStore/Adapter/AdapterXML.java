package DataStore.Adapter;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.*;
import org.xml.sax.SAXParseException;

import DataStore.Adapter.TypeAdapter.XmlList.*;
import DataStore.DataStore;
import Entity.*;

public class AdapterXML implements DataStoreAdapter {
    
    private XStream xstream;
    private String dirPath;

    public AdapterXML(String dirPath) {
        this.dirPath = dirPath;
        xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("list", CustomerList.class);
        xstream.alias("customer", Customer.class);
        xstream.addImplicitCollection(CustomerList.class, "customers");
        
        xstream.alias("item", Item.class);
        xstream.addImplicitCollection(ItemList.class, "items");
        
        xstream.alias("member", Member.class);
        xstream.addImplicitCollection(MemberList.class, "members");
        
        xstream.alias("bill", Bill.class);
        xstream.addImplicitCollection(BillList.class, "bills");
        
        xstream.alias("fixed_bill", FixedBill.class);
        // xstream.addImplicitCollection(FixedBillList.class, "fixed_bills");
    }

    public void delete(String className) {
        String filePath = dirPath + "/" + className;
        try {
            // Check if the file exists
            Path path = Paths.get(filePath + ".json");
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

    public ArrayList<Customer> readCustomers() throws IOException {
        // read XML data from file
        String filename = dirPath + "/customers.xml";
        String xml = "";
        try {
            FileInputStream file = new FileInputStream(filename);
            byte[] data = new byte[file.available()];
            file.read(data);
            file.close();
            xml = new String(data);

            // deserialize XML data into array of Customer objects
            CustomerList customerrr = (CustomerList) xstream.fromXML(xml);

            // ArrayList<Customer> temp = new ArrayList<>();
            return customerrr.customers; 

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return new ArrayList<Customer>();
            
        } catch (Exception e) {
            Throwable rootCause = e.getCause();
            if (rootCause instanceof SAXParseException && rootCause.getMessage().equals("Premature end of file.")) {
                // Handle the premature end of file error
                System.out.println("File is empty");
            } else {
                // Handle other XStream exceptions
                System.out.println("Error reading file: " + e.getMessage());
            }
            // e.printStackTrace();
            // // System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<Customer>();
        }        
    }

    public void writeCustomers(ArrayList<Customer> customers) throws IOException {
        // TODO: implement
        System.out.println("klajflkjaflka");
        try {
            String xml = xstream.toXML(customers);
            FileWriter fw = new FileWriter(dirPath + "/customers.xml");
            fw.write(xml);
            fw.close();
            
            delete("customers");
            
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Fail to write to customers.xml");
            throw e;
        }
    }

    public ArrayList<Item> readItems() throws IOException {
        // read XML data from file
        // please implement read items according to readCustomers
        String filename = dirPath + "/items.xml";
        String xml = "";
        try {
            FileInputStream file = new FileInputStream(filename);
            byte[] data = new byte[file.available()];
            file.read(data);
            file.close();
            xml = new String(data);

            // deserialize XML data into array of Customer objects
            ItemList itemmm = (ItemList) xstream.fromXML(xml);

            // ArrayList<Customer> temp = new ArrayList<>();
            return itemmm.items; 

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return new ArrayList<Item>();
            
        } catch (Exception e) {
            Throwable rootCause = e.getCause();
            if (rootCause instanceof SAXParseException && rootCause.getMessage().equals("Premature end of file.")) {
                // Handle the premature end of file error
                System.out.println("File is empty");
            } else {
                // Handle other XStream exceptions
                System.out.println("Error reading file: " + e.getMessage());
            }
            return new ArrayList<Item>();
        }

    }

    public void writeItems(ArrayList<Item> items) throws IOException {
        // TODO: implement
        try {
            String xml = xstream.toXML(items);
            FileWriter fw = new FileWriter(dirPath + "/items.xml");
            fw.write(xml);
            fw.close();
            delete("items");
            System.out.println(xml);
        } catch (IOException e) {
            System.out.println("Fail to write to items.xml");
            throw e;
        }
    }

    public ArrayList<Member> readMembers() throws IOException {
        // TODO: implement
        // please implement according to read customers
        String filename = dirPath + "/members.xml";
        String xml = "";
        try {
            FileInputStream file = new FileInputStream(filename);
            byte[] data = new byte[file.available()];
            file.read(data);
            file.close();
            xml = new String(data);

            // deserialize XML data into array of Customer objects
            MemberList mem = (MemberList) xstream.fromXML(xml);

            // ArrayList<Customer> temp = new ArrayList<>();
            return mem.members; 

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return new ArrayList<Member>();
            
        } catch (Exception e) {
            Throwable rootCause = e.getCause();
            if (rootCause instanceof SAXParseException && rootCause.getMessage().equals("Premature end of file.")) {
                // Handle the premature end of file error
                System.out.println("File is empty");
            } else {
                // Handle other XStream exceptions
                System.out.println("Error reading file: " + e.getMessage());
            }
            return new ArrayList<Member>();
        }
    }

    public void writeMembers(ArrayList<Member> members) throws IOException {
        // TODO: implement
        try {
            String xml = xstream.toXML(members);
            FileWriter fw = new FileWriter(dirPath + "/members.xml");
            fw.write(xml);
            fw.close();
            delete("members");
            System.out.println(xml);
        } catch (IOException e) {
            System.out.println("Fail to write to members.xml");
            throw e;
        }
    }

    public ArrayList<Bill> readBills() throws IOException {
        // TODO: implement
        // please implement according to read customers
        String filename = dirPath + "/bills.xml";
        String xml = "";
        try {
            FileInputStream file = new FileInputStream(filename);
            byte[] data = new byte[file.available()];
            file.read(data);
            file.close();
            xml = new String(data);

            // deserialize XML data into array of Customer objects
            BillList billl = (BillList) xstream.fromXML(xml);

            // ArrayList<Customer> temp = new ArrayList<>();
            return billl.bills; 

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return new ArrayList<Bill>();
            
        } catch (Exception e) {
            Throwable rootCause = e.getCause();
            if (rootCause instanceof SAXParseException && rootCause.getMessage().equals("Premature end of file.")) {
                // Handle the premature end of file error
                System.out.println("File is empty");
            } else {
                // Handle other XStream exceptions
                System.out.println("Error reading file: " + e.getMessage());
            }
            return new ArrayList<Bill>();
        }
    }

    public void writeBills(ArrayList<Bill> bills) throws IOException {
        // TODO: implement
        try {
            String xml = xstream.toXML(bills);
            FileWriter fw = new FileWriter(dirPath + "/bills.xml");
            fw.write(xml);
            fw.close();
            delete("bills");
            System.out.println(xml);
        } catch (IOException e) {
            System.out.println("Fail to write to bills.xml");
            throw e;
        }
    }

    public ArrayList<FixedBill> readFixedBills() throws IOException {
        // TODO: implement
        // please implement according to read customers
        String filename = dirPath + "/fixed_bills.xml";
        String xml = "";
        try {
            FileInputStream file = new FileInputStream(filename);
            byte[] data = new byte[file.available()];
            file.read(data);
            file.close();
            xml = new String(data);

            // deserialize XML data into array of Customer objects
            FixedBillList fixedBilll = (FixedBillList) xstream.fromXML(xml);

            // ArrayList<Customer> temp = new ArrayList<>();
            return fixedBilll.fixedBills; 

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return new ArrayList<FixedBill>();
            
        } catch (Exception e) {
            Throwable rootCause = e.getCause();
            if (rootCause instanceof SAXParseException && rootCause.getMessage().equals("Premature end of file.")) {
                // Handle the premature end of file error
                System.out.println("File is empty");
            } else {
                // Handle other XStream exceptions
                System.out.println("Error reading file: " + e.getMessage());
            }
            return new ArrayList<FixedBill>();
        }
    }

    public void writeFixedBills(ArrayList<FixedBill> fixedBills) throws IOException {
        // TODO: implement
        try {
            String xml = xstream.toXML(fixedBills);
            FileWriter fw = new FileWriter(dirPath + "/fixed_bills.xml");
            fw.write(xml);
            fw.close();
            delete("fixed_bills");
            System.out.println(xml);
        } catch (IOException e) {
            Throwable rootCause = e.getCause();
            if (rootCause instanceof SAXParseException && rootCause.getMessage().equals("Premature end of file.")) {
                // Handle the premature end of file error
                System.out.println("File is empty");
            } else {
                // Handle other XStream exceptions
                System.out.println("Error reading file: " + e.getMessage());
            }
            throw e;
        }
    }

    public static void main(String[] args) {
        // test writecustomer
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer(1));
        customers.add(new Customer(2));
        customers.add(new Customer(3));
        customers.add(new Customer(4));

        AdapterXML adapterXML = new AdapterXML("D:/ITB 21/KULYAHHH/SEMESTER 4/OOP/Tubes 2 OOP/Tubes2_OOP_AJG/database/customers.xml");
        try {
            adapterXML.writeCustomers(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // test readcustomer
        // AdapterXML adapterXML = new AdapterXML(this.dirPath);
        // try {
        //     // ArrayList<Item> customerss = adapterXML.readItems();
        //     ArrayList<Customer> customers = adapterXML.readCustomers();
        //     for (Customer customer : customers) {
        //         System.out.println(customer.getId());
        //     }

        // } catch (IOException e) {
        //     System.out.println("disini");
        //     e.printStackTrace();
        // }
    }
}

