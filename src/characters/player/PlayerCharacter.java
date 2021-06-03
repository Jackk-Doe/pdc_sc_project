/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters.player;

import characters.Character;
import items.HpPotion;
import characters.monsters.MonsterCharacter;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author sengthavongphilavong
 */
public abstract class PlayerCharacter extends Character{
    
    Random rand = new Random();
    
    protected boolean passiveSkill1;
    protected boolean passiveSkill2;
    
    protected String passiveSkill1Name;
    protected String passiveSkill2Name;
    protected String job;
    protected ArrayList<HpPotion> carryingItems;
    
    public PlayerCharacter() {
        this.x_position = 1;
        this.y_position = 1;
        this.defendStatus = false;
        this.chargeAttack = false;
        this.stunned = false;
        this.poisoned = false;
        this.passiveSkill1 = false;
        this.passiveSkill2 = false;
        carryingItems = new ArrayList<HpPotion>();
    }

    public String getJob() {
        return job;
    }

    public ArrayList<HpPotion> getCarryingItems() {
        return carryingItems;
    }
    
    abstract public boolean gainExp(int exp);
    
    abstract public void attack(MonsterCharacter monster);
    
    public boolean checkPotionStock()
    {
        return !carryingItems.isEmpty();
    }
    
    public void usingPotion()
    {
        this.currentHp += carryingItems.get(0).getHpPotionPoint();
            carryingItems.remove(0);
            
            if (this.currentHp > this.fullHp) {
                this.currentHp = this.fullHp;
            }
    }

    @Override
    public void showStatus() {
        String status = "+-----------------------+\n";
        status += "| NAME  : " + String.format("%-14s", this.name) + "|\n";
        status += "| JOB   : " + String.format("%-14s", this.job) + "|\n";
        status += "| LVL   : " + String.format("%-14d", this.level) + "|\n";
        status += "| HP    : " + String.format("%2d", this.currentHp) + "/" + String.format("%-11d", this.fullHp) + "|\n";
        status += "| ATK   : " + String.format("%-14d", this.baseAtk) + "|\n";
        status += "| ARMOR : " + String.format("%-14d", this.armor) + "|\n";
        status += "| LUCK  : " + String.format("%-14d", this.luck) + "|\n";
        status += "| POTION: " + String.format("%-14d", carryingItems.size()) + "|\n";
        if (passiveSkill1) {
            status += "| SKILL : " + String.format("%-14s", this.passiveSkill1Name) + "|\n";
        }
        if (passiveSkill2) {
            status += "| SKILL : " + String.format("%-14s", this.passiveSkill2Name) + "|\n";
        }
        status += "+-----------------------+";
        System.out.println(status);
        
    }
    
    @Override
    public String[] newedShowStatus() {
        
        String[] status = new String[10];
        
        status[0] = "  NAME    :     " + String.format("%-14s", this.name);
        status[1] = "  JOB        :     " + String.format("%-14s", this.job);
        status[2] = "  LVL        :     " + String.format("%-14d", this.level);
        status[3] = "  HP         :     " + String.format("%2d", this.currentHp) + "/" + String.format("%-11d", this.fullHp);
        status[4] = "  ATK       :     " + String.format("%-14d", this.baseAtk);
        status[5] = "  ARMOR  :     " + String.format("%-14d", this.armor);
        status[6] = "  LUCK     :     " + String.format("%-14d", this.luck);
        status[7] = "  POTION :     " + String.format("%-14d", carryingItems.size());
        if (passiveSkill1) {
            status[8] = "  SKILL      :    " + String.format("%-14s", this.passiveSkill1Name);
        }
        else {
            status[8] = "  SKILL      :    NONE";
        }
        if (passiveSkill2) {
            status[9] = "  SKILL      :    " + String.format("%-14s", this.passiveSkill2Name);
        }
        else {
            status[9] = "  SKILL      :    NONE";
        }
        
        return status;
    }
    
    public boolean checkItemDrop()
    {
        boolean droppedItem = false;
        int dropChance = rand.nextInt(18)+1;
        
        if (dropChance <= luck) {
            carryingItems.add(new HpPotion());
            droppedItem = true;
        }
        return droppedItem;
    }

    public void afterBattleStatusUpdate(int exp) {
        
        boolean lvlUpCheck = gainExp(exp);
        if (lvlUpCheck) {
            if (this.level == 5) {
                System.out.println(this.name+" has maxed "+this.job+" LEVEL\n");
            } else {
                System.out.println(this.name+" has level-upped !\n");
            }
            createLoadingTime(1);
            
            if (this.passiveSkill1 && this.level == 3) {
                System.out.println(this.name+" has learned new passive skill : "
                        +this.passiveSkill1Name+"\n");
                createLoadingTime(1);
            }
            
            if (this.passiveSkill2 && this.level == 5) {
                System.out.println(this.name+" has learned new passive skill : "
                        +this.passiveSkill2Name+"\n");
                createLoadingTime(1);
            }
        }
        
        boolean itemDropCheck = checkItemDrop();
        if (itemDropCheck) {
            System.out.println("Item was dropped from the monster\n");
            createLoadingTime(2);
            System.out.println(this.name+" recieved 1 potion item\n");
            createLoadingTime(2);
        }
        
        System.out.println("Updated "+this.name+" status: ");
        createLoadingTime(2);
        showStatus();
        createLoadingTime(2);
    }
}
