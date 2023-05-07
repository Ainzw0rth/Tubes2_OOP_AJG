package DataStore;

import java.util.*;
import java.util.stream.Collectors;

import DataStore.Adapter.*;
import DataStore.Thread.BillWorker;
import Entity.*;
import lombok.*;

import java.io.*;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import Utils.Collections.ObservableCollection;

public class DataStore {
    @Getter private ObservableCollection<Customer> customers;
    @Getter private ObservableCollection<Item> items;
    @Getter private ObservableCollection<Member> members;
    @Getter private ObservableCollection<Bill> bills;
    @Getter private ObservableCollection<FixedBill> fixedBills;
    // @WaitForImplement
    // @Setter private ArrayList<String> pluginPaths;

    private static BillWorker billWorker; // autosave manager for bills

    // Debug color
    final String RED = "\033[0;31m";     // RED
    final String RESET = "\033[0m";  // Text Reset

    private DataStoreAdapter adapter;
    private String dirPath;


    public enum FileStoreExt {
        JSON, 
        XML,
        OBJ
    } 

    public enum MemberStatus {
        ACTIVE,
        INACTIVE,
        VIP
    }
    
    private static DataStore instance;
    
    private DataStore() {
        this.adapter = new AdapterJSON();   

        try {
            this.customers = new ObservableCollection<Customer>(this.adapter.readCustomers());
            this.items = new ObservableCollection<Item>(this.adapter.readItems());
            this.members = new ObservableCollection<Member>(this.adapter.readMembers());
            this.bills = new ObservableCollection<Bill>(this.adapter.readBills());
            this.fixedBills = new ObservableCollection<FixedBill>(this.adapter.readFixedBills());
        } catch (Exception e) {
            printError("Fail to load data", e);
        }
    }

    // Singleton point
    public static DataStore getInstance() {
        if (DataStore.instance == null) {
            // create new instance
            DataStore.instance = new DataStore();

            // start bill autosave routine
            DataStore.billWorker = new BillWorker();
            new Thread(DataStore.billWorker).start();
        }
        return DataStore.instance;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers.setElements(customers);
    }

    public void setItems(ArrayList<Item> items) {
        this.items.setElements(items);
    }

    public void setMembers(ArrayList<Member> members) {
        this.members.setElements(members);
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills.setElements(bills);
    }

    public void setFixedBills(ArrayList<FixedBill> fixedBills) {
        this.fixedBills.setElements(fixedBills);
    }

    /**
     * Read all datastore from file. Most likely will be deprecated
     * @throws Exception
     */
    public void loadData() throws Exception{
        try {
            this.adapter.read(this);
        } catch (Exception e) {
            printError("Fail to load data", e);
            throw e;
        }
    }

    /**
     * Change file extension to write/read
     * @param ext
     */
    public void changeExt(FileStoreExt ext) throws Exception{
        switch (ext) {
            case JSON:
                this.adapter = new AdapterJSON();
                break;
            case XML:
                this.adapter = new AdapterXML();
                break;
            case OBJ:
                this.adapter = new AdapterOBJ();
                break;
            default:
                this.adapter = new AdapterJSON();
                break;
        }

        try {            
            this.adapter.writeBills(this.bills.getElements());
            this.adapter.writeCustomers(this.customers.getElements());
            this.adapter.writeFixedBills(this.fixedBills.getElements());
            this.adapter.writeItems(this.items.getElements());
            this.adapter.writeMembers(this.members.getElements());
        } catch (Exception e) {
            printError("Fail to change file extension", e);
            throw e;
        }
    }

    public void changeConfig(String dirPath, String ext) {
        try {
            // create a Gson object
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
            // create a map representing the new JSON structure
            Map<String, String> myMap = new HashMap<>();
            myMap.put("dirPath", dirPath);
            myMap.put("ext", ext);
            

            // serialize the map into JSON format
            String jsonString = gson.toJson(myMap);
            
            // write the JSON string to a file
            FileWriter writer = new FileWriter("src/main/config/config.json");
            writer.write(jsonString);
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getConfig() {
        Map<String, String> config = new HashMap<>();
        try {
            String jsonStr = "";
            BufferedReader reader = new BufferedReader(new FileReader("src/main/config/config.json"));
            String line = reader.readLine();
            while (line != null) {
                jsonStr += line;
                line = reader.readLine();
            }
            reader.close();
    
            JSONObject jsonObj = new JSONObject(jsonStr);
            config.put("ext", jsonObj.getString("ext"));
            config.put("dirPath", jsonObj.getString("dirPath"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return config;
    }


    
    // /**
    //  * Get customers from file
    //  * @return ArrayList of Customer
    //  */
    // public ArrayList<Customer> getCustomers() throws Exception{
    //     try {
    //         setCustomers(this.adapter.readCustomers());
    //         return this.customers;
    //     } catch (Exception e) {
    //         printError("Fail to get customers", e);
    //         throw e;
    //     }
    // }

    /**
     * Add customer to customers and write to file
     * @param customer
     */
    public void addCustomer(Customer customer) throws Exception{
        try {
            this.customers.add(customer);
            this.adapter.writeCustomers(customers.getElements());
        } catch (Exception e) {
            printError("Fail to add customer", e);
            this.customers.remove(customer);
            throw e;
        }
    }
    
    /**
     * Generate customer id
     * @return Integer item id
     */
    public Integer generateCustomerId() throws Exception {
        try {
            setCustomers(this.adapter.readCustomers());
        } catch (Exception e){
            printError("Fail to generate customer id", e);
            throw e;
        }

        if (this.customers.size() == 0) {
            return 1;
        }

        return this.customers.get(this.customers.size()-1).getId()+1;
    }

    // /**
    //  * Get items from file
    //  * @return ArrayList of Item
    //  */
    // public ArrayList<Item> getItems() throws Exception{
    //     try {
    //         setItems(this.adapter.readItems());
    //         return this.items;
    //     } catch (Exception e) {
    //         printError("Fail to get items", e);
    //         throw e;
    //     }
    // }

    /**
     * Add item to items and write to file
     * @param item
     */
    public void addItem(Item item) throws Exception{
        try {
            this.items.add(item);
            this.adapter.writeItems(items.getElements());
        } catch (Exception e) {
            printError("Fail to add item", e);
            this.items.remove(item);
            throw e;
        }
    }

    /**
     * Remove item from items and write to file
     * @param item_id
     */
    public void removeItem(Integer item_id) throws Exception{
        try {
            for (Item item : this.items.getElements()) {
                if (item.getId() == item_id) {
                    this.items.remove(item);
                    break;
                }
            }
            this.adapter.writeItems(items.getElements());
        } catch (Exception e) {
            printError("Fail to remove item", e);
            throw e;
        }
    }

    /**
     * Update item from items and write to file
     * @param item_id
     * @param new_item
    */
    public void updateItem(Integer item_id, Item new_item) throws Exception{
        try {
            for (Item item : this.items.getElements()) {
                if (item.getId() == item_id) {
                    this.items.remove(item);
                    this.items.add(new_item);
                    break;
                }
            }
            this.adapter.writeItems(items.getElements());
        } catch (Exception e) {
            printError("Fail to update item", e);
            throw e;
        }
    }

    /**
     * Generate item id
     * @return Integer item id
     */
    public Integer generateItemId() throws Exception {
        try {
            setItems(this.adapter.readItems());
        } catch (Exception e) {
            printError("Fail to generate item id", e);
            throw e;
        }

        if (this.items.size() == 0) {
            return 1;
        }

        return this.items.get(this.items.size()-1).getId()+1;
    }


    // /**
    //  * Get members from file (including inactive members)
    //  * @return ArrayList of Member
    //  */
    // public ArrayList<Member> getMembers() throws Exception {
    //     try {
    //         setMembers(this.adapter.readMembers());
    //         return this.members;
    //     } catch (Exception e) {
    //         printError("Fail to get members", e);
    //         throw e;
    //     }
    // }

    /**
     * Get members from file
     * @return ArrayList of Member
     */
    public ArrayList<Member> getActiveMembers(Boolean includeVIP) /*throws Exception*/ {
        // try {
            // setMembers(this.adapter.readMembers());

        ArrayList<Member> mem_arr = this.members.getElements().stream()
            .filter(member -> member.getIsActive())
            .collect(Collectors.toCollection(ArrayList::new));

        if (includeVIP) {
            return mem_arr;
        } else {
            return mem_arr.stream()
                .filter(member -> !(member instanceof VIP))
                .collect(Collectors.toCollection(ArrayList::new));
            }
        // } catch (Exception e) {
        //     printError("Fail to get members", e);
        //     throw e;
        // }
    }

    /**
     * Get members from file (does include VIP)
     * @return ArrayList of Member
     */
    public ArrayList<Member> getActiveMembers() throws Exception {
        return getActiveMembers(true);
    }

    /**
     * Get member by id
     * @param member
     * @throws Exception
     */
    public Member getMemberById(Integer member_id) throws Exception {
        // try {
        //     setMembers(this.adapter.readMembers());
        for (Member member : this.members.getElements()) {
            if (member.getId() == member_id) {
                return member;
            }
        }
        return null;
        // } catch (Exception e) {
        //     printError("Fail to get member by id", e);
        //     throw e;
        // }
    }

    /**
     * Add member to members and write to file
     * @param member
     */
    public void addMember(Member member) throws Exception {
        try {
            this.members.add(member);
            this.adapter.writeMembers(members.getElements());
        } catch (Exception e) {
            printError("Fail to add member", e);
            this.members.remove(member);
            throw e;
        }
    }

    /**
     * Update member to members and write to file
     * @param member_id 
     * @param member
     */
    public void updateMember(Integer member_id, Member member) throws Exception {
        try {
            Boolean found = false;
            for (Member m : this.members.getElements()) {
                if (m.getId() == member_id) {
                    this.members.remove(m);
                    this.members.add(new Member(
                        m.getId(), m.getName(), m.getPhoneNumber(), m.getIsActive(), m.getPoint()
                    ));
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new Exception("Member not found");
            }

            this.adapter.writeMembers(members.getElements());
        } catch (Exception e) {
            printError("Fail to update member", e);
            throw e;
        }
    }

    /**
     * Change status of member
     * @param member_id
     * @param status
     */
    public void changeMemberStatus(Integer member_id, MemberStatus status) throws Exception {
        try {
            int index = -1;
            int it = 0;
            for (Member m : this.members.getElements()) {
                if (m.getId() == member_id) {
                    index = it;
                    break;
                }
                it++;
            }
            if (index == -1) {
                throw new Exception("Member not found");
            } else {
                switch (status) {
                    case ACTIVE:
                        this.members.get(index).setIsActive(true);
                        break;
                    case INACTIVE:
                        this.members.get(index).setIsActive(false);
                        break;
                    case VIP:
                        Member m = this.members.get(index);
                        this.members.set(index, new VIP(
                            m.getId(), m.getName(), m.getPhoneNumber(), true, m.getPoint()
                        ));
                        break;
                    default:
                        throw new Exception("Invalid status");
                }
            }
        } catch (Exception e) {
            printError("Fail to change member status", e);
            throw e;
        }
    }


    /**
     * Get fixed bills from file
     * @return ArrayList of FixedBill
    */
    // public ArrayList<FixedBill> getFixedBills() throws Exception {
    //     try {
    //         setFixedBills(this.adapter.readFixedBills());
    //         return this.fixedBills;
    //     } catch (Exception e) {
    //         printError("Fail to get fixed bills", e);
    //         throw e;
    //     }
    // }

    /**
     * Get fixed bills by cust_id
     * @param bill_id
     * @return ArrayList of FixedBill
     * @throws Exception
    */
    public ArrayList<FixedBill> getFixedBillsByCustId(Integer cust_id) throws Exception {
        // try {
        //     setFixedBills(this.adapter.readFixedBills());
        ArrayList<FixedBill> bills = new ArrayList<FixedBill>();
        for (FixedBill bill : this.fixedBills.getElements()) {
            if (bill.getIdCustomer() == cust_id) {
                bills.add(bill);
            }
        }
        if (bills.size() == 0) {
            throw new Exception("No fixed bill found");
        }

        return bills;
        // } catch (Exception e) {
        //     printError("Fail to get fixed bills by cust_id", e);
        //     throw e;
        // }
    }

    /**
     * Add FixedBill to fixedBills and write to file
     * @param fixedBill
     * @throws Exception
     */
    public void addFixedBill(FixedBill fixedBill) throws Exception {
        try {
            this.fixedBills.add(fixedBill);
            this.adapter.writeFixedBills(fixedBills.getElements());
        } catch (Exception e) {
            printError("Fail to add fixed bill", e);
            this.fixedBills.remove(fixedBill);
            throw e;
        }
    }

    /**
     * Update FixedBill to fixedBills and write to file
     * @implNote **DO NOT CALL THIS METHOD DIRECTLY**! Only Called by BillWorker
     * @param bill_id
     * @param fixedBill
     * @throws Exception
     */
    public void writeBills(ArrayList<Bill> bills) throws Exception {
        try {
            this.adapter.writeBills(bills);
        } catch (Exception e) {
            printError("Fail to write bills", e);
            throw e;
        }
    }

    /**
     * Get bills from file
     * @return ArrayList of Bill
    */
    // public ArrayList<Bill> getBills() throws Exception {
    //     try {
    //         setBills(this.adapter.readBills());
    //         return this.bills;
    //     } catch (Exception e) {
    //         printError("Fail to get bills", e);
    //         throw e;
    //     }
    // }

    /**
     * Start new bill session
     * @param bill
     * @throws Exception
     */
    public void startNewBill(Bill bill) throws Exception {
        this.bills.add(bill);
        DataStore.billWorker.addBill(bill);
    }

    /**
     * Finish bill session
     * @param bill
     * @throws Exception
     */
    public void finishBill(Bill bill) throws Exception {
        try {
            this.bills.remove(bill);
            DataStore.billWorker.removeBill(bill);
            
            this.addFixedBill(new FixedBill(
                bill.getId(), bill.getTotalPrice(), bill.getItems(), bill.getIdCustomer()
            ));

            this.adapter.writeBills(bills.getElements());
        } catch (Exception e) {
            printError("Fail to finish bill, might cause fixed bill data desync", e);
            DataStore.billWorker.addBill(bill);
            this.bills.add(bill);
            throw e;
        }
    }

    /**
     * Generate bill id (same as fixed bill id)
     * @return Integer
     * @throws Exception
    */
    public Integer generateBillId() throws Exception {
        Integer activeBillsLen = 0;
        try {
            setFixedBills(this.adapter.readFixedBills());
            activeBillsLen = this.bills.size();
        } catch (Exception e){
            printError("Fail to generate fixed bills id", e);
            throw e;
        }

        if (this.fixedBills.size() == 0) {
            return 1 + this.bills.size();
        }

        return this.fixedBills.get(this.fixedBills.size() - 1).getId() + activeBillsLen + 1;
    }

    /**
     * Print error with format
     * @param prompt
     * @param e
     */
    private void printError(String prompt, Exception e) {
        System.out.println(new StringBuilder().append(RED)
            .append(prompt).append(RESET).append(": ")
            .append(e.getMessage()).append("\n").toString());
    }

    public static void main(String[] args) {
        DataStore ds = new DataStore();
        ds.getConfig();
    }
}
