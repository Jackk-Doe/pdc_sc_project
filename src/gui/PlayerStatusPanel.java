/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.PlayerCharacter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class used to show Player current Status GUI
 * 
 * Update Every time when Player received damage, level-up, gained items, used potion
 * 
 * This Class is located inside InnerPanel
 */
public class PlayerStatusPanel extends JPanel {

    public JLabel[] labels;

    private PlayerCharacter player;

    private String[] playerStatus;

    public PlayerStatusPanel(PlayerCharacter playerCharacter) {

        setBorder(BorderFactory.createTitledBorder("Player's Status:"));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.player = playerCharacter;

        labels = new JLabel[10];

        playerStatus = player.showStatus();

        for (int i = 0; i < 10; i++) {
            labels[i] = new JLabel(playerStatus[i]);
            add(labels[i]);
        }

//        setPreferredSize(new Dimension(230, 185));
//        setSize(230, 185);
    }
    
    // Update player current status
    public void updatePlayerStatusGUI() {
        
        playerStatus = player.showStatus();
        
        for (int i = 0; i < 10; i++) {
            labels[i].setText(playerStatus[i]);
        }
        
    }

}
