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
    char[][] map = new char[40][25];
    public String[][] newMap = new String[25][40];
    int x, y;

    public Map() {
        for (int y = 0; y < 25; y++) {
            for (int x = 0; x < 40; x++) {
                newMap[y][x] = "* ";
            }
        }
    }
    
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
            map[x][24] = '_';
        }
        map[39][24] = '|';
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
        newMap[p.getX_position()][p.getY_position()] = "P ";
        
        for (MonsterCharacter m : monsterCharacter) {
            if (m.getName().equalsIgnoreCase("Slime")) {
                newMap[m.getX_position()][m.getY_position()] = "S ";
            } 
            else if (m.getName().equalsIgnoreCase("Goblin")) {
                newMap[m.getX_position()][m.getY_position()] = "G ";
            } 
            else if (m.getName().equalsIgnoreCase("Skeleton Warrior")) {
                newMap[m.getX_position()][m.getY_position()] = "W ";
            }
            else if (m.getName().equalsIgnoreCase("Ancient Dragon")) {
                newMap[m.getX_position()][m.getY_position()] = "W ";
            }
        }
    }
    
    public void addMonster(ArrayList<MonsterCharacter> monsterCharacters) {
        for (MonsterCharacter monster : monsterCharacters) {
            if (monster.getName().equalsIgnoreCase("Slime")) {
                map[monster.getX_position()][monster.getY_position()] = 'S';
            } 
            else if (monster.getName().equalsIgnoreCase("Goblin")) {
                map[monster.getX_position()][monster.getY_position()] = 'G';
            } 
            else if (monster.getName().equalsIgnoreCase("Skeleton Warrior")) {
                map[monster.getX_position()][monster.getY_position()] = 'W';
            }
            else if (monster.getName().equalsIgnoreCase("Ancient Dragon")) {
                map[monster.getX_position()][monster.getY_position()] = 'D';
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
    
    public static void main(String[] args) {
        Map map = new Map();
        map.createMap();
        map.printMap();
    }
}
