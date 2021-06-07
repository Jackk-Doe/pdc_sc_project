/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import characters.monsters.Dragon;
import characters.monsters.MonsterCharacter;
import characters.player.PlayerCharacter;
import gui.GameView;

/**
 *
 * @author sengthavongphilavong
 */
public class Battle {
    
    private GameView gameView;
    
    private PlayerCharacter player;
    private MonsterCharacter monster;
    
    private boolean playerTurn, monsterTurn, battleEnd;
    
    public Battle() {
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
    
    public void enterBattle(PlayerCharacter inPlayer, MonsterCharacter inMonster) {
        this.player = inPlayer;
        this.monster = inMonster;
        
        if (monster instanceof Dragon) {
            // If dragon
        }
        else {
            gameView.updateActionListGUI(player.getName()+" is facing a "+monster.getName()+"!");
        }
//        int i = 0;
//        while ( (player.getCurrentHp() > 0 && monster.getCurrentHp() > 0)) {
//            System.out.println(i++);
//            Thread.yield();
//        }
    }
}
