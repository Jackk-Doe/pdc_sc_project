/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import characters.player.PlayerCharacter;
import characters.player.WarriorJob;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 */
public class InnerPanel extends JPanel{
    
    public JPanel innerMainPanel;
    public ActionListPanel actionListPanel;
    public PlayerStatusPanel playerStatusPanel;
    
    public PlayerCharacter player;
    
    public InnerPanel() {
        
        setLayout(null);
        
//        setLayout(null);
        
        innerMainPanel = new InnerMainPanelMap();
        innerMainPanel.setSize(420, 425);
        innerMainPanel.setLocation(10, 20);
        innerMainPanel.setBackground(Color.red);
        
        actionListPanel = new ActionListPanel();
        actionListPanel.setSize(230, 235);
        actionListPanel.setLocation(435, 20);
        actionListPanel.setBackground(Color.blue);
        
        playerStatusPanel = new PlayerStatusPanel(new WarriorJob("Ajax", 300, 10));
        playerStatusPanel.setSize(230, 185);
        playerStatusPanel.setLocation(435, 260);
        playerStatusPanel.setBackground(Color.gray);
        
        add(innerMainPanel);
        add(actionListPanel);
        add(playerStatusPanel);
        
//        setPreferredSize(new Dimension(735, 610));
//        setSize(660, 480);
        setPreferredSize(new Dimension(680, 490));
    }
    
    public void changeState(int inputed) {
        if (inputed == 1) {
            InnerMainPanelMap newPanel = new InnerMainPanelMap();
            this.remove(innerMainPanel);
            innerMainPanel = newPanel;
            this.add(innerMainPanel);
            innerMainPanel.setSize(420, 425);
            innerMainPanel.setLocation(10, 20);
            this.revalidate();
            this.repaint();
        }
        else {
            InnerMainPanelBattle newPanel = new InnerMainPanelBattle();
            this.remove(innerMainPanel);
            innerMainPanel = newPanel;
            this.add(innerMainPanel);
            innerMainPanel.setSize(420, 425);
            innerMainPanel.setLocation(10, 20);
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
