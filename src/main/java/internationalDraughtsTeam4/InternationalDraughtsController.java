package main.java.internationalDraughtsTeam4;

import main.java.model.GameModel;
import main.java.model.viewToModel.ITurnManager;
import main.java.model.viewToModel.IViewRequestor;


/**
 * This is the class which initiates our game
 */
public class InternationalDraughtsController {

    /**
     * Main method for the program
     * @param args
     */
    public static void main(String[] args) {
        InternationalDraughtsController idc = new InternationalDraughtsController();
        idc.start(10, 10);
    }

    /**
     * This method is used to initialise the game and is invoked in the main()
     * @param row
     * @param col
     */
    public void start(int row, int col) {
        GameModel gameModel = new GameModel(row, col);
        final InternationalDraughtsUI boardView = new InternationalDraughtsUI();
        boardView.setDimension(gameModel.getBoardModel().getDimension());
        gameModel.setCommand(boardView.getCommand());
        gameModel.setViewAdmin(boardView, new ITurnManager() {

            @Override
            public void takeTurn(IViewRequestor iViewRequestor) {
                boardView.setiViewRequestor(iViewRequestor);
            }
        });
        boardView.setiModelManager(gameModel);
        boardView.setPlayers(gameModel.getPlayers());
    }
}

