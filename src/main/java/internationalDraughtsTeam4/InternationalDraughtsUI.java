package main.java.internationalDraughtsTeam4;

import java.util.*;

import main.java.gameIO.IModel;
import main.java.gameIO.IView;
import main.java.model.board.ABoardModel;
import main.java.model.board.InternationalDraughtsBoard;
import main.java.model.modelToView.ICommand;
import main.java.model.modelToView.IRejectCommand;
import main.java.model.modelToView.IViewManager;
import main.java.model.move.IBoardStatusVisitor;
import main.java.model.move.ICheckMoveVisitor;
import main.java.model.move.IUndoMove;
import main.java.model.utility.Dimension;
import main.java.model.viewToModel.IModelManager;
import main.java.model.viewToModel.IViewRequestor;

public class InternationalDraughtsUI implements IView, IViewManager, ICommand {

    String[] symbolStr = {"B", "W"};
    IModelManager iModelManager;
    IViewRequestor iViewRequestor;
    List<Object> players;
    Dimension size = new Dimension(3);
    String[][] cells = new String[3][3];
    static Scanner input = new Scanner(System.in);
    public static int row,col;
    int flag1 = 1;
    public static int piecePositionX;
    public static int piecePositionY;
    public static int rowInit;
    public static int colInit;
    public static int blackScore = 0;
    public static int whiteScore = 0;
    public static int randVal = 0;
    RandomValidMoveStrategy randomValidMove = new RandomValidMoveStrategy();
    InternationalDraughtsBoard aBoard = new InternationalDraughtsBoard(10,10);
    Random random = new Random();

    /**
     * This function is used to print the InternationalDraughtsBoard.
     * Every time a change is made to the pieces on the board, it can be displayed by calling this function.
     */
    public void print() {
        System.out.println("Printing the board! ");
        aBoard.printNumbers();
        System.out.println();
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                if(j==0){
                    System.out.print(" " + i + " ");
                }
                if(cells[i][j]==null){
                    cells[i][j] = " ";
                    System.out.print(" " + "-" + " ");
                }else if(cells[i][j]!=null && cells[i][j].equals(" ")){
                    System.out.print(" " + "-" + " ");
                }else{
                    System.out.print(" " + symbolStr[Integer.parseInt(cells[i][j])] + " ");
                }
            }
            System.out.println();
        }
    }



    public IModelManager getiModelManager() {
        return iModelManager;
    }

    public void setiModelManager(IModelManager iModelManager) {
        this.iModelManager = iModelManager;
    }

    public IViewRequestor getiViewRequestor() {
        return iViewRequestor;
    }


    /**
     * This method is used to place tokens on the board for valid moves, for invalid moves a valid value is requested again
     * @param iViewRequestor
     */
    @Override
    public void setiViewRequestor(IViewRequestor iViewRequestor) {
        this.iViewRequestor = iViewRequestor;

            if (flag1 == 1) {
                setBinitaltokens(iViewRequestor);
                System.out.println("Enter one of the following numbers 1.Human Player, 2. Random Strategy(Computer Player):");
                randVal = Integer.parseInt(input.next());
            }
            flag1 += 1;
            if (flag1 == 3) {
                clearTokenAt(rowInit, colInit);
                setWInitialTokens(iViewRequestor);
            }

            if (flag1 == 2) {
                System.out.println("White Player plays first");
                System.out.println("Black Player plays second");
                System.out.println("Enter S to start game and place all pieces on the board");
                String start_val = input.next();
                row = 5;
                col = 5;
            }
            else {
                System.out.println("Enter the row value of the piece which you want to move: ");
                piecePositionX = Integer.parseInt(input.next());
                System.out.println("Enter the column value of the piece which you want to move: ");
                piecePositionY = Integer.parseInt(input.next());


                if (cells[piecePositionX][piecePositionY].equals("0")) {
                    if(randVal != 1){
                        row = randomValidMove.getRandVal()[0];
                        col = randomValidMove.getRandVal()[1];
                        while(true){
                            row = randomValidMove.getRandVal()[0];
                            col = randomValidMove.getRandVal()[1];
                            if(validRandomMoveCheck(piecePositionX, piecePositionY, row, col)){
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("please enter a row value?");
                        row = Integer.parseInt(input.next());
                        System.out.println("please enter a col value?");
                        col = Integer.parseInt(input.next());
                    }
                    while(!validBlackMoveCheck(piecePositionX, piecePositionY, row, col) && randVal==1) {
                            System.out.println("Invalid Move, Enter position again");
                            System.out.println("please enter a row value?");
                            row = Integer.parseInt(input.next());
                            System.out.println("please enter a col value?");
                            col = Integer.parseInt(input.next());
                        }
                    }

                    if (cells[piecePositionX][piecePositionY].equals("1")) {
                        System.out.println("please enter a row value?");
                        row = Integer.parseInt(input.next());
                        System.out.println("please enter a col value?");
                        col = Integer.parseInt(input.next());
                        while (!validWhiteMoveCheck(piecePositionX, piecePositionY, row, col)){
                            System.out.println("Invalid Move, Enter position again");
                            System.out.println("please enter a row value?");
                            row = Integer.parseInt(input.next());
                            System.out.println("please enter a col value?");
                            col = Integer.parseInt(input.next());
                        }
                    }

                clearTokenAt(piecePositionX, piecePositionY);
            }
            if (flag1 == 2) {
                rowInit = row;
                colInit = col;
            }

            if(flag1>2){
            playerWinCheck();
            }

            if (flag1 > 100) {
                System.out.println("Too many moves stopping the game!");
                if (whiteScore > blackScore) {
                    System.out.println("White (Player 1) has won!");
                    this.iModelManager.reset();
                }
                if (whiteScore < blackScore) {
                    System.out.println("Black (Player 0) has won!");
                    this.iModelManager.reset();
                }
            }
        this.iViewRequestor.setTokenAt(row, col, new IRejectCommand() {
            @Override
            public void execute() {
                setMessage("There is already an " + symbolStr[Integer.parseInt(cells[row][col])] + "inside Point[x=" + row + ",y=" + col + "].");
                print();
            }
        });
        scoreDisplay();
    }

    /**
     * This function returns a boolean value on whether the selected black piece is valid or not
     * @param x
     * @param y
     * @return boolean
     */
    boolean validWhitePieceCheck(int x, int y){

        if(x>=0 && y>=0 && x<=9 && y<=9){
            return true;
        }
        return false;
    }

    /**
     * This function returns a boolean value on whether the selected black piece is valid or not
     * @param x
     * @param y
     * @return boolean
     */
    boolean validBlackPieceCheck(int x, int y){

        if(x>=0 && y>=0 && x<=9 && y<=9){
            return true;
        }
        return false;
    }

    /**
     * This function returns a boolean value on whether the selected white move is valid or not
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @return boolean
     */
    boolean validWhiteMoveCheck(int x, int y, int x1, int y1){
        if(x1>x || x1<x){
            if(x1==x+2 || x1==x-2){
                removePiece(x, y, x1, y1);
                whiteScore+=1;
                return true;
            }
        }
        if((x1==x+1 || x1==x-1) && (y1==y+1 || y1==y-1)){
            return true;
        }
        else
            return false;
    }

    /**
     * This function returns a boolean value on whether the selected black move is valid or not
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @return boolean
     */
    boolean validBlackMoveCheck(int x, int y, int x1, int y1){
        if(x1>x || x1<x){
            if(x1==x+2 || x1==x-2){
                removePiece(x, y, x1, y1);
                blackScore+=1;
                return true;
            }
        }
        if((x1==x+1 || x1==x-1) && (y1==y+1 || y1==y-1)){
            return true;
        }
        else
            return false;
    }

    /**
     * This method is used to test whether the random value that is being generated is value or not.
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @return
     */
    boolean validRandomMoveCheck(int x, int y, int x1, int y1){
        if(x1!=x){
            if(x1-x==1 || x-x1==1){
                if(cells[x1][y1].equals(" ")){
                    return true;
                }
            }
            else if(x1-x==2 || x-x1==2){
                if(cells[x1-1][y1-1].equals("1")|| cells[x1+1][y1+1].equals("1")||cells[x1-1][y1+1].equals("1")||cells[x1+1][y1-1].equals("1")){
                    return true;
                }
            }
            else
                return false;
        }

        else{
            return false;}
        return false;
    }

    /**
     * This function removes the piece that has been cut on the InternationalDraughtsBoard
     * @param x
     * @param y
     * @param x1
     * @param y1
     */
    void removePiece(int x, int y, int x1, int y1){
        if((x1==x+2 || x1==x-2) && (y1==y+2 || y1==y-2)){
            if(x1>x && y1>y)
                clearTokenAt(x1-1, y1-1);
            if(x1<x && y1<y)
                clearTokenAt(x1+1, y1+1);
            if(x1>x && y1<y)
                clearTokenAt(x1-1, y1+1);
            if(x1<x && y1>y)
                clearTokenAt(x1+1, y1-1);
        }

    }

    /**
     * This function is used to do keep tally of the score of both the players
     * This data can be used to decide the winner among the two players, it can also be used to declare a draw
     */
    void playerWinCheck(){
        int black_piece = 0;
        int white_piece = 0;
        for(int i=0; i<=9; i++){
            for(int j=0; j<=9; j++){
                if(cells[i][j].equals("1")){
                    white_piece+=1;
                }
                if(cells[i][j].equals("0")){
                    black_piece+=1;
                }
            }
        }
        System.out.println("No of white pieces on draughts board: " + white_piece);
        System.out.println("No of black pieces on draughts board :" + black_piece);
        if(white_piece == 0){
            System.out.println("Black (Player 0) has won!");

            this.iModelManager.reset();
        }

        if(black_piece == 0){
            System.out.println("White (Player 1) has won!");
            this.iModelManager.reset();
        }

        if(white_piece == 1 && black_piece == 1){
            System.out.println("Game is a Draw!");
            this.iModelManager.reset();
        }
    }

    /**
     * This function is used to display the score of both the players
     */
    void scoreDisplay(){
        System.out.println("White Score: " + whiteScore);
        System.out.println("Black Score: " + blackScore);
    }

    @Override
    public ICommand getCommand() {
        return this;
    }

    @Override
    public void setPlayers(List<Object> players) {
        this.players = players;
        List<Object> l = this.iModelManager.getPlayers();
        this.iModelManager.setPlayers(l.get(1), l.get(0));
    }

    @Override
    public void setDimension(Dimension size) {
        this.size = size;
        this.cells = new String[size.width][size.height];
    }

    @Override
    public void draw() {
        getCommand().setMessage("Its a Draw!");
        this.iModelManager.reset();
    }

    @Override
    public void win(int player) {
        getCommand().setMessage("Player" + symbolStr[player] + "has won!");
        this.iModelManager.reset();
    }

    @Override
    public void reset() {
        getCommand().setMessage("Resetting the Game!");
        this.cells = new String[size.width][size.height];
        this.iModelManager.reset();
    }

    @Override
    public void setTokenAt(int row, int col, int player) {
        cells[row][col] = player+"";
        if(flag1>=2){
            print();
        }
    }

    /**
     * This method is used to place the initial black pieces on the board
     * @param iViewRequestor
     */
    public void setBinitaltokens(IViewRequestor iViewRequestor){

        for(int i=6; i<=8; i+=2){
            for(int j=1; j<=9; j+=2){
                int row = i;
                int col = j;
                this.iViewRequestor.setTokenAt(row, col, new IRejectCommand() {
                    @Override
                    public void execute() {
                        setMessage("There is already an " + symbolStr[Integer.parseInt(cells[row][col])] + "inside Point[x=" + row + ",y=" + col + "].");
//                        print();
                    }
                });
            }
        }

        for(int i=7; i<=9; i+=2){
            for(int j=0; j<=8; j+=2){
                int row = i;
                int col = j;
                this.iViewRequestor.setTokenAt(row, col, new IRejectCommand() {
                    @Override
                    public void execute() {
                        setMessage("There is already an " + symbolStr[Integer.parseInt(cells[row][col])] + "inside Point[x=" + row + ",y=" + col + "].");
//                        print();
                    }
                });
            }
        }
    }

    /**
     * This method is used to place the initial white pieces on the board
     * @param iViewRequestor
     */
    public void setWInitialTokens(IViewRequestor iViewRequestor){

        for(int i=1; i<=3; i+=2){
            for(int j=0; j<=8; j+=2){
                int row = i;
                int col = j;
                this.iViewRequestor.setTokenAt(row, col, new IRejectCommand() {
                    @Override
                    public void execute() {
                        setMessage("There is already an " + symbolStr[Integer.parseInt(cells[row][col])] + "inside Point[x=" + row + ",y=" + col + "].");
//                        print();
                    }
                });
            }
        }

        for(int i=0; i<=2; i+=2){
            for(int j=1; j<=9; j+=2){
                int row = i;
                int col = j;
                this.iViewRequestor.setTokenAt(row, col, new IRejectCommand() {
                    @Override
                    public void execute() {
                        setMessage("There is already an " + symbolStr[Integer.parseInt(cells[row][col])] + "inside Point[x=" + row + ",y=" + col + "].");
//                        print();
                    }
                });
            }
        }
    }

    @Override
    public void clearTokenAt(int row, int col) {
        cells[row][col] = " ";
    }

    @Override
    public void setMessage(String s) {
        System.out.println("Message: " + s);
    }

    public void exit() {
        this.iModelManager.exit();
    }
}
