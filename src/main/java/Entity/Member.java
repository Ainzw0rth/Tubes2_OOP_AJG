package Entity;
import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Member extends Customer {
    @NotNull
    protected String name;
    @NotNull
    protected String phoneNumber;
    @NotNull
    protected boolean isActive;
    // @NonNegative (ntar bikin) 
    protected Integer point;

    public void onTransaction(Integer transaction) {
        this.point += transaction/100;
    }
}
