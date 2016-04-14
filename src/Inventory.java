import java.util.ArrayList;

/**
 * Created by Evgeniy on 12.04.2016.
 */
public class Inventory {

    private int gold;
    private ArrayList<Item> inv;

    public Inventory() {
        gold = 0;
        inv = new ArrayList<>();
    }

    public void addSomeCoins(int amount) {
        gold += amount;
    }

    public void spendSomeCoins(int amount){
        gold-=amount;
    }

    public boolean isCoinsEnough(int amount) {
        if (gold >= amount) {
            return true;
        }
        return false;
    }

    public void add (Item newItem){
        inv.add(newItem);
    }

    public int getSize(){
        return inv.size();
    }

    public String useItem(int itemId){
        if (itemId == 0)
            return "";
        String item = inv.get(itemId-1).getName();
        if(inv.get(itemId-1).getType() == Item.ItemType.Consumables){
            inv.remove(itemId-1);
        }
        return item;
    }

    public void showAllItems(){
        System.out.println("Инвентарь: ");
        System.out.println("0. Закончить осмотр");
        if (inv.size()>0){
            for (int i = 0; i < inv.size(); i++)
                System.out.println((i+1)+". " + inv.get(i).getName());
        } else System.out.println("Инвентарь пуст. ");
        System.out.println("Золото: " + gold);
    }

    public void transferAllItemsToAnotherInventory(Inventory inventory){
        for (int i = 0; i< inv.size(); i++){
            inventory.add(inv.get(i));
            inventory.addSomeCoins(gold);
            System.out.println("Герой забрал у убитого монстра "+ gold+" золота и припасы. ");
        }
    }

}
