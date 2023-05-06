package Entity;

import java.io.Serializable;
import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item implements Serializable{
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String category;
    @NotNull
    private Integer price;
    @NotNull
    private String imageUrl;
    @NotNull
    private Integer stock;
}
