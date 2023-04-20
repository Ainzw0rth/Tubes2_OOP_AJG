package GUI;

/* Imports */
import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class App extends JFrame {
    final int WIDTH = 1200;
    final int HEIGHT = 720;

    public App() {
        super("BNMOStore by AJG ( ✧Д✧)"); // init JFrame

        /* Window Configuration */
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* Set icon */
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/icon.jpg"));
        setIconImage(icon.getImage());
        
        /* Initialize window with its components */
        init();
    }

    private void init() {
    /* Initialize window with its components */
        System.out.println("Initializing window...");
    }
}