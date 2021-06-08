/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.PlayerCharacter;
import game.GameModel;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class used to carry and draw other GUI class: ActionListPanel,
 *   PlayerStatusPanel, InnerMainMap & InnerMainBattle.
 * 
 * Frequently changing between InnerMainMap & InnerMainBattle GUI
 * 
 * This Class is located inside OuterPanel
 */
public class InnerPanel extends JPanel{
    
    private GameModel gameModel;
    
    public InnerMainPanelMap innerPanelMap;
    public InnerMainPanelBattle innerPanelBattle;
    
    public ActionListPanel actionListPanel;
    public PlayerStatusPanel playerStatusPanel;
    
    public PlayerCharacter player;
    
    // Passing GameModel to other GUI Class
    public InnerPanel(GameModel model) {
        
        this.gameModel = model;
        
        setLayout(null);
        
        innerPanelMap = new InnerMainPanelMap(gameModel);
        innerPanelMap.setSize(420, 425);
        innerPanelMap.setLocation(10, 20);
        
        // Add Monster to Battle
        innerPanelBattle = new InnerMainPanelBattle(gameModel);
        innerPanelBattle.setSize(420, 425);
        
        actionListPanel = new ActionListPanel();
        actionListPanel.setSize(230, 235);
        actionListPanel.setLocation(435, 20);
        actionListPanel.setBackground(Color.blue);
        
        playerStatusPanel = new PlayerStatusPanel(gameModel.getPlayer());
        playerStatusPanel.setSize(230, 185);
        playerStatusPanel.setLocation(435, 260);
        playerStatusPanel.setBackground(Color.gray);
        
        add(innerPanelMap);
        add(actionListPanel);
        add(playerStatusPanel);
        
        setSize(680, 490);
    }
    
    // Change Map GUI to Battle GUI
    // Ant set monster for MonsterStatusGUI 
    // Get Monster from GameModel' attribute: currentMonster
    public void changeMapToBattleGUI() {
        
        innerPanelBattle.setMonsterStatusGUI();
        innerPanelBattle.setLocation(10, 20);
        
        this.remove(innerPanelMap);
        this.add(innerPanelBattle);
        
        this.revalidate();
        this.repaint();
    }
    
    // Change Battle GUI to Map GUI
    public void changeFromBattleBackToMapGUI() {
        
        this.remove(innerPanelBattle);
        this.add(innerPanelMap);
        
        this.revalidate();
        this.repaint();
    }
    
}
