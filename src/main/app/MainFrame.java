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

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        panels = new HashMap<>();

        createHeader();

        dashboardPanel = new DashBoardPanel(this);

        BDOPanel bdoPanel = new BDOPanel(this);
        MayaPanel mayaPanel = new MayaPanel(this);
        AddBankPanel addBankPanel = new AddBankPanel(this);
        AboutPanel aboutPanel = new AboutPanel(this);

        panels.put("dashboard", dashboardPanel);
        panels.put("bdo", bdoPanel);
        panels.put("maya", mayaPanel);
        panels.put("addbank", addBankPanel);
        panels.put("about", aboutPanel);


        mainContainer.add(dashboardPanel, "dashboard");
        mainContainer.add(bdoPanel, "bdo");
        mainContainer.add(mayaPanel, "maya");
        mainContainer.add(addBankPanel, "addbank");
        mainContainer.add(aboutPanel, "about");

        add(mainContainer, BorderLayout.CENTER);

        setVisible(true);
    }

    private void  createHeader(){

        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 120));
        header.setBackground(82,94,84);
        header.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JLabel welcome = new JLabel("WELCOME");
        welcome.setFont(new Font("Arial", Font.PLAIN, 14));
        welcome.setForeground(Color.WHITE);


    }


    public static void main(String[] args) {

    }
}


