package Entity;
import org.jetbrains.annotations.NotNull;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XStreamAlias("Customer")
public class Customer {
    @NotNull
    protected Integer id;

    public Customer(Integer id){
        this.id = id;
    }
}