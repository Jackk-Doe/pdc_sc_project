/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.ThiefJob;
import characters.player.WarriorJob;
import game.GameModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author sengthavongphilavong
 */
public class OuterPanel extends JPanel{
    
    private GameModel gameModel;
    
    private GameStartPanel gameStartPanel;
    private GameStartPanelCreateCharacter gameStartPanelCreateCharacter;
    
    public InnerPanel innerPanel;
    
    
    public OuterPanel(GameModel model) {
        
        this.gameModel = model;
        
        setLayout(null);
        
        setBackground(Color.GRAY);
        
//        innerPanel = new InnerPanel(gameModel);
        gameStartPanel = new GameStartPanel();
        gameStartPanelCreateCharacter = new GameStartPanelCreateCharacter();
        
        gameStartPanel.getNewGameButton().addActionListener(new GameStartButtonListener());
        gameStartPanel.getLoadGameButton().addActionListener(new GameStartButtonListener());
        
        gameStartPanelCreateCharacter.getPickWarriorButton().addActionListener(new GameStartButtonListener());
        gameStartPanelCreateCharacter.getPickThiefButton().addActionListener(new GameStartButtonListener());
        gameStartPanelCreateCharacter.getTextField().getDocument().addDocumentListener(new TextFieldDocumentListener());
        
//        gameStartPanel.setSize(680, 490);
        gameStartPanel.setLocation(25, 20);
//        
        add(gameStartPanel);
        
        JLabel label = new JLabel("Nintendo GAME(OLD)BOY");
        label.setFont(new Font("Helvetica", Font.BOLD, 30));
        label.setBackground(Color.BLUE);
        label.setSize(460, 50);
        label.setLocation(175, 505);
        add(label);
        
        setPreferredSize(new Dimension(730, 550));
//        setSize(730, 500);
    }
    
    // Change from GameStartPanel to GameStartPanelCreateCharacter
    private void changeToCreateNewCharacPanel() {
        this.remove(gameStartPanel);
        this.add(gameStartPanelCreateCharacter);
        gameStartPanelCreateCharacter.setLocation(25, 20);
        this.revalidate();
        this.repaint();
    }
    
    // Create a new Character through the GameStartPanelCreateCharacter Panel
    private void createPlayerCharacThenChangeToMapPanel(String classInput, String name) {
        
        String pickedName = name;
        
        // If forget to input Character's name
        // Set name to "The Forgotten"
        if (pickedName.equalsIgnoreCase("")) {
            pickedName = "The Forgotten";
        }
        
        if (classInput.equalsIgnoreCase("W")) {
            this.gameModel.setPlayer(new WarriorJob(pickedName));
        }
        if (classInput.equalsIgnoreCase("T")) {
            this.gameModel.setPlayer(new ThiefJob(pickedName));
        }
        
        // Change to InnerPanel
        this.remove(gameStartPanelCreateCharacter);
        
        // Define innerPanel after created Character,
        // Result: Prevent NullPointer
        innerPanel = new InnerPanel(gameModel);
        this.add(innerPanel);
        innerPanel.setLocation(25, 20);
        
        // Add text to ActionListPanel
        innerPanel.actionListPanel.addTextToList("Welcome to Dungeon "+pickedName);
        
        // Update Map
        // After Player created character
        innerPanel.innerPanelMap.updateMap();
        
        this.revalidate();
        this.repaint();
    }
    
    // Check if JTextField is empty 
    // If empty, disable buttons
    private void checkCreateCharacPanelTextField() {
        if (gameStartPanelCreateCharacter.getTextField().getText().equals("")) {
            gameStartPanelCreateCharacter.getPickWarriorButton().setEnabled(false);
            gameStartPanelCreateCharacter.getPickThiefButton().setEnabled(false);
        }
        else {
            gameStartPanelCreateCharacter.getPickWarriorButton().setEnabled(true);
            gameStartPanelCreateCharacter.getPickThiefButton().setEnabled(true);
        }
    }
    
    private class TextFieldDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            checkCreateCharacPanelTextField();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            checkCreateCharacPanelTextField();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkCreateCharacPanelTextField();
        }
        
    }
    
    private class GameStartButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            
            if (event.getSource() == gameStartPanel.getNewGameButton()) {
                changeToCreateNewCharacPanel();
            }
            
            if (event.getSource() == gameStartPanelCreateCharacter.getPickWarriorButton()) {
                String name = gameStartPanelCreateCharacter.getTextField().getText().trim();
                createPlayerCharacThenChangeToMapPanel("W", name);
            }
            
            if (event.getSource() == gameStartPanelCreateCharacter.getPickThiefButton()) {
                String name = gameStartPanelCreateCharacter.getTextField().getText().trim();
                createPlayerCharacThenChangeToMapPanel("T", name);
            }
        }
        
    }
    
}
