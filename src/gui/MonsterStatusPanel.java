/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.monsters.MonsterCharacter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 * 
 * This class used to show Player's current fighting Monster's Status GUI
 * 
 * This Class is located inside InnerMainBattlePanel
 */
public class MonsterStatusPanel extends JPanel{
    
    public JLabel[] labels;
    
    private MonsterCharacter monster;
    
    private String[] monsterStatus;

    public MonsterStatusPanel() {
        
        setBorder(BorderFactory.createTitledBorder("Monster Status:"));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        labels = new JLabel[6];
        
        for (int i = 0; i < 6; i++) {
            labels[i] = new JLabel();
            add(labels[i]);
        }
        
        setSize(230, 165);
    }
    
    // Update Monster's status GUI
    public void updateMonsterStatusGUI(MonsterCharacter inMonster) {
        
        this.monster = inMonster;
        
        // Return Array String from monster's showStatus mehtod
        monsterStatus = monster.showStatus();
        
        for (int i = 0; i < 6; i++) {
            labels[i].setText(monsterStatus[i]);
        }
        
    }
}
