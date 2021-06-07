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
public class SlimeMonster extends MonsterCharacter{

    public SlimeMonster(int x, int y) {
        super();
        this.name = "Slime";
        this.x_position = x;
        this.y_position = y;
        this.fullHp = 15;
        this.currentHp = fullHp;
        this.baseAtk = 6;
        this.armor = 2;
        this.luck = 2;
        this.level = 1;
        this.exp = 10;
    }
    
    public SlimeMonster() {
        super();
        this.name = "Slime";
        this.x_position = rand.nextInt(35)+1;
        this.y_position = rand.nextInt(8)+1;
        this.fullHp = 15;
        this.currentHp = fullHp;
        this.baseAtk = 6;
        this.armor = 2;
        this.luck = 2;
        this.level = 1;
        this.exp = 10;
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
            int poisonChance = rand.nextInt(10)+1;
            if (poisonChance <= this.luck) {
                player.setPoisoned(true);
                gameView.updateActionListGUI(this.getName()+" hit "+player.getName()+" with poison !");
                createLoadingTime(1);
                gameView.updateActionListGUI(player.getName()+" got poisoned");
                createLoadingTime(1);
                gameView.updateActionListGUI(player.getName()+" will recieve 5 damages for 3 turns");
            }
        }
        
        if (player.getCurrentHp() < 1) {
            player.setCurrentHp(0);
        }
    }
}
