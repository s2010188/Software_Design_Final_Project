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


}