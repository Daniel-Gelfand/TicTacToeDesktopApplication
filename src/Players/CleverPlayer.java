package Players;

import MVVM.Board;
import Enums.eGameStatus;
import Enums.eMark;
import java.util.HashMap;

/**
 * A class that represents a smart player
 */
public class CleverPlayer extends WhateverPlayer {
    private HashMap<eGameStatus, Integer> scores;

    /**
     * A constructor that initializes the player data and passes it to the base class.
     * @param name name player(PlayerX, PlayerO).
     * @param defaultSign what his sign(X, O).
     */
    public CleverPlayer(String name, eMark defaultSign) {
        super(name, defaultSign);

        scores = new HashMap<>();

        if(defaultSign == eMark.X) {
            scores.put(eGameStatus.X_WIN, 1);
            scores.put(eGameStatus.O_WIN, -1);
            scores.put(eGameStatus.DRAW, 0);
        }
        else
        {
            scores.put(eGameStatus.X_WIN, -1);
            scores.put(eGameStatus.O_WIN, 1);
            scores.put(eGameStatus.DRAW, 0);
        }
    }

    /**
     * A method that returns the governor mark from the player.
     * @return sign(X OR O).
     */
    private eMark getOppositeSign() {
        if(getDefaultSign() == eMark.X)
            return eMark.O;
        else
            return eMark.X;
    }

    /**
     * A method that returns coordinates selected by the smart player.
     * @param board On which board to mark.
     * @return In what position to mark.
     */
    @Override
    public int getCoordinates(Board board){

        int bestScore = Integer.MIN_VALUE;
        int[] move = {0, 0};
        eMark[][] currentBoard = board.getBoard();;

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (currentBoard[i][j] == eMark.BLANK) {
                    currentBoard[i][j] = getDefaultSign();
                    int score = minimax(currentBoard, 0, false, board , i+1, j+1);
                    currentBoard[i][j] = eMark.BLANK;
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i + 1;
                        move[1] = j + 1;
                    }
                }
            }
        }

        return move[0] * 10 + move[1];
    }

    /**
     *An algorithm that selects which position to mark on the board, depending on the current state of the board.
     * The algorithm tries to simulate the continuation of the game, in such a way that it plays as the 2 players,
     * and chooses for both of them the optimal choice.
     * @param board On which board to mark.
     * @param depth What depth the algorithm has reached.
     * @param isMaximizing Checks if it's a maximum choice.
     * @param boardClass On which board to mark.
     * @param currentI Current row position
     * @param currentJ Current col position
     * @return Returns the algorithm selection.
     */
    private int minimax(eMark[][] board, int depth, boolean isMaximizing, Board boardClass, int currentI, int currentJ) {

        eGameStatus result = boardClass.gameStatus(currentI,currentJ);

        if (result != eGameStatus.IN_PROGRESS ) {
            return scores.get(result);
        }

        int bestScore;

        if(isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < Board.SIZE ; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    if (board[i][j] == eMark.BLANK) {
                        board[i][j] = getDefaultSign();
                        int score = minimax(board, depth + 1, false, boardClass, i+1, j+1);
                        board[i][j] = eMark.BLANK;
                        bestScore = Math.max(score, bestScore);

                    }
                }
            }
        }
        else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < Board.SIZE; j++) {
                    if (board[i][j] == eMark.BLANK) {
                        board[i][j] = getOppositeSign();
                        int score = minimax(board, depth + 1, true, boardClass, i+1, j+1);
                        board[i][j] = eMark.BLANK;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }
}


