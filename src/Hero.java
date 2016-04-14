/**
 * Created by Evgeniy on 10.04.2016.
 */
public class Hero extends GameCharacter {

    private int currentExp;
    private int expToNextLevel;
    private int killedMonsters;

    private int currentZone;
    private int posX;
    private int posY;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setXY(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void moveHero(int vx, int vy){
        posX += vx;
        posY += vy;
    }

    public int getZoneDangerous(){
        return currentZone;
    }

    public void goToDangerousZone(){
        currentZone++;
        System.out.println("Герой перешел в зону опасности " + currentZone);
    }



    public Hero(String charClass, String name, int strength, int dexterity, int endurance) {
        super(charClass, name, strength, dexterity, endurance);
        currentExp = 0;
        currentZone = 0;
        expToNextLevel = 1000;
        killedMonsters = 0;
        myInv = new Inventory();
        myInv.add(new Item("Слабый камень здоровья", Item.ItemType.InfConsumables));
        myInv.add(new Item("Слабое зелье лечения", Item.ItemType.Consumables));
        myInv.addSomeCoins(1000);
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
