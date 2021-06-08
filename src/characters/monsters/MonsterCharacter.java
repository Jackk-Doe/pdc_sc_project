/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters.monsters;

import characters.player.PlayerCharacter;
import characters.Character;

import java.util.Random;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class is abstract for all Monsters
 */
public class MonsterCharacter extends Character{
    
    Random rand = new Random();

    public MonsterCharacter() {
        this.defendStatus = false;
        this.chargeAttack = false;
        this.stunned = false;
        this.poisoned = false;
    }
    
    // This method is called when monster attack player
    // Note: each monster have their own special attack defined in thier own class
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
        
        if (player.getCurrentHp() < 1) {
            player.setCurrentHp(0);
        }
    }
    
}
