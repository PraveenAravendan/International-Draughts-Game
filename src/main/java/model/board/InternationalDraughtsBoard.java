package main.java.model.board;

import main.java.gameIO.IModel;
import main.java.model.board.*;
import main.java.model.modelToView.*;
import main.java.model.move.*;
import main.java.model.utility.*;

public class InternationalDraughtsBoard extends ABoardModel{

    private int IN_ROW;
    int flag = 1;

    /**
     * Constructor for InternationalDraughtsBoard
     * @param i
     * @param j
     */
    public InternationalDraughtsBoard(int i, int j) {
        super(i, j);
        IN_ROW = 3;
    }


    /**
     * This function is used to make the move on the board for valid cases, it also changes the state of the board
     * @param row
     * @param col
     * @param i
     * @param icheckmovevisitor
     * @param iboardstatusvisitor
     * @return IUndoMove
     */
    public synchronized IUndoMove makeMove(final int row, final int col, int i, ICheckMoveVisitor icheckmovevisitor, IBoardStatusVisitor iboardstatusvisitor) {
        if (isValidMove(i, row,col)){
            if (flag<22){
                chgState(0);
            }
            else{
//                chgState(checkWin(row, col));
            }
            flag+=1;
            icheckmovevisitor.validMoveCase();
            super.execute(iboardstatusvisitor, null);
            return new IUndoMove() {
                public void apply(IUndoVisitor iundovisitor) {
                    undoMove(row, col, iundovisitor);
                }
            };
        } else{
            icheckmovevisitor.invalidMoveCase();
            return new IUndoMove() {
                public void apply(IUndoVisitor undoVisitor) {
                }
            };
        }
    }

    public synchronized void undoMove(int i, int j, IUndoVisitor iundovisitor) {
        int k = super.cells[i][j];
        if (k == 0){
            iundovisitor.noTokenCase();
        } else{
            super.cells[i][j] = 0;
            iundovisitor.tokenCase((k + 1) / 2);
        }
        super.state = NonTerminalState.Singleton;
    }
    public boolean isValidMove(int i, int j, int k) {
        return 0 == super.cells[j][k];
    }

    /**
     * This function is used to format the output
     */
    public void printNumbers(){
        System.out.print("   ");
        for(int i=0; i<=9; i++){
            System.out.print(" "+ i + " ");
        }
    }
}
