
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UserXP
 */
public class SimpleListener implements ActionListener{
    private JFrame frame;
    private JPanel contentPane;
    private JButton tesButton, tes2Button;


    public void actionPerformed(ActionEvent e) {
Object source = e.getSource();
if(source != null && source == tesButton){
    JOptionPane.showMessageDialog(null, "Hola");
}
else if(source != null && source == tes2Button){
    JOptionPane.showMessageDialog(null, "woiiii!!");
}
    }

    public SimpleListener(){
        tesButton = new JButton("Test");
        tes2Button = new JButton("Test Juga");
        contentPane = new JPanel(new BorderLayout(5, 5));
        contentPane.add(tesButton, BorderLayout.CENTER);
        contentPane.add(new JPanel(),  BorderLayout.CENTER);
        contentPane.add(tes2Button, BorderLayout.CENTER);
        contentPane.add(new JPanel(), BorderLayout.CENTER);
        contentPane.add(new JPanel(), BorderLayout.CENTER);

        tesButton.addActionListener(this);
        tes2Button.addActionListener(this);

        frame = new JFrame();
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(frame);


    }

}
