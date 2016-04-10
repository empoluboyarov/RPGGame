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

    public GameClass(){
        initGame();
    }
    // метод инициализирует начальное состояние игры
    public void initGame() {
        heroPattern[0] = new Hero("Knight","Lancelot");
        heroPattern[1] = new Hero("Barbarian", "Konan");
        heroPattern[2] = new Hero("Dwarf", "Gimli");

        monsterPattern[0] = new Monster("Humanoid", "Goblin");
        monsterPattern[1] = new Monster("Humanoid", "Orc");
        monsterPattern[2] = new Monster("Humanoid", "Troll");
    }
    // метод отвечает за основную игровую логику
    public void mainGameLoop(){

        Scanner sc = new Scanner(System.in);
        int input = 0;
        System.out.println("Игра началась");
        System.out.println("Выберите героя");
        for (int i = 0; i<3; i++ ){
            System.out.println((i+1)+". "+ heroPattern[i].getName());
        }

    }

}
