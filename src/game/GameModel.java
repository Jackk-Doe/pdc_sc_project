/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import characters.monsters.GoblinMonster;
import characters.monsters.MonsterCharacter;
import characters.monsters.SlimeMonster;
import characters.player.PlayerCharacter;
import characters.player.ThiefJob;
import gui.GameView;
import java.util.ArrayList;
import map.Map;

/**
 *
 * @author sengthavongphilavong
 */
public class GameModel {
    
    public GameView gameView;
    
    public PlayerCharacter player;
//    public PlayerCharacter player = new ThiefJob("Kyle");
    public ArrayList<MonsterCharacter> monsters;
    public Map map;

    public GameModel(GameView gameView) {
        
        this.gameView = gameView;
        
        monsters = new ArrayList<>();
        monsters.add(new SlimeMonster(3, 3));
        monsters.add(new SlimeMonster(4, 9));
        monsters.add(new SlimeMonster(30, 1));
        monsters.add(new SlimeMonster());
        monsters.add(new SlimeMonster());
        monsters.add(new SlimeMonster());
        monsters.add(new GoblinMonster(25, 21));
        
        map = new Map();
        
        map.addMonster(monsters);
//        gameView.outerPanel.innerPanel.innerPanelMap.updateMap();
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }
    
    public ArrayList<MonsterCharacter> getMonsters() {
        return monsters;
    }

    public Map getMap() {
        return map;
    }
    
    
}
