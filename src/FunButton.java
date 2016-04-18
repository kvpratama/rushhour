
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author UserXP
 */
public class FunButton implements ActionListener {

    private JFrame frame;
    private JButton buttonNorth, buttonSouth, buttonEast, buttonWest, buttonCenter;
    private JPanel contentPane;
    private int random, count;
private boolean north, south, east, west, center , again = true;

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
while (again == true){
    random = (int)(Math.random()*4 + 1);
    if(count ==4){
            buttonNorth.setEnabled(true);
            buttonSouth.setEnabled(true);
            buttonEast.setEnabled(true);
            buttonWest.setEnabled(true);
            buttonCenter.setEnabled(true);

        }
        else if(random == 1 && center == true){
            buttonCenter.setEnabled(true);
            center = false;
            again = false;
            count++;
        }
//        else if(random == 2){
//            buttonNorth.setEnabled(true);
//        }
        else if(random == 2 && south == true){
            buttonSouth.setEnabled(true);
            south = false;
            again = false;
            count++;
        }
        else if(random == 3 && west == true){
            buttonWest.setEnabled(true);
            west = false;
            again = false;
            count++;

        }
        else if(random == 4 && east == true){
            buttonEast.setEnabled(true);
            east = false;
            again = false;
            count++;
        }
//        else {
//            random = (int)(Math.random()*4 + 1);
//        }
}
        


//        if (source != null && source == buttonNorth) {
//            System.out.println("n");
//            buttonCenter.setEnabled(true);
//        } else if (source != null && source == buttonSouth) {
//            System.out.println("s");
//            buttonEast.setEnabled(true);
//            buttonWest.setEnabled(true);
//        } else if (source != null && source == buttonEast) {
//            System.out.println("e");
//            buttonCenter.setEnabled(false);
//            buttonWest.setEnabled(false);
//            buttonEast.setEnabled(false);
//            buttonSouth.setEnabled(false);
//        } else if (source != null && source == buttonWest) {
//            System.out.println("w");
//            buttonCenter.setEnabled(false);
//            buttonWest.setEnabled(false);
//            buttonEast.setEnabled(false);
//            buttonSouth.setEnabled(false);
//        } else if (source != null && source == buttonCenter) {
//            System.out.println("c");
//            buttonSouth.setEnabled(true);
//        }
    }

    public void populateDefaultSettings() {
        buttonNorth.setEnabled(true);
        buttonSouth.setEnabled(false);
        buttonEast.setEnabled(false);
        buttonWest.setEnabled(false);
        buttonCenter.setEnabled(false);
    }
    
    public FunButton() {
        contentPane = new JPanel(new BorderLayout(5,5));

        buttonNorth = new JButton("Test");
        buttonSouth = new JButton("Test");
        buttonEast = new JButton("Test");
        buttonWest = new JButton("Test");
        buttonCenter = new JButton("Test");

        contentPane.add(buttonNorth, BorderLayout.NORTH);
        contentPane.add(buttonSouth, BorderLayout.SOUTH);
        contentPane.add(buttonEast, BorderLayout.EAST);
        contentPane.add(buttonWest, BorderLayout.WEST);
        contentPane.add(buttonCenter, BorderLayout.CENTER);

        buttonNorth.addActionListener(this);
        buttonSouth.addActionListener(this);
        buttonEast.addActionListener(this);
        buttonWest.addActionListener(this);
        buttonCenter.addActionListener(this);

        populateDefaultSettings();
        
        frame = new JFrame("Test Button");
        frame.setResizable(false);
        frame.setSize(200,120);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }catch (Exception e) {
                    e.printStackTrace();
                }
                FunButton funButton = new FunButton();
            }
        });
    }
}
