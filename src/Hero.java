/**
 * Created by Evgeniy on 10.04.2016.
 */
public class Hero extends GameCharacter {

    private int currentExp;
    private int expToNextLevel;
    private int killedMonsters;
    public Inventory myInv;

    public Hero(String charClass, String name, int strength, int dexterity, int endurance) {
            super(charClass, name, strength, dexterity, endurance);
            currentExp = 0;
            expToNextLevel = 1000;
            killedMonsters = 0;
            myInv = new Inventory();
            myInv.add("Слабое зелье лечения");
        }

    // метод увеличения опыта главного героя
    public void expGain(int exp){
        currentExp += exp;
        System.out.println(name + " получил " + exp + " ед. опыта. До следующего уровня осталось " +(expToNextLevel-currentExp)+  "ед. опыта.");
        if(currentExp > expToNextLevel){ // если набрали необхлодимый опыт, то повышаем уровень
            currentExp -= expToNextLevel;
            expToNextLevel *= 2;
            level++;
            attack += 5;
            hpMax += 50;
            strength += 2;
            dexterity += 2;
            endurance += 1;
            calculateSecondaryParameters();
            hp = hpMax;
            System.out.println(name + " повысил уровень до " + level);
        }
    }

    public void addKillCounter() {
        killedMonsters++;
    }

    @Override
    public void showInfo() {
        System.out.println("Имя: " + name + " Здоровье: " + hp + "/" + hpMax + " Уровень: " + level + "[" +currentExp + " / " + expToNextLevel + "]" + " Убито монстров: "+killedMonsters);
    }
}
