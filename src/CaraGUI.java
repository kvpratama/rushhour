

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class CaraGUI {

    private int n = 5;
    private JFrame frame;
    private JPanel sayaaaaaaaaaa, mainPanel, garagePanel, actionPanel;
    private JComboBox vehiclesCombo, directionCombo;
    private JLabel stepLabel;
    private JTextField stepField;
    private JButton doButton;
    //private String[] direction = {"up", "down", "left", "right"};
    private JButton buttons;
    private char[][] map;
    private List listOfVehicles = new ArrayList();
    private Color buttonColor;
    private Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.LIGHT_GRAY,
        Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
    private EventHandler handler = new EventHandler();

    private void initializeItems() {
        vehiclesCombo = new JComboBox(listOfVehicles.toArray());
        directionCombo = new JComboBox();
        DefaultDirection();
        stepLabel = new JLabel("Step:");
        stepField = new JTextField(2);
        doButton = new JButton("Do");
    }

    private void initializePanels() {
        mainPanel = new JPanel(new BorderLayout(5, 5));
        garagePanel = new JPanel(new GridLayout(5, 5));
        actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        sayaaaaaaaaaa = new JPanel(new BorderLayout());
    }

    private void initialization() {
        initializeItems();
        initializePanels();
    }

    public Color getColorOfVehicle(char x) {
        char a = '0';
        for (int i = 0; i < listOfVehicles.size(); i++) {
            a = listOfVehicles.get(i).toString().charAt(0);
            if (a == x) {
                return colors[i];
            }
        }
        return null;
    }



    private void buildLayout() {
        //initialization();
        int garageBorderCounter = 0;
        for (int i = 0; i < n; i++) {
            garageBorderCounter = 0;
            for (int j = 0; j < n + 2; j++) {
                if (garageBorderCounter < n) {
                    if (map[i][j] != '0') {
                        buttons = new JButton("" + map[i][j]);
                        garagePanel.add(buttons);
                        buttonColor = getColorOfVehicle(map[i][j]);
                        if (buttonColor != null) {
                            buttons.setForeground(Color.BLACK);
                            buttons.setBackground(buttonColor);
                            buttons.setEnabled(false);
                        }
                    } else {
                        garagePanel.add(new JPanel());
                    }
                    garageBorderCounter++;
                } else {
                    if (i != (n / 2)) {
                        buttons = new JButton("@");
                        buttons.setEnabled(false);
                        buttons.setBackground(Color.BLACK);
                        garagePanel.add(buttons);
                    } else {
                        buttons = new JButton("*");
                        buttons.setEnabled(false);
                        buttons.setBackground(Color.WHITE);
                        garagePanel.add(buttons);
                    }
                }
            }
        }
        actionPanel.add(vehiclesCombo);
        actionPanel.add(directionCombo);
        actionPanel.add(stepLabel);
        actionPanel.add(stepField);
        actionPanel.add(doButton);

        mainPanel.add(garagePanel, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);
        mainPanel.add(new JLabel(), BorderLayout.EAST);
        mainPanel.add(new JLabel(), BorderLayout.WEST);
        mainPanel.add(new JLabel(), BorderLayout.NORTH);

        sayaaaaaaaaaa.add(mainPanel, BorderLayout.CENTER);
    }

    private void refreshLayout() {
        garagePanel.removeAll();
        int garageBorderCounter = 0;
        for (int i = 0; i < n; i++) {
            garageBorderCounter = 0;
            for (int j = 0; j < n + 2; j++) {
                if (garageBorderCounter < n) {
                    if (map[i][j] != '0') {
                        buttons = new JButton("" + map[i][j]);
                        garagePanel.add(buttons);
                        buttonColor = getColorOfVehicle(map[i][j]);
                        if (buttonColor != null) {
                            buttons.setForeground(Color.BLACK);
                            buttons.setBackground(buttonColor);
                            buttons.setEnabled(false);
                        }
                    } else {
                        garagePanel.add(new JLabel());
                    }
                    garageBorderCounter++;
                } else {
                    if (i != (n / 2)) {
                        buttons = new JButton("@");
                        buttons.setEnabled(false);
                        buttons.setBackground(Color.BLACK);
                        garagePanel.add(buttons);
                    } else {
                        buttons = new JButton("*");
                        buttons.setEnabled(false);
                        buttons.setBackground(Color.WHITE);
                        garagePanel.add(buttons);
                    }
                }
            }
            garagePanel.validate();
        }
    }

    private void buildGUI() {
        readFile();
        initialization();
        buildLayout();
        populateDefaultSettings();
        registerEventListener();
        frame = new JFrame("Rush Hour");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        //TIDAK BOLEH DILUPAKAN!!
        frame.setContentPane(sayaaaaaaaaaa);
        frame.setVisible(true);
    }

    public void populateDefaultSettings() {
    }

    public void registerEventListener() {
        doButton.addActionListener(new EventHandler());
        vehiclesCombo.addItemListener(new vehiclesComboListener());
    }

    public CaraGUI() {
        buildGUI();
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

    public void DefaultDirection(){
        String vehicleList = listOfVehicles.toString();
        char car = vehicleList.charAt(1);
        System.out.println("temp = " + car);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == car && map[i][j+1] == car){
                    directionCombo.addItem("right");
                    directionCombo.addItem("left");
                    System.out.println("1");
                    break;
                } else if(map[i][j] == car && map[i+1][j] == car){
                    directionCombo.addItem("up");
                    directionCombo.addItem("down");
                    System.out.println("2");
                    break;
                } else if(map[i][j] == car){
                    directionCombo.addItem("up");
                    directionCombo.addItem("down");
                    directionCombo.addItem("right");
                    directionCombo.addItem("left");
                    System.out.println("3");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        CaraGUI cara = new CaraGUI();
    }

    private class vehiclesComboListener implements ItemListener {

        int x, y;
        boolean quit;

        public void itemStateChanged(ItemEvent e) {
            quit = false;
            String vehicle = vehiclesCombo.getSelectedItem().toString();

            for (x = 0; x < n; x++) {
                for (y = 0; y < n; y++) {
                    if (map[x][y] == vehicle.charAt(0)) {
                        if (x == n - 1) {
                            if (map[x][y + 1] == vehicle.charAt(0)) {
                                directionCombo.removeAllItems();
                                directionCombo.addItem("left");
                                directionCombo.addItem("right");
                                quit = true;
                                break;
                            } else {
                                directionCombo.removeAllItems();
                                directionCombo.addItem("up");
                                directionCombo.addItem("down");
                                directionCombo.addItem("left");
                                directionCombo.addItem("right");
                                quit = true;
                                break;
                            }
                        } else if (map[x][y + 1] == vehicle.charAt(0)) {
                            directionCombo.removeAllItems();
                            directionCombo.addItem("left");
                            directionCombo.addItem("right");
                            quit = true;
                            break;

                        } else if (map[x + 1][y] == vehicle.charAt(0)) {
                            directionCombo.removeAllItems();
                            directionCombo.addItem("up");
                            directionCombo.addItem("down");
                            quit = true;
                            break;

                        } else {
                            directionCombo.removeAllItems();
                            directionCombo.addItem("up");
                            directionCombo.addItem("down");
                            directionCombo.addItem("left");
                            directionCombo.addItem("right");
                            quit = true;
                            break;
                        }
                    }
                }
                if (quit) {
                    break;
                }
            }
        }
    }

    private class EventHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source != null && source == doButton) {
                String myVehicle = vehiclesCombo.getSelectedItem().toString();
                String myDirection = directionCombo.getSelectedItem().toString();
                int myStep = -1;
                try {
                    myStep = Integer.parseInt(stepField.getText());
                    System.out.println("myVehicle  = " + myVehicle);
                    if (myDirection.equals("right")) {
                        if(myVehicle.charAt(0) == 'a'){
                            for(int j=0; j<n+1; j++){
                                if(map[((n+1)/2)-1][j] == 'a'){
                                    int addStep = 0;
                                    for(int k=1; k <= myStep; k++){
                                        if(map[((n+1)/2)-1][j+k] == '0' && j + k < n+2 && j != n+1){
                                            addStep++;
                                        } else {
                                            break;
                                        }
                                    }
                                    if(addStep > 0){
                                        map[((n+1)/2)-1][j + addStep] = 'a';
                                        map[((n+1)/2)-1][j] = '0';
                                        if(j != 0){
                                            move((((n+1)/2)-1), j, myDirection, myVehicle.charAt(0), addStep);
                                        }
                                    }
                                }
                            }
                        } else {
                            for (int i = n - 1; i >= 0; i--) {
                                for (int j = n - 1; j >= 0; j--) {
                                    if (map[i][j] == myVehicle.charAt(0)) {
                                        int addStep = 0;
                                        for (int k = 1; k <= myStep; k++) {
                                            if (map[i][j + k] == '0' && j + k < n && j != n - 1) {
                                                addStep++;
                                            } else {
                                                break;
                                            }
                                        }
                                        if (addStep > 0) {
                                            map[i][j + addStep] = myVehicle.charAt(0);
                                            map[i][j] = '0';
                                            if (j != 0) {
                                                move(i, j, myDirection, myVehicle.charAt(0), addStep);
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        refreshLayout();
                    }
                    if (myDirection.equals("left")) {
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                if (map[i][j] == myVehicle.charAt(0)) {
                                    int addStep = 0;
                                    for (int k = 1; k <= myStep; k++) {
                                        if (map[i][j - k] == '0' && j - k >= 0 && j != 0) {
                                            addStep++;
                                        } else {
                                            break;
                                        }
                                    }
                                    if (addStep > 0) {
                                        map[i][j - addStep] = myVehicle.charAt(0);
                                        map[i][j] = '0';
                                        if (j != n - 1) {
                                            move(i, j, myDirection, myVehicle.charAt(0), addStep);
                                        } else {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                        refreshLayout();
                    }
                    if (myDirection.equals("up")) {
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                if (map[i][j] == myVehicle.charAt(0)) {
                                    int addStep = 0;
                                    for (int k = 1; k <= myStep; k++) {
                                        if (map[i - k][j] == '0' && i - k >= 0 && i != 0) {
                                            addStep++;
                                        } else {
                                            break;
                                        }
                                    }
                                    if (addStep > 0) {
                                        map[i - addStep][j] = myVehicle.charAt(0);
                                        map[i][j] = '0';
                                        if (i != n - 1) {
                                            move(i, j, myDirection, myVehicle.charAt(0), addStep);
                                        } else {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                        refreshLayout();
                    }
                    if (myDirection.equals("down")) {
                        for (int i = n - 1; i >= 0; i--) {
                            for (int j = n - 1; j >= 0; j--) {
                                if (map[i][j] == myVehicle.charAt(0)) {
                                    int addStep = 0;
                                    for (int k = 1; k <= myStep; k++) {
                                        if (map[i + k][j] == '0' && i + k < n && i != n - 1) {
                                            addStep++;
                                        } else {
                                            break;
                                        }
                                    }
                                    if (addStep > 0) {
                                        map[i + addStep][j] = myVehicle.charAt(0);
                                        map[i][j] = '0';
                                        if (i != 0) {
                                            move(i, j, myDirection, myVehicle.charAt(0), addStep);
                                        } else {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                        refreshLayout();
                    }
                } catch (Exception ex) {
                    myStep = 0;
                }
            }
        }
    }

    public void move(int i, int j, String myDirection, char myVehicle, int addStep) {
        System.out.println();
        if (myDirection.equals("right")) {
            if (j != 0 && j > 1) {
                if (map[i][j - 1] == myVehicle) {
                    map[i][j + addStep - 1] = myVehicle;
                    map[i][j - 1] = '0';
                    move(i, j, myDirection, myVehicle, addStep);
                    j--;
                }
            }
        }
        if (myDirection.equals("left")) {
            if (j != n - 1 && j < n - 2) {
                if (map[i][j + 1] == myVehicle) {
                    map[i][j - addStep + 1] = myVehicle;
                    map[i][j + 1] = '0';
                    move(i, j, myDirection, myVehicle, addStep);
                    j++;
                }
            }
        }
        if (myDirection.equals("up")) {
            if (i != n - 1 && i < n - 2) {
                if (map[i + 1][j] == myVehicle) {
                    map[i - addStep + 1][j] = myVehicle;
                    map[i + 1][j] = '0';
                    move(i, j, myDirection, myVehicle, addStep);
                    i++;
                }
            }
        }
        if (myDirection.equals("down")) {
            if (i != 0 && i < 1) {
                if (map[i + addStep - 1][j] == myVehicle) {
                    map[i][j] = myVehicle;
                    map[i - 1][j] = '0';
                    move(i, j, myDirection, myVehicle, addStep);
                    i--;
                }
            }
        }
    }
}
