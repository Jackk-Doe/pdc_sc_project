/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 */
public class ButtonPanel extends JPanel{
    
    public JButton upButton, downButton, leftButton, rightButton,
            aButton, bButton, startButton, selectButton;

    public ButtonPanel() {
        
        setLayout(null);
        
        upButton = new JButton("UP");
        upButton.setSize(70, 70);
        upButton.setLocation(90, 40);
        
        downButton = new JButton("DOWN");
        downButton.setSize(70, 70);
        downButton.setLocation(90, 180);
        
        leftButton = new JButton("LEFT");
        leftButton.setSize(70, 70);
        leftButton.setLocation(160, 110);
        
        rightButton = new JButton("RIGHT");
        rightButton.setSize(70, 70);
        rightButton.setLocation(20, 110);
        
        bButton = new JButton("B");
        bButton.setSize(80, 80);
        bButton.setLocation(470, 140);
        
        aButton = new JButton("A");
        aButton.setSize(80, 80);
        aButton.setLocation(550, 60);
        
        selectButton = new JButton("SELECT");
        selectButton.setSize(110, 40);
        selectButton.setLocation(240, 270);
        
        startButton = new JButton("Start");
        startButton.setSize(110, 40);
        startButton.setLocation(350, 270);
        
        add(upButton);
        add(downButton);
        add(leftButton);
        add(rightButton);
        add(bButton);
        add(aButton);
        add(selectButton);
        add(startButton);
        
        setPreferredSize(new Dimension(670, 330));
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.getContentPane().add(new ButtonPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
