package main.app;

import main.panels.*;

import javax.swing.*;

import java.awt.*;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainFrame extends JFrame {


    public DashBoardPanel getDashboard(){
        return dashboardPanel;
    }


    private CardLayout cardLayout;
    private JPanel mainContainer;

    private HashMap<String, JPanel> panels = new HashMap<>();
    private DashBoardPanel dashboardPanel;

    public static void main(String[] args) {

    }
}


