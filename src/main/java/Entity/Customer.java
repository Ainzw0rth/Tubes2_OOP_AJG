package Entity;
import org.jetbrains.annotations.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    @NotNull
    protected Integer id;

    public Customer(Integer id){
        this.id = id;
    }
}