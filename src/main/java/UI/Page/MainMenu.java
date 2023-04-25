package UI.Page;

import UI.AppFont;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.lang.Thread;

/* 
 * _Cara integrasiin tab ke aplikasi_ 
 * Tambahin tabnya ke AppActionListener dibagian AppActionListener yang
*/

public class MainMenu extends JPanel {
    private final String[] authors = {
        "Salomo Reinhart Gregory Manalu (13521063)",
        "Louis Caesa Kesuma (13521069)",
        "Margaretha Olivia Haryono (13521071)",
        "Addin Munawwar Yusuf (13521085)",
        "Muhammad Rizky Sya’ban (13521119)"
    };

    /** Construct main menu panel */
    public MainMenu() {
        init();
    }

    /* Initialized main menu components */
    private void init() {
        setLayout(null);
        initLeftSide();
        initRightSide();
    }

    private void initLeftSide(){
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBounds(0, 0, 600, 652);
        this.add(leftPanel);

        ImageIcon zetaa = new ImageIcon(getClass().getResource("/images/main-menu/iya-zeta-iyaa.png"));
        JLabel zetaImg = new JLabel(zetaa);

        zetaImg.setHorizontalAlignment(JLabel.RIGHT);
        zetaImg.setVerticalAlignment(JLabel.BOTTOM);  
        zetaImg.setBounds(0, 0, 580, 652);    
        
        leftPanel.add(zetaImg);
    }

    private void initRightSide(){
        /* Adding right panel */
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBounds(675, 0, 550, 652);
        this.add(rightPanel);

        /* Empty space */
        rightPanel.add(Box.createRigidArea(new Dimension(0, 120)));

        /* Date label */
        JLabel dateLabel = new JLabel("Senin, 10 April 2023");
        dateLabel.setFont(AppFont.create(AppFont.REGULAR, 16));
        dateLabel.setForeground(Color.BLACK);
        rightPanel.add(dateLabel);

        /* Clock label */
        JLabel clockLabel = new JLabel("22:07:14");
        clockLabel.setFont(AppFont.create(AppFont.BOLD, 64));
        clockLabel.setForeground(Color.BLACK);
        rightPanel.add(clockLabel);

        /* Title label */
        JLabel titleLabel = new JLabel("BNMOStore");
        titleLabel.setFont(AppFont.create(AppFont.BOLD, 48));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); // simulate margin
        rightPanel.add(titleLabel);

        /* Authored by */
        JLabel authoredBy = new JLabel("Authored by:");
        authoredBy.setFont(AppFont.create(AppFont.BOLD, 15));
        authoredBy.setForeground(Color.BLACK);
        authoredBy.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // simulate margin
        rightPanel.add(authoredBy);

        /* Authors */
        for (String author : authors) {
            JLabel authorLabel = new JLabel(author);
            authorLabel.setFont(AppFont.create(AppFont.REGULAR, 15));
            authorLabel.setForeground(Color.BLACK);
            rightPanel.add(authorLabel);
        }
    }   
}
