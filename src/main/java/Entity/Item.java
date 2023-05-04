package Entity;

import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    @NotNull
    private String name;
    @NotNull
    private Integer price;
    @NotNull
    private String imageUrl;
    @NotNull
    private Integer stock;
}
