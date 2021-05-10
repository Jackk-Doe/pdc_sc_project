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
    char[][] map = new char[40][40];
    int x, y;
    
    public void createMap()
    {
//        for (x = 0; x < map[x][y]; x++) {
//            for (y = 0; y < map[x][y]; y++) {
//                map[x][y] = ' ';
//            }
//        }
        
        for (x = 0; x < map[0].length; x++) {
            map[0][x] = '|';
            map[39][x] = '|';
            map[x][0] = '_';
            map[x][39] = '_';
        }
        map[39][39] = '|';
    }
    
    public void printMap()
    {
        for (y = 0; y < map[0].length; y++)
        {
            for (x = 0; x < map[0].length; x++)
            {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void addCharacters(PlayerCharacter p, ArrayList<MonsterCharacter> monsterCharacter)
    {
        map[p.getX_position()][p.getY_position()] = 'P';
        
        for (MonsterCharacter m : monsterCharacter) {
            if (m.getName().equalsIgnoreCase("Slime")) {
                map[m.getX_position()][m.getY_position()] = 'S';
            } 
            else if (m.getName().equalsIgnoreCase("Goblin")) {
                map[m.getX_position()][m.getY_position()] = 'G';
            } 
            else if (m.getName().equalsIgnoreCase("Skeleton Warrior")) {
                map[m.getX_position()][m.getY_position()] = 'W';
            }
            else if (m.getName().equalsIgnoreCase("Ancient Dragon")) {
                map[m.getX_position()][m.getY_position()] = 'D';
            }
        }
    }
    
    public void updateMap(PlayerCharacter p, char input)
    {
        switch (input) {
            case 'w':
                map[p.getX_position()][p.getY_position() + 1] = ' ';
                break;
            case 's':
                map[p.getX_position()][p.getY_position() - 1] = ' ';
                break;
            case 'd':
                map[p.getX_position() - 1][p.getY_position()] = ' ';
                break;
            case 'a':
                map[p.getX_position() + 1][p.getY_position()] = ' ';
                break;
        }
        map[p.getX_position()][p.getY_position()] = 'P';
    }
}
