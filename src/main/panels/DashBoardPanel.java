package main.panels;

import main.app.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DashBoardPanel extends JPanel {

    private MainFrame mainFrame;

    private JPanel buttonPanel;
    private JPanel dashboardButtons;
    private JLabel totalLabel;

    private HashMap<String, Double> bankTotals;
    private HashMap<String, JButton> bankButtons;


    public DashBoardPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBackground(new Color(240,240,240));

        ButtonPanel();
        DashboardCard();

    }

    private void ButtonPanel(){

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        buttonPanel.setBackground(new Color(240,240,240));

        buttonPanel.add(Button("BDO", "bdo"));
        buttonPanel.add(Button("Maya", "maya"));
        buttonPanel.add(AddButton());



    }

    private JButton Button(String text, String panelName){

        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));


        switch (text.toLowerCase()) {
            case "bdo":
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setForeground(Color.BLACK);
                break;

            case "maya":
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setForeground(Color.BLACK);
                break;

            default:
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setForeground(Color.BLACK);
        }

        btn.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        btn.setOpaque(true);

        btn.addActionListener(e -> mainFrame.showPanel(panelName));

        return btn;
    }

    private JButton AddButton(){

        JButton btn = new JButton("+ Add Bank");
        btn.setFocusPainted(false);
        btn.setBackground(new Color(100,130,100));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btn.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addActionListener(e -> mainFrame.showPanel("addbank"));

        return btn;
    }

    private void DashboardCard(){

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(700, 350));

        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(30,40,30,40)
        ));

        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        dashboardButtons = new JPanel(new FlowLayout(FlowLayout.CENTER,15,10));
        dashboardButtons.setBackground(Color.WHITE);

        dashboardButtons.add(Button("BDO", "bdo"));
        dashboardButtons.add(Button("Maya", "maya"));
        dashboardButtons.add(AddButton());

        JLabel totalText = new JLabel("Total Balance");
        totalText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        totalText.setForeground(new Color(120,120,120));
        totalText.setAlignmentX(Component.CENTER_ALIGNMENT);

        totalLabel = new JLabel("₱ 0.00");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 42));
        totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardPanel.add(title);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(dashboardButtons);
        cardPanel.add(Box.createVerticalStrut(25));
        cardPanel.add(totalText);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(totalLabel);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(240,240,240));
        wrapper.add(cardPanel);

        add(wrapper, BorderLayout.CENTER);
    }

    public void addBankButton(String bankName, double balance){


        JButton newBtn = Button(bankName.toUpperCase(), bankName);


        dashboardButtons.add(newBtn, dashboardButtons.getComponentCount() - 1);

        bankButtons.put(bankName, newBtn);
        bankTotals.put(bankName, balance);

        revalidate();
        repaint();
    }

    public void removeBankButton(String bankName){

        JButton btn = bankButtons.get(bankName);

        if(btn != null){
            dashboardButtons.remove(btn);
            bankButtons.remove(bankName);
            bankTotals.remove(bankName);

            revalidate();
            repaint();
        }
    }






}