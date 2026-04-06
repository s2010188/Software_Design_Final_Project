//final
package main.panels;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class AddBankPanel extends JPanel {
    private  MainFrame mainFrame;
    private JTextField bankNameField;
    private JLabel logoPreview;

    private ImageIcon selectedLogo;


    public AddBankPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F0F0F0"));


        //card panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setPreferredSize(new Dimension(380, 420));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), BorderFactory.createEmptyBorder(30, 30, 30, 30)));


        JLabel title = new JLabel("+ Add New Bank");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //bank name
        JLabel banklabel = new JLabel("Bank Name");
        banklabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bankNameField = new JTextField();
        bankNameField.setMaximumSize(new Dimension(320, 35));
        bankNameField.setPreferredSize(new Dimension(320, 35));


        //logo prev
        logoPreview = new JLabel("No Logo", SwingConstants.CENTER);
        logoPreview.setPreferredSize(new Dimension(160, 70));
        logoPreview.setMaximumSize(new Dimension(160, 70));
        logoPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        logoPreview.setAlignmentX(Component.CENTER_ALIGNMENT);


        //buttons
        JButton chooseBtn = new JButton("Choose logo");
        chooseBtn.setBackground(Color.decode("#000000"));
        chooseBtn.setForeground(Color.decode("#FFFFFF"));
        chooseBtn.setFocusPainted(false);
        chooseBtn.setBorderPainted(false);
        chooseBtn.setOpaque(true);

        JButton addBtn = new JButton("Add Bank");
        JButton backBtn = new JButton("Back");

        chooseBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        chooseBtn.setMaximumSize(new Dimension(180, 35));
        addBtn.setMaximumSize(new Dimension(180, 35));
        backBtn.setMaximumSize(new Dimension(100, 28));

        addBtn.setBackground(Color.decode("#5D765E"));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setBorderPainted(false);
        addBtn.setOpaque(true);

        chooseBtn.addActionListener(e -> chooseLogo());
        addBtn.addActionListener(e -> addBank());
        backBtn.addActionListener(e -> mainFrame.showPanel("dashboard"));


        cardPanel.add(title);
        cardPanel.add(Box.createVerticalStrut(25));

        cardPanel.add(banklabel);
        cardPanel.add(Box.createVerticalStrut(8));

        cardPanel.add(bankNameField);
        cardPanel.add(Box.createVerticalStrut(20));

        cardPanel.add(logoPreview);
        cardPanel.add(Box.createVerticalStrut(15));

        cardPanel.add(chooseBtn);
        cardPanel.add(Box.createVerticalStrut(20));

        cardPanel.add(addBtn);
        cardPanel.add(Box.createVerticalStrut(15));

        cardPanel.add(backBtn);


        JPanel wrap = new JPanel(new GridBagLayout());
        wrap.setBackground(Color.decode("#F0F0F0"));
        wrap.add(cardPanel);


        add(wrap, BorderLayout.CENTER);

    }

    private void chooseLogo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileHidingEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (PNG, JPG, JPEG)",
                "png", "jpg", "jpeg");

        chooser.setFileFilter(filter);

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());

            Image img = icon.getImage().getScaledInstance(140, 50, Image.SCALE_SMOOTH);

            selectedLogo = new ImageIcon(img);

            logoPreview.setIcon(selectedLogo);
            logoPreview.setText("");


        }
    }

    private void addBank(){
        String bank = bankNameField.getText().trim();
        if(bank.isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter bank name" );
            return;

        }
        mainFrame.addNewbank(bank, selectedLogo);

        bankNameField.setText("");
        logoPreview.setIcon(null);
        logoPreview.setText("No Logo");

        mainFrame.showPanel("dashboard");









    }

}
