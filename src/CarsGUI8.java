
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CarsGUI8 {

    private int n;
    private byte level = 1;
    private JFrame frame;
    private JPanel contentPane, mainPanel, garagePanel, actionPanel, optionPanel, doPanel;
    private JComboBox vehiclesCombo, directionCombo, stepCombo;
    private JLabel stepLabel, levelLabel;
    private Font levelLabelFont, buttonFont;
    private JButton doButton, nextLevelButton, howToPlayButton, restartLevelButton;
    private JButton buttons;
    private char[][] map;
    private String[] step = {"1", "2", "3", "4", "5"};
    private List listOfVehicles = new ArrayList();
    private Color buttonColor;
    private Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.LIGHT_GRAY,
        Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW, Color.GRAY,
        Color.DARK_GRAY, Color.GREEN, Color.CYAN, Color.ORANGE};
    private File file = new File("map2.txt");

    private void initializeItems() {
        vehiclesCombo = new JComboBox(listOfVehicles.toArray());
        directionCombo = new JComboBox();
        giveDefaultDirection();
        stepCombo = new JComboBox(step);
        stepLabel = new JLabel("Step:");
        levelLabel = new JLabel("Level " + level, JLabel.CENTER);
        levelLabelFont = new Font("monospaced", Font.BOLD, 25);
        buttonFont = new Font("monospaced", Font.BOLD, 20);
        levelLabel.setForeground(Color.RED);
        doButton = new JButton("Do");
        nextLevelButton = new JButton("Next Level");
        howToPlayButton = new JButton("How To Play");
        restartLevelButton = new JButton("Restart Level");
    }

    private void initializePanels() {
        mainPanel = new JPanel(new BorderLayout(5, 5));
        garagePanel = new JPanel(new GridLayout(n, n));
        doPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
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
            if (exit) {
                break;
            }
        }

    }

    private void buildLayout() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (map[i][j] != '0') {
                    if (map[i][j] == '@') {
                        buttons = new JButton("" + map[i][j]);
                        buttons.setBackground(Color.BLACK);
                        buttons.setFont(buttonFont);
                        buttons.setEnabled(false);
                        garagePanel.add(buttons);
                    } else if (map[i][j] == '*') {
                        buttons = new JButton("" + map[i][j]);
                        buttons.setBackground(Color.WHITE);
                        buttons.setFont(buttonFont);
                        buttons.setEnabled(false);
                        garagePanel.add(buttons);
                    } else {
                        buttons = new JButton(("" + map[i][j]).toUpperCase());
                        garagePanel.add(buttons);
                        buttons.setFont(buttonFont);
                        buttons.setEnabled(false);
                        if (buttons.getText().charAt(0) == 'A') {
                            buttons.setForeground(Color.BLACK);
                            buttons.setBackground(Color.RED);
                        } else {
                            buttonColor = getColorOfVehicle(map[i][j]);
                            if (buttonColor != null) {
                                buttons.setForeground(Color.BLACK);
                                buttons.setBackground(buttonColor);
                            }
                        }

                    }
                } else {
                    garagePanel.add(new JPanel());
                }
            }
        }

        actionPanel.add(vehiclesCombo);
        actionPanel.add(directionCombo);
        actionPanel.add(stepLabel);
        actionPanel.add(stepCombo);
        actionPanel.add(doButton);

        optionPanel.add(howToPlayButton);
        optionPanel.add(restartLevelButton);
        optionPanel.add(nextLevelButton);

        doPanel.add(actionPanel);
        doPanel.add(optionPanel);

        mainPanel.add(garagePanel, BorderLayout.CENTER);
        mainPanel.add(doPanel, BorderLayout.SOUTH);
        mainPanel.add(new JLabel(), BorderLayout.EAST);
        mainPanel.add(new JLabel(), BorderLayout.WEST);
        mainPanel.add(levelLabel, BorderLayout.NORTH);

        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    private void buildLayout2() {
        garagePanel.removeAll();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (map[i][j] != '0') {
                    if (map[i][j] == '@') {
                        buttons = new JButton("" + map[i][j]);
                        buttons.setBackground(Color.BLACK);
                        buttons.setFont(buttonFont);
                        buttons.setEnabled(false);
                        garagePanel.add(buttons);
                    } else if (map[i][j] == '*') {
                        buttons = new JButton("" + map[i][j]);
                        buttons.setBackground(Color.WHITE);
                        buttons.setFont(buttonFont);
                        buttons.setEnabled(false);
                        garagePanel.add(buttons);
                    } else {
                        buttons = new JButton(("" + map[i][j]).toUpperCase());
                        garagePanel.add(buttons);
                        buttons.setFont(buttonFont);
                        buttons.setEnabled(false);
                        if (buttons.getText().charAt(0) == 'A') {
                            buttons.setForeground(Color.BLACK);
                            buttons.setBackground(Color.RED);
                        } else {
                            buttonColor = getColorOfVehicle(map[i][j]);
                            if (buttonColor != null) {
                                buttons.setForeground(Color.BLACK);
                                buttons.setBackground(buttonColor);
                            }
                        }

                    }
                } else {
                    garagePanel.add(new JPanel());
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
        frame.setSize(570, 570);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public void populateDefaultSettings() {
        nextLevelButton.setEnabled(false);
        nextLevelButton.setBackground(Color.WHITE);
        levelLabel.setFont(levelLabelFont);
    }

    public void registerEventListener() {
        doButton.addActionListener(new EventHandler());
        nextLevelButton.addActionListener(new nextLevelListener());
        restartLevelButton.addActionListener(new restartLevelListener());
        howToPlayButton.addActionListener(new howToPlayListener());
        vehiclesCombo.addItemListener(new vehiclesComboListener());
    }

    public void readFile() {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String s;
            int x = 0, y = 0;
            while (null != (s = br.readLine())) {
                StringTokenizer st = new StringTokenizer(s, ",");
                if (y == 0) {
                    n = st.countTokens();
                    System.out.println("n: " + n);
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
            for (int a = 0; a < n; a++) {
                for (int b = n; b < n + 2; b++) {
                    if (a != ((n + 1) / 2) - 1) {
                        map[a][b] = '@';
                    } else {
                        map[a][b] = '*';
                    }
                }
            }
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n + 2; b++) {
                    System.out.print("" + map[a][b]);
                }
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CarsGUI8() {
        buildGUI();
    }

    public static void main(String[] args) {
        CarsGUI8 carsGUI8 = new CarsGUI8();
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
                    myStep = Integer.parseInt(stepCombo.getSelectedItem().toString());
                } catch (Exception ex) {
                    myStep = 0;
                }
                System.out.println(myVehicle + " " + myDirection + " " + myStep);
                if (myDirection.equalsIgnoreCase("up")) {
                    for (x = 0; x < n; x++) {
                        for (y = 0; y < n; y++) {
                            if (map[x][y] == myVehicle.charAt(0)) {
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
                        for (y = 0; y < n + 2; y++) {
                            if (map[x][y] == myVehicle.charAt(0)) {
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
                    byte count = 0;
                    for (int a = 0; a < n; a++) {
                        for (int b = n; b < n + 2; b++) {
                            if (map[a][b] == '*') {
                                count++;
                            }
                        }
                    }
                    if (count < 2) {
                        for (int a = 0; a < n; a++) {
                            for (int b = n; b < n + 2; b++) {
                                if (map[a][b] == '*') {
                                    map[a][b - 1] = '*';
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
                    boolean win = true;
                    for (x = n - 1; x >= 0; x--) {
                        for (y = n + 1; y >= 0; y--) {
                            if (myVehicle.charAt(0) == 'a') {
                                if (map[x][y] == myVehicle.charAt(0)) {
                                    for (int a = 0; a < myStep; a++) {
                                        if (y == n + 1) {
                                            map[x][y] = myVehicle.charAt(0);
                                            break;
                                        } else if (map[x][y + 1] == '0' || map[x][y + 1] == '*') {
                                            map[x][y] = '0';
                                            y = y + 1;
                                            map[x][y] = myVehicle.charAt(0);
                                        } else if (map[x][y + 1] != '0') {
                                            map[x][y] = myVehicle.charAt(0);
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (map[x][y] == myVehicle.charAt(0)) {
                                    for (int a = 0; a < myStep; a++) {
                                        if (y == n + 1) {
                                            map[x][y] = myVehicle.charAt(0);
                                            break;
                                        } else if (map[x][y + 1] == '0') {
                                            map[x][y] = '0';
                                            y = y + 1;
                                            map[x][y] = myVehicle.charAt(0);
                                        } else if (map[x][y + 1] != '0') {
                                            map[x][y] = myVehicle.charAt(0);
                                            break;
                                        }
                                    }
                                }
                            }

                        }
                    }
                    for (int i = 0; i < n; i++) {
                        for (int j = n; j < n + 2; j++) {
                            if (map[i][j] == '*') {
                                win = false;
                            }
                        }
                    }
                    if (win) {
                        System.out.println("you win!!");
                        nextLevelButton.setEnabled(true);
                        nextLevelButton.setBackground(Color.YELLOW);
                        doButton.setEnabled(false);
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

    private class nextLevelListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            level++;
            //listOfVehicles.clear();
            if (level == 2) {
                file = new File("map3.txt");
            } else if (level == 3) {
                file = new File("map4.txt");
            } else if (level == 4) {
                file = new File("map5.txt");
            } else if (level == 5) {
                file = new File("map6.txt");
            } else if (level == 6) {
                file = new File("map7.txt");
            } else if (level == 7) {
                file = new File("map8.txt");
            } else if (level == 8) {
                file = new File("map9.txt");
            }
            readFile();
            buildLayout2();
//            vehiclesCombo.removeAllItems();
//            vehiclesCombo.addItem(listOfVehicles);

            nextLevelButton.setEnabled(false);
            nextLevelButton.setBackground(Color.WHITE);
            doButton.setEnabled(true);
            if (level > 8) {
                level = 8;
                nextLevelButton.setEnabled(false);
                doButton.setEnabled(false);
            }
            levelLabel.setText("Level " + level);

        }
    }

    private class howToPlayListener implements ActionListener {

        JLabel howLabel;
        JLabel toLabel;
        JLabel playLabel;
        JButton okButton;
        Font howToFont, playFont;
        JPanel okPanel;
        JFrame howToFrame;
        String how = "The goal of this game is to move";
        String to = "the A button to the white spot.";
        String play = "Have fun!!";

        public void initializeHowToPlayItem() {
            howLabel = new JLabel(how, JLabel.CENTER);
            toLabel = new JLabel(to, JLabel.CENTER);
            playLabel = new JLabel(play, JLabel.CENTER);
            okButton = new JButton("Ok");
            okButton.addActionListener(new okListener());
            howToFont = new Font("monospaced", Font.PLAIN, 15);
            playFont = new Font("monospaced", Font.BOLD, 20);
        }

        public void initializeHowToPlayPanel() {
            okPanel = new JPanel(new GridLayout(2, 3, 2, 2));
            howToFrame = new JFrame("How To Play");
        }

        public void defaultSetting() {
            howLabel.setFont(howToFont);
            howLabel.setForeground(Color.BLUE);
            toLabel.setFont(howToFont);
            toLabel.setForeground(Color.BLUE);
            playLabel.setFont(playFont);
            playLabel.setForeground(Color.RED);
        }

        public void build() {
            initializeHowToPlayItem();
            initializeHowToPlayPanel();
            defaultSetting();

            okPanel.add(new JLabel());
            okPanel.add(playLabel);
            okPanel.add(new JLabel());
            okPanel.add(new JLabel());
            okPanel.add(okButton);
            okPanel.add(new JLabel());

            howToFrame.getContentPane().add(howLabel, BorderLayout.NORTH);
            howToFrame.getContentPane().add(toLabel, BorderLayout.CENTER);
            howToFrame.getContentPane().add(okPanel, BorderLayout.SOUTH);
            howToFrame.setLocation(455, 445);
            howToFrame.setSize(380, 150);
            howToFrame.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            build();
            doButton.setEnabled(false);
        }

        class okListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                doButton.setEnabled(true);
                howToFrame.setVisible(false);
            }
        }
    }

    private class restartLevelListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int a = JOptionPane.showConfirmDialog(frame, "Are you sure want to restart this level?");
            if (a == 0) {
                readFile();
                buildLayout2();
                nextLevelButton.setEnabled(false);
                nextLevelButton.setBackground(Color.WHITE);
            } else {
            }
        }
    }
}
