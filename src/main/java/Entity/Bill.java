package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class Bill {
    private int id;
    private int totalPrice;
    private Item[] items;
    private int idCustomer;

}





