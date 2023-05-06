package DataStore.Thread;

import java.util.*;
import DataStore.DataStore;
import Entity.*;

public class BillWorker implements Runnable{
    final static int BILL_AUTOSAVE_TIME = 30; // in seconds
    private ArrayList<Bill> billRefs;
    private DataStore dataStoreRef;
    
    public BillWorker(){
        this.billRefs = new ArrayList<Bill>();
        this.dataStoreRef = DataStore.getInstance();
    }

    public void addBill(Bill bill){
        this.billRefs.add(bill);
    }

    public void removeBill(Bill bill){
        this.billRefs.remove(bill);
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(BILL_AUTOSAVE_TIME * 1000);
                dataStoreRef.writeBills(billRefs);
                // System.out.println("Bill autosaved!");
            } catch (InterruptedException e) {
                System.out.println("Error in BillWorker: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error in BillWorker: " + e.getMessage());
            }
        }
    }
}
