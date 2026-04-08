
// ---FINAL---

package main.panels;

import  main.app.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import main.services.FirebaseService;

public class DynamicBankPanel extends JPanel{

    private JLabel SLabel;
    private JLabel TotalLabel;
    private MainFrame mainframe;
    private double savings =0;
    private ArrayList<Double> subAmounts = new ArrayList<>();
    private ArrayList<JLabel> subLabels = new ArrayList<>();
    private JPanel subContainer;
    private String BankName;
    private ImageIcon Logo;
    private String name;



    public DynamicBankPanel(MainFrame mainframe, String BankName, String logo){
        this.mainframe = mainframe;
        this.BankName = BankName;


        if(logo != null){
            ImageIcon icon = new ImageIcon(logo);
            Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            this.Logo = new ImageIcon(img);
        }else{
            this.Logo = null;
        }

        FirebaseService.createBank(BankName, logo);



        setLayout(new BorderLayout());
        setBackground(new Color(240,240,240));

        JPanel enhancedCardPanel = new JPanel(new BorderLayout());
        enhancedCardPanel.setBackground(Color.WHITE);
        enhancedCardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color (200,200,200),1),
                BorderFactory.createEmptyBorder(15,15,15,15)
        ));


        JPanel TopPanel = new JPanel(new BorderLayout());
        TopPanel.setBackground(Color.WHITE);

        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        leftHeader.setOpaque(false);

        JLabel LogoLabel;

        if(Logo != null){
            LogoLabel = new JLabel(Logo);
        }else{
            LogoLabel = new JLabel();
        }

        JLabel bankTitle = new JLabel(BankName);
        bankTitle.setFont(new Font("Arial", Font.BOLD,18));

        leftHeader.add(LogoLabel);
        leftHeader.add(bankTitle);

        TopPanel.add(leftHeader, BorderLayout.WEST);

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
        c.setBackground(Color.WHITE);

        JPanel SPanel = createPanel("Savings");
        SPanel.setBackground(new Color(245, 245,245));

        SPanel.setPreferredSize(new Dimension(0,110));
        SPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,110));

        SLabel = new JLabel("PHP 0.00");
        SLabel.setFont(new Font("Arial",Font.BOLD,18));
        SLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton Edit= createEditButton();

        Edit.addActionListener(e->{

            String input = JOptionPane.showInputDialog("Savings");
            if(input!=null){

                double previous = savings;
                savings = Double.parseDouble(input);

                SLabel.setText("PHP " + savings);

                FirebaseService.updateSavings(BankName, previous, savings);

                updateTotal();
            }
        });


        JPanel editWrapper = new JPanel(new GridBagLayout());
        editWrapper.setBackground(new Color(245,245,245));
        editWrapper.add(Edit);
        SPanel.add(SLabel,BorderLayout.CENTER);
        SPanel.add(editWrapper,BorderLayout.EAST);

        JPanel SubHeader = new JPanel(new BorderLayout());
        SubHeader.setBackground(Color.WHITE);
        SubHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE,35));

        JLabel subLabel = new JLabel("SUB ACCOUNTS", SwingConstants.CENTER);
        subLabel.setFont(new Font("Arial",Font.BOLD,16));
        JButton addButton = new JButton("+ Add Sub Account");
        addButton.setBackground(new Color(0,100,0));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        addButton.addActionListener(e->
                addSubAccount()
        );


        JPanel addWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
        addWrapper.setBackground(Color.WHITE);
        addWrapper.add(addButton);
        SubHeader.add(subLabel,BorderLayout.CENTER);
        SubHeader.add(addWrapper,BorderLayout.EAST);

        subContainer = new JPanel();
        subContainer.setLayout(new BoxLayout(subContainer,BoxLayout.Y_AXIS));
        subContainer.setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(subContainer);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(300,110));
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE,130));
        c.add(SPanel);
        c.add(Box.createVerticalStrut(8));
        c.add(SubHeader);
        c.add(Box.createVerticalStrut(5));
        c.add(scroll);

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        JLabel totalText = new JLabel("TOTAL:");
        totalText.setFont(new Font("Arial",Font.BOLD,16));
        TotalLabel = new JLabel("PHP 0.00");
        TotalLabel.setFont(new Font("Arial",Font.BOLD,20));
        TotalLabel.setForeground(new Color(0,100,0));
        JButton removeBank = createRemoveBankButton();

        removeBank.addActionListener(e->{
            mainframe.getDashboard().removeBankButton(BankName);
            mainframe.updateDashboardTotal(BankName,0);
            mainframe.removePanel(BankName);
            FirebaseService.removeBank(BankName);
            mainframe.showPanel("dashboard");

        });

        JButton back = new JButton("Back");
        back.addActionListener(e->
                mainframe.showPanel("dashboard")
        );

        bottom.add(totalText);
        bottom.add(TotalLabel);
        bottom.add(removeBank);
        bottom.add(back);

        enhancedCardPanel.add(TopPanel,BorderLayout.NORTH);
        enhancedCardPanel.add(c,BorderLayout.CENTER);
        enhancedCardPanel.add(bottom,BorderLayout.SOUTH);
        add(enhancedCardPanel,BorderLayout.CENTER);
    }

    private JPanel createPanel(String title){

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                title,
                0,
                0,
                new Font("Arial",Font.BOLD,14)
        ));
        panel.setBackground(Color.WHITE);

        return panel;
    }




    private JButton createEditButton(){
        JButton b = new JButton("Edit");
        b.setFont(new Font("Arial",Font.BOLD,11));
        b.setMargin(new Insets(3,10,3,10));
        b.setFocusPainted(false);

        return b;
    }




    private JButton createRemoveSubButton(){
        JButton b = new JButton("Remove");
        b.setBackground(new Color(180,0,0));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);

        return b;
    }


    private JButton createRemoveBankButton(){
        JButton b = new JButton("Remove Bank");
        b.setBackground(new Color(180,0,0));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);

        return b;
    }




    private void addSubAccount(){

        String name = JOptionPane.showInputDialog("Sub Account Name");

        if(name == null || name.isEmpty()){
            return;
        }

        String amountS = JOptionPane.showInputDialog("Amount");

        if(amountS == null || amountS.isEmpty()){
            return;
        }

        double amount;


        try{
            amount = Double.parseDouble(amountS);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(
                    this,
                    "Enter numbers only",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        FirebaseService.updateSubAccount(BankName, name, amount);
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

            String input = JOptionPane.showInputDialog("Savings");

            if(input != null){

                try{

                    double previous = savings;
                    savings = Double.parseDouble(input);

                    SLabel.setText("PHP " + savings);

                    FirebaseService.updateSavings(BankName, previous, savings);

                    updateTotal();

                }catch(NumberFormatException ex){

                    JOptionPane.showMessageDialog(
                            this,
                            "Enter numbers only",
                            "Invalid Input",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
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

            FirebaseService.removeSubAccount(BankName, name);
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

    private void updateTotal(){
        double total = savings;
        for(double v : subAmounts){
            total += v;
        }

        TotalLabel.setText("PHP " + total);
        mainframe.updateDashboardTotal(BankName,total);

        System.out.println("Uploading to Firebase...");
        FirebaseService.updateBank(BankName,total);
    }

    public void setSavings(Double value){

        if(value == null) return;

        savings = value;
        SLabel.setText("PHP " + savings);
        updateTotal();
    }

    public void addLoadedSub(String name, Double amount){

        if(name == null || amount == null) return;

        subAmounts.add(amount);

        JPanel row = createPanel(name);

        JLabel amountLabel = new JLabel("PHP " + amount);

        subLabels.add(amountLabel);

        row.add(amountLabel, BorderLayout.CENTER);

        subContainer.add(row);


    }

    public void finalizeLoad(){
        updateTotal();
    }
}
