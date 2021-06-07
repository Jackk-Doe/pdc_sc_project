/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author sengthavongphilavong
 */
public class GameStartInstructionPanel extends JPanel{
    
    private JLabel label;
    private JList<String> list;
    private JScrollPane scrollPane;
    private JButton nextButton, startButton;

    private BufferedReader br;
    
    private String[] texts;
    
    public GameStartInstructionPanel() {
        
        setLayout(null);
        
        label = new JLabel("Game Instruction");
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        label.setForeground(Color.BLACK);
        label.setSize(460, 100);
        label.setLocation(180, 20);
        add(label);
        
        texts = new String[10];
        
        try {
            br = new BufferedReader(new FileReader("./IOresources/instruction.txt"));
            String line = null;
            int count = 0;
            
            while ((line= br.readLine()) != null) {
                texts[count] = line;
                count++;
            }
            
            br.close();
        }
        catch (IOException e) {
            System.out.println("Reading Error...");
        }
        
        list = new JList<>(texts);
        scrollPane = new JScrollPane(list, 
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(620, 200);
        scrollPane.setLocation(30, 140);
        add(scrollPane);
        
        nextButton = new JButton("NEXT");
        nextButton.setSize(160, 60);
        nextButton.setLocation(160, 360);
        add(nextButton);
        
        startButton = new JButton("START");
        startButton.setSize(160, 60);
        startButton.setLocation(360, 360);
        add(startButton);
        
        setSize(680, 490);
        
    }
    
    public void readSecondText() {
        
        texts = new String[33];
        int count = 0;
        
        try {
            br = new BufferedReader(new FileReader("./IOresources/gamemechanic.txt"));
            String line = null;
            
            while ((line= br.readLine()) != null) {
                texts[count] = line;
                count++;
            }
            
            br.close();
        }
        catch (IOException e) {
            System.out.println("Reading Error...");
        }
        
        list.setListData(texts);
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getStartButton() {
        return startButton;
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Testing");
        frame.getContentPane().add(new GameStartInstructionPanel());
        frame.setSize(680, 490);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
