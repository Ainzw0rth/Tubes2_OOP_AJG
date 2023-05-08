package Entity;

import java.io.Serializable;
import org.jetbrains.annotations.NotNull;

import DataStore.DataStore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item implements Serializable{
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String category;
    @NotNull
    private Double price;
    @NotNull
    private String imageUrl = "/images/plugin/icon.png";
    @NotNull
    private Integer stock;

    public Item(String name, String category, Double price, String imageurl, Integer stock) throws Exception {
        DataStore d = DataStore.getInstance();
        try {
            this.id = d.generateItemId();
        } catch (Exception e) {
            throw e;
        }

        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrl = imageurl;
        this.stock = stock;
    }

    public Item(String name, String category, Double price, Integer stock) throws Exception {
        DataStore d = DataStore.getInstance();
        try {
            this.id = d.generateItemId();
        } catch (Exception e) {
            throw e;
        }

        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
}
