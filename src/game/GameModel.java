/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import characters.monsters.Dragon;
import characters.monsters.GoblinMonster;
import characters.monsters.MonsterCharacter;
import characters.monsters.SkeletonWarriorMonster;
import characters.monsters.SlimeMonster;
import characters.player.PlayerCharacter;
import control.GameControl;
import gui.GameView;
import java.util.ArrayList;
import java.util.Random;
import map.Map;

/**
 *
 * @author sengthavongphilavong
 */
public class GameModel {

    public GameView gameView;

    public PlayerCharacter player;
    public ArrayList<MonsterCharacter> monsters;

    public MonsterCharacter currentMonster;

    public Map map;

    private int poisonedPlayerCount = 3;
    private int poisonedMonsterCount = 3;
    
    public GameModel(GameView inGameView) {

        this.gameView = inGameView;

        monsters = new ArrayList<>();
        monsters.add(new SlimeMonster(3, 3));
        monsters.add(new SlimeMonster(4, 9));
        monsters.add(new SlimeMonster(30, 1));
        monsters.add(new SlimeMonster(25, 4));
        monsters.add(new SlimeMonster(10, 2));
        monsters.add(new GoblinMonster(8, 12));
        monsters.add(new GoblinMonster(16, 10));
        monsters.add(new GoblinMonster(26, 14));
        monsters.add(new GoblinMonster(37, 11));
        monsters.add(new GoblinMonster(6, 8));
        monsters.add(new GoblinMonster(24, 13));
        monsters.add(new GoblinMonster(37, 16));
        monsters.add(new SkeletonWarriorMonster(6, 18));
        monsters.add(new SkeletonWarriorMonster(14, 22));
        monsters.add(new SkeletonWarriorMonster(18, 20));
        monsters.add(new SkeletonWarriorMonster(29, 21));
        monsters.add(new SkeletonWarriorMonster(36, 17));
        monsters.add(new Dragon());

        currentMonster = null;

        map = new Map();

        // Add Monster to map
        map.addMonster(monsters);

    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public void setPlayer(PlayerCharacter player) {
        this.player = player;
        this.player.setGameView(gameView);
    }

    public ArrayList<MonsterCharacter> getMonsters() {
        return monsters;
    }

    public Map getMap() {
        return map;
    }

    // Set GameView for GameModel & all monsters
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
        
        for (MonsterCharacter monster : monsters) {
            monster.setGameView(gameView);
        }
    }

    public void updatePlayerCurrentLocation(char input) {

        map.updateMap(player, input);
        gameView.updateMapGUI();
        printOutDirectionGUI(input);

        // Add checkEnemyEncounter
        checkEnemyEncounter();
    }

    public void printOutDirectionGUI(char input) {

        String stringOut = player.getName() + " moved ";

        switch (input) {
            case 'w':
                stringOut += "up";
                break;
            case 's':
                stringOut += "down";
                break;
            case 'a':
                stringOut += "left";
                break;
            case 'd':
                stringOut += "right";
                break;
        }

        gameView.updateActionListGUI(stringOut);
    }

    private void checkEnemyEncounter() {
        for (MonsterCharacter monster : monsters) {
            if ((monster.getCurrentHp() > 0)
                    && ((player.getX_position() == monster.getX_position())
                    && (player.getY_position() == monster.getY_position()))) {

                // Update GUI
                gameView.updateActionListGUI("Encountered Monster!");

                // Set Currently fighting monster
                currentMonster = monster;

                if (monster instanceof Dragon) {
                    gameView.updateActionListGUI("Be focused!");
                    //1
                    gameView.updateActionListGUI(player.getName() + " is "
                            + "now facing the strongest monster in this Dungeon!");
                    //1
                    gameView.updateActionListGUI("The Ancient Dragon");
                }
                else {
                    gameView.updateActionListGUI(player.getName() + " is facing a " + monster.getName() + "!");
                }

                // Set MAPTRAVELINGSTATE to false
                // Set INBATTLESTATE to true
                // Keys stop listening for map-traveling 
                // Keys start listening for battle-command
                GameControl.MAPTRAVELINGSTATE = false;
                GameControl.INBATTLESTATE = true;

                // No idea why GUI does not change with just one call
                // (Probably something to do with Order)
                gameView.updateMapToBattleGUI();
                gameView.outerPanel.innerPanel.changeMapToBattleGUI();
                gameView.outerPanel.innerPanel.innerPanelBattle.setMonsterStatusGUI();

            }

        }
    }

    // Read player command from GameControl
    public void readPlayerBattleCommand(int listIndex) {

        boolean blockIncomingAttack = false;

        // If Player recieved Poision damage
        if ((player.isPoisoned()) && (player.getCurrentHp() > 0)) {
            gameView.updateActionListGUI(player.getName() + " recieved 5 damages from poison");

            player.setCurrentHp(player.getCurrentHp() - 5);

            if (player.getCurrentHp() < 0) {
                player.setCurrentHp(0);
            }

            gameView.updateActionListGUI(player.getName() + " current HP : "
                    + "" + player.getCurrentHp() + "/" + player.getFullHp());

            poisonedPlayerCount--;
            if (poisonedPlayerCount <= 0) {
                player.setPoisoned(false);
                poisonedPlayerCount = 3;
            }
            gameView.updatePlayerStatusGUI();
        }

        switch (listIndex) {
            case 0:
                gameView.updateActionListGUI(player.getName() + " attacked "
                        + "" + currentMonster.getName());
                blockIncomingAttack = currentMonster.blockAttacking();

                if (blockIncomingAttack) {
                    // If Dragon
                    if (currentMonster instanceof Dragon) {
                        gameView.updateActionListGUI(currentMonster.getName() + " "
                                + "protected itself with its metal-like wings");
                    }
                    else {
                        gameView.updateActionListGUI(currentMonster.getName()
                                + " blocked " + player.getName() + "'s attack!");
                    }
                }
                else {
                    if (player.isChargeAttack()) {
                        gameView.updateActionListGUI(player.getName() + " dealted "
                                + "critical damage from charge attack");
                    }

                    player.attack(currentMonster);
                    // Update Monster's GUI status
                    gameView.updateMonsterStatusGUI(currentMonster);
                }

                // Monster current hp afted player attacked
                gameView.updateActionListGUI(currentMonster.getName() + " current HP : "
                        + "" + currentMonster.getCurrentHp() + "/" + currentMonster.getFullHp());

                break;

            case 1:
                player.setDefendStatus(true);
                gameView.updateActionListGUI(player.getName() + " take guard  "
                        + "Increase defence from " + currentMonster.getName() + "'s next attack");
                break;

            case 2:
                player.setChargeAttack(true);
                gameView.updateActionListGUI(player.getName() + " is ready for "
                        + "Critical Attack, Increase " + player.getName() + "'s next attack");
                break;

            case 3:
                if (player.checkPotionStock()) {
                    player.usingPotion();
                    gameView.updateActionListGUI(player.getName() + " used HP potion");

                    if (player.getCurrentHp() >= player.getFullHp()) {
                        gameView.updateActionListGUI(player.getName() + " recovered full HP");
                    }

                    gameView.updateActionListGUI(player.getName() + " current HP : "
                            + "" + player.getCurrentHp() + "/" + player.getFullHp());
                    gameView.updateActionListGUI(player.getName() + " has "
                            + "" + player.getCarryingItems().size() + " hp potion(s) left");
                }
                else {
                    gameView.updateActionListGUI("No potion carrying");
                    gameView.updateActionListGUI("Turn skip");
                }
                gameView.updatePlayerStatusGUI();
                break;
        }

        // Monster's turn
        // If Monster recieved poison damage
        if ((currentMonster.isPoisoned()) && (currentMonster.getCurrentHp() > 0)) {
            gameView.updateActionListGUI(currentMonster.getName() + " recieved 5 damages from poison");

            currentMonster.setCurrentHp(currentMonster.getCurrentHp() - 5);

            if (currentMonster.getCurrentHp() < 0) {
                currentMonster.setCurrentHp(0);
            }

            gameView.updateActionListGUI(currentMonster.getName() + " current HP : "
                    + "" + currentMonster.getCurrentHp() + "/" + currentMonster.getFullHp());

            poisonedMonsterCount--;
            if (poisonedMonsterCount <= 0) {
                currentMonster.setPoisoned(false);
                poisonedMonsterCount = 3;
            }
            
            gameView.updateMonsterStatusGUI(currentMonster);
        }

        // If Player's & Monster's current hp still more than 0
        // Move to Monster's turn
        if ((currentMonster.getCurrentHp() > 0) && (player.getCurrentHp() > 0)) {

            // Monster's turn
            monstersTurn();

            // If player got stunned
            // Monster gained 1 extra turn
            if ((player.isStunned()) && (player.getCurrentHp() > 0)) {
                gameView.updateActionListGUI(player.getName() + " was stunned");
                gameView.updateActionListGUI("Skipped " + player.getName() + "'s turn");
                player.setStunned(false);

                monstersTurn();

            }
        }

        // If Player defeated the Monster
        if ((currentMonster.getCurrentHp() <= 0) && (player.getCurrentHp() > 0)) {
            if (currentMonster instanceof Dragon) {
                gameView.updateActionListGUI("The Mighty " + currentMonster.getName() + " has fallen");
            }
            else {
                gameView.updateActionListGUI(currentMonster.getName() + " has been eliminated!");
            }

            // Update Player current status after battle
            gameView.updateActionListGUI("Update " + player.getName() + "'s status ");
            player.afterBattleStatusUpdate(currentMonster.getExp());
            // BattleEnd = true

            // Battle ended!
            // Set MAPTRAVELINGSTATE & INBATTLESTATE back
            GameControl.MAPTRAVELINGSTATE = true;
            GameControl.INBATTLESTATE = false;

            // Update GUI
            gameView.updateActionListGUI("Battle ended!");
            gameView.updateActionListGUI("Back to Map");
            gameView.updateBattleToMapGUI();
        }

        // If Player was defeated by Monster
        // Game Over
        if (player.getCurrentHp() <= 0) {
            if (currentMonster instanceof Dragon) {
                gameView.updateActionListGUI(player.getName() + " was burned to "
                        + "ashes by the Might " + currentMonster.getName());
            }
            else {
                gameView.updateActionListGUI(player.getName() + " "
                        + "was defeated by " + currentMonster.getName());
            }
            // GameOver, Change GUI
            gameView.updateGameEndGUI("GAME OVER");
            
        }

        // If Player defeated the Dragon
        // Note : The Dragon is stored in last index of ArrayLIst
        if (monsters.get(monsters.size() - 1).getCurrentHp() <= 0) {
            gameView.updateActionListGUI("The Ancient Dragon has been slained!");
            //1
            gameView.updateActionListGUI("The Dungeon objective has been fufilled");
            //1
            gameView.updateActionListGUI("Game End");
            //1
            gameView.updateGameEndGUI("GAME CLEAR");
            
        }
    }

    private void monstersTurn() {

        Random rand = new Random();

        boolean blockIncomingAttack = false;

        // If Monster got stunned
        // Skip Monster's turn
        if (currentMonster.isStunned()) {
            gameView.updateActionListGUI(currentMonster.getName() + " was stunned");
            gameView.updateActionListGUI("Skipped " + currentMonster.getName() + "'s turn");
            currentMonster.setStunned(false);
        }
        else {

            gameView.updateActionListGUI(currentMonster.getName() + "'s turn");

            // Monster's move is decided with random number
            int monsterMove = rand.nextInt(8) + 1;

            if (monsterMove == 1 && !(currentMonster.isDefendStatus())) {
                currentMonster.setDefendStatus(true);

                // If Dragon
                if (currentMonster instanceof Dragon) {
                    gameView.updateActionListGUI(currentMonster.getName() + " "
                            + "created a massive tornado to defend itself from "
                            + "" + player.getName() + "'s next attack");
                }
                else {
                    gameView.updateActionListGUI(currentMonster.getName() + " increase"
                            + " defence from " + player.getName() + "'s attack ");
                }
            }

            else if (monsterMove == 2 && !(currentMonster.isChargeAttack())) {
                currentMonster.setChargeAttack(true);

                // If Dragon
                if (currentMonster instanceof Dragon) {
                    gameView.updateActionListGUI(currentMonster.getName() + " "
                            + "absorbed force energy around to release Dragon Breath in it's next attack");
                }
                else {
                    gameView.updateActionListGUI(currentMonster.getName() + " is charged for "
                            + "Critical Attack, Increase " + currentMonster.getName() + "'s next attack");
                }
            }
            else {
                gameView.updateActionListGUI(currentMonster.getName() + " attacked "
                        + "" + player.getName());
                blockIncomingAttack = player.blockAttacking();

                if (blockIncomingAttack) {
                    gameView.updateActionListGUI(player.getName() + " blocked"
                            + " " + currentMonster.getName() + "'s attack!");
                }
                else {
                    if (currentMonster.isChargeAttack()) {
                        if (currentMonster instanceof Dragon) {
                            gameView.updateActionListGUI(currentMonster.getName()
                                    + " threw Dragon Fire Breath at " + player.getName());
                        }
                        else {
                            gameView.updateActionListGUI(currentMonster.getName()
                                    + " dealted critical damage from charge attack");
                        }
                    }
                    currentMonster.attack(player);
                    // Update Player's Status GUI
                    gameView.updatePlayerStatusGUI();
                }
                // Player current hp afted Monster attacked
                gameView.updateActionListGUI(player.getName() + " current HP : "
                        + "" + player.getCurrentHp() + "/" + player.getFullHp());
            }
        }
    }
}
