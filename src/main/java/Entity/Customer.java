package Entity;
import java.io.Serializable;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer implements Serializable{
    @NotNull
    protected Integer id;

    public Customer(Integer id){
        this.id = id;
    }
}