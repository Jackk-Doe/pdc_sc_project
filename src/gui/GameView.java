/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Testing");
        
        frame.getContentPane().add(new GameView());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
