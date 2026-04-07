package main.app;

import main.panels.*;
import main.services.FirebaseInitializer;
import main.services.FirebaseService;

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

        FirebaseInitializer.initialize();
        FirebaseService.testConnection();


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

        BDOPanel bdoPanel = new BDOPanel(this);cd
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
        header.setBackground(new Color (82,94,84));
        header.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JLabel welcome = new JLabel("WELCOME");
        welcome.setFont(new Font("Arial", Font.PLAIN, 14));
        welcome.setForeground(Color.WHITE);

        JLabel name = new JLabel("JUAN DELA CRUZ");
        name.setFont(new Font("Arial", Font.BOLD, 24));
        name.setForeground(Color.WHITE);


        leftPanel.add(welcome);
        leftPanel.add(Box.createRigidArea(new Dimension(0,5)));
        leftPanel.add(name);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setOpaque(false);

        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        topRow.setOpaque(false);

        JLabel about = new JLabel("<html><u>About</u></html>");
        about.setForeground(Color.WHITE);
        about.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showPanel("about");
            }
        });

        JLabel exit = new JLabel("<html><u>Exit</u></html>");
        exit.setForeground(Color.WHITE);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if(confirm == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }

            public void mouseEntered(java.awt.event.MouseEvent e) {
                exit.setForeground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                exit.setForeground(Color.WHITE);
            }
        });

        topRow.add(about);
        topRow.add(exit);

        String date = LocalDate.now().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy")
        );

        JLabel dateLabel = new JLabel(date);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        rightPanel.add(topRow, BorderLayout.NORTH);
        rightPanel.add(dateLabel, BorderLayout.SOUTH);


        header.add(leftPanel, BorderLayout.WEST);
        header.add(rightPanel, BorderLayout.EAST);


        add(header, BorderLayout.NORTH);

    }

    public void showPanel(String name){
        cardLayout.show(mainContainer, name);
    }


    public void updateDashboardTotal(String bank, double amount){
        dashboardPanel.updateBank(bank, amount);
    }


    public void addNewBank(String bankName, ImageIcon logo) {


        DynamicBankPanel panel =
                new DynamicBankPanel(this, bankName, logo);


        panels.put(bankName, panel);
        mainContainer.add(panel, bankName);


        dashboardPanel.addBankButton(bankName,0);


        revalidate();
        repaint();
    }

    public void removePanel(String bankName){


        JPanel panel = panels.get(bankName);


        if(panel != null){


            mainContainer.remove(panel);
            panels.remove(bankName);


            dashboardPanel.removeBankButton(bankName);


            showPanel("dashboard");


            revalidate();
            repaint();
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}