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
        
        if (this.passiveSkill1) {
            int doubleAttackChance = rand.nextInt(22)+1;
            if (doubleAttackChance <= this.luck) {
                System.out.println(this.name+" triggered "+this.passiveSkill1Name+" skill !\n");
                monster.setCurrentHp(monster.getCurrentHp() + (monster.getArmor() - this.baseAtk));
            }
        }
        
        if (this.passiveSkill2 && (monster.isPoisoned() == false)) {
            int poisonChance = rand.nextInt(22)+1;
            if (poisonChance <= this.luck) {
                monster.setPoisoned(true);
                System.out.println(this.name+" throwed a poisoned knife !\n");
                createLoadingTime(1);
                System.out.println(monster.getName()+" was poisoned\n");
                createLoadingTime(1);
                System.out.println(monster.getName()+" will recieve 5 damages for 3 turns\n");
            }
        }
        
        if (monster.getCurrentHp() < 1) {
            monster.setCurrentHp(0);
        }
        
    }
    
}
