/**
 * Created by Evgeniy on 14.04.2016.
 */
public class Item {

    public static enum ItemType {Consumables, InfConsumables, Quest, Armor, Weapon}

    private String name;
    private ItemType type;

    public Item(String name, ItemType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }
}
