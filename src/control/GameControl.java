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
    
    public GameView gameView;
    
    public GameModel gameModel;
    
    public GameControl() {
        super("Nintendo GAMEBOY Simulator");
        
        gameModel = new GameModel(gameView);
        
        gameView = new GameView(gameModel);
        
        gameView.buttonPanel.upButton.addActionListener(new TestButton());
        
        add(gameView);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
    
    private class TestButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == gameView.buttonPanel.upButton) {
                gameModel.player.showStatus();
                System.out.println("Hello");
            }
            
            
        }
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new GameControl();
        frame.setVisible(true);
        
        
    }
}
