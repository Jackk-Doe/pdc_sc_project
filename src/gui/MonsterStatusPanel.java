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
 */
public class MonsterStatusPanel extends JPanel{
    
    public JLabel[] labels;
    
    private MonsterCharacter monster;
    
    private String[] monsterStatus;

    public MonsterStatusPanel(MonsterCharacter character) {
        
        setBorder(BorderFactory.createTitledBorder("Monster Status:"));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.monster = character;
        
        labels = new JLabel[6];
        
        monsterStatus = monster.newedShowStatus();
        
        for (int i = 0; i < 6; i++) {
            labels[i] = new JLabel(monsterStatus[i]);
            add(labels[i]);
        }
    }
    
    
}
