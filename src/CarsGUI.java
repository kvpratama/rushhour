/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author UserXP
 */
public class CarsGUI {

    private int n = 5;
    private JFrame frame;
    private JPanel contentPane, mainPanel, garagePanel, actionPanel;
    private JComboBox vehicleCombo, directionCombo;
    private JLabel stepLabel;
    private JTextField stepField;
    private JButton doButton;
    private String[] directions = {"up", "down", "left", "right"};
    private char[][] map;
    private List listOfVehicles = new ArrayList();
    private JButton buttons;
    private Color buttonColor;
    private Color[] colors = {Color.BLUE, Color.CYAN,
    Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

    private void initializeItems() {
        vehicleCombo = new JComboBox(listOfVehicles.toArray());
        directionCombo = new JComboBox(directions);
        stepLabel = new JLabel("Step ");
        stepField = new JTextField(2);
        doButton = new JButton("Do");

    }

    private void initializePanels() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        garagePanel = new JPanel(new GridLayout(5, 5));
        actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane = new JPanel(new BorderLayout());
    }

    private void initialization() {
        initializeItems();
        initializePanels();
    }

    public Color getColorOfVehicle(char x){
        char a = '0';
        for(int i = 0; i < listOfVehicles.size(); i++){
            a = listOfVehicles.get(i).toString().charAt(0);
            if(a == x){
                return colors[i];
            }
        }
        return null;
    }

    private void buildLayout() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != '0') {
                    buttons = new JButton("" + String.valueOf(map[i][j]));
                    garagePanel.add(buttons);
                    buttonColor = getColorOfVehicle(map[i][j]);
                    if(buttonColor != null){
                        buttons.setForeground(Color.BLACK);
                        buttons.setBackground(buttonColor);
                        buttons.setEnabled(false);
                    }
                } else {
                    garagePanel.add(new JLabel());
                }
            }

        }

        actionPanel.add(vehicleCombo);
        actionPanel.add(directionCombo);
        actionPanel.add(stepLabel);
        actionPanel.add(stepField);
        actionPanel.add(doButton);


        mainPanel.add(garagePanel, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);
        mainPanel.add(new JLabel(), BorderLayout.NORTH);
        mainPanel.add(new JLabel(), BorderLayout.WEST);
        mainPanel.add(new JLabel(), BorderLayout.EAST);

        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    private void buildGUI() {
        readFile();
        initialization();
        buildLayout();
        populateDefaultSettings();
        registerEventListener();

        frame = new JFrame("RushHour");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public void populateDefaultSettings() {
    }

    public void registerEventListener() {
        doButton.addActionListener(new EventHandler());
    }

    public void readFile() {
        try {
            File file = new File("map.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String s;
            int x = 0, y = 0;
            while (null != (s = br.readLine())) {
                StringTokenizer st = new StringTokenizer(s, ",");
                if (y == 0) {
                    n = st.countTokens();
                    map = new char[n][n];
                }
                x = 0;
                while (st.hasMoreTokens()) {
                    map[y][x] = st.nextToken(",").charAt(0);
                    if (!listOfVehicles.contains(map[y][x]) && map[y][x] != '0') {
                        listOfVehicles.add(map[y][x]);
                    }
                    x++;
                }
                y++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CarsGUI() {
        buildGUI();
    }

    public static void main(String[] args) {
        CarsGUI carsGUI = new CarsGUI();
    }

    private class EventHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source !=null && source == doButton){
                String myVehicle = vehicleCombo.getSelectedItem().toString();
                String myDirection = directionCombo.getSelectedItem().toString();
                int myStep = -1;
                try {
                    myStep = Integer.parseInt(stepField.getText());
                } catch (Exception ex) {
                    myStep = 0;
                }
                System.out.println(myVehicle + " " + myDirection + " " + myStep);

                
                
            }
        }
    }
}
//email ke ndarsono@student.ciputra.ac.id : format Nama _ NIM _ judul