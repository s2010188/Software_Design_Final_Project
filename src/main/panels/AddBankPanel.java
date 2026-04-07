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


        JLabel title = new JLabel("Add New Bank");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bankLabel = new JLabel("Bank Name");
        bankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bankNameField = new JTextField();
        bankNameField.setMaximumSize(new Dimension(320,35));
        bankNameField.setPreferredSize(new Dimension(320,35));

        logoPreview = new JLabel("No Logo", SwingConstants.CENTER);
        logoPreview.setPreferredSize(new Dimension(160,70));
        logoPreview.setMaximumSize(new Dimension(160,70));
        logoPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        logoPreview.setAlignmentX(Component.CENTER_ALIGNMENT);

        




    }
}