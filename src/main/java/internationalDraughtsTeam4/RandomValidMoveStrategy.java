package main.java.internationalDraughtsTeam4;

import main.java.gameIO.IModel;
import main.java.model.move.INextMoveStrategy;
import main.java.model.utility.Point;
import java.util.Random;

public class RandomValidMoveStrategy implements INextMoveStrategy {

    @Override
    public Point getNextMove(IModel context, int player) {
        return null;
    }

    /**
     * This function is used to obtain random values for the computer to perform moves on the board
     * @return int[]
     */
    public int[] getRandVal(){
        int x =10, y = 10;
        Random random = new Random();
        int[] randVal = {random.nextInt(x), random.nextInt(y)};
        return randVal;
    }
}
