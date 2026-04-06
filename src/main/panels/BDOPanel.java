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

        JLabel title = new JLabel("BDO UNIBANK");
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel leftHeaderEL = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftHeaderEL.setOpaque(false);
        leftHeaderEL.add(logo);
        leftHeaderEL.add(title);

        headerEL.add(leftHeaderEL, BorderLayout.WEST);

        JPanel eCardPanel = new JPanel(new BorderLayout());
        eCardPanel.setBackground(Color.WHITE);
        eCardPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);

        JPanel savingsPanelEL = createPanel("Savings");
        savingsPanelEL.setBackground(new Color(245, 245,245));

        savingsPanelEL.setPreferredSize(new Dimension(0, 110));
        savingsPanelEL.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        savingsLabel = new JLabel("₱ 0.00");
        savingsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        savingsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton editSavings = createEditButton();

    }
}
