package DataStore;

import Entity.*;
// import java.util.*;

public class DataStoreTest {
    private final static String[] item_names = {
        "Baju",
        "Celana",
        "Sepatu",
        "Kaos Kaki",
        "Topi",
        "Jaket",
        "Kemeja",
        "Kaos",
        "Sandal",
        "Tas"
    };
    
    public static void main(String[] args) {
        DataStore data = DataStore.getInstance();
        for (int i = 0; i < 20; i++) {
            try {
                data.addItem(new Item(
                    data.generateItemId(), 
                    item_names[i%10], 
                    i%10*1000, 
                    item_names[i%10], 
                    i%10
                ));
                data.addCustomer(new Customer(
                    data.generateCustomerId()
                ));
            } catch (Exception e) {
                System.out.println(e.getMessage() + " from datastoretest");
            }
        }
        // for (int i = 0; i < 10; i++) {
        //     data.addCustomer(new Customer(i));
        // }
    }
}
