package DataStore.Adapter;

import java.io.Serializable;
import java.util.*;
import Entity.*;


public class CustomerList  implements Serializable {
    private ArrayList<Customer> customers;

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
}
