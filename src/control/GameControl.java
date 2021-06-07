/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import game.GameModel;
import gui.GameView;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author sengthavongphilavong
 */
public class GameControl extends JFrame{
    
    public static boolean MAPTRAVELINGSTATE = false;
    public static boolean INBATTLESTATE = false;
    
    public GameView gameView;
    
    public GameModel gameModel;
    
    public GameControl() {
        
        super("Nintendo GAMEBOY Simulator");
        
        gameModel = new GameModel(gameView);
        
        gameView = new GameView(gameModel);
        
        gameModel.setGameView(gameView);
        
        GameButtonListener gameButtonListener = new GameButtonListener();
        
        gameView.buttonPanel.upButton.addActionListener(gameButtonListener);
        gameView.buttonPanel.downButton.addActionListener(gameButtonListener);
        gameView.buttonPanel.leftButton.addActionListener(gameButtonListener);
        gameView.buttonPanel.rightButton.addActionListener(gameButtonListener);
        
        gameView.buttonPanel.aButton.addActionListener(gameButtonListener);
        
        add(gameView);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
    
    private void moveBetweenBattleJList(int currentIndex, char input) {
        
        switch (input) {
            case 'w':
                gameView.outerPanel.innerPanel.innerPanelBattle.commandList.setSelectedIndex(currentIndex - 1);
                break;
            case 's':
                gameView.outerPanel.innerPanel.innerPanelBattle.commandList.setSelectedIndex(currentIndex + 1);
                break;
        }
    }
    
    private class GameButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            
            if (MAPTRAVELINGSTATE) {
                
                if (event.getSource() == gameView.buttonPanel.upButton) {
                    gameModel.updatePlayerCurrentLocation('w');
                    
                }
                
                if (event.getSource() == gameView.buttonPanel.downButton) {
                    gameModel.updatePlayerCurrentLocation('s');
                    
                }
                
                if (event.getSource() == gameView.buttonPanel.leftButton) {
                    gameModel.updatePlayerCurrentLocation('a');
                    
                }
                
                if (event.getSource() == gameView.buttonPanel.rightButton) {
                    gameModel.updatePlayerCurrentLocation('d');
                    
                }
                
            }
            
            if (INBATTLESTATE) {
                
                int listIndex = gameView.outerPanel.innerPanel.innerPanelBattle.commandList.getSelectedIndex();
                gameView.outerPanel.innerPanel.innerPanelBattle.commandList.setSelectedIndex(0);
                
                if (event.getSource() == gameView.buttonPanel.upButton) {
                    moveBetweenBattleJList(listIndex, 'w');
                }
                
                if (event.getSource() == gameView.buttonPanel.downButton) {
                    moveBetweenBattleJList(listIndex, 's');
                }
                
                if (event.getSource() == gameView.buttonPanel.aButton) {
                    int playerChoice = gameView.outerPanel.innerPanel.innerPanelBattle.commandList.getSelectedIndex();
                    System.out.println(playerChoice);
                    
                    // Player's action
                    gameModel.readPlayerBattleCommand(listIndex);
                }
                
                // For checking current JList index
//                System.out.println(gameView.outerPanel.innerPanel.innerPanelBattle.commandList.getSelectedIndex());
            }
            
            // Add game clear here
        }
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new GameControl();
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width/2) - (frame.getWidth()/2), (d.height/2) - (frame.getHeight()/2)));
        
        frame.setVisible(true);
        
        
    }
}
