package main.panels;

import main.app.MainFrame;
import javax.swing.*;
import java.awt.*;
import main.services.FirebaseService;

public class AboutPanel extends JPanel {

    private final Color PRIMARY_COLOR = new Color(93,118,93);
    private final Color BG_COLOR = new Color(240,240,240);

    public AboutPanel(MainFrame mainFrame) {

        setLayout(new BorderLayout());
        setBackground(BG_COLOR);


        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(BG_COLOR);
        content.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));


        JPanel toprow = new JPanel(new BorderLayout(40,0));
        toprow.setOpaque(false);
        toprow.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel logo;
        try {
            String logoPath = "src/main/images/logoApp.png";
            FirebaseService.ourlogo(logoPath);

            ImageIcon icon = new ImageIcon(logoPath);
            Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            logo = new JLabel(new ImageIcon(img));

        } catch (Exception e) {
            logo = new JLabel("LOGO");
            logo.setFont(new Font("Segoe UI",Font.BOLD,40));
        }

        JTextArea description = new JTextArea(
                "This banking system is designed to help users manage and monitor\n" +
                        "their financial accounts in a simple and organized way.\n\n" +
                        "It allows users to track their savings and sub-accounts across\n" +
                        "different banks while automatically calculating the total balance\n" +
                        "in real time."
        );
        styleTextArea(description);
        description.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        toprow.add(logo, BorderLayout.WEST);
        toprow.add(description, BorderLayout.CENTER);


        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new BoxLayout(featurePanel, BoxLayout.Y_AXIS));
        featurePanel.setOpaque(false);
        featurePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);


        JLabel featuretitle = new JLabel("KEY FEATURES:");
        featuretitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        featuretitle.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JTextArea features = new JTextArea(
                "✔ Real-time total balance calculation\n" +
                        "✔ Easy bank navigation (BDO, Maya, etc.)\n" +
                        "✔ Add and remove custom banks\n" +
                        "✔ Sub-account management\n" +
                        "✔ Editable savings and balances\n" +
                        "✔ Simple and user-friendly interface"
        );
        styleTextArea(features);
        features.setFont(new Font("Segeo UI", Font.PLAIN, 20 ));
        features.setAlignmentX(Component.CENTER_ALIGNMENT);

        featurePanel.add(featuretitle);
        featurePanel.add(Box.createVerticalStrut(15));
        featurePanel.add(features);



        JButton backBtn = new JButton("Back");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setPreferredSize(new Dimension(120,40));
        backBtn.setMaximumSize(new Dimension(150,40));
        backBtn.setBackground(PRIMARY_COLOR);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setFont(new Font("Segeo UI", Font.BOLD, 16));

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
        content.add(Box.createVerticalStrut(30));
        content.add(new JSeparator());
        content.add(Box.createVerticalStrut(30));
        content.add(featurePanel);
        content.add(Box.createVerticalStrut(30));
        content.add(backBtn);


        JScrollPane scrollPanel = new JScrollPane(content);
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollPanel, BorderLayout.CENTER);
    }

    private void styleTextArea(JTextArea area){
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setOpaque(false);
        area.setBorder(null);
    }
}
// edited
