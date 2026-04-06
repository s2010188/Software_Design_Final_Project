package main.panels;
import main.app.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BDOPanel extends JPanel {
    private JLabel savingsLabel;
    private JLabel totalEL;
    private MainFrame mainFrame;
    private double savings = 0;

    private ArrayList<Double> subAmountsEL = new ArrayList<>();
    private ArrayList<JLabel> subLabelsEL = new ArrayList<>();
    private JPanel subContainerEL;

    public BDOPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBackground(new Color(0, 51, 102));

        JPanel headerEL = new JPanel(new BorderLayout());
        headerEL.setBackground(Color.WHITE);
        headerEL.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel logo = new JLabel();

        try {
            ImageIcon icon = new ImageIcon("src/main/images/bdo-logo.png");
            Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(img));
        }

        catch (Exception e) {
            logo.setText("BDO");
            logo.setFont(new Font("Arial", Font.BOLD, 24));
            logo.setForeground(new Color(0, 51, 102));
        }
    }
}
