package Entity;

import java.io.Serializable;
import java.util.LinkedList;

import org.jetbrains.annotations.NotNull;

import DataStore.DataStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Bill implements Serializable{
    @NotNull private int id;
    @NotNull private Integer totalPrice;
    @NotNull private LinkedList<Item> items;
    @NotNull private int idCustomer;

    public Bill(int idCustomer) throws Exception {
        DataStore d = DataStore.getInstance();
        try {
            this.id = d.generateBillId();
            this.idCustomer = d.generateCustomerId();
        } catch (Exception e) {
            throw e;
        }
        this.totalPrice = 0;
        this.items = new LinkedList<Item>();
    }
}





