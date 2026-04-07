package main.panels;

import main.app.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AddBankPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextField bankNameField;

    private ImageIcon selectedLogo;

    public AddBankPanel(MainFrame mainFrame){
        this.mainFrame= mainFrame;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F0F0"));

        
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(380, 420));
        cardPanel.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(new Color(200,200,200)), BorderFactory.createEmptyBorder(30,30,30,30)));



    }
}