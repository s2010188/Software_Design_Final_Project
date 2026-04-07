//partial update 1
package main.panels;
import main.app.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import main.services.FirebaseService;

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
        FirebaseService.createBank("bdo");

        setLayout(new BorderLayout());
        setBackground(Color.decode("#6ED39A"));

        JPanel headerEL = new JPanel(new BorderLayout());
        headerEL.setBackground(Color.WHITE);
        headerEL.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel logo = new JLabel();

        ImageIcon icon = new ImageIcon("src/main/images/BDO image.png");
        Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(img));

        JLabel title = new JLabel("BDO");
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


        editSavings.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter Savings Amount");

            if (input != null && !input.isEmpty()){
                try {
                    savings = Double.parseDouble(input);
                    savingsLabel.setText(String.format("₱ %,.2f", savings));
                    updateTotal();
                }

                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel editWrapper = new JPanel(new GridBagLayout());
        editWrapper.setBackground(new Color(245, 245, 245));
        editWrapper.add(editSavings);

        savingsPanelEL.add(savingsLabel, BorderLayout.CENTER);
        savingsPanelEL.add(editWrapper, BorderLayout.EAST);

        JPanel subHeader = new JPanel(new BorderLayout());
        subHeader.setBackground(Color.WHITE);
        subHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        JLabel subLabel = new JLabel("SUB ACCOUNTS", SwingConstants.CENTER);
        subLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton addButton = new JButton("+ Add Sub Account");
        addButton.setBackground(new Color(0, 100, 0));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        addButton.addActionListener(e -> addSubAccount());

        JPanel addWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        addWrapper.setBackground(Color.WHITE);
        addWrapper.add(addButton);

        subHeader.add(subLabel, BorderLayout.CENTER);
        subHeader.add(addWrapper, BorderLayout.EAST);

        subContainerEL = new JPanel();
        subContainerEL.setLayout(new BoxLayout(subContainerEL, BoxLayout.Y_AXIS));
        subContainerEL.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(subContainerEL);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(300, 110));
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

        center.add(savingsPanelEL);
        center.add(Box.createVerticalStrut(8));
        center.add(subHeader);
        center.add(Box.createVerticalStrut(5));
        center.add(scroll);

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);

        JLabel totalText = new JLabel("TOTAL: ");
        totalText.setFont(new Font("Arial", Font.BOLD, 16));

        totalEL = new JLabel("₱ 0.00");
        totalEL.setFont(new Font("Arial", Font.BOLD, 20));
        totalEL.setForeground(new Color(0, 100, 0));

        JButton removeBank = createRemoveBankButton();

        removeBank.addActionListener(e -> {
            mainFrame.getDashboard().removeBankButton("bdo");
            mainFrame.updateDashboardTotal("bdo", 0);
            mainFrame.removePanel("bdo");
            mainFrame.showPanel("dashboard");
        });

        JButton back = new JButton("Back");
        back.addActionListener(e -> mainFrame.showPanel("dashboard"));

        bottom.add(totalText);
        bottom.add(totalEL);
        bottom.add(removeBank);
        bottom.add(back);

        eCardPanel.add(center, BorderLayout.CENTER);
        eCardPanel.add(bottom, BorderLayout.SOUTH);

        add(headerEL, BorderLayout.NORTH);
        add(eCardPanel, BorderLayout.CENTER);

    }

    private JPanel createPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), title, 0, 0, new Font("Arial", Font.BOLD, 14)));

        panel.setBackground(Color.WHITE);

        return panel;
    }

    private JButton createEditButton() {
        JButton button = new JButton("Edit");
        button.setFont(new Font("Arial", Font.BOLD, 11));
        button.setMargin(new Insets(3, 10, 3, 10));
        button.setFocusPainted(false);

        return button;
    }

    private JButton createRemoveSubButton() {
        JButton btn = new JButton("Remove");
        btn.setBackground(new Color(180, 0, 0));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);

        return btn;
    }

    private JButton createRemoveBankButton() {
        JButton button = new JButton("Remove Bank");
        button.setBackground(new Color(180, 0, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        return button;
    }

    private void addSubAccount() {
        String name = JOptionPane.showInputDialog("Sub Account Name");

        if (name == null || name.isEmpty()){
            return;
        }

        String amountString = JOptionPane.showInputDialog("Amount");

        if (amountString == null || amountString.isEmpty()){
            return;
        }

        try {
            double amount = Double.parseDouble(amountString);

            subAmountsEL.add(amount);

            JPanel row = createPanel(name);
            row.setBackground(new Color(245, 245, 245));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));

            JLabel amountLabel = new JLabel(String.format("₱ %,.2f", amount));
            amountLabel.setFont(new Font("Arial", Font.BOLD, 14));

            subLabelsEL.add(amountLabel);

            JButton edit = createEditButton();
            JButton remove = createRemoveSubButton();

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            buttonPanel.setOpaque(false);

            edit.addActionListener(e -> {
                String input = JOptionPane.showInputDialog("Edit Amount");

                if (input != null && !input.isEmpty()) {
                    try {
                        double updated = Double.parseDouble(input);

                        int index = subLabelsEL.indexOf(amountLabel);
                        subAmountsEL.set(index, updated);

                        amountLabel.setText(String.format("₱ %,.2f", updated));

                        updateTotal();
                    }

                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            remove.addActionListener(e -> {
                int index = subLabelsEL.indexOf(amountLabel);

                if (index >= 0) {
                    subLabelsEL.remove(index);
                    subAmountsEL.remove(index);
                }

                subContainerEL.remove(row);
                subContainerEL.revalidate();
                subContainerEL.repaint();

                updateTotal();
            });

            buttonPanel.add(edit);
            buttonPanel.add(remove);

            row.add(amountLabel, BorderLayout.CENTER);
            row.add(buttonPanel, BorderLayout.EAST);

            subContainerEL.add(row);
            subContainerEL.add(Box.createVerticalStrut(5));

            subContainerEL.revalidate();
            subContainerEL.repaint();

            updateTotal();
        }

        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateTotal() {
        double total = savings;

        for (double v : subAmountsEL) {
            total += v;
        }

        totalEL.setText(String.format("₱ %,.2f", total));

        mainFrame.updateDashboardTotal("bdo", total);
    }

    public double getBalance() {
        return savings;
    }

    private void setBalance(double balance) {
        this.savings = balance;
        savingsLabel.setText(String.format("₱ %,.2f", balance));
        updateTotal();
    }
}
