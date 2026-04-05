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

    public MainFrame(){

        setTitle("Bank System");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(
                getClass().getResource("/main/images/logoApp.png")
        ).getImage());

        setLayout(new BorderLayout());

    }
    public static void main(String[] args) {

    }
}


