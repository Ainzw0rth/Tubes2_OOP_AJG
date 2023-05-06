package Entity;

import org.jetbrains.annotations.NotNull;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@XStreamAlias("Item")
public class Item {
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
