package DataStore;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import DataStore.Adapter.*;
import DataStore.Utils.BillWorker;
import Entity.*;
import lombok.*;

public class DataStore {
    @Setter @Getter private ArrayList<Customer> customers;
    @Setter @Getter private ArrayList<Item> items;
    @Setter @Getter private ArrayList<Member> members;
    @Setter @Getter private ArrayList<Bill> bills;
    @Setter @Getter private ArrayList<FixedBill> fixedBills;
    // @WaitForImplement
    // @Setter private ArrayList<String> pluginPaths;

    private static BillWorker billWorker; // autosave manager for bills

    // Debug color
    final String RED = "\033[0;31m";     // RED
    final String RESET = "\033[0m";  // Text Reset

    private DataStoreAdapter adapter;

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
            this.customers = this.adapter.readCustomers();
            this.items = this.adapter.readItems();
            this.members = this.adapter.readMembers();
            this.bills = this.adapter.readBills();
            this.fixedBills = this.adapter.readFixedBills();
        } catch (IOException e) {
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

    /**
     * Read all datastore from file. Most likely will be deprecated
     * @throws IOException
     */
    public void loadData() throws IOException{
        try {
            this.adapter.read(this);
        } catch (IOException e) {
            printError("Fail to load data", e);
            throw e;
        }
    }

    /**
     * Change file extension to write/read
     * @param ext
     */
    public void changeExt(FileStoreExt ext) {
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
    }

    // /**
    //  * Get customers from file
    //  * @return ArrayList of Customer
    //  */
    // public ArrayList<Customer> getCustomers() throws IOException{
    //     try {
    //         setCustomers(this.adapter.readCustomers());
    //         return this.customers;
    //     } catch (IOException e) {
    //         printError("Fail to get customers", e);
    //         throw e;
    //     }
    // }

    /**
     * Add customer to customers and write to file
     * @param customer
     */
    public void addCustomer(Customer customer) throws IOException{
        try {
            this.customers.add(customer);
            this.adapter.writeCustomers(customers);
        } catch (IOException e) {
            printError("Fail to add customer", e);
            this.customers.remove(customer);
            throw e;
        }
    }
    
    /**
     * Generate customer id
     * @return Integer item id
     */
    public Integer generateCustomerId() throws IOException {
        try {
            setCustomers(this.adapter.readCustomers());
        } catch (IOException e){
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
    // public ArrayList<Item> getItems() throws IOException{
    //     try {
    //         setItems(this.adapter.readItems());
    //         return this.items;
    //     } catch (IOException e) {
    //         printError("Fail to get items", e);
    //         throw e;
    //     }
    // }

    /**
     * Add item to items and write to file
     * @param item
     */
    public void addItem(Item item) throws IOException{
        try {
            this.items.add(item);
            this.adapter.writeItems(items);
        } catch (IOException e) {
            printError("Fail to add item", e);
            this.items.remove(item);
            throw e;
        }
    }

    /**
     * Remove item from items and write to file
     * @param item_id
     */
    public void removeItem(Integer item_id) throws IOException{
        try {
            for (Item item : this.items) {
                if (item.getId() == item_id) {
                    this.items.remove(item);
                    break;
                }
            }
            this.adapter.writeItems(items);
        } catch (IOException e) {
            printError("Fail to remove item", e);
            throw e;
        }
    }

    /**
     * Update item from items and write to file
     * @param item_id
     * @param new_item
    */
    public void updateItem(Integer item_id, Item new_item) throws IOException{
        try {
            for (Item item : this.items) {
                if (item.getId() == item_id) {
                    this.items.remove(item);
                    this.items.add(new_item);
                    break;
                }
            }
            this.adapter.writeItems(items);
        } catch (IOException e) {
            printError("Fail to update item", e);
            throw e;
        }
    }

    /**
     * Generate item id
     * @return Integer item id
     */
    public Integer generateItemId() throws IOException {
        try {
            setItems(this.adapter.readItems());
        } catch (IOException e) {
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
    // public ArrayList<Member> getMembers() throws IOException {
    //     try {
    //         setMembers(this.adapter.readMembers());
    //         return this.members;
    //     } catch (IOException e) {
    //         printError("Fail to get members", e);
    //         throw e;
    //     }
    // }

    /**
     * Get members from file
     * @return ArrayList of Member
     */
    public ArrayList<Member> getActiveMembers(Boolean includeVIP) /*throws IOException*/ {
        // try {
            // setMembers(this.adapter.readMembers());

        ArrayList<Member> mem_arr = this.members.stream()
            .filter(member -> member.getIsActive())
            .collect(Collectors.toCollection(ArrayList::new));

        if (includeVIP) {
            return mem_arr;
        } else {
            return mem_arr.stream()
                .filter(member -> !(member instanceof VIP))
                .collect(Collectors.toCollection(ArrayList::new));
            }
        // } catch (IOException e) {
        //     printError("Fail to get members", e);
        //     throw e;
        // }
    }

    /**
     * Get members from file (does include VIP)
     * @return ArrayList of Member
     */
    public ArrayList<Member> getActiveMembers() throws IOException {
        return getActiveMembers(true);
    }

    /**
     * Get member by id
     * @param member
     * @throws IOException
     */
    public Member getMemberById(Integer member_id) throws IOException {
        // try {
        //     setMembers(this.adapter.readMembers());
        for (Member member : this.members) {
            if (member.getId() == member_id) {
                return member;
            }
        }
        return null;
        // } catch (IOException e) {
        //     printError("Fail to get member by id", e);
        //     throw e;
        // }
    }

    /**
     * Add member to members and write to file
     * @param member
     */
    public void addMember(Member member) throws IOException {
        try {
            this.members.add(member);
            this.adapter.writeMembers(members);
        } catch (IOException e) {
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
            for (Member m : this.members) {
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

            this.adapter.writeMembers(members);
        } catch (IOException e) {
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
            for (Member m : this.members) {
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
        } catch (IOException e) {
            printError("Fail to change member status", e);
            throw e;
        }
    }


    /**
     * Get fixed bills from file
     * @return ArrayList of FixedBill
    */
    // public ArrayList<FixedBill> getFixedBills() throws IOException {
    //     try {
    //         setFixedBills(this.adapter.readFixedBills());
    //         return this.fixedBills;
    //     } catch (IOException e) {
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
        for (FixedBill bill : this.fixedBills) {
            if (bill.getIdCustomer() == cust_id) {
                bills.add(bill);
            }
        }
        if (bills.size() == 0) {
            throw new Exception("No fixed bill found");
        }

        return bills;
        // } catch (IOException e) {
        //     printError("Fail to get fixed bills by cust_id", e);
        //     throw e;
        // }
    }

    /**
     * Add FixedBill to fixedBills and write to file
     * @param fixedBill
     * @throws IOException
     */
    private void addFixedBill(FixedBill fixedBill) throws IOException {
        try {
            this.fixedBills.add(fixedBill);
            this.adapter.writeFixedBills(fixedBills);
        } catch (IOException e) {
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
     * @throws IOException
     */
    public void writeBills(ArrayList<Bill> bills) throws IOException {
        try {
            this.adapter.writeBills(bills);
        } catch (IOException e) {
            printError("Fail to write bills", e);
            throw e;
        }
    }

    /**
     * Get bills from file
     * @return ArrayList of Bill
    */
    // public ArrayList<Bill> getBills() throws IOException {
    //     try {
    //         setBills(this.adapter.readBills());
    //         return this.bills;
    //     } catch (IOException e) {
    //         printError("Fail to get bills", e);
    //         throw e;
    //     }
    // }

    /**
     * Start new bill session
     * @param bill
     * @throws IOException
     */
    public void startNewBill(Bill bill) throws IOException {
        this.bills.add(bill);
        DataStore.billWorker.addBill(bill);
    }

    /**
     * Finish bill session
     * @param bill
     * @throws IOException
     */
    public void finishBill(Bill bill) throws IOException {
        try {
            this.bills.remove(bill);
            DataStore.billWorker.removeBill(bill);
            
            this.addFixedBill(new FixedBill(
                bill.getId(), bill.getTotalPrice(), bill.getItems(), bill.getIdCustomer()
            ));

            this.adapter.writeBills(bills);
        } catch (IOException e) {
            printError("Fail to finish bill, might cause fixed bill data desync", e);
            DataStore.billWorker.addBill(bill);
            this.bills.add(bill);
            throw e;
        }
    }

    /**
     * Generate bill id (same as fixed bill id)
     * @return Integer
     * @throws IOException
    */
    public Integer generateBillId() throws IOException {
        Integer activeBillsLen = 0;
        try {
            setFixedBills(this.adapter.readFixedBills());
            activeBillsLen = this.bills.size();
        } catch (IOException e){
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
}
