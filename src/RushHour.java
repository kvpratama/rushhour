/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package oop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Julius
 */
public class RushHour extends JFrame{
    int table = 5;
    int goal = 0;
    int count = 0;
    int x1 = 0, x2 = 0 , y1 = 0 , y2 = 0, x1coor = 0 , x2coor = 0 , y1coor = 0 , y2coor = 0;
    char [][] mobil = new char[10][10];
    char [] Char = new char[20];
    String [] sChars;
    private int n = 5;
    private JFrame peta;
    private JPanel contentPane, mainPanel, garagePanel, actionPanel;
    private JComboBox mobilCombo, panelCombo;
    private JLabel stepLabel;
    private JTextField stepField;
    private JButton doButton;
    private String[] direction = {"up", "down", "left", "right"};
    private JButton tombol;
    private List listOfVehicles;
    private Color warnaTombol;
    private EventHandler handler = new EventHandler();
    private Color[] warna = {Color.BLUE, Color.CYAN, Color.GREEN, Color.LIGHT_GRAY,
    Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

        public static void main(String[] args) {
        int x = 0, y = 0, length, CheckChar, check= 0, x1=0,x2=0,y1=0,y2=0;
        RushHour a = new RushHour();
        JFileChooser fc = new JFileChooser(".");
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fc.getSelectedFile()));
                String s;
                while (null != (s = reader.readLine())) {
                    length = s.length();
                    x = 0;
                    for(int i = 0 ; i < length ; i++){
                        if( i % 2 == 0){
                            a.mobil[x][y] = s.charAt(i);
                            System.out.print(a.mobil[x][y] + " ");
                            x++;
                        }
                    }
                    x = 0;
                    for(int i = 0 ; i < length ; i++){
                        if( i % 2 == 0){
                            System.out.print(a.mobil[x][y] + " ");
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

        a.count = 0;
        for(int i = 0 ; i < x ; i++){
            for(int j = 0 ; j < y ; j++){
                if(a.count == 0){
                    // if(game.car[i][j] != '0'){
                        a.Char[a.count] = a.mobil[i][j];
                        a.count++;
                    // }
                } else {
                    CheckChar = 0;
                    for(int k = 0 ; k < a.count ; k++){
                        if(a.mobil[i][j] != a.Char[k]){
                            CheckChar++;
                        }
                    }
                    if(CheckChar == a.count){
                        // if(game.car[i][j] != '0'){
                            a.Char[a.count] = a.mobil[i][j];
                            a.count++;
                        // }
                    }
                }
            }
        }

        for(int i = 0 ; i < a.count ; i++){
            x1 = 0; x2 = 0; y1 = 0; y2 = 0;
            check = 0;
            if(a.Char[i] != '0'){
                System.out.print(a.Char[i] + ":");

            for(int j = 0 ; j < x ; j++){
                for(int k = 0 ; k < y ; k++){
                        if(a.mobil[j][k] == a.Char[i]){
                            if(check == 0){
                                x1 = j;
                                y1 = k;
                                check++;
                            }  else if( check == 1) {
                                x2 = j;
                                y2 = k;
                            }
                            System.out.print("[" + j + "," + k + "]");
                        }
                }
            }
                System.out.print(":");
                if(x2 == 0 && y2 == 0){
                    System.out.println("HV");
                } else if( y1 == y2){
                    System.out.println("V");
                } else {
                    System.out.println("H");
                }
            }
        }

        a.makeGrassAndFinish();
        a.cetakMap();
        a.TampilanMap();
    }

    private void charToString(){
        int temp = 0;
        sChars = new String[count-1];
        for(int i = 0 ; i < count ; i++){
            if(Char[i] != '0'){
                sChars[temp] = Character.toString(Char[i]);
                temp++;
            }
        }
    }

    private int checkMobilKanan(int step, int x, int y){
        int check = 0;
        for(int i = 1 ; i <= step ; i++){
            if(mobil[x+i][y] == '0' || mobil[x+i][y] == '*')
                check++;
        }
        return check;
    }

    private int checkMobilKiri(int step, int x, int y){
        int check = 0;
        for(int i = 1 ; i <= step ; i++){
            if(mobil[x-i][y] == '0' || mobil[x-i][y] == '*')
                check++;
        }
        return check;
    }

    private int checkMobilAtas(int step, int x, int y){
        int check = 0;
        for(int i = 1 ; i <= step ; i++){
            if(mobil[x][y-i] == '0' || mobil[x][y-i] == '*')
                check++;
        }
        return check;
    }

    private int checkMobilBawah(int step, int x, int y){
        int check = 0;
        for(int i = 1 ; i <= step ; i++){
            if(mobil[x][y+i] == '0' || mobil[x][y+i] == '*')
                check++;
        }
        return check;
    }

    private class EventHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            x1coor = -1;
            x2coor = -1;
            y1coor = -1;
            y2coor = -1;
            String myVehicle = "";
            String myDirection = "";
            int myStep = -1;
            Object source = e.getSource();
            if(source != null && source == doButton){
                myVehicle = mobilCombo.getSelectedItem().toString();
                myDirection = panelCombo.getSelectedItem().toString();
                myStep = -1;
                try{
                    myStep = Integer.parseInt(stepField.getText());
                } catch(Exception ex){
                    myStep = 0;
                }
            }
            if(myStep < 7 && myStep > 0){
                if(myDirection.equals("up"))
                    checkAtas(myVehicle,myDirection,myStep);
                if(myDirection.equals("down"))
                    checkBawah(myVehicle,myDirection,myStep);
                if(myDirection.equals("left"))
                    checkKiri(myVehicle,myDirection,myStep);
                if(myDirection.equals("right"))
                    checkRight(myVehicle,myDirection,myStep);
            } else {
                System.out.println("Invalid Move!");
            }
        }
    }

    public void checkAtas(String cars, String direction, int step){
        int temp = 0;
        int check = 0;
        for(int i = 0 ; i < count ; i++){
            if(cars.charAt(0) == Char[i]){
                for(int j = 0 ; j < 7 ; j++){
                    for(int k = 0 ; k < 5 ; k++){
                        if(temp == 0){
                            if(mobil[j][k] == Char[i]){
                                x1coor = j;
                                y1coor = k;
                                temp++;
                            }
                        } else {
                            if(mobil[j][k] == Char[i]){
                                x2coor = j;
                                y2coor = k;
                                temp = 0;
                            }
                        }
                    }
                }
            }
        }
        if(x2coor != -1){
            if(y2coor - y1coor == 0){
                System.out.println("Invalid Move!");
            } else {
                    if(y1coor - step <= -1)
                        System.out.println("Invalid Move!");
                    else{
                        check = checkMobilAtas(step,x1coor,y1coor);
                    if(check == step){
                        if(mobil[x1coor][y1coor-step] != '0')
                            System.out.println("Invalid Move!");
                        else {
                            mobil[x1coor][y1coor-step] = cars.charAt(0);
                            mobil[x1coor][y2coor-step] = cars.charAt(0);
                            for(int i = 0 ; i < step ; i++){
                                mobil[x1coor][y2coor-i] = '0';
                            }
                            mengubahMap();
                            TampilanMap();
                        }
                    } else {
                        System.out.println("Invalid Move!");
                    }
                }
            }
        } else {
                if(y1coor - step <= -1)
                    System.out.println("Invalid Move!");
                else{
                    check = checkMobilAtas(step,x1coor,y1coor);
                    if(check == step){
                        if(mobil[x1coor][y1coor-step] != '0')
                            System.out.println("Invalid Move!");
                        else{
                                mobil[x1coor][y1coor-step] = cars.charAt(0);
                                for(int i = 0 ; i < step ; i++){
                                    mobil[x1coor][y1coor-i] = '0';
                                }
                                mengubahMap();
                                TampilanMap();
                                }
                            } else {
                                System.out.println("Invalid Move!");
                            }
                }
            }
        }

    public void checkBawah(String cars, String direction, int step){
        int temp = 0;
        int check = 0;
        for(int i = 0 ; i < count ; i++){
            if(cars.charAt(0) == Char[i]){
                for(int j = 0 ; j < 7 ; j++){
                    for(int k = 0 ; k < 5 ; k++){
                        if(temp == 0){
                            if(mobil[j][k] == Char[i]){
                                x1coor = j;
                                y1coor = k;
                                temp++;
                            }
                        } else {
                            if(mobil[j][k] == Char[i]){
                                x2coor = j;
                                y2coor = k;
                                temp = 0;
                            }
                        }
                    }
                }
            }
        }
        if(x2coor != -1){
            if(y2coor - y1coor == 0){
                System.out.println("Invalid Move!");
            } else {
                    if(y2coor + step >= 5)
                        System.out.println("Invalid Move!");
                    else {
                        check = checkMobilBawah(step,x1coor,y2coor);
                         if(check == step){
                        if(mobil[x2coor][y2coor+step] != '0')
                        System.out.println("Invalid Move!");
                    else{
                            mobil[x1coor][y2coor+step] = cars.charAt(0);
                            mobil[x1coor][y1coor+step] = cars.charAt(0);
                            for(int i = 0 ; i < step ; i++){
                                mobil[x1coor][y1coor+i] = '0';
                            }
                            mengubahMap();
                            TampilanMap();
                    }
                } else {
                    System.out.println("Invalid Move!");
                }
                    }
            }
        } else {
                if(y1coor + step >= 5)
                    System.out.println("Invalid Move!");
                else {
                    check = checkMobilBawah(step,x1coor,y1coor);
                if(check == step){
                if(mobil[x1coor][y1coor+step] != '0')
                    System.out.println("Invalid Move!");
                else{
                    mobil[x1coor][y1coor+step] = cars.charAt(0);
                    for(int i = 0 ; i < step ; i++){
                        mobil[x1coor][y1coor+i] = '0';
                    }
                    mengubahMap();
                    TampilanMap();
                }
            }
                }
        }
    }

    public void checkRight(String cars, String direction, int step){
        int temp = 0;
        int check = 0;
        for(int i = 0 ; i < count ; i++){
            if(cars.charAt(0) == Char[i]){
                for(int j = 0 ; j < 7 ; j++){
                    for(int k = 0 ; k < 5 ; k++){
                        if(temp == 0){
                            if(mobil[j][k] == Char[i]){
                                x1coor = j;
                                y1coor = k;
                                temp++;
                            }
                        } else {
                            if(mobil[j][k] == Char[i]){
                                x2coor = j;
                                y2coor = k;
                                temp = 0;
                            }
                        }
                    }
                }
            }
        }
        if(x2coor != -1){
            if(x2coor - x1coor == 0){
                System.out.println("Invalid Move!");
            } else {
                check = checkMobilKanan(step,x2coor,y1coor);
                            if(check == step){
                    if(mobil[x2coor+step][y1coor] == '*'){
                        mobil[x2coor+step][y1coor] = cars.charAt(0);
                        mobil[x1coor+step][y1coor] = cars.charAt(0);
                        for(int i = 0 ; i < step ; i++){
                            mobil[x1coor+i][y1coor] = '0';
                        }
                        mengubahMap();
                        TampilanMap();
                        if(mobil[5][2] != '*' && mobil[6][2] != '*'){
                                peta.setVisible(false);
                                JOptionPane.showMessageDialog(null, "You Won!");
                        }
                    } else {
                        if(x2coor + step >= 5)
                            System.out.println("Invalid Move!");
                        else {
                                if(mobil[x2coor + step][y2coor] != '0'){
                                System.out.println("Invalid Move!");
                            }
                            else{
                                mobil[x2coor+step][y1coor] = cars.charAt(0);
                                mobil[x1coor+step][y1coor] = cars.charAt(0);
                                for(int i = 0 ; i < step ; i++){
                                    mobil[x1coor+i][y1coor] = '0';
                                }
                                mengubahMap();
                                TampilanMap();

                            }
                        }
                    }
                    } else {
                        System.out.println("Invalid Move!");
                    }
               }

        } else {
                if(x1coor + step >= 5)
                    System.out.println("Invalid Move!");
                else {
                    check = checkMobilKanan(step,x1coor,y1coor);
                if(check == step){
                if(mobil[x1coor + step][y1coor] != '0')
                    System.out.println("Invalid Move!");
                else{
                    mobil[x1coor+step][y1coor] = cars.charAt(0);
                    for(int i = 0 ; i < step ; i++){
                        mobil[x1coor+i][y1coor] = '0';
                    }
                    mengubahMap();
                    TampilanMap();

                }
            } else {
                System.out.println("Invalid Move!");
            }
                }
        }
    }

    public void checkKiri(String cars, String direction, int step){
        int temp = 0;
        int check = 0;
        for(int i = 0 ; i < count ; i++){
            if(cars.charAt(0) == Char[i]){
                for(int j = 0 ; j < 7 ; j++){
                    for(int k = 0 ; k < 5 ; k++){
                        if(temp == 0){
                            if(mobil[j][k] == Char[i]){
                                x1coor = j;
                                y1coor = k;
                                temp++;
                            }
                        } else {
                            if(mobil[j][k] == Char[i]){
                                x2coor = j;
                                y2coor = k;
                                temp = 0;
                            }
                        }
                    }
                }
            }
        }
        if(x2coor != -1){
            if(x2coor - x1coor == 0){
                System.out.println("Invalid Move!");
            } else {
                    if(x1coor - step <= -1)
                        System.out.println("Invalid Move!");
                    else {
                        check = checkMobilKiri(step,x1coor,y1coor);
                        if(check == step){
                            if(mobil[x1coor - step][y1coor] != '0')
                                System.out.println("Invalid Move!");
                            else{
                                mobil[x1coor-step][y1coor] = cars.charAt(0);
                                mobil[x2coor-step][y1coor] = cars.charAt(0);
                                for(int i = 0 ; i < step ; i++){
                                    mobil[x2coor-i][y1coor] = '0';
                                }
                                fixFinish();
                                mengubahMap();
                                TampilanMap();
                            }
                        } else {
                            System.out.println("Invalid Move!");
                        }
                    }
            }
        } else {
                if(x1coor - step <= -1)
                    System.out.println("Invalid Move!");
                else {
                    check = checkMobilKiri(step,x1coor,y1coor);
                    if(check == step){
                        if(mobil[x1coor-step][y1coor] != '0')
                            System.out.println("Invalid Move!");
                        else{
                            mobil[x1coor-step][y1coor] = cars.charAt(0);
                            for(int i = 0 ; i < step ; i++){
                                mobil[x1coor-i][y1coor] = '0';
                            }
                            fixFinish();
                            mengubahMap();
                            TampilanMap();
                        }
                    } else {
                        System.out.println("Invalid Move!");
                    }
                }
        }
    }

    public void registerEventListener(){
        doButton.addActionListener(new EventHandler());
    }


    public void initializeItems(){
        charToString();
        mobilCombo = new JComboBox(sChars);
        panelCombo = new JComboBox(direction);
        stepLabel = new JLabel("Step:");
        stepField = new JTextField(2);
        doButton = new JButton("Do");
    }

    public void initializePanels(){
        mainPanel = new JPanel(new BorderLayout(5,5));
        garagePanel = new JPanel(new GridLayout(table,table+2));
        actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        contentPane = new JPanel(new BorderLayout());
    }

    public void initialization(){
        initializeItems();
        initializePanels();
    }

    public Color getColorOfVehicle(char x){
        char a = '0';
        for(int i = 0; i < count; i++){
            a = Char[i];
            if(a == x){
                return warna[i];
            }
        }
        return null;
    }

    private void fixFinish(){
        if(mobil[5][2] == '0')
            mobil[5][2] = '*';
        if(mobil[6][2] == '0')
            mobil[6][2] = '*';
    }

    private void makeGrassAndFinish(){
        for(int i = 0 ; i < 5 ; i++){
            for(int j = 5; j < 7 ; j++){

                if(i != 2){
                    mobil[j][i] = '@';
                } else {
                    mobil[j][i] = '*';
                }
            }
        }
    }

    private void mengubahMap(){
        peta.setVisible(false);
        initialization();
        JButton grassButton = null;
        for(int i =0; i< table; i++){
            for(int j=0; j<table+2; j++){
                    if(mobil[j][i] == '@'){
                        grassButton = new JButton("@");
                        grassButton.setBackground(new Color(30,30,30));
                        grassButton.setForeground(Color.WHITE);
                        grassButton.setEnabled(false);
                        garagePanel.add(grassButton);
                    } else if(mobil[j][i] == '*'){
                        grassButton = new JButton("*");
                        grassButton.setEnabled(false);
                        garagePanel.add(grassButton);
                    } else if(mobil[j][i] != '0'){
                        tombol = new JButton("" + String.valueOf(mobil[j][i]));
                        warnaTombol = getColorOfVehicle(mobil[j][i]);
                        if(warnaTombol != null){
                            tombol.setForeground(Color.BLACK);
                            tombol.setBackground(warnaTombol);
                            tombol.setEnabled(false);
                        }
                        garagePanel.add(tombol);
                    } else {
                        garagePanel.add(new JLabel());
                    }
            }
        }
        
        actionPanel.add(mobilCombo);
        actionPanel.add(panelCombo);
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

    private void cetakMap(){
        initialization();
        JButton grassButton = null;
        for(int i =0; i< table; i++){
            for(int j=0; j<table+2; j++){
                    if(mobil[j][i] == '@'){
                        grassButton = new JButton("@");
                        grassButton.setBackground(new Color(30,30,30));
                        grassButton.setForeground(Color.WHITE);
                        grassButton.setEnabled(false);
                        garagePanel.add(grassButton);
                    } else if(mobil[j][i] == '*'){
                        grassButton = new JButton("*");
                        grassButton.setEnabled(false);
                        garagePanel.add(grassButton);
                    } else if(mobil[j][i] != '0'){
                        tombol = new JButton("" + String.valueOf(mobil[j][i]));
                        warnaTombol = getColorOfVehicle(mobil[j][i]);
                        if(warnaTombol != null){
                            tombol.setForeground(Color.BLACK);
                            tombol.setBackground(warnaTombol);
                            tombol.setEnabled(false);
                        }
                        garagePanel.add(tombol);
                    } else {
                        garagePanel.add(new JLabel());
                    }
            }
        }

        actionPanel.add(mobilCombo);
        actionPanel.add(panelCombo);
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

    private void TampilanMap(){
        registerEventListener();
        peta = new JFrame("Rush Hour");
        peta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        peta.setSize(500,500);
        peta.setLocationRelativeTo(null);
        peta.setResizable(false);
        peta.setContentPane(contentPane);
        peta.setVisible(true);
    }



}