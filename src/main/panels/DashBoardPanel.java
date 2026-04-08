package main.panels;

import main.app.MainFrame;
import main.services.FirebaseService;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DashBoardPanel extends JPanel {

    private MainFrame mainFrame;


    private JPanel dashboardButtons;
    private JLabel totalLabel;

    private HashMap<String, Double> bankTotals;
    private HashMap<String, JButton> bankButtons;


    public DashBoardPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        this.bankTotals = new HashMap<>();
        this.bankButtons = new HashMap<>();

        setLayout(new BorderLayout());
        setBackground(new Color(240,240,240));


        createDashboardCard();
    }



    private void createDashboardCard(){


        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(900, 420));


        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(40,40,40,40)
        ));

        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        dashboardButtons = new JPanel(new FlowLayout(FlowLayout.CENTER,15,10));
        dashboardButtons.setBackground(Color.WHITE);


        JButton bdoBtn = createButton("BDO", "bdo");
        JButton mayaBtn = createButton("Maya", "maya");
        JButton addBtn = createAddButton();


        bankButtons.put("bdo", bdoBtn);
        bankButtons.put("maya", mayaBtn);


        dashboardButtons.add(bdoBtn);
        dashboardButtons.add(mayaBtn);
        dashboardButtons.add(addBtn);


        JLabel totalText = new JLabel("Total Balance");
        totalText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        totalText.setForeground(new Color(120,120,120));
        totalText.setAlignmentX(Component.CENTER_ALIGNMENT);

        totalLabel = new JLabel("₱ 0.00");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPanel.add(title);
        cardPanel.add(Box.createVerticalStrut(25));
        cardPanel.add(dashboardButtons);
        cardPanel.add(Box.createVerticalStrut(35));
        cardPanel.add(totalText);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(totalLabel);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(240,240,240));
        wrapper.add(cardPanel);

        add(wrapper, BorderLayout.CENTER);
    }



    private JButton createButton(String text, String panelName){


        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));


        btn.setBackground(new Color(200,200,200));
        btn.setForeground(Color.BLACK);


        btn.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        btn.setOpaque(true);


        btn.addActionListener(e -> mainFrame.showPanel(panelName));


        return btn;
    }





    private JButton createAddButton(){


        JButton btn = new JButton("+ Add Bank");
        btn.setFocusPainted(false);
        btn.setBackground(new Color(93,118,94));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));


        btn.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));


        btn.addActionListener(e -> mainFrame.showPanel("addbank"));


        return btn;
    }

    public void addBankButton(String bankName, double balance){


        JButton newBtn = createButton(bankName, bankName);


        int index = dashboardButtons.getComponentCount() - 1;
        dashboardButtons.add(newBtn, index);


        bankButtons.put(bankName, newBtn);
        bankTotals.put(bankName, balance);

        updateTotal();
        revalidate();
        repaint();
    }

    public void removeBankButton(String bankName){

        JButton btn = bankButtons.get(bankName);

        if(btn != null){
            dashboardButtons.remove(btn);
            bankButtons.remove(bankName);
            bankTotals.remove(bankName);

            updateTotal();
            revalidate();
            repaint();
        }
    }

    public void updateBank(String bank, double amount){

        bank = bank.trim().toLowerCase();
        bankTotals.put(bank, amount);
        updateTotal();
    }

    private void updateTotal(){

        double total = 0;

        for(double value : bankTotals.values()){
            total += value;
        }

        totalLabel.setText("₱ " + String.format("%.2f", total));

        FirebaseService.updateGrandTotal(total);
    }
}