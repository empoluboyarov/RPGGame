import java.util.ArrayList;

/**
 * Created by Evgeniy on 12.04.2016.
 */
public class Inventory {

    private ArrayList<String> inv;

    public Inventory() {
        inv = new ArrayList<String>();
    }

    public void add (String newItem){
        inv.add(newItem);
    }

    public int getSize(){
        return inv.size();
    }

    public String useItem(int itemId){
        if (itemId == 0)
            return "";
        String item = inv.get(itemId-1);
        inv.remove(itemId-1);
        return item;
    }

    public void showAllItems(){
        System.out.println("Инвентарь: ");
        if (inv.size()>0){
            for (int i = 0; i < inv.size(); i++)
                System.out.println((i+1)+". " + inv.get(i));
        } else System.out.println("Инвентарь пуст. ");

    }


}
