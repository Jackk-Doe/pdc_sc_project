/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import map.Map;
import characters.monsters.SlimeMonster;
import characters.monsters.GoblinMonster;
import characters.monsters.SkeletonWarriorMonster;
import characters.monsters.Dragon;
import characters.monsters.MonsterCharacter;
import characters.player.WarriorJob;
import characters.player.ThiefJob;
import characters.player.PlayerCharacter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author sengthavongphilavong
 */
public class Game {
    
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    
    PrintWriter pw;
    BufferedReader br;
    
    PlayerCharacter player;
    ArrayList<MonsterCharacter> monsters;
    Map map;
    
    char input;
    boolean gameFinish, gameOver;
    
    public void gameStart() {
        System.out.println("Welcome you to The Lord of Dungeon");
        createLoadingTime(2);
        System.out.println("Please Enter : ");
        System.out.println("\t1) To start a new game");
        System.out.print("\t2) To load save file\n> ");
        
        while (input != '1' && input != '2') {
            input = scan.next().charAt(0);
            if (input != '1' && input != '2') {
                System.out.println("Invalid input\nTry Again\n");
            }
        }
        createLoadingTime(1);

        switch (input) {
            case '1':
                //Game mechanic Explain
                gameMechanicExplain();
                
                System.out.print("\nChoose your adventurer name \n> ");
                String name = scan.next();
                System.out.print("\nChoose your adventurer job :\n"
                        + "\t1) Warrior\n"
                        + "\t2) Thief\n> ");
                
                scan.reset();
                input = scan.next().charAt(0);
                
                if (input == '1') {
                    player = new WarriorJob(name);
                } else if (input == '2') {
                    player = new ThiefJob(name);
                }
                createLoadingTime(1);
                System.out.println("\nYou are " + player.getName() + " the " + player.getJob());
                
                //Dungeon Explain
                dungeonIntro();
        
                break;
            case '2':
                loadSavedCharacter();
                System.out.println("Loading success\n");
                createLoadingTime(1);
                System.out.println("Welcome back "+ player.getName()+" the "+player.getJob()+"\n");
                createLoadingTime(2);
                break;
            
        }
        
        System.out.println("Entering Dungeon\n");
        createLoadingTime(2);

        monsters = new ArrayList<MonsterCharacter>();
        monsters.add(new SlimeMonster(3, 3));
        monsters.add(new SlimeMonster(4, 10));
        monsters.add(new SlimeMonster(30, 11));
        monsters.add(new SlimeMonster());
        monsters.add(new SlimeMonster());
        monsters.add(new SlimeMonster());
        monsters.add(new GoblinMonster(25, 21));
        monsters.add(new GoblinMonster(34, 27));
        monsters.add(new GoblinMonster());
        monsters.add(new GoblinMonster());
        monsters.add(new GoblinMonster());
        monsters.add(new GoblinMonster());
        monsters.add(new SkeletonWarriorMonster(31, 30));
        monsters.add(new SkeletonWarriorMonster(12, 35));
        monsters.add(new SkeletonWarriorMonster(35, 37));
        monsters.add(new SkeletonWarriorMonster());
        monsters.add(new SkeletonWarriorMonster());
        monsters.add(new SkeletonWarriorMonster());
        monsters.add(new Dragon());
        
        map = new Map();
        
        map.createMap();
        map.addCharacters(player, monsters);
        
        while (!gameFinish && !gameOver) {
            
            map.printMap();
            System.out.print("Enter command\n> ");
            
            do {
                input = scan.next().charAt(0);
                
                input = Character.toLowerCase(input);
                
                if (input != 'w' && input != 'a' && input != 's'
                        && input != 'd' && input != 'i' && input != 'q') {
                    System.out.print("Invalid input\nTry Again\n> ");
                }
                
                if (input == 'i') {
                    player.showStatus();
                    input = ' ';
                    System.out.print("Enter command\n>");
                }
                
            } while (input != 'w' && input != 'a' && input != 's'
                    && input != 'd' && input != 'i' && input != 'q');
            
            if (input == 'q') {
                gameOver = true;
            }
            
            readPlayerCommand(input);
            
            checkMapBoundery(player);

            //Battle
            checkEnemyEncounter(player, monsters);
            
            map.updateMap(player, input);
            
        }
        
        scan.reset();
        
        if (gameOver) {
            if (player.getCurrentHp() > 0) {
                System.out.println("You have left the dungeon and quit the game\n");
                createLoadingTime(2);
            }
            else
            {
                System.out.println("Adventurer died in dungeon\n");
                createLoadingTime(2);
                System.out.println("\tGave Over\n");
                createLoadingTime(2);
            }
        }
        
        if (gameFinish) {
            System.out.println("The Ancient Dragon has been slain\n");
            createLoadingTime(1);
            System.out.println("The Dungeon objective has been fufilled");
            createLoadingTime(2);
            System.out.println("\n\tGame End\n");
            createLoadingTime(2);
        }
        
        System.out.print("Would you want to save your character status to use again in next play ? (y/n)\n> ");
        input = scan.next().charAt(0);
            
        input = Character.toLowerCase(input);
            
        if (input == 'y') {
            saveCharacter(player);
            createLoadingTime(1);
        }
        System.out.println("\n\tFare Well Adventurer\n\n");
    }
    
    public void loadSavedCharacter() {
        try {
            br = new BufferedReader(new FileReader("./Saves/SavedCharacter.txt"));
            String line = br.readLine();
            String[] str = line.split(" ");
            
            if (str.length > 3) {
                if (str[1].equalsIgnoreCase("warrior")) {
                    player = new WarriorJob(str[0], Integer.parseInt(str[2]), Integer.parseInt(str[3]));
                } else if (str[1].equalsIgnoreCase("thief")) {
                    player = new ThiefJob(str[0], Integer.parseInt(str[2]), Integer.parseInt(str[3]));
                }
            } else {
                System.out.println("Reading Error...");
                createLoadingTime(1);
                System.out.println("Please restart program Again");
                System.exit(0);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Find no save files");
            createLoadingTime(1);
            System.out.println("Please restart program Again & make a new character");
            System.exit(0);
        } catch (IOException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Reading Error...");
            createLoadingTime(1);
            System.out.println("Please restart program Again");
            System.exit(0);
        }
    }
    
    public void saveCharacter(PlayerCharacter player) {
        try {
            pw = new PrintWriter("./Saves/SavedCharacter.txt");
            pw.println(player.getName() + " " + player.getJob() + " " + player.getExp() + " " + player.getCarryingItems().size());
            System.out.println("Save completed \n");
        } catch (IOException e) {
            System.out.println("Writing Error...");
        }
        pw.close();
    }
    
    public void createLoadingTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void readPlayerCommand(char input) {
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
    }
    
    public void checkMapBoundery(PlayerCharacter p) {
        boolean checkBound = false;
        if (p.getX_position() < 1) {
            p.setX_position(p.getX_position() + 1);
            checkBound = true;
        } else if (p.getY_position() < 1) {
            p.setY_position(p.getY_position() + 1);
            checkBound = true;
        } else if (p.getX_position() > 38) {
            p.setX_position(p.getX_position() - 1);
            checkBound = true;
        } else if (p.getY_position() > 38) {
            p.setY_position(p.getY_position() - 1);
            checkBound = true;
        }
        if (checkBound) {
            System.out.println("\n" + p.getName() + " have reached the map edge.");
        }
    }
    
    public void checkEnemyEncounter(PlayerCharacter p, ArrayList<MonsterCharacter> ms) {
        for (MonsterCharacter m : ms) {
            if (m.getCurrentHp() > 0 && p.getX_position() == m.getX_position()
                    && p.getY_position() == m.getY_position()) {
                enterBattle(p, m);
                
                if (m instanceof Dragon && m.getCurrentHp() <= 0) {
                    gameFinish = true;
                }
            }
        }
        // Uncomment if need to delete monster, after eliminated
//        for (MonsterCharacter m : ms) {
//            if (m.getCurrentHp() <= 0) {
//                monsters.remove(m);
//                break;
//            }
//        }
    }
    
    public void enterBattle(PlayerCharacter player, MonsterCharacter monster) {
        if (monster instanceof Dragon) {
            System.out.println("Be focused \n");
            createLoadingTime(1);
            System.out.println("You are now facing the strongest monster in this dungeon\n");
            createLoadingTime(2);
            System.out.println("The Ancient Dragon");
            createLoadingTime(1);
        } else {
            System.out.println("Facing a " + monster.getName());
        }
        createLoadingTime(1);
        System.out.print("\nEntering battle");
        createLoadingTime(1);
        System.out.print(".");
        createLoadingTime(1);
        System.out.print(".");
        createLoadingTime(1);
        System.out.println(".\n");
        createLoadingTime(1);

        System.out.println("Showing both character status: \n");
        createLoadingTime(1);
        System.out.println("Adventurer :");
        createLoadingTime(1);
        player.showStatus();
        createLoadingTime(1);
        System.out.println("\nMonster :");
        createLoadingTime(1);
        monster.showStatus();
        createLoadingTime(2);
        System.out.println();

        boolean battleEndd = false;
        boolean blockingAttack;
        
        int poisonedPlayerCount = 3;
        int poisonedMonsterCount = 3;
        
        while (!battleEndd) {
            
            char playerCommand = '0';
            
            if ((player.isPoisoned()) && (player.getCurrentHp() > 0)) {
                System.out.println(player.getName()+" recieved 5 damages from poison\n");
                player.setCurrentHp(player.getCurrentHp() - 5);
                if (player.getCurrentHp() <= 0) {
                    player.setCurrentHp(0);
                }
                createLoadingTime(1);
                System.out.println(player.getName() + " current hp : " + player.getCurrentHp() + "/" + player.getFullHp() + "\n");
                createLoadingTime(1);
                poisonedPlayerCount--;
                if (poisonedPlayerCount < 1) {
                    player.setPoisoned(false);
                    poisonedPlayerCount = 3;
                }
            }
            
            if (player.isStunned() == false && (player.getCurrentHp() > 0)) {
                System.out.println("Please choose your move : ");
                System.out.println("\t1) Attack enemy");
                System.out.println("\t2) Take guard");
                System.out.println("\t3) Charge attack");
                System.out.println("\t4) Use potion");
                System.out.print("\nCommand > ");
            
                playerCommand = scan.next().charAt(0);
                
                if (playerCommand != '1' && playerCommand != '2'
                    && playerCommand != '3' && playerCommand != '4') {
                    System.out.println("Invalid command");
                    createLoadingTime(1);
                    System.out.println("Skip Adventurer turn");
                    createLoadingTime(1);
                }
            } else if ((player.isStunned() == true) && (player.getCurrentHp() > 0)){
                System.out.println(player.getName()+" was stunned \n");
                createLoadingTime(1);
                System.out.println("Skipped "+player.getName()+"'s turn\n");
                player.setStunned(false);
            }
            createLoadingTime(1);
            
            switch (playerCommand) {
                case '1':
                    System.out.println(player.getName() + " attacked " + monster.getName() + "\n");
                    blockingAttack = monster.blockAttacking();
                    if (blockingAttack) {
                        createLoadingTime(1);
                        if (monster instanceof Dragon) {
                            System.out.println(monster.getName()+" protected itself with it's metal-like wings \n");
                        } else {
                            System.out.println(monster.getName() + " blocked " + player.getName() + "'s attack!\n");
                        }
                        
                    } else {
                        if (player.isChargeAttack()) {
                            System.out.println(player.getName() + " dealted critical damage from charge attack\n ");
                        }
                        player.attack(monster);
                    }   
                    createLoadingTime(1);
                    System.out.println(monster.getName() + " current hp : " + monster.getCurrentHp() + "/" + monster.getFullHp() + "\n");
                    break;
                case '2':
                    player.setDefendStatus(true);
                    System.out.println(player.getName() + " take guard.\nIncrease defence for " + monster.getName() + "'s next attack\n");
                    break;
                case '3':
                    player.setChargeAttack(true);
                    System.out.println(player.getName() + " set to special attack\nIncrease damage deatling for " + player.getName() + "'s next attack\n");
                    break;
                case '4':
                    if (player.checkPotionStock()) {
                        player.usingPotion();
                        System.out.println(player.getName() + " used HP potion\n");
                        
                        if (player.getCurrentHp() >= player.getFullHp()) {
                            System.out.println(player.getName() + " recovered full HP.\n");
                        }
                        System.out.println(player.getName() + " current HP : " + player.getCurrentHp() + "/" + player.getFullHp() + "\n");
                        createLoadingTime(1);
                        System.out.println(player.getName() + " has " + player.getCarryingItems().size() + " hp potion(s) left\n");
                    } else {
                        System.out.println("No potion carrying\nturn skip\n");
                    }
                    break;
                default:
                    break;
            }
            
            createLoadingTime(2);
            //Monster turn
            
            if (monster.isPoisoned() && monster.getCurrentHp() > 0) {
                System.out.println(monster.getName()+" recieved 5 damages from poison\n");
                monster.setCurrentHp(monster.getCurrentHp() - 5);
                if (monster.getCurrentHp() <= 0) {
                    monster.setCurrentHp(0);
                }
                createLoadingTime(1);
                System.out.println(monster.getName() + " current hp : " + monster.getCurrentHp() + "/" + monster.getFullHp() + "\n");
                createLoadingTime(1);
                poisonedMonsterCount--;
                if (poisonedMonsterCount < 1) {
                    monster.setPoisoned(false);
                    poisonedMonsterCount = 3;
                }
            }

            if ((monster.getCurrentHp() > 0) && (player.getCurrentHp() > 0)) {
                
                if (monster.isStunned() == false) {
                    System.out.println(monster.getName() + "'s turn\n");
                    createLoadingTime(1);
                    int monsterMove = rand.nextInt(8) + 1;
                    
                    if (monsterMove == 1 && !(monster.isDefendStatus())) {
                        monster.setDefendStatus(true);
                        if (monster instanceof Dragon) {
                            System.out.println(monster.getName()+" created a tornado around itself to defend "+player.getName()+"'s next attack\n");
                        } else {
                            System.out.println(monster.getName() + " take guard.\nIncrease defence for " + player.getName() + "'s next attack\n");
                        }
                        
                    } else if (monsterMove == 2 && !(monster.isChargeAttack())) {
                        monster.setChargeAttack(true);
                        if (monster instanceof Dragon) {
                            System.out.println(monster.getName()+" absorbed energy nearby to release a Dragon Breath for it's next attack\n");
                        } else {
                            System.out.println(monster.getName() + " set to special attack\nIncrease damage deatling for " + monster.getName() + "'s next attack\n");
                        }
                        
                    } else {
                        System.out.println(monster.getName() + " attacked " + player.getName() + "\n");
                        blockingAttack = player.blockAttacking();
                        if (blockingAttack) {
                            createLoadingTime(1);
                            System.out.println(player.getName() + " blocked " + monster.getName() + "'s attack!\n");
                            
                        } else {
                            if (monster.isChargeAttack()) {
                                if (monster instanceof Dragon) {
                                    System.out.println(monster.getName() + " threw it Dragon Fire Breath at "+player.getName()+"\n");
                                } else {
                                    System.out.println(monster.getName() + " dealted critical damage from charge attack\n ");
                                }
                            }
                            monster.attack(player);
                        }   
                        createLoadingTime(1);
                        System.out.println(player.getName() + " current hp : " + player.getCurrentHp() + "/" + player.getFullHp() + "\n");
                    }
                } else {
                    System.out.println(monster.getName() + " was stunned\n");
                    createLoadingTime(1);
                    System.out.println("Skipped " + monster.getName() + "'s turn\n");
                    monster.setStunned(false);
                }
            }
            
            if ((monster.getCurrentHp() <= 0) && (player.getCurrentHp() > 0)){
                if (monster instanceof Dragon) {
                    System.out.println(monster.getName()+" has fallen \n");
                    createLoadingTime(1);
                    System.out.println("Human has just overthrown the Dragon \n");
                    createLoadingTime(1);
                } else {
                    System.out.println(monster.getName() + " has been eliminated!\n");
                }
            }
            
            if (monster.getCurrentHp() <= 0) {
                System.out.print("Update " + player.getName() + "'s status");
                createLoadingTime(1);
                System.out.print(".");
                createLoadingTime(1);
                System.out.print(".");
                createLoadingTime(1);
                System.out.println(".\n");
                createLoadingTime(1);
                player.afterBattleStatusUpdate(monster.getExp());
                battleEndd = true;
            }
            
            if (player.getCurrentHp() <= 0) {
                if (monster instanceof Dragon) {
                    System.out.println(player.getName()+" was burned to ash by the Mighty "+monster.getName());
                } else {
                    System.out.println(player.getName() + " was defeated by " + monster.getName()+"\n");
                }
                createLoadingTime(1);
                battleEndd = true;
                gameOver = true;
            }
        }
    }

    public void dungeonIntro() {
        System.out.println("\nYour objective is to explore this dungeon\n");
        createLoadingTime(2);
        System.out.println("Fight with Monsters\n");
        createLoadingTime(2);
        System.out.println("Get experience and Level-up\n");
        createLoadingTime(2);
        System.out.println("Then when You the Adventurer felt ready\n");
        createLoadingTime(2);
        System.out.println("Go deeper in the dungeon\n");
        createLoadingTime(2);
        System.out.print("To find ");
        createLoadingTime(2);
        System.out.print("and eliminate ");
        createLoadingTime(2);
        System.out.println("The Ancient Dragon\n");
        createLoadingTime(2);
    }

    public void gameMechanicExplain() {
        System.out.println("Dungeon Mechanic Explain: \n");
        createLoadingTime(2);
        System.out.println("The map of dungeon will be printed in every turn\n");
        createLoadingTime(2);
        System.out.println("On the map 'P' is used to represent Player's current location\n");
        createLoadingTime(2);
        System.out.println("           'S' is for Slime monster\n");
        createLoadingTime(2);
        System.out.println("           'G' is for Goblin monster\n");
        createLoadingTime(2);
        System.out.println("           'W' is for Skeleton Warrior monster\n");
        createLoadingTime(2);
        System.out.println("           'D' is for Ancient Dragon\n");
        createLoadingTime(2);
        System.out.println("Use W, A, S, D to move inside the dungeon\n");
        createLoadingTime(2);
        System.out.println("             I to view adventurer status\n");
        createLoadingTime(2);
        System.out.println("             Q to leave the dungeon and quit game\n");
        createLoadingTime(2);
        System.out.println("You will have choose to play between WARRIOR and THIEF\n");
        createLoadingTime(2);
        System.out.println("Warrior : A fierceful soldier who survived from countless battle field");
        createLoadingTime(3);
        System.out.println("          Specialize in endurance, let oneself stand still in the combat");
        createLoadingTime(3);
        System.out.println("          With more combat experience Warrior can learn to a Life Drain Skill to steal enemy's hp");
        createLoadingTime(3);
        System.out.println("          And Shield Smash to stun enemy with Warrior heavy shield\n");
        createLoadingTime(3);
        System.out.println("Thief   : Shadow bringer who fight above the battle line, which led to absolute victory");
        createLoadingTime(3);
        System.out.println("          Using knife to kill enemy before one known, with thier deadly Poison none creatures can't be killed");
        createLoadingTime(3);
        System.out.println("          Thief were also well-known with their LUCK in fight");
        createLoadingTime(3);
        System.out.println("          With more killing experience Thief will learn to kill it's target faster with Double Strike Skill");
        createLoadingTime(3);
        System.out.println("          And with it's Poison Knife, even the strongest man will have to run for life\n");
        createLoadingTime(3);
    }
}
