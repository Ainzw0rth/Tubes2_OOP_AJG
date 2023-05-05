package DataStore;

import Entity.*;
import java.util.*;

public class DataStoreTest {
    // private final static String[] item_names = {
    //     "Baju",
    //     "Celana",
    //     "Sepatu",
    //     "Kaos Kaki",
    //     "Topi",
    //     "Jaket",
    //     "Kemeja",
    //     "Kaos",
    //     "Sandal",
    //     "Tas"
    // };
    
    public static void main(String[] args) {
        // Bill bill1 = new Bill(1, 1000, new LinkedList<Item>(), 1);
        // Bill bill2 = new Bill(2, 2000, new LinkedList<Item>(), 2);

        DataStore data = DataStore.getInstance();
        
        Integer id;
        try {
            ArrayList<Member> members = data.getActiveMembers();
            System.out.println(members.size());
            for (Member member : members) {
                System.out.println(member.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // try {
        //     data.startNewBill(bill1);
        //     data.startNewBill(bill2);
        //     Thread.sleep(8000);
        //     bill1.setTotalPrice(900);
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }



        // ArrayList<Member> members = new ArrayList<>();
        // DataStore data = DataStore.getInstance();
        // try {
        //     members = data.getMembers();
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

        // if (members != null) {
        //     for (Member member : members) {
        //         System.out.println(member);
        //     }
        // }
        // for (int i = 0; i < 20; i++) {
        //     try {
        //         data.addItem(new Item(
        //             data.generateItemId(), 
        //             item_names[i%10], 
        //             i%10*1000, 
        //             item_names[i%10], 
        //             i%10
        //         ));
        //         data.addCustomer(new Customer(
        //             data.generateCustomerId()
        //         ));
        //     } catch (Exception e) {
        //         System.out.println(e.getMessage() + " from datastoretest");
        //     }
        // }
        // for (int i = 0; i < 10; i++) {
        //     data.addCustomer(new Customer(i));
        // }
    }
}
