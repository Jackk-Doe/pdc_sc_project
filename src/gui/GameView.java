/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.monsters.MonsterCharacter;
import game.GameModel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author sengthavongphilavong
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
    
    public void updateMapGUI() {
        outerPanel.innerPanel.innerPanelMap.updateMap();
    }
    
    public void updateActionListGUI(String inString) {
        outerPanel.innerPanel.actionListPanel.addTextToList(inString);
    }
    
    // V1
//    public void updateMapToBattleGUI(MonsterCharacter inMonster) {
//        outerPanel.innerPanel.changeMapToBattleGUI(inMonster);
//        this.revalidate();
//        this.repaint();
//    }
    
    // V2
    public void updateMapToBattleGUI() {
        outerPanel.innerPanel.changeMapToBattleGUI();
        this.revalidate();
        this.repaint();
    }
    
    public void updateBattleToMapGUI() {
        outerPanel.innerPanel.changeBattleBackToMapGUI();
    }
    
    public void updateMonsterStatusGUI(MonsterCharacter inMonster) {
        outerPanel.innerPanel.innerPanelBattle.monsterStatusPanel.updateMonsterStatusGUI(inMonster);
    }
    
    public void updatePlayerStatusGUI() {
        outerPanel.innerPanel.playerStatusPanel.updatePlayerStatusGUI();
    }
    
    public void updateGameEndGUI(String inString) {
        outerPanel.changeGameEndPanel(inString);
    }
}
