package main.panels;

import main.app.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddBankPanel extends JPanel {

    private MainFrame mainFrame;
    private JTextField bankNameField;
    private JTextField balanceField;

    public AddBankPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBackground(new Color(240,240,240));

        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200,200,200)),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));

        // ===== TOP =====
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Add New Bank");
        title.setFont(new Font("Arial", Font.BOLD, 22));

        topPanel.add(title, BorderLayout.WEST);

        // ===== CENTER =====
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        JLabel dateLabel = new JLabel(currentDate);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bankLabel = new JLabel("BANK NAME");
        bankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bankNameField = new JTextField();
        bankNameField.setMaximumSize(new Dimension(300,30));

        JLabel balanceLabel = new JLabel("ACCOUNT BALANCE");
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        balanceField = new JTextField("0.00");
        balanceField.setFont(new Font("Arial", Font.BOLD, 24));
        balanceField.setMaximumSize(new Dimension(300,40));
        balanceField.setHorizontalAlignment(JTextField.CENTER);

        centerPanel.add(dateLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(bankLabel);
        centerPanel.add(bankNameField);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(balanceLabel);
        centerPanel.add(balanceField);

        // ===== BOTTOM =====
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);

        JButton addBtn = new JButton("ADD BANK");
        JButton backBtn = new JButton("BACK");

        addBtn.addActionListener(e -> addBank());

        // CHANGE HERE
        backBtn.addActionListener(e -> mainFrame.showPanel("dashboard"));

        bottomPanel.add(addBtn);
        bottomPanel.add(backBtn);

        cardPanel.add(topPanel, BorderLayout.NORTH);
        cardPanel.add(centerPanel, BorderLayout.CENTER);
        cardPanel.add(bottomPanel, BorderLayout.SOUTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(240,240,240));
        wrapper.add(cardPanel);

        add(wrapper, BorderLayout.CENTER);
    }

    private void addBank() {

        try {

            String bankName = bankNameField.getText().trim().toLowerCase();
            double balance = Double.parseDouble(balanceField.getText());

            if(bankName.isEmpty()){
                JOptionPane.showMessageDialog(this,"Enter Bank Name");
                return;
            }

            // CONNECTS TO DASHBOARD
            mainFrame.addNewBank(bankName, balance);

            bankNameField.setText("");
            balanceField.setText("0.00");

            // RETURN TO DASHBOARD
            mainFrame.showPanel("dashboard");

        } catch (Exception e){
            JOptionPane.showMessageDialog(this,"Invalid Input");
        }

    }
}
