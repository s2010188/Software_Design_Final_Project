package main.panels;
import main.app.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class DashBoardPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel totalLabel;

    public DashBoardPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));


    }
}