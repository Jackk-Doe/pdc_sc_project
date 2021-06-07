/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import characters.monsters.MonsterCharacter;
import characters.player.PlayerCharacter;

import java.util.ArrayList;

/**
 *
 * @author sengthavongphilavong
 */
public class Map {
    
    public String[][] dunMap = new String[40][25];

    public Map() {
        for (int y = 0; y < 25; y++) {
            for (int x = 0; x < 40; x++) {
                dunMap[x][y] = "* ";
            }
        }
        
        dunMap[0][0] = "P ";
    }
    
    public void printMap()
    {
        for (int y = 0; y < 25; y++)
        {
            for (int x = 0; x < 40; x++)
            {
                System.out.print(dunMap[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // New
    public void addMonster(ArrayList<MonsterCharacter> monsterCharacters) {
        for (MonsterCharacter m : monsterCharacters) {
            if (m.getName().equalsIgnoreCase("Slime")) {
                dunMap[m.getX_position()][m.getY_position()] = "S ";
            } 
            else if (m.getName().equalsIgnoreCase("Goblin")) {
                dunMap[m.getX_position()][m.getY_position()] = "G ";
            } 
            else if (m.getName().equalsIgnoreCase("Skeleton Warrior")) {
                dunMap[m.getX_position()][m.getY_position()] = "W ";
            }
            else if (m.getName().equalsIgnoreCase("Ancient Dragon")) {
                dunMap[m.getX_position()][m.getY_position()] = "D ";
            }
        }
    }
    
    // Update player current location in map, after (move)
    public void updateMap(PlayerCharacter player, char input)
    {
        dunMap[player.getX_position()][player.getY_position()] = "* ";
        
        switch (input) {
            case 'w':
                player.setY_position(player.getY_position() - 1);
                break;
                
            case 's':
                player.setY_position(player.getY_position() + 1);
                break;
                
            case 'd':
                player.setX_position(player.getX_position() + 1);
                break;
                
            case 'a':
                player.setX_position(player.getX_position() - 1);
                break;
        }
        
        checkReachMapBoundery(player);
        
        dunMap[player.getX_position()][player.getY_position()] = "P ";
    }
    
    private void checkReachMapBoundery(PlayerCharacter player) {
        if (player.getX_position() < 0) {
            player.setX_position(0);
        }
        else if (player.getX_position() > 39) {
            player.setX_position(39);
        }
        else if (player.getY_position() < 0) {
            player.setY_position(0);
        }
        else if (player.getY_position() > 24) {
            player.setY_position(24);
        }
    }
    
    public static void main(String[] args) {
        Map map = new Map();
        map.printMap();
    }
}
