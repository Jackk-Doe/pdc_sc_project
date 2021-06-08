/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import system.DBManager;

/**
 *
 * @author sengthavongphilavong
 */
public class GameStartLoadSavePanel extends JPanel{
    
    private DBManager dbManager;
    
    private JLabel label;
    private JList<String> list;
    private JScrollPane scrollPane;
    private JButton newGameButton, deleteSaveButton, loadSaveButton;
    
    private ArrayList<String> arrayList;
    
    private ArrayList<String> nameList;
    private ArrayList<Integer> expList;
    private ArrayList<Integer> potionList;
    private ArrayList<String> jobList;
    
    public GameStartLoadSavePanel() {
        
        arrayList = new ArrayList<>();
        nameList = new ArrayList<>();
        expList = new ArrayList<>();
        potionList = new ArrayList<>();
        jobList = new ArrayList<>();
        
        setLayout(null);
        
        label = new JLabel("Found Saves");
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        label.setForeground(Color.BLACK);
        label.setSize(460, 100);
        label.setLocation(180, 20);
        add(label);
        
        // Load Save from table
        loadSaveFromTable();
        
        String[] characterList = new String[arrayList.size()];
        
        for (int i = 0; i < arrayList.size(); i++) {
            characterList[i] = arrayList.get(i);
        }
        
        list = new JList<>(characterList);
        scrollPane = new JScrollPane(list, 
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(620, 200);
        scrollPane.setLocation(30, 140);
        add(scrollPane);
        
        // Firstly set list point to index 0
        // Prevent NullPointerException when player accidently
        // pressed deleteButton without select anything from list
        list.setSelectedIndex(0);
        
        newGameButton = new JButton("NEW GAME");
        newGameButton.setSize(160, 60);
        newGameButton.setLocation(60, 360);
        add(newGameButton);
        
        deleteSaveButton = new JButton("DELETE SAVE");
        deleteSaveButton.addActionListener((ActionEvent e) -> {
            deleteSaveFromTable();
        });
        
        deleteSaveButton.setSize(160, 60);
        deleteSaveButton.setLocation(250, 360);
        
        // If there's only 1 option in list 
        // Disable deleteButton
        if (arrayList.size() <= 1) {
            deleteSaveButton.setEnabled(false);
        }
        add(deleteSaveButton);
        
        loadSaveButton = new JButton("LOAD SAVE");
        loadSaveButton.setSize(160, 60);
        loadSaveButton.setLocation(440, 360);
        
        // If there's no save found in table
        // Disable loadSaveButton
        if (arrayList.size() <= 0) {
            loadSaveButton.setEnabled(false);
        }
        add(loadSaveButton);
        
        setSize(680, 490);
        
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getLoadSaveButton() {
        return loadSaveButton;
    }
    
    // Called by LoadSaveButton
    // Called from OuterPanel
    // Getting loadSave by the currently pointing in Jlist
    public String[] loadCharacterFromTable() {
        
        String[] characterInfo = new String[4];
        
        int loadIndex = list.getSelectedIndex();
        
        characterInfo[0] = nameList.get(loadIndex);
        
        characterInfo[1] =Integer.toString(expList.get(loadIndex));
        
        characterInfo[2] =Integer.toString(potionList.get(loadIndex));
        
        characterInfo[3] = jobList.get(loadIndex);
        
        return characterInfo;
    }
    
    // LoadSaveData from table
    // Then add to JList
    private void loadSaveFromTable() {
        
        dbManager = new DBManager();
        
        ResultSet rs = dbManager.myQuery("SELECT * FROM SAVEDCHARACTER");
        
        try {
            
            while (rs.next()) {
                
                String name = rs.getString(1);
                int exp = rs.getInt(2);
                int potion = rs.getInt(3);
                String job = rs.getString(4);
                
                /**
                 * Store Name, Exp, Potion & Job
                 * 
                 * Needed (Only Name) when have to delete data from table
                 * &
                 * Create new Character from loadSave (Simplicity)
                 */
                nameList.add(name);
                expList.add(exp);
                potionList.add(potion);
                jobList.add(job);
                
                String text = "Name:  "+String.format("%-25s", name)+""
                        + " Class:  "+String.format("%-15s", job)+""
                        + " LVL:  "+String.format("%-10d", calculateLVL(exp))+""
                        + " Potion Item:  "+String.format("%-10d", potion);

                arrayList.add(text);
                
            }
            
            dbManager.closeConnections();
            
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Delete selected data from table 
    // And all other Arraylists
    // Deleting data chose by currently pointing index in JList
    private void deleteSaveFromTable() {
        
        // Get Index from Jlist
        int deleteIndex = list.getSelectedIndex();
        
        String deleteName = nameList.get(deleteIndex);
        
        // Remove from all ArrayList
        arrayList.remove(deleteIndex);
        nameList.remove(deleteIndex);
        expList.remove(deleteIndex);
        potionList.remove(deleteIndex);
        jobList.remove(deleteIndex);
        
        dbManager = new DBManager();
        
        try {
            Statement statement = dbManager.getConnection().createStatement();
            
            String sqlDelete = "DELETE FROM SAVEDCHARACTER WHERE NAME = '"+deleteName+"'";
            
            statement.executeUpdate(sqlDelete);
            
            
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        dbManager.closeConnections();
        
        // Disable deleteButton when there's only one data left in table
        if (arrayList.size() <= 1) {
            deleteSaveButton.setEnabled(false);
        }
        
        updateList();
    }
    
    // Update GUI after deleted
    private void updateList() {
        String[] newList = new String[arrayList.size()];
        
        for (int i = 0; i < arrayList.size(); i++) {
            newList[i] = arrayList.get(i);
        }
        
        list.setListData(newList);
    }
    
    private int calculateLVL(int exp) {
        if (exp >= 250) {
            return 5;
        }
        else if (exp >= 160) {
            return 4;
        }
        else if (exp >= 80) {
            return 3;
        }
        else if (exp >= 30) {
            return 2;
        }
        else {
            return 1;
        }
    }
    
}
