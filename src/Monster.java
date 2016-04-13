import java.util.ArrayList;

/**
 * Created by Evgeniy on 10.04.2016.
 */
public class Monster extends GameCharacter {

    private ArrayList<String> loot;
    private int lootIndex;

    public Monster(String charClass, String name, int strength, int dexterity, int endurance)
    {
        super(charClass, name, strength, dexterity, endurance);
        loot = createLoot();
    }

    private ArrayList<String> createLoot() {
        loot = new ArrayList<String>();
        loot.add(0, "Слабое зелье лечения");
        loot.add(1, "Среднее зелье лечения");
        loot.add(2, "Сильное зелье лечения");
        return loot;
    }

    public String getLoot() {

        int loots = GameClass.random.nextInt(100);

        if (loots <=100 && loots > 50 ) {
            lootIndex = 0;
        } else if (loots <= 50 && loots >= 15){
            lootIndex = 1;
        } else lootIndex = 2;

        System.out.println("От убитого монстра остается " + loot.get(lootIndex));

        return loot.get(lootIndex);
    }

}
