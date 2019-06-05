import java.util.*;

/**
 * Created by Amir on 19/06/2017.
 */
public class Core {

    //this method gives the index of the last empty square
    private static int getIndexOfEmpty(int[][] a, int col) {
        int index = 0;
        int[] temp = new int[6];
        for (int i = 0; i < 6; i++)
            temp[i] = a[i][col];
        if (temp[5] == 0) {
            index = 6;
        } else {
            while (temp[index] == 0)
                index++;
        }
        return index - 1;
    }

    // fills the column if he could andhe did it he returns true and if he cant he returns false
    public static boolean fill(int[][] playGround, int column, int playersNumber) {
        boolean res = false;
        if (playGround[0][column] != 0) {
            System.out.println("this column is full !");
            return res;
        }
        if (getIndexOfEmpty(playGround, column) != -1) {//means its not full
            playGround[getIndexOfEmpty(playGround, column)][column] = playersNumber;
            res = true;
        } else
            System.out.println("this colum is full");
        return res;
    }
    public static int getWinner(int[][] playGround) {


            final int HEIGHT = playGround.length;
            final int WIDTH = playGround[0].length;
            for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
                for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                    int player = playGround[r][c];
                    if (player == 0)
                        continue; // don't check empty slots

                    if (c + 3 < WIDTH &&
                            player == playGround[r][c+1] && // look for winner in row
                            player == playGround[r][c+2] &&
                            player == playGround[r][c+3])
                        return player;
                    if (r + 3 < HEIGHT) {
                        if (player == playGround[r+1][c] && // look for winner in column
                                player == playGround[r+2][c] &&
                                player == playGround[r+3][c])
                            return player;
                        if (c + 3 < WIDTH &&
                                player == playGround[r+1][c+1] && // look up & right
                                player == playGround[r+2][c+2] &&
                                player == playGround[r+3][c+3])
                            return player;
                        if (c - 3 >= 0 &&
                                player == playGround[r+1][c-1] && // look up & left
                                player == playGround[r+2][c-2] &&
                                player == playGround[r+3][c-3])
                            return player;
                    }
                }
            }
            // checks if the game is tie or no!
            int count=0;
            for(int i=0;i<WIDTH;i++){
                if(playGround[0][i]!=0)
                    count++;
            }
            if(count==WIDTH){
                return 2;

            }
            return 0; // the game should be continued ...

    }
}
