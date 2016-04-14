import java.util.Random;
import java.util.Scanner;

/**
 * Created by Evgeniy on 10.04.2016.
 */
public class GameClass {

    private Hero[] heroPattern = new Hero[3];
    private Monster[] monsterPattern = new Monster[3];
    private Hero mainHero;
    private Monster currentMonster;
    private GameMap map;
    private InGameShop shop;
    private int currentRound; // переменная считает количество раундо
    private int inpInt;


    public GameClass(){
        initGame();
    }


    public void mainGameLoop() {

        map = new GameMap();
        shop = new InGameShop();
        inpInt = 0;
        System.out.println("Игра началась");
        selectHero();
        mainHero.setXY(10,3);
        map.buildDangMap(10,3);
        map.updateMap(mainHero.getPosX(),mainHero.getPosY());
        map.showMap();

        while (true){
            int x = getAction(1, 6, "Что Вы хотите делать дальше 1. Пойти влево; 2. Пойти вправо; 3. Пойти вверх; 4. Пойти вниз; 5. Найти монстров; 6. Отдохнуть");

            switch (x){
                case 1:
                    if(map.isCellEmpty(mainHero.getPosX() - 1, mainHero.getPosY()))
                        mainHero.moveHero(-1, 0);
                    break;
                case 2:
                    if(map.isCellEmpty(mainHero.getPosX() + 1, mainHero.getPosY()))
                        mainHero.moveHero(1, 0);
                    break;
                case 3:
                    if(map.isCellEmpty(mainHero.getPosX(), mainHero.getPosY() - 1))
                        mainHero.moveHero(0, -1);
                    break;
                case 4:
                    if(map.isCellEmpty(mainHero.getPosX(), mainHero.getPosY() + 1))
                        mainHero.moveHero(0, 1);
                    break;
                case 5:
                    currentMonster = (Monster) monsterPattern[(Utils.random.nextInt(3))].clone();
                    currentMonster.lvlUp(map.getDangerous(mainHero.getPosX(),mainHero.getPosY()));
                    battle(mainHero,currentMonster);
                    break;
                case 6:
                    mainHero.fullHeal();
                    break;
            }
            if (map.getObstValue(mainHero.getPosX(),mainHero.getPosY()) == 'S')
                shopAction();
            if (Utils.random.nextInt(100)<3){
                System.out.println("На Вас внезапно напали!!!");
                currentMonster = (Monster) monsterPattern[(Utils.random.nextInt(3))-1].clone();
                currentMonster.lvlUp(map.getDangerous(mainHero.getPosX(), mainHero.getPosY()));
                battle(mainHero, currentMonster);
            }
            map.updateMap(mainHero.getPosX(),mainHero.getPosY());
            map.showMap();
            if (!mainHero.isAlive())
                break;
        }
        System.out.println("Игра завершена");
    }


    private void selectHero() {
        String startText = "Выберите героя";
        System.out.println(startText);
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + heroPattern[i].getName());
        }
        inpInt = getAction(1,3,startText);
        mainHero = (Hero) heroPattern[inpInt - 1].clone();
        System.out.println(mainHero.getName() + " начал свое путешествие");
    }

    public void initGame() {
        heroPattern[0] = new Hero("Knight", "Lancelot", 16, 8, 12);
        heroPattern[1] = new Hero("Barbarian", "Konan", 16, 8, 12);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 16, 8, 12);

        monsterPattern[0] = new Monster("Humanoid", "Goblin", 12, 4, 4);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 18, 6, 6);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 32, 12, 10);

        currentRound = 1;
    }

    public void battle(Hero hero, Monster monster){
        currentRound = 1;
        System.out.println("Бой между игроком " + hero.getName() + " и монстром " + monster.getName() + " начался");

        do {
            System.out.println("Текущий раунд: " + currentRound);
            hero.showInfo();
            monster.showInfo();
            hero.makeNewRound();
            inpInt = getAction(0,3,"Ход игрока: 1.Атака  2.Защита  3.Покопаться в сумке  0.Завершить игру.");
            System.out.print("\n\n");
            if (inpInt == 1){
                monster.getDamage(hero.makeAttack());
                if (!monster.isAlive()){ // если монстр погиб от удара
                    System.out.println(monster.getName() + " погиб");
                    hero.addKillCounter();
                    hero.expGain(monster.getHpMax() * 2); // Даем герою опыта в размере (Здоровье_монстра * 2)
                    monster.myInv.transferAllItemsToAnotherInventory(hero.myInv);
                    break;
                }
            }

            if (inpInt == 2){
                hero.setBlockStance();
            }

            if (inpInt == 3){
                hero.myInv.showAllItems();
                int invInput = getAction(0, hero.myInv.getSize(), "Выберите предмет для использования");
                String usedItem = hero.myInv.useItem(invInput);
                if (usedItem != "") {
                    System.out.println(hero.getName() + " использовал " + usedItem);
                    hero.useItem(usedItem);
                } else
                    System.out.println(hero.getName() + " просто закрыл сумку");
            }

            if (inpInt == 0)
                break;

            // ход монстра
            monster.makeNewRound();
            if(Utils.random.nextInt(100) < 80)
                hero.getDamage(monster.makeAttack()); // По умолчанию, монстр всегда атакует
            else
                monster.setBlockStance();
            if (!hero.isAlive())
                break;

        } while (true);
        // В зависимости от того, кто остался в живых - выводим итоговое сообщение о результате игры
        if (monster.isAlive() && hero.isAlive())
            System.out.println(hero.getName() + " сбежал с поля боя");
        if (!monster.isAlive())
            System.out.println("Победил " + hero.getName());
        if (!hero.isAlive())
            System.out.println("Победил " + monster.getName());
    }

    public void shopAction(){
        shop.showItems();
        System.out.println("Для выхода из магазина нажмите 0");
        int x = getAction(0, shop.ITEMS_COUNT, "Введите номер покупаемого товара");
        if (x == 0) return;
        shop.buyByHero(x - 1, mainHero);
    }

    public int getAction(int min, int max, String str) {
        int x = 0;
        do {
            if(str != "" ) System.out.println(str);
            x = Utils.sc.nextInt();
        } while (x < min || x > max);
        return x;
    }
}
