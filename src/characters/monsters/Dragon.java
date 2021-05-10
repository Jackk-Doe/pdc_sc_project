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
public class Dragon extends MonsterCharacter{
    public Dragon() {
        super();
        this.name = "Ancient Dragon";
        this.x_position = 38;
        this.y_position = 38;
        this.fullHp = 99;
        this.currentHp = fullHp;
        this.baseAtk = 16;
        this.armor = 7;
        this.luck = 3;
        this.level = 5;
        this.exp = 100;
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
        
        if (player.isPoisoned() == false) {
            int poisonChance = rand.nextInt(18)+1;
            if (poisonChance <= this.luck) {
                player.setPoisoned(true);
                System.out.println(this.getName()+" use Poison Breath skill !\n");
                createLoadingTime(1);
                System.out.println(player.getName()+" was poisoned\n");
                createLoadingTime(1);
                System.out.println(player.getName()+" will recieve 5 damages for 3 turns\n");
            }
        }
        
        if (player.isStunned()== false) {
            int stunChance = rand.nextInt(18)+1;
            if (stunChance <= this.luck) {
                player.setStunned(true);
                System.out.println(this.getName()+" smashed "+player.getName()+" with tail !\n");
                createLoadingTime(1);
                System.out.println(player.getName()+" got stunned\n");
            }
        }
        
        if (player.getCurrentHp() < 1) {
            player.setCurrentHp(0);
        }
    }
}
