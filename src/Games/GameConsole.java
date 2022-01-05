package Games;


import MVVM.Board;
import Enums.eGameStatus;
import Players.Player;
import Renderers.iRenderer;

/**
 * A class that implements the console board type.
 */
public class GameConsole extends Game{

    /**
     * Initializes the data and transfers it to the base class
     * @param playerX first player.
     * @param playerO second player.
     * @param renderer board type console.
     */
    public GameConsole(Player playerX, Player playerO, iRenderer renderer) {
        super(playerX, playerO, renderer);
    }

    /**
     * Runs the game, console-type.
     * @return Returns the type of winner.
     */
    @Override
    public eGameStatus run() {
        Board board = new Board(3);
        eGameStatus winner = eGameStatus.IN_PROGRESS;
        Player[] myPlayers = new Player[2];
        int turnToPlay = 0;
        int inputRowAndCol, row, col;

        myPlayers[0] = getPlayerX();
        myPlayers[1] = getPlayerO();

        while (winner == eGameStatus.IN_PROGRESS) {

            turnToPlay++;

            inputRowAndCol = myPlayers[turnToPlay % 2].getCoordinates(board);

            col = inputRowAndCol % 10;
            row = inputRowAndCol / 10;

            myPlayers[turnToPlay % 2].playTurn(board, myPlayers[turnToPlay % 2].getDefaultSign(), row, col);

            getRenderer().renderBoard(board);
            winner = board.gameStatus(row, col);

        }

        return winner;
    }

}
