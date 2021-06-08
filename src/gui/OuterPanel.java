/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.ThiefJob;
import characters.player.WarriorJob;
import control.GameControl;
import game.GameModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import system.DBManager;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class carries All Other GUI Classes, except ButtonPanel (just one below GameView)
 * 
 * All Carrying GUI Class's Button ActionEvents are defined within this Class
 * (2nd-GameControler)
 * 
 * Help shorten GameView's within Method by defined most necessary inside this Class
 * 
 * Note: Change each GUI Class with remove(), add(), revalidate() & repaint() methods
 * 
 */
public class OuterPanel extends JPanel{
    
    public static final String TABLENAME = "SAVEDCHARACTER";
    
    private GameModel gameModel;
    
    private DBManager dBManager;
    
    private GameStartPanel gameStartPanel;
    private GameStartLoadSavePanel gameStartLoadSavePanel;
    private GameStartPanelCreateCharacter gameStartPanelCreateCharacter;
    private GameStartInstructionPanel gameStartInstructionPanel;
    
    public InnerPanel innerPanel;
    
    private GameEndPanel gameEndPanel;
    
    
    public OuterPanel(GameModel model) {
        
        this.gameModel = model;
        
        setLayout(null);
        
        setBackground(Color.GRAY);
        
        gameStartPanel = new GameStartPanel();
        gameStartPanel.setLocation(25, 20);
        
        // add button listener
        gameStartLoadSavePanel = new GameStartLoadSavePanel();
        
        gameStartPanelCreateCharacter = new GameStartPanelCreateCharacter();
        
        gameEndPanel = new GameEndPanel(null);
        
        gameStartInstructionPanel = new GameStartInstructionPanel();
        
        /**
         * Adding Button Listener to each carrying GUI Class's Buttons
         */
        gameStartPanel.getNewGameButton().addActionListener(new GameStartButtonListener());
        gameStartPanel.getLoadGameButton().addActionListener(new GameStartButtonListener());
        
        gameStartLoadSavePanel.getNewGameButton().addActionListener(new GameStartButtonListener());
        gameStartLoadSavePanel.getLoadSaveButton().addActionListener(new GameStartButtonListener());
        
        gameStartInstructionPanel.getNextButton().addActionListener(new GameStartButtonListener());
        gameStartInstructionPanel.getStartButton().addActionListener(new GameStartButtonListener());
        
        gameStartPanelCreateCharacter.getPickWarriorButton().addActionListener(new GameStartButtonListener());
        gameStartPanelCreateCharacter.getPickThiefButton().addActionListener(new GameStartButtonListener());
        
        gameStartPanelCreateCharacter.getPickWarriorButton().setEnabled(false);
        gameStartPanelCreateCharacter.getPickThiefButton().setEnabled(false);
        
        gameStartPanelCreateCharacter.getTextField().getDocument().addDocumentListener(new TextFieldDocumentListener());
        
        // Firstly just added GameStartPanel
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
    
    // Change from InnerPanel to GameEndPanel when player was defeated
    // Can be either GAME OVER or GAME CLEAR, based on string parameter
    public void changeGameEndPanel(String inString) {
        gameEndPanel = new GameEndPanel(inString);
        gameEndPanel.setLocation(25, 20);
        
        gameEndPanel.getEndButton().addActionListener(new GameStartButtonListener());
        gameEndPanel.getSaveButton().addActionListener(new GameStartButtonListener());
        
        this.remove(innerPanel);
        this.add(gameEndPanel);
        
        this.revalidate();
        this.repaint();
        
        // Print out all user's commands and game events
        innerPanel.actionListPanel.printEnd();
    }
    
    // Load Saved Characters from DataBase Table from GameStartLoadSavePanel
    // Remove GameStartPanel, then change to GameStartLoadSavePanel
    private void changeToLoadSavePanel() {
        this.remove(gameStartPanel);
        gameStartLoadSavePanel.setLocation(25, 20);
        this.add(gameStartLoadSavePanel);
        this.revalidate();
        this.repaint();
    }
    
    // If player change thier mind not want to load save character
    // Change to CreateCharacterPanel
    private void changeFromLoadSavePanelToCreateNewCharacPanel() {
        this.remove(gameStartLoadSavePanel);
        this.add(gameStartPanelCreateCharacter);
        gameStartPanelCreateCharacter.setLocation(25, 20);
        this.revalidate();
        this.repaint();
    }
    
    // Explain Game Mechanic through InstructionPanel
    // Remove GameStartPanel to GameStartInstructionPanel
    // Then change to CreateNewCharacterPanel
    private void changeFromGameStartPanelToInstructionPanel() {
        this.remove(gameStartPanel);
        this.add(gameStartInstructionPanel);
        gameStartInstructionPanel.setLocation(25, 20);
        this.revalidate();
        this.repaint();
    }
    
    // Change from InstructionPanel to GameStartPanelCreateCharacter
    private void changeToCreateNewCharacPanel() {
        this.remove(gameStartInstructionPanel);
        this.add(gameStartPanelCreateCharacter);
        gameStartPanelCreateCharacter.setLocation(25, 20);
        this.revalidate();
        this.repaint();
    }
    
    // Get loading character information from LoadSavePanel's method
    // and Create a new character base on the collect info
    // Then Change to inner panel
    private void createCharacterFromSaveTable() {
        
        String[] characterInfo = gameStartLoadSavePanel.loadCharacterFromTable();
        
        String name = characterInfo[0];
        int exp = Integer.parseInt(characterInfo[1]);
        int potion = Integer.parseInt(characterInfo[2]);
        String job = characterInfo[3];
        
        if (job.equalsIgnoreCase("warrior")) {
            this.gameModel.setPlayer(new WarriorJob(name, exp, potion));
        }
        if (job.equalsIgnoreCase("thief")) {
            this.gameModel.setPlayer(new ThiefJob(name, exp, potion));
        }
        
        this.remove(gameStartLoadSavePanel);
        
        // Define innerPanel AFTER CREATED Character,
        // Result: Prevent NullPointer from Player Character
        innerPanel = new InnerPanel(gameModel);
        this.add(innerPanel);
        innerPanel.setLocation(25, 20);
        
        // Set MAPTRAVELINGSTATE to true after AFTER CREATED and map
        // To enable Buttons in GameView
        GameControl.MAPTRAVELINGSTATE = true;
        
        // Add text to ActionListPanel
        innerPanel.actionListPanel.addTextToList("Welcome Back to Dungeon "+name);
        
        // Update Map
        // After Player created character
        innerPanel.innerPanelMap.updateMap();
        
        this.revalidate();
        this.repaint();
    }
    
    // Create a new Character through the GameStartPanelCreateCharacter Panel
    // Then Set created character to GameMode.player
    // And Change to InnerPanel
    private void createPlayerCharacThenChangeToInstructionPanel(String classInput, String name) {
        
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
        
        // Define innerPanel AFTER CREATED Character,
        // Result: Prevent NullPointer from Player Character
        innerPanel = new InnerPanel(gameModel);
        this.add(innerPanel);
        innerPanel.setLocation(25, 20);
        
        // Set MAPTRAVELINGSTATE to true after AFTER CREATED and map
        // To enable Buttons in GameView
        GameControl.MAPTRAVELINGSTATE = true;
        
        // Add text to ActionListPanel
        innerPanel.actionListPanel.addTextToList("Welcome to Dungeon "+pickedName);
        
        // Update Map
        // After Player created character
        innerPanel.innerPanelMap.updateMap();
        
        this.revalidate();
        this.repaint();
    }
    
    // Create Database Table to store player's character
    private void createTable() {
        try {
            dBManager = new DBManager();
            
            Statement statement = dBManager.getConnection().createStatement();
            
            String sqlCreate = "CREATE TABLE "+TABLENAME+" ( NAME VARCHAR(20) "
                    + "NOT NULL, EXP INT, POTION INT, CLASS VARCHAR(10))";
            
            statement.executeUpdate(sqlCreate);
            
            String printOutString = "Created Table";
            innerPanel.actionListPanel.addTextToList(printOutString);
            System.out.println(printOutString);
            
            dBManager.closeConnections();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Save player current character to Database Table
    private void saveCharacter() {
        try {
            dBManager = new DBManager();
            
            Statement statement = dBManager.getConnection().createStatement();
            
            String name = gameModel.getPlayer().getName();
            int exp = gameModel.getPlayer().getExp();
            int potions = gameModel.getPlayer().getCarryingItems().size();
            String job = gameModel.getPlayer().getJob();
            
            String sqlInsert = "INSERT INTO "+TABLENAME+" VALUES('"+name+"', "
                    + ""+exp+", "+potions+", '"+job+"')";
            
            statement.executeUpdate(sqlInsert);
            
            String printOutString2 = "Saved Player's Character";
            innerPanel.actionListPanel.addTextToList(printOutString2);
            System.out.println(printOutString2);
            
            dBManager.closeConnections();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Check if JTextField is empty 
    // If empty, disable Class Picking (Warrior, Thief) Buttons
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
                changeFromGameStartPanelToInstructionPanel();
            }
            
            if (event.getSource() == gameStartPanel.getLoadGameButton()) {
                changeToLoadSavePanel();
            }
            
            if (event.getSource() == gameStartLoadSavePanel.getNewGameButton()) {
                changeFromLoadSavePanelToCreateNewCharacPanel();
            }
            
            if (event.getSource() == gameStartLoadSavePanel.getLoadSaveButton()) {
                createCharacterFromSaveTable();
            }
            
            if (event.getSource() == gameStartInstructionPanel.getNextButton()) {
                gameStartInstructionPanel.readSecondText();
                gameStartInstructionPanel.getNextButton().setEnabled(false);
            }
            
            if (event.getSource() == gameStartInstructionPanel.getStartButton()) {
                changeToCreateNewCharacPanel();
            }
            
            if (event.getSource() == gameStartPanelCreateCharacter.getPickWarriorButton()) {
                String name = gameStartPanelCreateCharacter.getTextField().getText().trim();
                createPlayerCharacThenChangeToInstructionPanel("W", name);
            }
            
            if (event.getSource() == gameStartPanelCreateCharacter.getPickThiefButton()) {
                String name = gameStartPanelCreateCharacter.getTextField().getText().trim();
                createPlayerCharacThenChangeToInstructionPanel("T", name);
            }
            
            if (event.getSource() == gameEndPanel.getSaveButton()) {
                createTable();
                saveCharacter();
                gameEndPanel.getSaveButton().setText("SAVED!");
                gameEndPanel.getSaveButton().setEnabled(false);
            }
            
            if (event.getSource() == gameEndPanel.getEndButton()) {
                System.exit(0);
            }
        }
        
    }
    
}
