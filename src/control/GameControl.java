/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import characters.monsters.MonsterCharacter;
import characters.player.PlayerCharacter;
import characters.player.WarriorJob;
import gui.GameView;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author sengthavongphilavong
 */
public class GameControl extends JFrame{
    
    public static PlayerCharacter playerModel = new WarriorJob("AJAX");
    public static ArrayList<MonsterCharacter> monstersModel;
    
    private GameView gameView;
    
    public GameControl() {
        super("Nintendo GAMEBOY Simulator");
        
        gameView = new GameView();
        
        add(gameView);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
    
    public static void main(String[] args) {
        JFrame frame = new GameControl();
        frame.setVisible(true);
    }
}
