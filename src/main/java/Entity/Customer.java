package Entity;
import org.jetbrains.annotations.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    @NotNull
    protected Integer id;
    public static Integer numOfCustomer = 0; // ambil dari database (sementara 0)

    public Customer(){
        this.id = numOfCustomer+1;
    }
}