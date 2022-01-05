package Games;

import MVVM.Board;
import Enums.eGameStatus;
import MVVM.ViewModel;
import Players.Player;
import Renderers.ApplicationGuiRenderer;
import Renderers.iRenderer;
import javax.swing.*;

public class GameGui extends Game{


    private int boardSize;



    /**
     * Initializes the data and transfers it to the base class
     * @param playerX first player.
     * @param playerO second player.
     * @param renderer board type Gui.
     */
    public GameGui(Player playerX, Player playerO, iRenderer renderer) {
        super(playerX, playerO, renderer);
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * Runs the game, GUI-type.
     * @return Returns the type of winner.
     */
    @Override
    public eGameStatus run() {
        Board model = new Board(boardSize);
        ViewModel vm = new ViewModel();
        ApplicationGuiRenderer view = new ApplicationGuiRenderer(getPlayerX(), getPlayerO(),model, boardSize);
        eGameStatus winner = eGameStatus.IN_PROGRESS;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.init();
                view.start();
            }
        });

        vm.setModel(model);
        vm.setView(view);
        view.setViewModel(vm);

        return winner;
    }
}
