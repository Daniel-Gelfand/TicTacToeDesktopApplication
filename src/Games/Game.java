package Games;

import Enums.eGameStatus;
import Players.Player;
import Renderers.iRenderer;


public abstract class Game {

    //fields
    private Player playerX;
    private Player playerO;
    private iRenderer renderer;

    /**
     * Initializes the player types and board type.
     * @param playerX first player.
     * @param playerO second player.
     * @param renderer board type (Console, Gui).
     */
    public Game(Player playerX, Player playerO, iRenderer renderer) {
        setPlayerO(playerO);
        setPlayerX(playerX);
        setRenderer(renderer);
    }

    public void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    public void setPlayerO(Player playerO) {
        this.playerO = playerO;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public iRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(iRenderer renderer) {
        this.renderer = renderer;
    }

    /**
     * A method that runs the game on each of the boards
     * @return Returns the type of winner
     */
    abstract public eGameStatus runGame();
}


