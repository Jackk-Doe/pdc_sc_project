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
        this.y_position = rand.nextInt(10)+20;
        this.fullHp = 35;
        this.currentHp = fullHp;
        this.baseAtk = 13;
        this.armor = 4;
        this.luck = 3;
        this.level = 4;
        this.exp = 30;
    }

    @Override
    public void showStatus() {
        String status = "+------------------------+\n";
        status += "| NAME : " + String.format("%-16s", this.name) + "|\n";
        status += "| LVL  : " + String.format("%-16d", this.level) + "|\n";
        status += "| HP   : " + String.format("%2d", this.currentHp) + "/" + String.format("%-13d", this.fullHp) + "|\n";
        status += "| ATK  : " + String.format("%-16d", this.baseAtk) + "|\n";
        status += "| ARMOR: " + String.format("%-16d", this.armor) + "|\n";
        status += "| LUCK : " + String.format("%-16d", this.luck) + "|\n";
        status += "+------------------------+";
        System.out.println(status);
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
        
        if (player.isStunned()== false) {
            int stunChance = rand.nextInt(18)+1;
            if (stunChance <= this.luck) {
                player.setStunned(true);
                System.out.println(this.getName()+" smashed "+player.getName()+" with shield !\n");
                createLoadingTime(1);
                System.out.println(player.getName()+" got stunned\n");
            }
        }
        
        if (player.getCurrentHp() < 1) {
            player.setCurrentHp(0);
        }
    }
}
