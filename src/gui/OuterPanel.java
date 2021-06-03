/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.WarriorJob;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 */
public class OuterPanel extends JPanel{
    
    public JPanel innerPanel;

    public OuterPanel() {
        
        setBackground(Color.GRAY);
        
        innerPanel = new InnerPanel();
        
        add(innerPanel);
        
        JLabel label = new JLabel("Nintendo GAME(OLD)BOY");
        label.setFont(new Font("Helvetica", Font.BOLD, 30));
        label.setBackground(Color.BLUE);
        add(label);
        
        setPreferredSize(new Dimension(730, 550));
//        setSize(730, 500);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        OuterPanel outerPanel = new OuterPanel();
        frame.getContentPane().add(outerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setSize(680, 500);
        frame.setVisible(true);
    }
}
