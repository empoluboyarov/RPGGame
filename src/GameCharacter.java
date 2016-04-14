/**
 * Created by Evgeniy on 10.04.2016.
 */
public class GameCharacter implements Cloneable{

    protected String name;
    protected String charClass;

    protected int base_strength; // Primary Stats
    protected int base_dexterity;
    protected int base_endurance;

    protected int strength; // Primary Stats
    protected int dexterity;
    protected int endurance;

    protected int hpMax; // Secondary stats

    protected int attack;
    protected int defense;
    protected int critChance;
    protected float critMultiplier;
    protected int avoidChance;

    protected int level;
    protected int hp;
    protected boolean blockStance;
    protected boolean life;

    protected Inventory myInv;

    public GameCharacter(String charClass, String name, int strength, int dexterity, int endurance) {
        this.name = name;
        this.charClass = charClass;
        this.strength = strength;
        this.dexterity = dexterity;
        this.endurance = endurance;
        base_strength = strength;
        base_dexterity = dexterity;
        base_endurance = endurance;
        calculateSecondaryParameters();
        level = 1;
        hp = hpMax;
        life = true;
        blockStance = false;
    }

    protected void calculateSecondaryParameters() {
        attack = strength * 2;
        hpMax = endurance * 50;
        defense = (int)((strength + dexterity) / 4.0f);
        critChance = dexterity;
        critMultiplier = 1.2f + (dexterity / 20.0f);
        avoidChance = 8 + (int)(dexterity / 5.0f);
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
        int currentAttack = minAttack + Utils.random.nextInt(deltaAttack);
        if(Utils.random.nextInt(100) <= critChance) { // проверяем вероятнось нанесения критического урона
            currentAttack = (int)(currentAttack*critMultiplier);
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

        if(Utils.random.nextInt(100) < avoidChance) {
            System.out.println(name + " увернулся от атаки");
        } else {
            inputDamage -= Utils.random.nextInt(defense);
            if (blockStance){
                System.out.println(name + " дополнительно заблокировал чать урона в защитной стойке.");
                inputDamage -= Utils.random.nextInt(defense);}
            if (inputDamage<0)
                inputDamage = 0; // проверка на отрицательный урон для предотвращения эффекта лечения
            System.out.println(name + " получил " + inputDamage + " ед. урона.");
            hp -= inputDamage;
            if (hp <1)
                life = false;
        }
    }

    public void useItem(String item) {
        switch(item) {
            case "Слабое зелье лечения":
                cure(120);
                System.out.println(name + " пополнил здоровье на 120 ед.");
                break;

            case "Слабый камень здоровья":
                cure(60);
                System.out.println(name + " пополнил здоровье на 60 ед.");
                break;
        }
    }

    private void cure(int val) {
        hp += val;
        if(hp>hpMax) hp = hpMax;
    }

    public void fullHeal(){
        hp = hpMax;
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
