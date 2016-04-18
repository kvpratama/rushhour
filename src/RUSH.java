/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package puzzle;

/**
 *
 * @author Jacky Hoeny
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RUSH extends JFrame implements ActionListener {
    //garagePanel < diisi ulang
    //mainPanel.add(garagePanel)
    //mainPanel.setVisible(false);
    //mainPanel.setVisible(true);

    char[][] data = new char[100][100];
    int y = 0, x = 0, p = 0;
    char[] coordinate = new char[100];
    Object[] car = new Object[10];
    //String[] arah = {"up", "down", "left", "right"};
    JButton button;
    JComboBox box1;
    JComboBox box2;
    JTextField text;
    private JPanel tengah;
    private JPanel bawah;
    private JPanel spasi;
    private JPanel spasi1;
    private JPanel spasi2;
    private JPanel contentPane;

    public RUSH() {
        JFileChooser fc = new JFileChooser(".");
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String[] input = new String[10];
            int count = 0;
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fc.getSelectedFile()));
                String s;
                while (null != (s = reader.readLine())) {
                    input[count] = s;
                    count++;
                    y++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Whether it was a success or fail, close the stream.
                if (null != reader) {
                    try {
                        reader.close();
                    } catch (Exception ignored) {
                    }
                }
            }

            //untuk menghilangkan tanda koma, lalu dimasukkan kedalam array baru


            int aCount = 0;
            for (int i = 0; i < count; i++) {
                int len = input[i].length();
                aCount = 0;
                for (int j = 0; j < (2 * count) - 1; j++) {
                    char ch = input[i].charAt(j);
                    if (ch == ',') {
                        continue;
                    } else {
                        data[i][aCount] = ch;
                        x++;

                    }
                    aCount++;
                }
            }
            x /= y;

            for (int i = 0; i < count; i++) {
                for (int j = count; j < count + 2; j++) {
                    if (i == count / 2) {
                        data[i][j] = '?';
                    } else {
                        data[i][j] = '@';
                    }
                }
            }

            //untuk print peta tanpa tanda koma
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count + 2; j++) {
                    System.out.print(data[i][j]);
                }
                System.out.println("");
            }
            System.out.println("");

            //untuk print koordinat dari peta
            count = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (count == 0) {
                        if (data[i][j] != '0') {
                            coordinate[count] = data[i][j];
                            car[count] = coordinate[count];
                            count++;
                        }
                    } else {

                        int charCheck = 0;
                        for (int k = 0; k < count; k++) {
                            if (data[i][j] != coordinate[k]) {
                                charCheck++;
                            }
                        }
                        if (charCheck == count) {
                            if (data[i][j] != '0') {
                                coordinate[count] = data[i][j];
                                car[count] = coordinate[count];
                                count++;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < count; i++) {
                System.out.print(coordinate[i] + ":");
                int total = 0;
                int[] x1 = new int[20], y1 = new int[20];
                for (int j = 0; j < y; j++) {
                    for (int k = 0; k < x; k++) {
                        if (data[j][k] == coordinate[i]) {
                            if (total == 0) {
                                x1[0] = j;
                                y1[0] = k;
                                total++;
                            } else if (total == 1) {
                                x1[total] = j;
                                y1[total] = k;
                            }
                            System.out.print("[" + j + "," + k + "]");
                        }
                    }
                }
                System.out.print(":");
                if (x1[1] == 0 && y1[1] == 0) {
                    System.out.println("HV");
                } else if (y1[0] == y1[1]) {
                    System.out.println("V");
                } else {
                    System.out.println("H");
                }
            }
        }
    }

    public void buildLayout() {
        setTitle("RushHour");
        setSize(70 * x, 75 * y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.black);

        tengah = new JPanel();
        tengah.setLayout(new GridLayout(x, y));
        tengah.setBackground(Color.black);
        JButton[][] tombol = new JButton[x][y];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x + 2; j++) {
                if (j < x) {
                    if (data[i][j] != '0') {
                        tombol[i][j] = new JButton(Character.toString(data[i][j]));
                        tengah.add(tombol[i][j]);

                        if (data[i][j] == 'a') {
                            tombol[i][j].setBackground(Color.WHITE);
                        } else if (data[i][j] == 'b') {
                            tombol[i][j].setBackground(Color.BLUE);
                        } else if (data[i][j] == 'c') {
                            tombol[i][j].setBackground(Color.CYAN);
                        } else if (data[i][j] == 'd') {
                            tombol[i][j].setBackground(Color.GRAY);
                        } else if (data[i][j] == 'e') {
                            tombol[i][j].setBackground(Color.GREEN);
                        } else if (data[i][j] == 'f') {
                            tombol[i][j].setBackground(Color.LIGHT_GRAY);
                        } else if (data[i][j] == 'g') {
                            tombol[i][j].setBackground(Color.MAGENTA);
                        } else if (data[i][j] == 'h') {
                            tombol[i][j].setBackground(Color.ORANGE);
                        } else if (data[i][j] == 'i') {
                            tombol[i][j].setBackground(Color.PINK);
                        } else if (data[i][j] == 'j') {
                            tombol[i][j].setBackground(Color.RED);
                        } else if (data[i][j] == 'k') {
                            tombol[i][j].setBackground(Color.YELLOW);
                        }
                    } else {
                        tengah.add(new JLabel());
                    }
                } else {
                    JButton fence = new JButton();

                    if (i == y / 2) {
                        fence.setBackground(Color.white);
                        fence.setEnabled(false);
                        fence.setText("?");
                    } else {
                        fence.setBackground(Color.yellow);
                        fence.setEnabled(false);
                        fence.setText("@");
                    }
                    tengah.add(fence);
                }

            }
        }

        box1 = new JComboBox(car);
        box2 = new JComboBox();
        giveDefaultDirection();
        JLabel label = new JLabel("Step :");
        label.setForeground(Color.WHITE);
        text = new JTextField(2);
        button = new JButton("do");
        button.addActionListener(this);
        box1.addItemListener(new vehiclesComboListener());

        bawah = new JPanel();
        bawah.add(box1);
        bawah.add(box2);
        bawah.add(label);
        bawah.add(text);
        bawah.add(button);
        bawah.setBackground(Color.black);

        contentPane = new JPanel(new BorderLayout());
        contentPane.add(tengah, BorderLayout.CENTER);
        contentPane.add(bawah, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setVisible(true);
    }

    public void printMap() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println("");
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            boolean availability = false;
            String vehicle = box1.getSelectedItem().toString();
            String arah1 = box2.getSelectedItem().toString();
            int step = 0;
            try {
                step = Integer.parseInt(text.getText());

                if (arah1.equalsIgnoreCase("up")) {

                    for (int a = 0; a < y; a++) {
                        for (int b = 0; b < x; b++) {
                            if (data[a][b] != '0' && data[a][b] == vehicle.charAt(0)
                                    && data[a - 1][b] == '0') {
                                boolean check = true;
                                for (int c = 1; c <= step; c++) {
                                    if (data[a - c][b] != '0') {
                                        check = false;
                                        break;
                                    }
                                }
                                if (check == true) {
                                    System.out.println("TRUE");
                                    data[a - step][b] = vehicle.charAt(0);
                                    data[a][b] = '0';
                                    checkUp(a + 1, b, vehicle.charAt(0), step);
                                    availability = true;
                                    break;
                                } else {
                                    System.out.println("FALSE");
                                }
                            }
                        }
                        if (availability) {
                            break;
                        }
                    }
                }
                if (arah1.equalsIgnoreCase("down")) {
                    for (int a = y - 1; a >= 0; a--) {
                        for (int b = x - 1; b >= 0; b--) {
                            if (data[a][b] != '0' && data[a][b] == vehicle.charAt(0)
                                    && data[a + 1][b] == '0') {
                                boolean check = true;
                                for (int c = 1; c <= step; c++) {
                                    if (data[a + c][b] != '0') {
                                        check = false;
                                        break;
                                    }
                                }
                                if (check == true) {
                                    System.out.println("TRUE");
                                    data[a + step][b] = vehicle.charAt(0);
                                    data[a][b] = '0';
                                    for (int i = 0; i < y; i++) {
                                        for (int j = 0; j < x; j++) {
                                            System.out.print(data[i][j] + " ");
                                        }
                                        System.out.println("");
                                    }
                                    System.out.println("-----------");
                                    checkDown(a - 1, b, vehicle.charAt(0), step);
                                    availability = true;
                                    break;
                                } else {
                                    System.out.println("FALSE");
                                }
                            }
                        }
                        if (availability) {
                            break;
                        }
                    }
                }
                if (arah1.equalsIgnoreCase("left")) {
                    for (int a = 0; a < y; a++) {
                        for (int b = 0; b < x; b++) {
                            if (data[a][b] != '0' && data[a][b] == vehicle.charAt(0)
                                    && data[a][b - step] == '0') {
                                boolean check = true;
                                for (int c = 1; c <= step; c++) {
                                    if (data[a][b - c] != '0') {
                                        check = false;
                                        break;
                                    }
                                }
                                if (check == true) {
                                    System.out.println("TRUE");
                                    data[a][b - step] = vehicle.charAt(0);
                                    data[a][b] = '0';
                                    checkLeft(a, b + 1, vehicle.charAt(0), step);
                                    availability = true;
                                    break;
                                } else {
                                    System.out.println("FALSE");
                                }
                            }
                        }
                        if (availability) {
                            break;
                        }
                    }
                }

                if (arah1.equalsIgnoreCase("right")) {
                    for (int a = y - 1; a >= 0; a--) {
                        for (int b = x - 1; b >= 0; b--) {
                            if (data[a][b] != '0' && data[a][b] == vehicle.charAt(0)
                                    && data[a][b + step] == '0') {
                                boolean check = true;
                                for (int c = 1; c <= step; c++) {
                                    if (data[a][b + c] != '0') {
                                        check = false;
                                        break;
                                    }
                                }
                                if (check == true) {
                                    System.out.println("TRUE");
                                    data[a][b + step] = vehicle.charAt(0);
                                    data[a][b] = '0';
                                    checkRight(a, b - 1, vehicle.charAt(0), step);
                                    availability = true;
                                    break;
                                } else {
                                    System.out.println("FALSE");
                                }
                            }
                        }
                        if (availability) {
                            break;
                        }
                    }
                }
                printMap();
                buildLayout();

                setContentPane(contentPane);
                getContentPane().setVisible(false);
                getContentPane().setVisible(true);
                System.out.println(vehicle + " " + arah1 + " " + step + " " + availability);

                if (step > 0 && availability) {
                    System.out.println("doing Something");
                } else {
                    System.out.println("Illegal move");
                }
            } catch (Exception ex) {
                step = 0;
            }
        }
    }

    public void checkUp(int a, int b, char check, int step) {
        if (data[a][b] == check) {
            data[a - step][b] = check;
            data[a][b] = '0';
            if (a != x - 1) {
                checkUp(a + 1, b, check, step);
            }
        }
    }

    public void checkDown(int a, int b, char check, int step) {
        if (data[a][b] == check) {
            data[a + step][b] = check;
            data[a][b] = '0';
            if (a != 0) {
                checkDown(a - 1, b, check, step);
            }
        }
    }

    public void checkLeft(int a, int b, char check, int step) {
        if (data[a][b] == check) {
            data[a][b - step] = check;
            data[a][b] = '0';
            checkLeft(a, b + 1, check, step);
        }
    }

    public void checkRight(int a, int b, char check, int step) {
        if (data[a][b] == check) {
            data[a][b + step] = check;
            data[a][b] = '0';
            if (b != 0) {
                checkRight(a, b - 1, check, step);
            }
        }
    }

        public void giveDefaultDirection() {
        boolean exit = false;
        System.out.println("temp" + coordinate[0]);
        for (int a = 0; a < x; a++) {
            for (int b = 0; b < y; b++) {
                if (data[a][b] == coordinate[0]) {
                    if (data[a][b + 1] == coordinate[0]) {
                        box2.addItem("left");
                        box2.addItem("right");
                    } else if (data[a + 1][b] == coordinate[0]) {
                        box2.addItem("up");
                        box2.addItem("down");
                    } else {
                        box2.addItem("up");
                        box2.addItem("down");
                        box2.addItem("left");
                        box2.addItem("right");
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

    private class vehiclesComboListener implements ItemListener {

        boolean quit;

        public void itemStateChanged(ItemEvent e) {
            quit = false;
            String vehicle = box1.getSelectedItem().toString();

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (data[i][j] == vehicle.charAt(0)) {
                        if (i == y - 1) {
                            if (data[i][j + 1] == vehicle.charAt(0)) {
                                box2.removeAllItems();
                                box2.addItem("left");
                                box2.addItem("right");
                                quit = true;
                                break;
                            } else {
                                box2.removeAllItems();
                                box2.addItem("up");
                                box2.addItem("down");
                                box2.addItem("left");
                                box2.addItem("right");
                                quit = true;
                                break;
                            }
                        } else if (data[i][j + 1] == vehicle.charAt(0)) {
                            box2.removeAllItems();
                            box2.addItem("left");
                            box2.addItem("right");
                            quit = true;
                            break;

                        } else if (data[i + 1][j] == vehicle.charAt(0)) {
                            box2.removeAllItems();
                            box2.addItem("up");
                            box2.addItem("down");
                            quit = true;
                            break;

                        } else {
                            box2.removeAllItems();
                            box2.addItem("up");
                            box2.addItem("down");
                            box2.addItem("left");
                            box2.addItem("right");
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

    public static void main(String[] args) {
        RUSH x = new RUSH();

        x.buildLayout();


    }
}
