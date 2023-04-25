package UI;

/* Imports */
import UI.Interface.IRefreshable;
import UI.Component.AppMenu.*;
import org.jetbrains.annotations.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.*;
import UI.Page.*;

public class App extends JFrame implements IApp {
    
    /* Constants */
    private final int WIDTH = 1200;
    private final int HEIGHT = 720;

    /* GUI Components */
    @NotNull private JTabbedPane tabbedPane;
    @Nullable private AppActionListener actionListener;

    /* Singleton pattern */
    private static IApp instance;

    private App() {
        super("BNMOStore by AJG ( ✧Д✧)"); // init JFrame

        /* Window Configuration */
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* Set icon */
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/icon.jpg"));
        setIconImage(icon.getImage());

        /* Set action listener */
        this.actionListener = new AppActionListener(this);
        
        /* Initialize window with its components */
        init();
    }

    public static IApp getInstance() {
        if (App.instance == null) {
            App.instance = new App();
        }

        return App.instance;
    }

    private void init() {
    /* Initialize window with its components */
        System.out.println("Initializing window...");
    
        /* Menubar */
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu custMenu = new CustomerMenu(actionListener);
        JMenu invMenu = new InventoryMenu(actionListener);
        JMenu stgMenu = new SettingMenu(actionListener);

        menuBar.add(custMenu);
        menuBar.add(invMenu);
        menuBar.add(stgMenu);

        /* Tabbed Pane */
        this.tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(this.tabbedPane);

        addTab("Main Menu", new MainMenu());
    }

    @Override
    public void setVisible(boolean bool) {
        super.setVisible(bool);
    }

    @Override
    public int getSelectedTabIndex() {
        return this.tabbedPane.getSelectedIndex();
    }

    @Override
    public void addTab(String title, Component component) {
        /* Open a new tab */
        this.tabbedPane.addTab(title, component);
        int index = this.tabbedPane.indexOfComponent(component);
        
        /* Customize panel for tab bar, 
         * Swing default tab bar doesn't provide close button
        */
        JPanel pnlTab = new JPanel();
        pnlTab.setOpaque(false);

        /* Add close button to the tab bar */
        JLabel lblTitle = new JLabel(title);
        lblTitle.setHorizontalAlignment(JLabel.LEFT);

        JButton closeButton = new JButton("x");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 15));
        closeButton.addActionListener(actionListener);
        closeButton.setBackground(new Color(index, index, index, 0));
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setHorizontalAlignment(JButton.RIGHT);
        closeButton.setOpaque(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionListener.addTabCloseButton(closeButton);

        pnlTab.add(lblTitle);
        pnlTab.add(closeButton);

        this.tabbedPane.setTabComponentAt(index, pnlTab);

        this.tabbedPane.setSelectedIndex(index);
    }

    @Override
    public void removeTab(int index) {
        this.actionListener.removeTabCloseButton(index);
        this.tabbedPane.remove(index);
    }

    @Override
    public void refreshTab(int index) {
        Component pane = this.tabbedPane.getComponentAt(index);
        if (pane instanceof IRefreshable) {
            ((IRefreshable) pane).refresh();
        } else {
            System.out.println("Component is not refreshable");
        }
    }

    @Override
    public void addMenu(JMenu menu) {
        getJMenuBar().add(menu);
    }

    @Override
    public int getMenuIndex(String name) {
        JMenuBar menuBar = getJMenuBar();
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            if (menuBar.getMenu(i).getText().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addMenuItem(int index, JMenuItem item) {
        getJMenuBar().getMenu(index).add(item);
    }
}