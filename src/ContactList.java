
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author UserXP
 */
public class ContactList implements ActionListener {

    JFrame frame;
    JPanel contentPane, mainPanel, actionPanel, actionTopPanel, actionBottomPanel,
            titlePanel;
    JButton okButton, cancelButton, addToListButton;
    JTextField inputField;
    JTextArea contentArea;
    JLabel titleLabel;
    String content = "";

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source != null && source == addToListButton) {
            content += inputField.getText() + "\n";
            contentArea.setText(content);
            inputField.setText("");
        }
    }

    public void registerEventListener() {
        addToListButton.addActionListener(this);
    }

    public void initialization() {
        contentPane = new JPanel(new BorderLayout());
        mainPanel = new JPanel(new BorderLayout(5, 5));
        actionPanel = new JPanel(new GridLayout(2, 1));
        actionTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actionBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        okButton = new JButton("ok");
        cancelButton = new JButton("cancel");
        addToListButton = new JButton("add to list");
        inputField = new JTextField(25);
        contentArea = new JTextArea(5, 6);
        titleLabel = new JLabel("customer List");
    }

    public void buildGUI() {
        initialization();
        buildLayout();
        registerEventListener();

        frame = new JFrame("customer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(frame);
        //contentPane dimasukan ke frame
        frame.setContentPane(contentPane);
        //show frame
        frame.setVisible(true);
    }

    public void buildLayout() {
        titlePanel.add(titleLabel);

        actionTopPanel.add(inputField);
        actionTopPanel.add(addToListButton);

        actionBottomPanel.add(okButton);
        actionBottomPanel.add(cancelButton);

        actionPanel.add(actionTopPanel);
        actionPanel.add(actionBottomPanel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentArea, BorderLayout.CENTER);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);

        contentPane.add(mainPanel, BorderLayout.CENTER);

    }

    public ContactList() {
        buildGUI();
    }

    public static void main(String[] args) {
        ContactList cl = new ContactList();
    }
}
