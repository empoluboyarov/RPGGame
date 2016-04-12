/**
 * Created by Evgeniy on 10.04.2016.
 */
public class GameCharacter implements Cloneable{

    protected String charClass;
    protected String name;
    protected int hp;
    protected int hpMax; // сколько здоровья было изначально
    protected int attack;
    protected int defence;
    protected int critChance; // вероятность нанесения критического урона
    protected int level;
    protected boolean blockStance;
    
    protected boolean life;

    public GameCharacter(String charClass, String name, int hp, int attack, int defence) {
        this.charClass = charClass;
        this.name = name;
        this.hp = hp;
        hpMax = hp;
        this.attack = attack;
        this.defence = defence;
        critChance = 10; // вероятность нанесения критического урона
        life = true;
        level = 1;
        blockStance = false;
    }

    public void setBlockStance(){
        blockStance = true;
        System.out.println(name + " встал в защитную стойку");
    }

    public void makeNewRound(){
        blockStance = false;
    }

    public void showInfo() {
        System.out.println("Имя: "+name +". Здоровье: " + hp+"/"+hpMax+".");
    }

    public int makeAttack(){
        //атака варьируется в диапазоне 20% от значения attack
        int minAttack = (int)(0.8f * attack);
        int deltaAttack = (int)(0.4f * attack);
        int currentAttack = minAttack + GameClass.random.nextInt(deltaAttack);
        if(GameClass.random.nextInt(100) <= critChance) { // проверяем вероятнось нанесения критического урона
            currentAttack = currentAttack*2;
            System.out.println(name + " нанес критический урон в размере"+ currentAttack + " ед. урона.");
        } else System.out.println(name + " нанес "+ currentAttack + " ед. урона.");
        return currentAttack;
    }

    public int getHpMax() {
        return hpMax;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return life;
    }

    public void getDamage(int inputDamage){

        inputDamage -= GameClass.random.nextInt(defence);
        if (blockStance){
            System.out.println(name + " дополнительно заблокировал чать урона в защитной стойке.");
            inputDamage -= GameClass.random.nextInt(defence);
        }
        if (inputDamage<0) inputDamage = 0; // проверка на отрицательный урон для предотвращения эффекта лечения

        System.out.println(name + " получил " + inputDamage + " ед. урона.");
        hp -= inputDamage;
        if (hp <1)
            life = false;
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
