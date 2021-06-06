/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import characters.monsters.MonsterCharacter;
import characters.player.PlayerCharacter;
import game.GameModel;
import gui.GameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author sengthavongphilavong
 */
public class GameControl extends JFrame{
    
    public static PlayerCharacter playerModel;
    public static ArrayList<MonsterCharacter> monstersModel;
    
    public static boolean MAPTRAVELINGSTATE = true;
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
        
        add(gameView);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
    
    private class GameButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
//            if (event.getSource() == gameView.buttonPanel.upButton) {
//                gameModel.player.showStatus();
//                System.out.println("Hello");
//            }
            
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
                
//                gameView.updateMapGUI();
                
                
            }
            
            // Add game clear here
        }
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new GameControl();
        frame.setVisible(true);
        
        
    }
}
