package Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VIP extends Member {
    public VIP (Integer id, String name, String phoneNumber, boolean isActive, Integer point) {
        super(id, name, phoneNumber, isActive, point);
    }

    public Integer getDiscount(Integer price){
        return price/10;
    }
}
