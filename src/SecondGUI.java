
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
public class SecondGUI extends JPanel {

    char[][] car = new char[10][10];

    @Override
    public void paintComponent(Graphics g) {
        for (int a = 0; a < 5; a++) {
            for (int b = 0; b < 5; b++) {
                
                if(car[a][b] != 0){
                    if(car[a][b] == 'a' || car[a][b]=='n'){
                        g.setColor(Color.BLACK);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'b' || car[a][b]=='o'){
                        g.setColor(Color.BLUE);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'c' || car[a][b]=='p'){
                        g.setColor(Color.CYAN);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'd' || car[a][b]=='q'){
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'e' || car[a][b]=='r'){
                        g.setColor(Color.GRAY);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'f' || car[a][b]=='s'){
                        g.setColor(Color.GREEN);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'g' || car[a][b]=='t'){
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'h' || car[a][b]=='u'){
                        g.setColor(Color.MAGENTA);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'i' || car[a][b]=='v'){
                        g.setColor(Color.ORANGE);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'j' || car[a][b]=='w'){
                        g.setColor(Color.PINK);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'k' || car[a][b]=='x'){
                        g.setColor(Color.RED);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'l' || car[a][b]=='y'){
                        g.setColor(Color.pink);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                    else if(car[a][b] == 'm' || car[a][b]=='z'){
                        g.setColor(Color.YELLOW);
                        g.fillRect(a*60, b*60, 60, 60);
                    }
                }


            }
        }
    }

    public void visual() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(this);
        frame.setSize(318, 339);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public SecondGUI() {
        JFileChooser fc = new JFileChooser(".");
        int returnVal = fc.showOpenDialog(null);
        int length, x = 0, y = 0;
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
        }
    }

    public static void main(String[] args) {
        SecondGUI secondGUI = new SecondGUI();
        secondGUI.visual();

    }
}
