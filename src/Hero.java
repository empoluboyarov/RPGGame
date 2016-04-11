/**
 * Created by Evgeniy on 10.04.2016.
 */
public class Hero extends GameCharacter {

    private int currentExp;
    private int expToNextLevel;

    public Hero(String charClass, String name, int xp, int attack, int defence) {

        super(charClass, name, xp, attack, defence);
        initHero();
    }

    private void initHero(){
        currentExp = 0;
        expToNextLevel = 1000;
    }
    // метод увеличения опыта главного героя
    public void expGain(int exp){
        currentExp += exp;
        System.out.println(name + " получил " + exp + " ед. опыта. До следующего уровня осталось " +(expToNextLevel-currentExp)+  "ед. опыта.");
        if(currentExp > expToNextLevel){
            currentExp -= expToNextLevel;  // остаток эксы переносится на новый уровень
            expToNextLevel *= 2;   // кол-во опыта для роста уровня увеличивается в два раза
            level++;
            hp = hpMax;
            attack += 5;
            System.out.println(name + " повысил уровень до " + level);
        }
    }
}
