package Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VIP extends Member {
    public VIP (Integer id, String name, String phoneNumber, boolean isActive, Integer point) {
        super(id, name, phoneNumber, isActive, point);
    }

    @Override
    public Double getDiscount(Double price){
        return Double.valueOf(price.doubleValue()/10);
    }
}
