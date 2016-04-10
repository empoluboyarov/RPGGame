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
    private Monster currrentMonster;
    private int currentRound; // переменная считает количество раундов

    public GameClass(){
        initGame();
    }
    // метод инициализирует начальное состояние игры
    public void initGame() {
        heroPattern[0] = new Hero("Knight","Lancelot",100,10,5);
        heroPattern[1] = new Hero("Barbarian", "Konan",200,20,0);
        heroPattern[2] = new Hero("Dwarf", "Gimli",100,15,20);

        monsterPattern[0] = new Monster("Humanoid", "Goblin",50,5,1);
        monsterPattern[1] = new Monster("Humanoid", "Orc",80,6,2);
        monsterPattern[2] = new Monster("Humanoid", "Troll",90,5,1);

        currentRound = 1;
    }
    // метод отвечает за основную игровую логику
    public void mainGameLoop(){

        Scanner sc = new Scanner(System.in);
        int inpInt = 0;
        System.out.println("Игра началась");
        System.out.println("Выберите героя");
        for (int i = 0; i<3; i++ ){
            System.out.println((i+1)+". "+ heroPattern[i].getName());
        }
        inpInt = sc.nextInt();
        mainHero = (Hero)heroPattern[inpInt-1].clone();
        System.out.println("Вы выбрали " + mainHero.getName());
        currrentMonster = (Monster)monsterPattern[0].clone();

        do {
            System.out.println("Текущий раунд: " + currentRound);
            mainHero.showInfo();
            currrentMonster.showInfo();
            System.out.println("Ход игрока.");
            System.out.println("1.Атака  2.Защита  3.Пропустить ход  9.Завершить игру.");
            inpInt = sc.nextInt();
            // атакующие действия персонажа
            if (inpInt == 1){
                currrentMonster.getDamage(mainHero.makeAttack());
            }

            if (inpInt == 9) break;
            mainHero.getDamage(currrentMonster.makeAttack());
            currentRound++;
        } while (true);


    }

}
