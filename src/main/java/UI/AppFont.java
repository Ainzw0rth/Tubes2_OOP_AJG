package UI;

import java.awt.Font;
import java.io.InputStream;

public class AppFont {
    /* WEIGHT CONSTANT */
    public static final int REGULAR = 0;
    public static final int MEDIUM = 1;
    public static final int BOLD = 2;
    public static final int SEMIBOLD = 3;

    /**
     * Create font with specified weight and size
     * @param weight `REGULAR`, `MEDIUM`, `BOLD`, `SEMIBOLD`
     * @param size font size
     * @return font
     */
    public static Font create(int weight, float size) {
        InputStream requested;

        switch (weight) {
            case REGULAR:
                requested = AppFont.class.getClassLoader().getResourceAsStream("fonts/Poppins-Regular.ttf");
                break;
            case MEDIUM:
                requested = AppFont.class.getClassLoader().getResourceAsStream("fonts/Poppins-Medium.ttf");
                break;
            case BOLD:
                requested = AppFont.class.getClassLoader().getResourceAsStream("fonts/Poppins-Bold.ttf");;
                break;
            case SEMIBOLD:
                requested = AppFont.class.getClassLoader().getResourceAsStream("fonts/Poppins-SemiBold.ttf");
                break;
            default:
                requested = AppFont.class.getClassLoader().getResourceAsStream("fonts/Poppins-Regular.ttf");
                break;
        }

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, 
                requested);
        } catch (Exception e) {
            e.printStackTrace();
        }
        font = font.deriveFont(size);

        return font;
    }

    /**
     * Create font with default weight (regular) and size (12)
     * @return font
     */
    public static Font create() {
        return create(REGULAR, 12);
    }

    /**
     * Create font with specified weight and default size (12)
     * @param weight `REGULAR`, `MEDIUM`, `BOLD`, `SEMIBOLD`
     * @return font
     */
    public static Font create(int weight) {
        return create(weight, 12);
    }

    /**
     * Create font with specified size and default weight (regular)
     * @param size font size
     * @return font
     */
    public static Font create(float size) {
        return create(REGULAR, size);
    }
}
