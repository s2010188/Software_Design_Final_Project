//Final
package main.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MayaPanel extends JPanel {
    private JLabel savingsLabel;
    private JLabel totalLabel;

    private MainFrame mainFrame;
    private double savings=0;
    private ArrayList<Double> subAmounts = new ArrayList<>();
    private ArrayList<JLabel> subLabels = new ArrayList<>();

    private JPanel subContainer;

    public MayaPanel(MainFrame mainframe) {
        this.mainFrame = mainfrane;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#6ED39A"));


        //HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon("src/main/images/maya-logo.png");
        Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(img));

        JLabel title = new JLabel("Maya Bank");
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftHeader.setOpaque(false);
        leftHeader.add(logo);
        leftHeader.add(title);

        header.add(leftHeader, BorderLayout.WEST);

        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1), BorderFactory.createEmptyBorder(15, 15, 15, 15)));


        //Center
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);


        //The deafult savings
        JPanel savingsPanel = createPanel("Savings");
        savingsPanel.setBackground(Color.decode("#F5F5F5"));

        savingsPanel.setPreferredSize(new Dimension(0, 110));
        savingsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        savingsLabel = new JLabel("PHP 0.00");
        savingsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton editSavings = createEditButton();

        editSavings.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Savings");
            if (input != null) {
                savings = Double.parseDouble(input);
                savingsLabel.setText("PHP " + savings);

                updateTotal();
            }
        });

        JPanel editPanelW = new JPanel(new GridBagLayout());
        editPanelW.setBackground(Color.decode("#F5F5F5"));
        editPanelW.add(editSavings);

        savingsPanel.add(savingsLabel, BorderLayout.CENTER);
        savingsPanel.add(editPanelW, BorderLayout.EAST);


        //SUB ACC
        JPanel subHeader = new JPanel(new BorderLayout());
        subHeader.setBackground(Color.white);
        subHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        JLabel subLabel = new JLabel("SUB ACCOUNTS", SwingConstants.CENTER);
        subLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton addBtn = new JButton("+ Add Sub Account");
        addBtn.setBackground(Color.decode("#006400"));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        addBtn.addActionListener(e -> addSubAccount());

        JPanel addWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        addWrap.setBackground(Color.WHITE);
        addWrap.add(addBtn);

        subHeader.add(subLabel, BorderLayout.CENTER);
        subHeader.add(addWrap, BorderLayout.EAST);

        subContainer = new JPanel();
        subContainer.setLayout(new BoxLayout(subContainer, BoxLayout.Y_AXIS));
        subContainer.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(subContainer);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(300, 110));
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

        center.add(savingsPanel);
        center.add(Box.createVerticalStrut(8));

        center.add(subHeader);
        center.add(Box.createVerticalStrut(5));
        center.add(scroll);


        //bottom
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);

        JLabel totalText = new JLabel("TOTAL: ");
        totalText.setFont(new Font("Arial", Font.BOLD, 16));

        totalLabel = new JLabel("PHP 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalLabel.setForeground(Color.decode("#006400"));


        JButton removeBank = createRemoveBankButton();

        removeBank.addActionListener(e -> {
            mainframe.getDashboard().removeBankButton("maya");
            mainframe.updateDashboardTotal("maya", 0);
            mainframe.removePanel("maya");
            mainframe.showPanel("dashboard");
        });

        JButton back = new JButton("Back");
        back.addActionListener(e -> mainframe.showPanel("dashboard"));


        bottom.add(totalText);
        bottom.add(totalLabel);
        bottom.add(removeBank);
        bottom.add(back);

        cardPanel.add(center, BorderLayout.CENTER);
        add(cardPanel, BorderLayout.CENTER);
    }

    private JPanel createPanel(String title){
        JPanel panel= new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), title, 0,0, new Font("Arial", Font.BOLD, 14)));

        panel.setBackground(Color.white);

        return panel;
    }

    private  JButton createEditButton() {
        JButton btn = new JButton("Edit");
        btn.setFont(new Font("Arial",Font.BOLD,11));
        btn.setMargin(new Insets(3,10,3,10));
        btn.setFocusPainted(false);


        return btn;
    }

    private JButton createRemoveSubButton() {
        JButton btn = new JButton("Remove");
        btn.setBackground(new Color(180,0,0));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);

        return btn;
    }

    private JButton createRemoveBankButton() {
        JButton btn = new JButton("Remove Bank");
        btn.setBackground(new Color(180,0,0));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);

        return btn;
    }


    private void addSubAccount(){
        String name = JOptionPane.showInputDialog("Sub Account Name");

        if(name == null || name.isEmpty()) return;

        String amountStr = JOptionPane.showInputDialog("Amount");

        if(amountStr == null || amountStr.isEmpty()) return;

        double amount = Double.parseDouble(amountStr);

        subAmounts.add(amount);

        JPanel row = createPanel(name);
        row.setBackground(new Color(245,245,245));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE,55));

        JLabel amountLabel = new JLabel("PHP " + amount);
        amountLabel.setFont(new Font("Arial",Font.BOLD,14));

        subLabels.add(amountLabel);

        JButton edit = createEditButton();
        JButton remove = createRemoveSubButton();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,0));
        buttonPanel.setOpaque(false);
        edit.addActionListener(e->{

            String input = JOptionPane.showInputDialog("Edit Amount");
            if(input!=null){
                double updated = Double.parseDouble(input);
                int index = subLabels.indexOf(amountLabel);
                subAmounts.set(index, updated);
                amountLabel.setText("PHP " + updated);

                updateTotal();
            }

        });

        remove.addActionListener(e->{
            int index = subLabels.indexOf(amountLabel);
            if(index >= 0){
                subLabels.remove(index);
                subAmounts.remove(index);
            }

            subContainer.remove(row);
            subContainer.revalidate();
            subContainer.repaint();

            updateTotal();
        });

        buttonPanel.add(edit);
        buttonPanel.add(remove);

        row.add(amountLabel, BorderLayout.CENTER);
        row.add(buttonPanel, BorderLayout.EAST);

        subContainer.add(row);
        subContainer.add(Box.createVerticalStrut(5));

        subContainer.revalidate();
        subContainer.repaint();

        updateTotal();
    }

    private  void updateTotal() {
        double total = savings;
        for(double totals: subAmounts){
            total+= totals;
        }
        totalLabel.setText("PHP " + total);
        mainFrame.updateDashboardTotal("maya", total);


    }


}
