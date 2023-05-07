package DataStore;

import java.util.*;

import DataStore.Enums.*;
import Entity.*;

public interface DataService {
    public void setCustomers(ArrayList<Customer> customers);
    public void setItems(ArrayList<Item> items);
    public void setMembers(ArrayList<Member> members);
    public void setBills(ArrayList<Bill> bills);

    public void setFixedBills(ArrayList<FixedBill> fixedBills);

    /**
     * Read all datastore from file. Most likely will be deprecated
     * @throws Exception
     */
    public void loadData() throws Exception;

    /**
     * Add customer to customers and write to file
     * @param customer
     */
    public void addCustomer(Customer customer) throws Exception;
    
    /**
     * Generate customer id
     * @return Integer item id
     */
    public Integer generateCustomerId() throws Exception;

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
    public void addItem(Item item) throws Exception;

    /**
     * Remove item from items and write to file
     * @param item_id
     */
    public void removeItem(Integer item_id) throws Exception;

    /**
     * Update item from items and write to file
     * @param item_id
     * @param new_item
    */
    public void updateItem(Integer item_id, Item new_item) throws Exception;

    /**
     * Generate item id
     * @return Integer item id
     */
    public Integer generateItemId() throws Exception;

    /**
     * Get members from file
     * @return ArrayList of Member
     */
    public ArrayList<Member> getActiveMembers(Boolean includeVIP);

    /**
     * Get members from file (does include VIP)
     * @return ArrayList of Member
     */
    public ArrayList<Member> getActiveMembers() throws Exception;
    /**
     * Get member by id
     * @param member
     * @throws Exception
     */
    public Member getMemberById(Integer member_id) throws Exception;

    /**
     * Add member to members and write to file
     * @param member
     */
    public void addMember(Member member) throws Exception;
    /**
     * Update member to members and write to file
     * @param member_id 
     * @param member
     */
    public void updateMember(Integer member_id, Member member) throws Exception;

    /**
     * Change status of member
     * @param member_id
     * @param status
     */
    public void changeMemberStatus(Integer member_id, MemberStatus status) throws Exception;

    /**
     * Get fixed bills by cust_id
     * @param bill_id
     * @return ArrayList of FixedBill
     * @throws Exception
    */
    public ArrayList<FixedBill> getFixedBillsByCustId(Integer cust_id) throws Exception;

    /**
     * Add FixedBill to fixedBills and write to file
     * @param fixedBill
     * @throws Exception
     */
    public void addFixedBill(FixedBill fixedBill) throws Exception;

    /**
     * Update FixedBill to fixedBills and write to file
     * @implNote **DO NOT CALL THIS METHOD DIRECTLY**! Only Called by BillWorker
     * @param bill_id
     * @param fixedBill
     * @throws Exception
     */
    public void writeBills(ArrayList<Bill> bills) throws Exception ;

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
    public void startNewBill(Bill bill) throws Exception;

    /**
     * Finish bill session
     * @param bill
     * @throws Exception
     */
    public void finishBill(Bill bill) throws Exception;

    /**
     * Generate bill id (same as fixed bill id)
     * @return Integer
     * @throws Exception
    */
    public Integer generateBillId() throws Exception;
}
