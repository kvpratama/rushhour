
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

public class CarsGUI3 {

    private int n;
    private JFrame frame;
    private JPanel contentPane, mainPanel, garagePanel, actionPanel;
    private JComboBox vehiclesCombo, directionCombo;
    private JLabel stepLabel;
    private JTextField stepField;
    private JButton doButton;
    private JButton buttons;
    private char[][] map;
    private List listOfVehicles = new ArrayList();
    private Color buttonColor;
    private Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.LIGHT_GRAY,
        Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
    private EventHandler handler = new EventHandler();

    private void initializeItems() {
        vehiclesCombo = new JComboBox(listOfVehicles.toArray());
        directionCombo = new JComboBox();
        //giveDefaultDirection();
        stepLabel = new JLabel("Step:");
        stepField = new JTextField(2);
        doButton = new JButton("Do");

    }

    private void initializePanels() {
        mainPanel = new JPanel(new BorderLayout(5, 5));
        garagePanel = new JPanel(new GridLayout(n, n + 2));
        actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane = new JPanel(new BorderLayout());
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

    public void giveDefaultDirection() {
        String temp = listOfVehicles.toString();
        boolean exit = false;
        char tempChar = temp.charAt(1);
        System.out.println("temp" + tempChar);
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                if (map[a][b] == tempChar) {
                    if (map[a][b + 1] == tempChar) {
                        directionCombo.addItem("left");
                        directionCombo.addItem("right");
                    } else if (map[a + 1][b] == tempChar) {
                        directionCombo.addItem("up");
                        directionCombo.addItem("down");
                    } else {
                        directionCombo.addItem("up");
                        directionCombo.addItem("down");
                        directionCombo.addItem("left");
                        directionCombo.addItem("right");
                    }
                    exit = true;
                    break;
                }
            }
            if(exit){
                break;
            }
        }

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

        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    private void buildLayout2() {
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
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public void populateDefaultSettings() {
    }

    public void registerEventListener() {
        doButton.addActionListener(new EventHandler());
        vehiclesCombo.addItemListener(new vehiclesComboListener());
    }

    public CarsGUI3() {
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
                    map = new char[n][n + 2];
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

    public static void main(String[] args) {
        CarsGUI3 carsGUI3 = new CarsGUI3();
    }

    private class EventHandler implements ActionListener {

        int x, y;

        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source != null && source == doButton) {
                String myVehicle = vehiclesCombo.getSelectedItem().toString();
                String myDirection = directionCombo.getSelectedItem().toString();
                int myStep = -1;
                try {
                    myStep = Integer.parseInt(stepField.getText());
                } catch (Exception ex) {
                    myStep = 0;
                }
                System.out.println(myVehicle + " " + myDirection + " " + myStep);

                if (myDirection.equalsIgnoreCase("up")) {
                    for (x = 0; x < n; x++) {
                        for (y = 0; y < n; y++) {
                            if (map[x][y] == myVehicle.charAt(0)) {
                                map[x][y] = '0';
                                for (int a = 0; a < myStep; a++) {
                                    if (x == 0) {
                                        map[x][y] = myVehicle.charAt(0);
                                        break;
                                    } else if (map[x - 1][y] == '0') {
                                        map[x][y] = '0';
                                        x = x - 1;
                                        map[x][y] = myVehicle.charAt(0);
                                    } else if (map[x - 1][y] != '0') {
                                        map[x][y] = myVehicle.charAt(0);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    buildLayout2();
                }

                if (myDirection.equalsIgnoreCase("left")) {
                    for (x = 0; x < n; x++) {
                        for (y = 0; y < n; y++) {
                            if (map[x][y] == myVehicle.charAt(0)) {
                                map[x][y] = '0';
                                for (int a = 0; a < myStep; a++) {
                                    if (y == 0) {
                                        map[x][y] = myVehicle.charAt(0);
                                        break;
                                    } else if (map[x][y - 1] == '0') {
                                        map[x][y] = '0';
                                        y = y - 1;
                                        map[x][y] = myVehicle.charAt(0);
                                    } else if (map[x][y - 1] != '0') {
                                        map[x][y] = myVehicle.charAt(0);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    buildLayout2();
                }

                if (myDirection.equalsIgnoreCase("down")) {
                    for (x = n - 1; x >= 0; x--) {
                        for (y = n - 1; y >= 0; y--) {
                            if (map[x][y] == myVehicle.charAt(0)) {
                                map[x][y] = '0';
                                for (int a = 0; a < myStep; a++) {
                                    if (x == n - 1) {
                                        map[x][y] = myVehicle.charAt(0);
                                        break;
                                    } else if (map[x + 1][y] == '0') {
                                        map[x][y] = '0';
                                        x = x + 1;
                                        map[x][y] = myVehicle.charAt(0);
                                    } else if (map[x + 1][y] != '0') {
                                        map[x][y] = myVehicle.charAt(0);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    buildLayout2();
                }

                if (myDirection.equalsIgnoreCase("right")) {
                    for (x = n - 1; x >= 0; x--) {
                        for (y = n - 1; y >= 0; y--) {
                            if (map[x][y] == myVehicle.charAt(0)) {
                                map[x][y] = '0';
                                for (int a = 0; a < myStep; a++) {
                                    if (y == n - 1) {
                                        map[x][y] = myVehicle.charAt(0);
                                        break;
                                    } else if (map[x][y + 1] == '0') {
                                        map[x][y] = '0';
                                        y = y + 1;
                                        map[x][y] = myVehicle.charAt(0);
                                    } else if (map[x][y + 1] != 0) {
                                        map[x][y] = myVehicle.charAt(0);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    buildLayout2();
                }
            }
        }
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
}
//                            else if (map[x + 1][y] != vehicle.charAt(0) && map[x][y + 1] != vehicle.charAt(0)) {
//                            directionCombo.removeAllItems();
//                            directionCombo.addItem("up");
//                            directionCombo.addItem("down");
//                            directionCombo.addItem("left");
//                            directionCombo.addItem("right");
//                            quit=true;
//                            break;
//                        }

