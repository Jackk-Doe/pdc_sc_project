/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters.player;

import characters.monsters.MonsterCharacter;
import items.HpPotion;

/**
 *
 * @author sengthavongphilavong
 * 
 * Warrior has high hp and atk
 * 
 * Stay longer in battle and dealt high damage on enemy
 * 
 * At lvl 3 learn Life Drain
 * At lvl 5 learn Shield Smash
 */
public class WarriorJob extends PlayerCharacter{

    public WarriorJob(String name) {
        super();
        this.name = name;
        this.job = "Warrior";
        this.fullHp = 30;
        this.currentHp = fullHp;
        this.baseAtk = 8;
        this.armor = 3;
        this.luck = 1;
        this.level = 1;
        this.exp = 0;
        this.passiveSkill1Name = "Life Drain";
        this.passiveSkill2Name = "Shield Smash";
        carryingItems.add(new HpPotion());
        carryingItems.add(new HpPotion());
        carryingItems.add(new HpPotion());
    }
    
    public WarriorJob(String name, int exp, int size) {
        super();
        this.name = name;
        this.job = "Warrior";
        this.fullHp = 30;
        this.currentHp = fullHp;
        this.baseAtk = 8;
        this.armor = 3;
        this.luck = 1;
        this.level = 1;
        this.exp = exp;
        this.passiveSkill1Name = "Life Drain";
        this.passiveSkill2Name = "Stun Shot";
        if (size < 3) {
            carryingItems.add(new HpPotion());
            carryingItems.add(new HpPotion());
            carryingItems.add(new HpPotion());
        } else {
            for (int i = 0; i < size; i++) {
                carryingItems.add(new HpPotion());
            }
        }
        gainExp(exp);
    }
    
    @Override
    public boolean gainExp(int exp)
    {
        this.exp += exp;
        boolean lvlUpCheck = false;
        
        if (this.level == 1) {
            if (this.exp >= 30) {
                this.level = 2;
                this.fullHp += 10;
                this.currentHp = this.fullHp;
                this.baseAtk += 3;
                this.armor += 2;
                this.luck++;
                lvlUpCheck = true;
            }
        }
        
        if (this.level == 2) {
            if (this.exp >= 80) {
                this.level = 3;
                this.fullHp += 10;
                this.currentHp = this.fullHp;
                this.baseAtk += 4;
                this.armor += 2;
                lvlUpCheck = true;
                this.passiveSkill1 = true;
            }
        }
        
        if (this.level == 3) {
            if (this.exp >= 160) {
                this.level = 4;
                this.fullHp += 10;
                this.currentHp = this.fullHp;
                this.baseAtk += 5;
                this.armor += 2;
                this.luck++;
                lvlUpCheck = true;
            }
        }
        
        if (this.level == 4) {
            if (this.exp >= 250) {
                this.level = 5;
                this.fullHp += 10;
                this.currentHp = this.fullHp;
                this.baseAtk += 5;
                this.armor += 2;
                lvlUpCheck = true;
                this.passiveSkill2 = true;
            }
        }
        
        return lvlUpCheck;
    }
    
    @Override
    public void attack(MonsterCharacter monster)
    {
        int totalAtkDamage = rand.nextInt(4) + this.baseAtk;
        
        if (this.chargeAttack) {
            totalAtkDamage += this.baseAtk + rand.nextInt(3);
            this.chargeAttack = false;
        }
        
        if (monster.isDefendStatus()) {
            if (monster.getArmor() * 2 >= totalAtkDamage) {
                monster.setCurrentHp(monster.getCurrentHp() - 1);
            } else {
                monster.setCurrentHp(monster.getCurrentHp() + ((monster.getArmor() * 2) - totalAtkDamage));
            }
            monster.setDefendStatus(false);
        } else {
            monster.setCurrentHp(monster.getCurrentHp() + (monster.getArmor() - totalAtkDamage));
        }
        
        // Life Drain
        if (this.passiveSkill1) {
            int lifeSteal = totalAtkDamage/3;
            this.currentHp += lifeSteal;
            
            gameView.updateActionListGUI(this.name+" recieved "+lifeSteal+" hp from "+this.passiveSkill1Name+" skill");
            if (this.currentHp >= this.fullHp) {
                this.currentHp = this.fullHp;
            }
            gameView.updatePlayerStatusGUI();
        }
        
        // Shield Smash
        if (this.passiveSkill2 && (monster.isStunned() == false)) {
            int stunChance = rand.nextInt(18)+1;
            if (stunChance <= this.luck) {
                monster.setStunned(true);
                gameView.updateActionListGUI(this.name+" smashed with "+this.passiveSkill2Name+" skilled !");
                createLoadingTime(1);
                gameView.updateActionListGUI(monster.getName()+" was stunned with "+this.name+" shield !");
            }
        }
        
        if (monster.getCurrentHp() < 1) {
            monster.setCurrentHp(0);
        }
    }
}
