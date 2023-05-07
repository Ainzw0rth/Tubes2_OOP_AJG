package Entity;

import java.io.Serializable;
import java.util.ArrayList;
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
        
        this.id = -1;
        this.idCustomer = idCustomer;

        try {
            this.id = d.generateBillId();
            this.idCustomer = d.generateCustomerId();
        } catch (Exception e) {
            throw e;
        }
        this.totalPrice = 0;
        this.items = new LinkedList<Item>();
    }

    public void tambah (Item other) {
        Integer id = other.getId();
        String name = other.getName();
        String category = other.getCategory();
        Integer price = other.getPrice();
        String image = other.getImageUrl();
        Integer stock = 1;
        this.totalPrice += price;

        // cek apakah item tersebut sudah ada
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId() == id) {
                this.items.get(i).setStock(this.items.get(i).getStock() + 1);
                return;
            }
        }

        this.items.add(new Item(id, name, category, price, image, stock));
    }

    public void hapus (Item other) {
        Integer id = other.getId();
        Integer price = other.getPrice();
        this.totalPrice -= price;

        // cek apakah item tersebut sudah ada
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId() == id) {
                if (this.items.get(i).getStock() > 1) {
                    this.items.get(i).setStock(this.items.get(i).getStock() - 1);
                    return;
                } else {
                    this.items.remove(this.items.get(i));
                    break;
                }
            }
        }
    }

    public void validateBill (ArrayList<Item> storage) {

    }
}





