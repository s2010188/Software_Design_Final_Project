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

    public MayaPanel(MainFrame mainfrane){
        this.mainFrame= mainfrane;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#6ED39A"));


        //HEADER
        JPanel header= new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel logo= new JLabel();
        ImageIcon icon = new ImageIcon("src/main/images/maya-logo.png");
        Image img = icon.getImage().getScaledInstance(70,70,Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(img));

        JLabel title = new JLabel("Maya Bank");
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        leftHeader.setOpaque(false);
        leftHeader.add(logo);
        leftHeader.add(title);

        header.add(leftHeader, BorderLayout.WEST);

        JPanel cardPanel= new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200,200,200),1), BorderFactory.createEmptyBorder(15,15,15,15)));




        //Center
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
        center.setBackground(Color.WHITE);


        //The deafult savings
        JPanel savingsPanel= createPanel("Savings");
        savingsPanel.setBackground(Color.decode("#F5F5F5"));

        savingsPanel.setPreferredSize(new Dimension(0,110));
        savingsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,110));

        savingsLabel= new JLabel("PHP 0.00");
        savingsLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton editSavings = createEditButton();
        



    }


}
