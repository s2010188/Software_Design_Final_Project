package main.panels;

import main.app.MainFrame;
import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {

    private final Color PRIMARY_COLOR = new Color(93,118,93);
    private final Color BG_COLOR = new Color(240,240,240);

    public AboutPanel(MainFrame mainFrame) {

        setLayout(new BorderLayout());
        setBackground(BG_COLOR);


        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(0,50));




        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(BG_COLOR);
        content.setBorder(BorderFactory.createEmptyBorder(20,30,20,30));




        JPanel toprow = new JPanel(new BorderLayout(15,0));
        toprow.setOpaque(false);
        toprow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel logo;
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/iconlogo.png"));
            Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            logo = new JLabel(new ImageIcon(img));
        } catch (Exception e) {
            logo = new JLabel("No Image");
        }

        JTextArea description = new JTextArea(
                "This banking system is designed to help users manage and monitor\n" +
                        "their financial accounts in a simple and organized way.\n\n" +

                        "It allows users to track their savings and sub-accounts across\n" +
                        "different banks while automatically calculating the total balance\n" +
                        "in real time."
        );
        styleTextArea(description);
        description.setMaximumSize(new Dimension(Integer.MAX_VALUE, description.getPreferredSize().height));

        toprow.add(logo, BorderLayout.WEST);
        toprow.add(description, BorderLayout.CENTER);


        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new BoxLayout(featurePanel, BoxLayout.Y_AXIS));
        featurePanel.setOpaque(false);
        featurePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel featuretitle = new JLabel("KEY FEATURES:");
        featuretitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        featuretitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea features = new JTextArea(
                "✔ Real-time total balance calculation\n" +
                        "✔ Easy bank navigation (BDO, Maya, etc.)\n" +
                        "✔ Add and remove custom banks\n" +
                        "✔ Sub-account management\n" +
                        "✔ Editable savings and balances\n" +
                        "✔ Simple and user-friendly interface"
        );
        styleTextArea(features);
        features.setAlignmentX(Component.LEFT_ALIGNMENT);
        features.setMaximumSize(new Dimension(Integer.MAX_VALUE, features.getPreferredSize().height));

        featurePanel.add(featuretitle);
        featurePanel.add(Box.createVerticalStrut(15));
        featurePanel.add(features);



        JButton backBtn = new JButton("Back");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setBackground(PRIMARY_COLOR);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(15,20,10,20));

        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backBtn.setBackground(new Color(70,95,70));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backBtn.setBackground(PRIMARY_COLOR);
            }
        });

        backBtn.addActionListener(e -> mainFrame.showPanel("dashboard"));



        content.add(toprow);
        content.add(Box.createVerticalStrut(15));
        content.add(new JSeparator());
        content.add(Box.createVerticalStrut(15));

        content.add(featurePanel);
        content.add(Box.createVerticalStrut(25));

        content.add(backBtn);


        JScrollPane scrollPanel = new JScrollPane(content);
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setUnitIncrement(20);

        add(header, BorderLayout.NORTH);
        add(scrollPanel, BorderLayout.CENTER);
    }

    private void styleTextArea(JTextArea area){
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setOpaque(false);
        area.setBorder(null);
    }
}
// edited