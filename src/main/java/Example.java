/* Example class using lombok */
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Example {
    private String name = "tes";
    @NotNull
    private Integer x = 1;  
    @Nullable
    private Integer y = 1; 
    public static void main(String[] args) {
        Example a = new Example("", 1, null);
        System.out.print(a.getX());
    }
}
