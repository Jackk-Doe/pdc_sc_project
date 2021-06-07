/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.PlayerCharacter;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sengthavongphilavong
 */
public class GameStartPanelCreateCharacter extends JPanel {
    
    private JLabel nameLabel, classLabel;
    private JTextField textField;
    private JButton pickWarriorButton, pickThiefButton;
    
    public GameStartPanelCreateCharacter() {
        
        setLayout(null);
        
        nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        nameLabel.setSize(180, 30);
        nameLabel.setLocation(310, 50);
        
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setSize(160, 50);
        textField.setLocation(275, 100);
        
        classLabel = new JLabel("Class");
        classLabel.setFont(new Font("Arial", Font.BOLD, 30));
        classLabel.setSize(180, 30);
        classLabel.setLocation(310, 210);
        
        pickWarriorButton = new JButton("WARRIOR");
        pickWarriorButton.setSize(150, 70);
        pickWarriorButton.setLocation(190, 260);
        
        pickThiefButton = new JButton("THIEF");
        pickThiefButton.setSize(150, 70);
        pickThiefButton.setLocation(370, 260);
        
        add(nameLabel);
        add(textField);
        add(classLabel);
        add(pickWarriorButton);
        add(pickThiefButton);
        
        setSize(680, 490);
    }

    public JTextField getTextField() {
        return textField;
    }
    
    public JButton getPickWarriorButton() {
        return pickWarriorButton;
    }

    public JButton getPickThiefButton() {
        return pickThiefButton;
    }
    
}
