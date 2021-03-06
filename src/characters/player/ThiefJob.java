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
 * Thief has high luck
 * 
 * Thus higher chance to block incoming attack, drop item, trigger special attack
 * 
 * At lvl 3 learn Double Strike 
 * At lvl 5 learn Poison Knife
 */
public class ThiefJob extends PlayerCharacter {
    
    public ThiefJob(String name) {
        super();
        this.name = name;
        this.job = "Thief";
        this.fullHp = 25;
        this.currentHp = fullHp;
        this.baseAtk = 8;
        this.armor = 2;
        this.luck = 3;
        this.level = 1;
        this.exp = 0;
        this.passiveSkill1Name = "Double Strike";
        this.passiveSkill2Name = "Poison Knife";
        
        int extraPotionItem = rand.nextInt(4)+3;
        for (int i = 0; i < extraPotionItem; i++)
        {
            carryingItems.add(new HpPotion());
        }
    }
    
    public ThiefJob(String name, int exp, int size) {
        super();
        this.name = name;
        this.job = "Thief";
        this.fullHp = 25;
        this.currentHp = fullHp;
        this.baseAtk = 8;
        this.armor = 2;
        this.luck = 3;
        this.level = 1;
        this.exp = exp;
        this.passiveSkill1Name = "Double Attack";
        this.passiveSkill2Name = "Poision Strike";
        
        if (size < 3) {
            int extraPotionItem = rand.nextInt(4)+3;
            for (int i = 0; i < extraPotionItem; i++)
            {
                carryingItems.add(new HpPotion());
            }
        } 
        else {
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
                this.fullHp += 5;
                this.currentHp = this.fullHp;
                this.baseAtk += 2;
                this.armor++;
                this.luck += 2;
                lvlUpCheck = true;
            }
        }
        
        if (this.level == 2) {
            if (this.exp >= 80) {
                this.level = 3;
                this.fullHp += 4;
                this.currentHp = this.fullHp;
                this.baseAtk += 2;
                this.armor++;
                this.luck += 2;
                lvlUpCheck = true;
                this.passiveSkill1 = true;
            }
        }
        
        if (this.level == 3) {
            if (this.exp >= 160) {
                this.level = 4;
                this.fullHp += 3;
                this.currentHp = this.fullHp;
                this.baseAtk += 2;
                this.armor++;
                this.luck += 2;
                lvlUpCheck = true;
            }
        }
        
        if (this.level == 4) {
            if (this.exp >= 250) {
                this.level = 5;
                this.fullHp += 3;
                this.currentHp = this.fullHp;
                this.baseAtk += 2;
                this.armor++;
                this.luck++;
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
        
        // Double attack
        if (this.passiveSkill1) {
            int doubleAttackChance = rand.nextInt(22)+1;
            if (doubleAttackChance <= this.luck) {
                gameView.updateActionListGUI(this.name+" triggered "+this.passiveSkill1Name+" skill !");
                monster.setCurrentHp(monster.getCurrentHp() + (monster.getArmor() - this.baseAtk));
            }
        }
        
        // Poison Attack
        if (this.passiveSkill2 && (monster.isPoisoned() == false)) {
            int poisonChance = rand.nextInt(22)+1;
            if (poisonChance <= this.luck) {
                monster.setPoisoned(true);
                gameView.updateActionListGUI(this.name+" throwed a poisoned knife !");
                createLoadingTime(1);
                gameView.updateActionListGUI(monster.getName()+" was poisoned");
                createLoadingTime(1);
                gameView.updateActionListGUI(monster.getName()+" will recieve 5 damages for 3 turns");
            }
        }
        
        if (monster.getCurrentHp() < 1) {
            monster.setCurrentHp(0);
        }
        
    }
    
}
