/**
 * Created by Evgeniy on 10.04.2016.
 */
public class GameCharacter {

    protected String charClass;
    protected String name;

    public GameCharacter(String charClass, String name) {
        this.charClass = charClass;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void showInfo(){

    }
}
