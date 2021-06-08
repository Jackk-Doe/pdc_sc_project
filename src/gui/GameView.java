/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.monsters.MonsterCharacter;
import game.GameModel;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 * 
 * This GameView Class carries all other GUI Classes
 * 
 * Any GUI Activity happens through this GameView Class
 */
public class GameView extends JPanel{
    
    public OuterPanel outerPanel;
    public ButtonPanel buttonPanel;
    
    public GameModel gameModel;
    
    public GameView(GameModel inputGameModel) {
        
//        setBackground(Color.CYAN);

        this.gameModel = inputGameModel;
        
        buttonPanel = new ButtonPanel();
        
        outerPanel = new OuterPanel(gameModel);
        
        add(outerPanel);
        
        add(buttonPanel);
        
        setPreferredSize(new Dimension(800, 950));
    }
    
    // Call Method From InnerPanelMap GUI
    // Update Map
    public void updateMapGUI() {
        outerPanel.innerPanel.innerPanelMap.updateMap();
    }
    
    // Call Method from ActionListPanel GUI
    // Add and print game events to ActionList
    public void updateActionListGUI(String inString) {
        outerPanel.innerPanel.actionListPanel.addTextToList(inString);
    }
    
    // Call Mehtod from InnerPanel GUI
    // Change from Map GUI to Battle GUI
    public void updateMapToBattleGUI() {
        outerPanel.innerPanel.changeMapToBattleGUI();
    }
    
    // Call Mehtod from InnerPanel GUI
    // Change from Battle GUI to Map GUI
    public void updateBattleToMapGUI() {
        outerPanel.innerPanel.changeFromBattleBackToMapGUI();
    }
    
    // Call Mehtod from MonsterStatusPanel GUI
    // Update MonsterStatus GUI to the currentMonster from GameModel
    public void updateMonsterStatusGUI(MonsterCharacter inMonster) {
        outerPanel.innerPanel.innerPanelBattle.monsterStatusPanel.updateMonsterStatusGUI(inMonster);
    }
    // Call Mehtod from PlayerStatusPanel GUI
    // Update Player current Status to GUI
    public void updatePlayerStatusGUI() {
        outerPanel.innerPanel.playerStatusPanel.updatePlayerStatusGUI();
    }
    
    // Call Mehtod from OuterPanel
    // Show GameEnd GUI
    // Either GAME OVER or GAME CLEAR, based on parameter string
    public void updateGameEndGUI(String inString) {
        outerPanel.changeGameEndPanel(inString);
    }
}
