package DataStore.Adapter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;

import java.util.*;
import java.util.Locale.Category;

import DataStore.DataStore;
import Entity.*;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdapterXML implements DataStoreAdapter {
    
    private XStream xstream;

    public AdapterXML() {
        xstream = new XStream(new DomDriver());
        xstream.alias("customer", Customer.class);
        xstream.alias("item", Item.class);
        xstream.alias("member", Member.class);
        xstream.alias("bill", Bill.class);
        xstream.alias("fixed_bill", FixedBill.class);
        xstream.alias("list", ArrayList.class);
    }

    public void read(DataStore d) throws IOException {
        // TODO: implement
        d.setCustomers(readCustomers());
        d.setItems(readItems());
        d.setMembers(readMembers());
        d.setBills(readBills());
        d.setFixedBills(readFixedBills());
    }

    public ArrayList<Customer> readCustomers() throws IOException {
        // TODO: implement
        try {
            File file = new File("database/XML/customers.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList customerList = doc.getElementsByTagName("customer");
            
            ArrayList<Customer> customers = new ArrayList<Customer>();
    
            // Loop through all the customer nodes
            for (int i = 0; i < customerList.getLength(); i++) {
                Node customerNode = customerList.item(i);
                Element customerElement = (Element) customerNode;
                Element idElement = (Element) customerElement.getElementsByTagName("id").item(0);
                int id = Integer.parseInt(idElement.getTextContent());
                Customer customer = new Customer(id);
                customers.add(customer);
            }

            return customers;
        } catch (SAXException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void writeCustomers(ArrayList<Customer> customers) throws IOException {
        // TODO: implement
        try {
            String xml = xstream.toXML(customers);
            FileWriter fw = new FileWriter("database/XML/customers.xml");
            fw.write(xml);
            fw.close();
            System.out.println(xml);
        } catch (IOException e) {
            System.out.println("Fail to write to customers.xml");
            throw e;
        }
    }

    public ArrayList<Item> readItems() throws IOException {
        // TODO: implement
        try {
            File file = new File("database/XML/items.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList itemList = doc.getElementsByTagName("item");
            
            ArrayList<Item> items = new ArrayList<Item>();
    
            // Loop through all the item nodes
            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);
                Element itemElement = (Element) itemNode;
                
                // get all the attribut of item
                Element idElement = (Element) itemElement.getElementsByTagName("id").item(0);
                Element nameElement = (Element) itemElement.getElementsByTagName("name").item(0);
                Element priceElement = (Element) itemElement.getElementsByTagName("price").item(0);
                Element categoryElement = (Element) itemElement.getElementsByTagName("category").item(0);
                Element imageUrlElement = (Element) itemElement.getElementsByTagName("imageUrl").item(0);
                Element stockElement = (Element) itemElement.getElementsByTagName("stock").item(0);
                
                Integer id = Integer.parseInt(idElement.getTextContent());
                String name = nameElement.getTextContent();
                String category = categoryElement.getTextContent();
                Integer price = Integer.parseInt(priceElement.getTextContent());
                String imageUrl = imageUrlElement.getTextContent();
                Integer stock = Integer.parseInt(stockElement.getTextContent());
                
                Item item = new Item(id, name, category, price, imageUrl, stock);
                items.add(item);
            }

            return items;

        } catch (SAXException e) {
            e.printStackTrace();
            return new ArrayList<>();
        
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();            
        
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void writeItems(ArrayList<Item> items) throws IOException {
        // TODO: implement
        try {
            String xml = xstream.toXML(items);
            FileWriter fw = new FileWriter("database/XML/items.xml");
            fw.write(xml);
            fw.close();
            System.out.println(xml);
        } catch (IOException e) {
            System.out.println("Fail to write to items.xml");
            throw e;
        }
    }

    public ArrayList<Member> readMembers() throws IOException {
        // TODO: implement
        try {
            File file = new File("database/XML/members.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList memberList = doc.getElementsByTagName("member");
            
            ArrayList<Member> members = new ArrayList<Member>();
    
            // Loop through all the member nodes
            for (int i = 0; i < memberList.getLength(); i++) {

                Node memberNode = memberList.item(i);
                Element memberElement = (Element) memberNode;
                Element idElement = (Element) memberElement.getElementsByTagName("id").item(0);
                Element nameElement = (Element) memberElement.getElementsByTagName("name").item(0);
                Element phoneElement = (Element) memberElement.getElementsByTagName("phone").item(0);
                Element addressElement = (Element) memberElement.getElementsByTagName("address").item(0);
                Element pointElement = (Element) memberElement.getElementsByTagName("point").item(0);
                int id = Integer.parseInt(idElement.getTextContent());
                String name = nameElement.getTextContent();
                String phone = phoneElement.getTextContent();
                String address = addressElement.getTextContent();
                int point = Integer.parseInt(pointElement.getTextContent());
                Member member = new Member(id, name, phone, address, point);
                members.add(member);
            }

            return members;
        } catch (SAXException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    
    }

    public void writeMembers(ArrayList<Member> members) throws IOException {
        // TODO: implement
        try {
            String xml = xstream.toXML(members);
            FileWriter fw = new FileWriter("database/XML/members.xml");
            fw.write(xml);
            fw.close();
            System.out.println(xml);
        } catch (IOException e) {
            System.out.println("Fail to write to members.xml");
            throw e;
        }
    }

    public ArrayList<Bill> readBills() throws IOException {
        // TODO: implement
        try {
            File file = new File("database/XML/bills.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList billList = doc.getElementsByTagName("bill");
            
            ArrayList<Bill> bills = new ArrayList<Bill>();
    
            // Loop through all the bill nodes
            for (int i = 0; i < billList.getLength(); i++) {
                Node billNode = billList.item(i);
                Element billElement = (Element) billNode;

                // Bill have 4 attribute that is id:int, totalPrice:int, items:LinkedList<Item>, idCustomer:int
                // please instantiate Bill with all its attribute

                Element idElement = (Element) billElement.getElementsByTagName("id").item(0);
                Element totalPriceElement = (Element) billElement.getElementsByTagName("totalPrice").item(0);
                Element itemsElement = (Element) billElement.getElementsByTagName("items").item(0);
                Element idCustomerElement = (Element) billElement.getElementsByTagName("idCustomer").item(0);
                
                int id = Integer.parseInt(idElement.getTextContent());
                int totalPrice = Integer.parseInt(totalPriceElement.getTextContent());
                String[] itemStrings = itemsElement.getTextContent().split(";");
                int idCustomer = Integer.parseInt(idCustomerElement.getTextContent());
                
                LinkedList<Item> items = new LinkedList<Item>();
                for (String itemString : itemStrings) {
                    String[] itemStringSplit = itemString.split(",");

                    // item attribute: id, name, category, price, imageUrl, stock
                    int idItem = Integer.parseInt(itemStringSplit[0]);
                    int quantity = Integer.parseInt(itemStringSplit[1]);
                    


                    Item item = new Item(idItem, quantity);
                    items.add(item);
                }

                Bill bill = new Bill(id, totalPrice, items, idCustomer);
                bills.add(bill);
            }

            return bills;
        } catch (SAXException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void writeBills(ArrayList<Bill> bills) throws IOException {
        // TODO: implement
        try {
            String xml = xstream.toXML(bills);
            FileWriter fw = new FileWriter("database/XML/bills.xml");
            fw.write(xml);
            fw.close();
            System.out.println(xml);
        } catch (IOException e) {
            System.out.println("Fail to write to bills.xml");
            throw e;
        }
    }


    public ArrayList<FixedBill> readFixedBills() throws IOException {
        // TODO: implement
        try {
            File file = new File("database/XML/fixedBills.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList fixedBillList = doc.getElementsByTagName("fixedBill");
            
            ArrayList<FixedBill> fixedBills = new ArrayList<FixedBill>();
    
            // Loop through all the fixedBill nodes
            for (int i = 0; i < fixedBillList.getLength(); i++) {
                Node fixedBillNode = fixedBillList.item(i);
                Element fixedBillElement = (Element) fixedBillNode;
                Element idElement = (Element) fixedBillElement.getElementsByTagName("id").item(0);
                Element customerIDElement = (Element) fixedBillElement.getElementsByTagName("customerID").item(0);
                Element dateElement = (Element) fixedBillElement.getElementsByTagName("date").item(0);
                Element totalElement = (Element) fixedBillElement.getElementsByTagName("total").item(0);
                int id = Integer.parseInt(idElement.getTextContent());
                int customerID = Integer.parseInt(customerIDElement.getTextContent());
                String date = dateElement.getTextContent();
                double total = Double.parseDouble(totalElement.getTextContent());
                FixedBill fixedBill = new FixedBill(id, customerID, date, total);
                fixedBills.add(fixedBill);
            }

            return fixedBills;
        } catch (SAXException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void writeFixedBills(ArrayList<FixedBill> fixedBills) throws IOException {
        // TODO: implement
        try {
            String xml = xstream.toXML(fixedBills);
            FileWriter fw = new FileWriter("database/XML/fixedBills.xml");
            fw.write(xml);
            fw.close();
            System.out.println(xml);
        } catch (IOException e) {
            System.out.println("Fail to write to fixedBills.xml");
            throw e;
        }
    }

    public static void main(String[] args) {
        // // test writecustomer
        // ArrayList<Customer> customers = new ArrayList<Customer>();
        // customers.add(new Customer(1));
        // customers.add(new Customer(2));
        // customers.add(new Customer(3));
        // customers.add(new Customer(4));

        // AdapterXML adapterXML = new AdapterXML();
        // try {
        //     adapterXML.writeCustomers(customers);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        // // test readcustomer
        // AdapterXML adapterXML = new AdapterXML();
        // try {
        //     ArrayList<Customer> customers = adapterXML.readCustomers();
        //     for (Customer customer : customers) {
        //         System.out.println(customer);
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}
