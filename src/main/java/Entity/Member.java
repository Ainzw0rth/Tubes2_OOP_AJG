package Entity;
import org.jetbrains.annotations.NotNull;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XStreamAlias("Member")
public class Member extends Customer {
    @NotNull
    protected String name;
    @NotNull
    protected String phoneNumber;
    @NotNull
    protected Boolean isActive;
    @NotNull
    protected Integer point;

    public Member(Integer id, String name, String phoneNumber, boolean isActive, Integer point) {
        super(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.point = point;
    }

    public void onTransaction(Integer transaction) {
        this.point += transaction/100;
    }

    public float getDiscount(Integer price) {
        return (float) 0;
    }
}
