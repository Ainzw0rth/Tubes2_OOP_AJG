package Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VIP extends Member {
    public VIP (String name, String phoneNumber, boolean isActive, Integer point) {
        super(name, phoneNumber, isActive, point);
    }

    public Integer getDiscount(Integer price){
        return price/10;
    }
}
