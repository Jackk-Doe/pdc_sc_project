/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.PlayerCharacter;
import characters.player.WarriorJob;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
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
    
    // Need Update mehtod? (YES)
    public void updatePlayerStatusGUI() {
        playerStatus = player.showStatus();
        
        for (int i = 0; i < 10; i++) {
            labels[i].setText(playerStatus[i]);
        }
        
//        this.revalidate();
//        this.repaint();
    }

    public static void main(String[] args) {
        PlayerCharacter player = new WarriorJob("Ajax", 300, 5);
        PlayerStatusPanel panel = new PlayerStatusPanel(player);

        JFrame frame = new JFrame("Test");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
