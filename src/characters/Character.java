/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import gui.GameView;
import java.util.Random;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class is the highest abstract class for all characters
 */
public abstract class Character {
    
    protected int x_position;
    protected int y_position;
    protected int fullHp;
    protected int currentHp;
    protected int baseAtk;
    protected int armor;
    protected int luck;
    protected int level;
    protected int exp;
    protected String name;
    protected boolean defendStatus;
    protected boolean chargeAttack;
    protected boolean stunned;
    protected boolean poisoned;
    
    protected GameView gameView;

    public int getX_position() {
        return x_position;
    }
    public void setX_position(int x_position) {
        this.x_position = x_position;
    }
    public int getY_position() {
        return y_position;
    }
    public void setY_position(int y_position) {
        this.y_position = y_position;
    }
    public int getFullHp() {
        return fullHp;
    }
    public void setFullHp(int fullHp) {
        this.fullHp = fullHp;
    }
    public int getCurrentHp() {
        return currentHp;
    }
    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }
    public int getBaseAtk() {
        return baseAtk;
    }
    public void setBaseAtk(int baseAtk) {
        this.baseAtk = baseAtk;
    }
    public int getArmor() {
        return armor;
    }
    public void setArmor(int armor) {
        this.armor = armor;
    }
    public int getLuck() {
        return luck;
    }
    public void setLuck(int luck) {
        this.luck = luck;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getExp() {
        return exp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isDefendStatus() {
        return defendStatus;
    }
    public void setDefendStatus(boolean defendStatus) {
        this.defendStatus = defendStatus;
    }
    public boolean isChargeAttack() {
        return chargeAttack;
    }
    public void setChargeAttack(boolean chargeAttack) {
        this.chargeAttack = chargeAttack;
    }
    public boolean isStunned() {
        return stunned;
    }
    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }
    public boolean isPoisoned() {
        return poisoned;
    }
    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }
    
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
    
    // Return character's current status
    public String[] showStatus() {
        
        String[] status = new String[6];
        
        status[0] = "  NAME    :     " + String.format("%-16s", this.name);
        status[1] = "  LVL        :     " + String.format("%-16d", this.level);
        status[2] = "  HP         :     " + String.format("%2d", this.currentHp) + "/" + String.format("%-13d", this.fullHp);
        status[3] = "  ATK       :     " + String.format("%-16d", this.baseAtk);
        status[4] = "  ARMOR  :     " + String.format("%-16d", this.armor);
        status[5] = "  LUCK     :     " + String.format("%-16d", this.luck);
        
        return status;
    }
    
    // For each incoming attack
    // All Character have chance to block attack
    public boolean blockAttacking()
    {
        Random ran = new Random();
        
        boolean blockSuccess = false;
        int chance = ran.nextInt(20)+1;
        if (chance <= this.luck) {
            blockSuccess = true;
        }
        return blockSuccess;
    }
    
    public void createLoadingTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
