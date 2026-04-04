package main.panels;
import main.app.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class DashBoardPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel totalLabel;

    private HashMap<String, Double> bankTotals;


    public DashBoardPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        this.bankTotals = new HashMap<>();

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));


        JLabel title = new JLabel("DASHBOARD");
        title.setFont(new Font("Arial", Font.BOLD, 22));


        totalLabel = new JLabel("₱ 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 30));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel totalText = new JLabel("TOTAL BALANCE");
        totalText.setAlignmentX(Component.CENTER_ALIGNMENT);

        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(totalText);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(totalLabel);

        cardPanel.add(title, BorderLayout.NORTH);
        cardPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(240,240,240));
        wrapper.add(cardPanel);

        add(wrapper, BorderLayout.CENTER);
    }

    public void updateBank(String bank, double amount) {

        bankTotals.put(bank, amount);
        updateTotal();
    }

}