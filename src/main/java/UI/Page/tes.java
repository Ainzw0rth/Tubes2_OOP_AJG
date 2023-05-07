package UI.Page;

import javax.swing.*;
import java.awt.*;

public class tes extends JFrame {

    private JLabel label1, label2, label3;
    private JTextField field1, field2, field3;

    public tes() {
        super("Input Fields");

        label1 = new JLabel("Label 1:");
        label2 = new JLabel("Label 2:");
        label3 = new JLabel("Label 3:");

        field1 = new JTextField(10);
        field2 = new JTextField(10);
        field3 = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(label1);
        panel.add(field1);
        panel.add(label2);
        panel.add(field2);
        panel.add(label3);
        panel.add(field3);

        getContentPane().add(panel, BorderLayout.WEST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new tes();
    }
}