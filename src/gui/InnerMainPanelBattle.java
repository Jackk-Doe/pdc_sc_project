/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.monsters.MonsterCharacter;
import game.GameModel;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class take care of Battle State Player's commands GUI
 * 
 * Also Show MonsterStatusGUI
 * 
 * Player will have to choose Available Action Command hold in Jlist
 * 
 * This Class is located inside Battle InnerPanel
 * 
 * This Class also carries MonsterStatusPanel
 */
public class InnerMainPanelBattle extends JPanel{
    
    public JList<String> commandList;
    
    public MonsterStatusPanel monsterStatusPanel;
    
    public MonsterCharacter monster;
    
    private GameModel gameModel;

    public InnerMainPanelBattle(GameModel inGameModel) {
        
        this.gameModel = inGameModel;
        
        setLayout(null);
        
        setBorder(BorderFactory.createTitledBorder("Battle"));
        
        String[] battleCommandList = {"1) Attack enemy", "2) Take guard", 
            "3) Charge attack", "4) Use potion" };
        
        // Add avialable command to JList
        commandList = new JList<>(battleCommandList);
        
        // Set the first selectedIndex to 0
        commandList.setSelectedIndex(0);
        
        // Set font size and location
        commandList.setFont(new Font("Helvetica", Font.BOLD, 28));
        commandList.setSize(380, 200);
        commandList.setLocation(20, 30);
        
        monsterStatusPanel = new MonsterStatusPanel();
        monsterStatusPanel.setLocation(90, 250);
        
        add(commandList);
        
        setSize(420, 425);
    }
    
    // Set Monster's Status GUI When Player encountered with any Monster
    public void setMonsterStatusGUI() {
        
        this.monster = gameModel.currentMonster;
        
        monsterStatusPanel.updateMonsterStatusGUI(monster);
        
        add(monsterStatusPanel);
        
        this.revalidate();
        this.repaint();
    }
    
}
