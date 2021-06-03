/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.monsters.Dragon;
import characters.monsters.GoblinMonster;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 */
public class InnerMainPanelBattle extends JPanel{
    
    private JList<String> commandList;
    
    public MonsterStatusPanel monsterStatusPanel;

    public InnerMainPanelBattle() {
        
        setLayout(null);
        
        setBorder(BorderFactory.createTitledBorder("Battle"));
        
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        String[] battleCommandList = {"1) Attack enemy", "2) Take guard", 
            "3) Charge attack", "4) Use potion" };
        
        commandList = new JList<>(battleCommandList);
        commandList.setFont(new Font("Helvetica", Font.BOLD, 28));
        commandList.setSize(380, 200);
        commandList.setLocation(20, 30);
        
        monsterStatusPanel = new MonsterStatusPanel(new Dragon());
        monsterStatusPanel.setSize(230, 165);
        monsterStatusPanel.setLocation(90, 250);
        
        add(commandList);
        add(monsterStatusPanel);
        
//        setSize(420, 425);
//        setPreferredSize(new Dimension(420, 425));
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        InnerMainPanelBattle innerMainPanelBattle = new InnerMainPanelBattle();
        frame.getContentPane().add(innerMainPanelBattle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
}
