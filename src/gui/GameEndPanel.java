/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.PlayerCharacter;
import java.awt.Color;
import java.awt.Font;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class used for  both GAME OVER & GAME CLEAR GUI, based on income parameter
 * 
 * The Buttons Logic Codes are hold with OuterPanel
 * 
 * This Class is located in OuterPanel
 */
public class GameEndPanel extends JPanel{
    
    private JLabel label;
    private JButton saveButton, endButton;
    
    public GameEndPanel(String inString) {
        
        setLayout(null);
        
        label = new JLabel(inString);
        label.setFont(new Font("Arial", Font.BOLD, 60));
        label.setForeground(Color.BLACK);
        label.setSize(460, 185);
        label.setLocation(150, 40);
        add(label);
        
        saveButton = new JButton("Save Character");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 20));
        saveButton.setSize(180, 90);
        saveButton.setLocation(250, 250);
        add(saveButton);
        
        endButton = new JButton("Game End");
        endButton.setFont(new Font("Arial", Font.PLAIN, 20));
        endButton.setSize(180, 90);
        endButton.setLocation(250, 360);
        add(endButton);
        
        setSize(680, 490);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getEndButton() {
        return endButton;
    }
    
}
