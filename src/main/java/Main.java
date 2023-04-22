import UI.App;
import UI.IApp;

public class Main {
    public static void main(String[] args) {
        IApp app = App.getInstance();
        app.setVisible(true);
    }
}
