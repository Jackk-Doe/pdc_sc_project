/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 */
public class GameStartPanel extends JPanel{
    
    private JLabel label;
    private JButton newGameButton, loadGameButton;

    public GameStartPanel() {
        
        setLayout(null);
        
        label = new JLabel("Master of Dungeon v2.0");
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setForeground(Color.BLACK);
        label.setSize(460, 185);
        label.setLocation(120, 40);
        add(label);
        
        newGameButton = new JButton("NEW GAME");
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        newGameButton.setSize(180, 90);
        newGameButton.setLocation(250, 250);
        add(newGameButton);
        
        loadGameButton = new JButton("LOAD GAME");
        loadGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loadGameButton.setSize(180, 90);
        loadGameButton.setLocation(250, 360);
        add(loadGameButton);
        
//        setPreferredSize(new Dimension(680, 490));
        setSize(680, 490);
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getLoadGameButton() {
        return loadGameButton;
    }
    
}
