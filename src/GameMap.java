/**
 * Created by Evgeniy on 14.04.2016.
 */
public class GameMap {

    private final int msx = 20;
    private final int msy = 6;
    private String[][] map = new String[msy][msx];
    private int[][] dangMap = new int[msy][msx];
    private char[][] obstMap = new char[msy][msx];

    public GameMap() {
        obstMap[3][5] = 'S';
        obstMap[4][8] = 'X';
    }

    public char getObstValue(int x, int y){
        return obstMap[y][x];
    }

    public int getDangerous(int x, int y){
        return dangMap[y][x];
    }

    public boolean isCellEmpty(int x, int y){
        if (x < 0 || y < 0 || x > msx-1 || y > msy-1)
            return false;
        if (obstMap[y][x] == 'X')
            return false;
        return true;
    }

    public void updateMap(int hx, int hy){
        for (int i = 0; i < msy; i++){
            for (int j = 0; j < msx; j++){
                map[i][j] = "*";
                if (obstMap[i][j] == 'S')
                    map[i][j] = "S";
                if (obstMap[i][j] == 'X')
                    map[i][j] = "X";
            }
        }
        map[hy][hx] = "H";
    }

    public void showMap() {
        for (int i = 0; i < msy; i++){
            for (int j = 0; j< msx; j++){
                System.out.println(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void buildDangMap(int cx, int cy) {
        for (int i = 0; i <msy; i++){
            for (int j = 0; j < msx; j++){
                int dng = (int) Math.sqrt(Math.pow(cy - i, 2) + Math.pow(cx - j, 2));
                dangMap[i][j] = dng;
            }
        }
    }
}
