import java.util.Random;
import java.util.Scanner;

/**
 * Created by Evgeniy on 10.04.2016.
 */
public class GameClass {

    public static Random random = new Random(); //генератор случайных чисел

    private Hero[] heroPattern = new Hero[3];
    private Monster[] monsterPattern = new Monster[3];

    private Hero mainHero;
    private Monster currentMonster;
    private int currentRound; // переменная считает количество раундов
    private Scanner sc = new Scanner(System.in);

    public GameClass(){
        initGame();
    }
    // метод инициализирует начальное состояние игры
    public void initGame() {
        heroPattern[0] = new Hero("Knight", "Lancelot", 600, 30, 12);
        heroPattern[1] = new Hero("Barbarian", "Konan", 600, 50, 0);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 600, 20, 25);

        monsterPattern[0] = new Monster("Humanoid", "Goblin", 120, 30, 2);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 240, 50, 2);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 400, 25, 5);

        currentRound = 1;
    }
    // метод отвечает за основную игровую логику
    public void mainGameLoop() {
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! необходимо реализовать метод проверки введения правильности данных

        int inpInt = 0;
        System.out.println("Игра началась");
        String startText = "Выберите героя";
        System.out.println(startText);
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + heroPattern[i].getName());
        }
        inpInt = getAction(1,3,startText);


        mainHero = (Hero) heroPattern[inpInt - 1].clone();
        System.out.println("Вы выбрали " + mainHero.getName());
        currentMonster = (Monster) monsterPattern[random.nextInt(3)].clone();

        do {
            System.out.println("Текущий раунд: " + currentRound);
            mainHero.showInfo();
            currentMonster.showInfo();

            mainHero.makeNewRound();
// ход игрока

            inpInt = getAction(0,3,"Ход игрока: 1.Атака  2.Защита  3.Пропустить ход  0.Завершить игру.");
            System.out.print("\n\n");

            if (inpInt == 1){
                currentMonster.getDamage(mainHero.makeAttack());
                if (!currentMonster.isAlive()){ // если монстр погиб от удара
                    System.out.println(currentMonster.getName() + " погиб");
                    mainHero.expGain(currentMonster.getHpMax() * 2); // Даем герою опыта в размере (Здоровье_монстра * 2)
                    currentMonster = (Monster)monsterPattern[random.nextInt(3)].clone(); // Создаем нового монстра случайного типа, копируя из шаблона
                    System.out.println("На поле боя выходит " + currentMonster.getName()); // Выводим сообщение о выходе нового врага на поле боя
                }
            }

            if (inpInt == 2){
                mainHero.setBlockStance();
            }

            if (inpInt == 0)
                break;

            // ход монстра
            currentMonster.makeNewRound();
            mainHero.getDamage(currentMonster.makeAttack());
            if (!mainHero.isAlive())
                break;

        } while (true);
        // В зависимости от того, кто остался в живых - выводим итоговое сообщение о результате игры
        if (currentMonster.isAlive() && mainHero.isAlive())
            System.out.println(mainHero.getName() + " сбежал с поля боя");
        if (!currentMonster.isAlive())
            System.out.println("Победил " + mainHero.getName());
        if (!mainHero.isAlive())
            System.out.println("Победил " + currentMonster.getName());
        System.out.println("Игра завершена");
    }


    public int getAction(int min, int max, String str) {
        int x = 0;
        do {
            if(str != "" ) System.out.println(str);
            x = sc.nextInt();
        } while (x < min || x > max);
        return x;
    }
}
