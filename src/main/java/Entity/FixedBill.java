package Entity;

import java.util.LinkedList;

import org.jetbrains.annotations.NotNull;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@XStreamAlias("FixedBill")
public class FixedBill {
    @NotNull private int id;
    @NotNull private int totalPrice;
    @NotNull private LinkedList<Item> items;
    @NotNull private int idCustomer;
}





