/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

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
    
    public Battle(GameView inGameView) {
        this.gameView = inGameView;
    }
    
    public void enterBattle(PlayerCharacter inPlayer, MonsterCharacter inMonster) {
        this.player = inPlayer;
        this.monster = inMonster;
        
        
    }
}
