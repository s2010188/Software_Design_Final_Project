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


    }


}
