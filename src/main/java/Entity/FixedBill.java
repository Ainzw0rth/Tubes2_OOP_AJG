package Entity;

import java.io.Serializable;
import java.util.LinkedList;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class FixedBill implements Serializable{
    @NotNull private int id;
    @NotNull private int totalPrice;
    @NotNull private LinkedList<Item> items;
    @NotNull private int idCustomer;
}





