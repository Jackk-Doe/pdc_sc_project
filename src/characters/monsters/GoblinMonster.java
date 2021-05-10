/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters.monsters;

/**
 *
 * @author sengthavongphilavong
 */
public class GoblinMonster extends MonsterCharacter{
    
    public GoblinMonster(int x, int y) {
        super();
        this.name = "Goblin";
        this.x_position = x;
        this.y_position = y;
        this.fullHp = 25;
        this.currentHp = fullHp;
        this.baseAtk = 8;
        this.armor = 2;
        this.luck = 5;
        this.level = 2;
        this.exp = 15;
    }
    
    public GoblinMonster() {
        super();
        this.name = "Goblin";
        this.x_position = rand.nextInt(35)+1;
        this.y_position = rand.nextInt(10)+10;
        this.fullHp = 25;
        this.currentHp = fullHp;
        this.baseAtk = 8;
        this.armor = 2;
        this.luck = 5;
        this.level = 2;
        this.exp = 15;
    }
}
