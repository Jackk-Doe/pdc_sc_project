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
        this.x_position = 39;
        this.y_position = 24;
        this.fullHp = 99;
        this.currentHp = fullHp;
        this.baseAtk = 16;
        this.armor = 7;
        this.luck = 3;
        this.level = 5;
        this.exp = 100;
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
                gameView.updateActionListGUI(this.getName()+" use Poison Breath skill !");
                createLoadingTime(1);
                gameView.updateActionListGUI(player.getName()+" was poisoned");
                createLoadingTime(1);
                gameView.updateActionListGUI(player.getName()+" will recieve 5 damages for 3 turns");
            }
        }
        
        if (player.isStunned()== false) {
            int stunChance = rand.nextInt(18)+1;
            if (stunChance <= this.luck) {
                player.setStunned(true);
                gameView.updateActionListGUI(this.getName()+" smashed "+player.getName()+" with tail !");
                createLoadingTime(1);
                gameView.updateActionListGUI(player.getName()+" got stunned");
            }
        }
        
        if (player.getCurrentHp() < 1) {
            player.setCurrentHp(0);
        }
    }
}
