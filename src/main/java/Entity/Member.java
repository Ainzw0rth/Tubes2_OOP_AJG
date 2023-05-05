package Entity;
import org.jetbrains.annotations.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member extends Customer {
    @NotNull
    protected String name;
    @NotNull
    protected String phoneNumber;
    @NotNull
    protected boolean isActive;
    // @NonNegative (ntar bikin) 
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
}
