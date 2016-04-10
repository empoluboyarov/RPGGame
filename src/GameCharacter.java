/**
 * Created by Evgeniy on 10.04.2016.
 */
public class GameCharacter implements Cloneable{

    protected String charClass;
    protected String name;
    protected int hp;
    protected int attack;
    protected int defence;
    protected int critChance; // вероятность нанесения критического урона

    public GameCharacter(String charClass, String name, int hp, int attack, int defence) {
        this.charClass = charClass;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
    }

    public String getName() {
        return name;
    }

    public void showInfo() {
        System.out.println("Имя: "+name +" Здоровье: " + hp);
    }

    public int makeAttack(){
        //атака варьируется в диапазоне 20% от значения attack
        int minAttack = (int)(0.8 * attack);
        int deltaAttack = (int)(0.4 * attack);
        int currentAttack = minAttack + GameClass.random.nextInt(deltaAttack);

        System.out.println(name + " нанес "+ currentAttack + " ед. урона.");
        return currentAttack;
    }

    public void getDamage(int inputDamage){
        System.out.println(name + " получил " + inputDamage + " ед. урона.");
        hp -= inputDamage;
    }

    @Override
    protected Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return this;
        }
    }
}
