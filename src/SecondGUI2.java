

import java.awt.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kevin
 */
public class SecondGUI2 extends JPanel {

    char[][] car = new char[10][10];
    int x = 0, y = 0;

    @Override
    public void paintComponent(Graphics h) {
        for (int a = 0; a < y; a++) {
            for (int b = 0; b < x; b++) {

                if(car[a][b] != 0){
                    if(car[a][b] == 'a' || car[a][b]=='n'){
                        h.setColor(Color.BLACK);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'b' || car[a][b]=='o'){
                        h.setColor(Color.BLUE);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'c' || car[a][b]=='p'){
                        h.setColor(Color.CYAN);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'd' || car[a][b]=='q'){
                        h.setColor(Color.DARK_GRAY);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'e' || car[a][b]=='r'){
                        h.setColor(Color.GRAY);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'f' || car[a][b]=='s'){
                        h.setColor(Color.GREEN);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'g' || car[a][b]=='t'){
                        h.setColor(Color.LIGHT_GRAY);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'h' || car[a][b]=='u'){
                        h.setColor(Color.MAGENTA);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'i' || car[a][b]=='v'){
                        h.setColor(Color.ORANGE);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'j' || car[a][b]=='w'){
                        h.setColor(Color.PINK);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'k' || car[a][b]=='x'){
                        h.setColor(Color.RED);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'l' || car[a][b]=='y'){
                        h.setColor(Color.pink);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'm' || car[a][b]=='z'){
                        h.setColor(Color.YELLOW);
                        h.fillRect(a*60, b*60, 60, 60);
                    }
                }


            }
        }
    }

    public void visual() {
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(this, BorderLayout.CENTER);
       
        frame.setSize((x*60)+16, (y*60)+38);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public SecondGUI2() {
        JFileChooser fc = new JFileChooser(".");
        int returnVal = fc.showOpenDialog(null);
        int length;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fc.getSelectedFile()));
                String s;
                while (null != (s = reader.readLine())) {
                    length = s.length();
                    x = 0;
                    for (int i = 0; i < length; i++) {
                        if (i % 2 == 0) {
                            car[x][y] = s.charAt(i);
                            x++;
                        }
                    }
                    x = 0;
                    for (int i = 0; i < length; i++) {
                        if (i % 2 == 0) {
                            System.out.print(car[x][y] + " ");
                            x++;
                        }
                    }
                    System.out.println();
                    y++;
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
            System.out.println("x = " + x + "y = " + y);
        }
    }

    public static void main(String[] args) {
        SecondGUI2 secondGUI = new SecondGUI2();
        secondGUI.visual();

    }
}
