/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jacky Hoeny
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.*;

public class Read extends JFrame {

    int aCount, xLength, yLength;
    char[][] data = new char[100][100];
    char[] dataCoordinate = new char[100];

    public int Read() {
        JFileChooser fc = new JFileChooser(".");
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String[] input = new String[10];
            yLength = 0;
            int len = 0;
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fc.getSelectedFile()));
                String s;
                while (null != (s = reader.readLine())) {
                    len = s.length();
                    input[yLength] = s;
                    yLength++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != reader) {
                    try {
                        reader.close();
                    } catch (Exception ignored) {
                    }
                }
            }

            //untuk menghilangkan tanda koma, lalu dimasukkan kedalam array baru
            aCount = 0;
            xLength = 0;
            for (int i = 0; i < yLength; i++) {
                aCount = 0;
                for (int j = 0; j < len; j++) {
                    char ch = input[i].charAt(j);
                    if (ch == ',') {
                        continue;
                    } else {
                        data[i][aCount] = ch;
                        xLength++;
                    }
                    aCount++;
                }
            }
            xLength /= yLength;
            //untuk print peta tanpa tanda koma
            for (int i = 0; i < yLength; i++) {
                for (int j = 0; j < xLength; j++) {
                    System.out.print(data[i][j] + " ");
                }
                System.out.println("");
            }
            System.out.println("");
        }
        return yLength;
    }

    public void Coordinate() {
        //untuk print koordinat dari peta (menggabungkan huruf yang sama)
        aCount = 0;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (data[i][j] != '0' && aCount == 0) {
                    dataCoordinate[0] = data[i][j];
                    aCount++;
                } else if (data[i][j] != '0' && aCount > 0) {
                    int amount = 0;
                    for (int k = 0; k < aCount; k++) {
                        if (dataCoordinate[k] != data[i][j]) {
                            amount++;
                        }
                    }
                    if (amount == aCount) {
                        dataCoordinate[amount] = data[i][j];
                        aCount++;
                    }
                }
            }
        }

        //Coding untuk print koordinat xy
        for (int i = 0; i < aCount; i++) {
            System.out.print(dataCoordinate[i] + ": ");
            int total = 0;
            int[] x = new int[10], y = new int[10];
            //proses setiap koordinat dijalankan
            for (int j = 0; j < yLength; j++) {
                for (int k = 0; k < xLength; k++) {
                    if (dataCoordinate[i] == data[j][k]) {
                        if (total == 0) {
                            x[0] = j;
                            y[0] = k;
                            total++;
                        } else {
                            x[total] = j;
                            y[total] = k;
                        }
                        System.out.print("[" + j + "," + k + "]");
                    }
                }
            }
            //Untuk menentukan Horizontal/Vertical
            System.out.print(" : ");
            if (x[1] == 0 && y[1] == 0) {
                System.out.println("HV");
            } else if (y[0] == y[1]) {
                System.out.println("V");
            } else if (x[0] == x[1]) {
                System.out.println("H");
            }
        }
    }

    public void Model() {
//Create new Window
        setTitle("Rush Hour!");
        setSize(90 * xLength, 90 * yLength);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setLayout(new GridLayout(xLength, yLength));

//Create cars
        JButton[][] buttons = new JButton[yLength][xLength];
        getContentPane().setLayout(new GridLayout(yLength, xLength));
        getContentPane().setBackground(Color.WHITE);

        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                if (data[i][j] != '0') {
                    JButton button = new JButton(Character.toString(data[i][j]));
                    buttons[i][j] = button;
                    getContentPane().add(button);
                    if (data[i][j] == 'a' || data[i][j] == 'j'
                            || data[i][j] == 's') {
                        buttons[i][j].setBackground(Color.BLUE);
                    } else if (data[i][j] == 'b' || data[i][j] == 'k'
                            || data[i][j] == 't') {
                        buttons[i][j].setBackground(Color.CYAN);
                    } else if (data[i][j] == 'c' || data[i][j] == 'l'
                            || data[i][j] == 'u') {
                        buttons[i][j].setBackground(Color.PINK);
                    } else if (data[i][j] == 'd' || data[i][j] == 'm'
                            || data[i][j] == 'v') {
                        buttons[i][j].setBackground(Color.GRAY);
                    } else if (data[i][j] == 'e' || data[i][j] == 'n'
                            || data[i][j] == 'w') {
                        buttons[i][j].setBackground(Color.GREEN);
                    } else if (data[i][j] == 'f' || data[i][j] == 'o'
                            || data[i][j] == 'x') {
                        buttons[i][j].setBackground(Color.LIGHT_GRAY);
                    } else if (data[i][j] == 'g' || data[i][j] == 'p'
                            || data[i][j] == 'y') {
                        buttons[i][j].setBackground(Color.MAGENTA);
                    } else if (data[i][j] == 'h' || data[i][j] == 'q'
                            || data[i][j] == 'z') {
                        buttons[i][j].setBackground(Color.ORANGE);
                    } else if (data[i][j] == 'i' || data[i][j] == 'r') {
                        buttons[i][j].setBackground(Color.PINK);
                    }
                } else {
                    getContentPane().add(new JLabel());
                }
            }
        }
    }

    public static void main(String[] args) {
        Read r = new Read();
        r.Read();
        r.Coordinate();
        r.Model();
    }
}
