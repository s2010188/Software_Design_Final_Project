package main.panels;

import main.app.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DashBoardPanel extends JPanel {

    private MainFrame mainFrame;

    private JPanel buttonPanel;


    public DashBoardPanel(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBackground(new Color(240,240,240));

        ButtonPanel();

    }

    private void ButtonPanel(){

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        buttonPanel.setBackground(new Color(240,240,240));

        buttonPanel.add(Button("BDO", "bdo"));
        buttonPanel.add(Button("Maya", "maya"));
        buttonPanel.add();



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



}