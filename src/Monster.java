import java.util.ArrayList;

/**
 * Created by Evgeniy on 10.04.2016.
 */
public class Monster extends GameCharacter {

    public Monster(String charClass, String name, int strength, int dexterity, int endurance) {
        super(charClass, name, strength, dexterity, endurance);
        myInv = new Inventory();
        myInv.add(new Item("Слабое зелье лечения", Item.ItemType.Consumables));
        myInv.addSomeCoins(100);
    }

    public void lvlUp(int level){
        for (int i = 0; i < level; i++){
            strength += base_strength*0.3f;
            dexterity += base_dexterity*0.3f;
            endurance += base_endurance*0.3f;
        }
        calculateSecondaryParameters();
        hp = hpMax;
        showInfo();
    }
}
