/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters.monsters;

import characters.player.PlayerCharacter;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class represent Skeleton Warrior Monster
 * 
 * Skeleton Warrior is the most balance monster in game
 * Skeleton Warrior special attack is stunning attack
 * 
 * Stunning attack : put enemy to sleep for 1 turn
 */
public class SkeletonWarriorMonster extends MonsterCharacter{
    public SkeletonWarriorMonster(int x, int y) {
        super();
        this.name = "Skeleton Warrior";
        this.x_position = x;
        this.y_position = y;
        this.fullHp = 35;
        this.currentHp = fullHp;
        this.baseAtk = 13;
        this.armor = 4;
        this.luck = 3;
        this.level = 4;
        this.exp = 30;
    }
    
    public SkeletonWarriorMonster() {
        super();
        this.name = "Skeleton Warrior";
        this.x_position = rand.nextInt(35)+1;
        this.y_position = rand.nextInt(15)+10;
        this.fullHp = 35;
        this.currentHp = fullHp;
        this.baseAtk = 13;
        this.armor = 4;
        this.luck = 3;
        this.level = 4;
        this.exp = 30;
    }

    @Override
    public void attack(PlayerCharacter player) {
        int totalAtkDamage = rand.nextInt(4) + this.baseAtk;
        
        if (this.chargeAttack) {
            totalAtkDamage += this.baseAtk + rand.nextInt(3);
            this.chargeAttack = false;
        }
        
        if (player.isDefendStatus()) {
            if ((player.getArmor() * 2) >= totalAtkDamage) {
                player.setCurrentHp(player.getCurrentHp() - 1);
            } else {
                player.setCurrentHp(player.getCurrentHp() + ((player.getArmor() * 2) - totalAtkDamage));
            }
            player.setDefendStatus(false);
        } else {
            if (player.getArmor() >= totalAtkDamage) {
                player.setCurrentHp(player.getCurrentHp() - 1);
            } else {
                player.setCurrentHp(player.getCurrentHp() + (player.getArmor() - totalAtkDamage));
            }
        }
        
        // Stunning attack
        if (player.isStunned()== false) {
            int stunChance = rand.nextInt(18)+1;
            if (stunChance <= this.luck) {
                player.setStunned(true);
                gameView.updateActionListGUI(this.getName()+" smashed "+player.getName()+" with shield !");
                createLoadingTime(1);
                gameView.updateActionListGUI(player.getName()+" got stunned");
            }
        }
        
        if (player.getCurrentHp() < 1) {
            player.setCurrentHp(0);
        }
    }
}
