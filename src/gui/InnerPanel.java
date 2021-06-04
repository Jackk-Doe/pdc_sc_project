/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.PlayerCharacter;
import game.GameModel;
import java.awt.Color;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 */
public class InnerPanel extends JPanel{
    
    private GameModel gameModel;
    
//    public JPanel innerMainPanel;
    
    public InnerMainPanelMap innerPanelMap;
    public InnerMainPanelBattle innerPanelBattle;
    
    public ActionListPanel actionListPanel;
    public PlayerStatusPanel playerStatusPanel;
    
    public PlayerCharacter player;
    
    public InnerPanel(GameModel model) {
        
        this.gameModel = model;
        
        setLayout(null);
        
        innerPanelMap = new InnerMainPanelMap(gameModel);
        innerPanelMap.setSize(420, 425);
        innerPanelMap.setLocation(10, 20);
        
        // Add Monster to Battle
        innerPanelBattle = new InnerMainPanelBattle();
        innerPanelBattle.setSize(420, 425);
        
//        innerMainPanel = new InnerMainPanelMap();
//        innerMainPanel.setSize(420, 425);
//        innerMainPanel.setLocation(10, 20);
//        innerMainPanel.setBackground(Color.red);
        
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
    
    public void changeState(int inputed) {
        // Change to Battle 
        if (inputed == 1) {
            this.remove(innerPanelMap);
            this.add(innerPanelBattle);
            innerPanelBattle.setLocation(10, 20);
            this.revalidate();
            this.repaint();
        }
        // Change to Map
        else {
            this.remove(innerPanelBattle);
            this.add(innerPanelMap);
            innerPanelMap.setLocation(10, 20);
            this.revalidate();
            this.repaint();
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        InnerPanel innerPanel = new InnerPanel();
        frame.getContentPane().add(innerPanel);
//        frame.pack();
        frame.setSize(660, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        while (true) {
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);
            int inputting = scan.nextInt();
            innerPanel.changeState(inputting);
//            // frame.revalidate = ask JFrame to recalculate it's components
//            frame.revalidate();
//            // frame.repaint = ask JFrame to repaint it's components
//            frame.repaint();
        }
    }
}
