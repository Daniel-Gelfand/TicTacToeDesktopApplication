package MVVM;


import Enums.eGameStatus;
import Enums.eMark;

import java.util.ArrayList;

public class Board {

    public static int SIZE = 5;
    public static int WIN_STREAK = 4;
    private int emptyPlace;
    private eMark[][] board;
    private eMark lastPlayer;
    private boolean isHaveWinner;

    /**
     * emptyLocations -> Arraylist The saves all empty places on the board.
     */
    public static ArrayList<Integer> emptyLocations;

    /**
     * 'Board' Constructor build Board in size that chosen.
     * Updates the list of empty places and initializes the board
     */


    public Board(int boardSize) {
        SIZE = boardSize;
        WIN_STREAK = SIZE;
        board = new eMark[SIZE][SIZE];
        emptyLocations = new ArrayList<>();
        installBoard();
        emptyPlace = SIZE*SIZE;
        isHaveWinner = false;

    }

    public eMark[][] getBoard() {
        return board;
    }

    /**
     * 'installBoard' is method that that initializes the entire board to empty spaces.
     * In addition, it launches an array that saves all the locations of the empty spaces
     */
    private void installBoard(){
        int num = 0;

        for(int i = 0 ; i < board.length ; i++)
        {
            for (int j = 0 ; j < board[i].length ; j++)
            {
                num = (i+1) * 10 + (j+1);
                board[i][j] = eMark.BLANK;
                emptyLocations.add(num);
            }
        }
    }

    /**
     * 'putMark' method is method that accepted direction on board and sign.
     * The method checked the input integrity, if it's properly, he'll return true.
     * The method fill the sign in the board and remove the location of the sign from the array.
     * @param mark -> sign of X or O.
     * @param row -> move to next row.
     * @param col -> move to next col.
     * @return
     */
    public boolean putMark(eMark mark, int row, int col) // סימון על הלוח
    {
        Boolean result  = false;
        int numberToRemove;
        if(row > 0 && row <= SIZE && col > 0 && col <= SIZE )
        {
            if(board[row - 1][col - 1] == eMark.BLANK) {
                board[row - 1][col - 1] = mark;
                result = true;
                emptyPlace--;
                numberToRemove = row * 10 + col;
                emptyLocations.removeIf(number -> number == numberToRemove);
            }
        }

        lastPlayer = mark;

        return result;
    }

    /**
     * 'GameStatus' is method that check if there are game winner.
     * After the player in turn makes a move, the method is called and checks whether the move the player made caused a strike of the selected size.
     * This method check all types of strike options.
     * If there is a strike, return the truth.
     * If there is NO strike, return the truth.
     * @param row -> row on board.
     * @param col -> column on board.
     * @return status options eGameStatus (Draw,X,O,IN PROGRESS).
     */
    public eGameStatus gameStatus(int row, int col) // במהלך המשחק / מי ניצח
    {
        eMark mark = board[row-1][col-1];
        eGameStatus result = eGameStatus.IN_PROGRESS;
        boolean flag = false;
        int SequenceOfColumn = checkingDirection(row-1, col-1, mark, 1 ,0)
                + checkingDirection(row-1, col-1, mark, -1 ,0);
        int SequenceOfLine = checkingDirection(row-1, col-1, mark, 0 ,-1)
                + checkingDirection(row-1, col-1, mark, 0 ,1);
        int SequenceOfMainDiagonal = checkingDirection(row-1, col-1, mark, -1 ,-1)
                + checkingDirection(row-1, col-1, mark, 1 ,1);
        int SequenceOfSecondaryDiagonal = checkingDirection(row-1, col-1, mark, -1 ,1)
                + checkingDirection(row-1, col-1, mark, 1 ,-1);

        if(SequenceOfLine == WIN_STREAK - 1)
            flag = true;

        if(SequenceOfColumn == WIN_STREAK - 1)
            flag = true;

        if(SequenceOfMainDiagonal == WIN_STREAK - 1)
            flag = true;

        if(SequenceOfSecondaryDiagonal == WIN_STREAK - 1)
            flag = true;

        if(flag == true)
        {
            if(mark == eMark.X)
                result = eGameStatus.X_WIN;
            if (mark == eMark.O)
                result = eGameStatus.O_WIN;
        }
        if(flag == false && emptyPlace == 0)
        {
            result = eGameStatus.DRAW;
        }

        return result;
    }

    /**
     * 'checkingDirection' is help method to 'GameStatus', she accepted the direcation of the move that was inserted.
     * Additionally, she accepted direction of column and row.
     * Using the direction she checks how much of that inserted move is in sequence with the direction she was given.
     * There are end conditions here, such as not checking the size of the board.
     * The method returns at the end the size of the sequence it went in its direction
     * @param row -> row on board.
     * @param col -> column on board.
     * @param mark -> sign of X or O.
     * @param moveCol -> move to next col.
     * @param moveRow -> move to next row.
     * @return
     */
    private int checkingDirection(int row, int col, eMark mark,int moveCol, int moveRow) {
        int count = 0;

        row += moveRow;
        col += moveCol;

        while(row < SIZE && col < SIZE && row >= 0 && col >= 0 && board[row][col] == mark)
        {
            count++;
            row += moveRow;
            col += moveCol;

        }

        return count;
    }


    /**
     * 'getMark' is method that return sign of direction on board.
     * @param row -> row on board.
     * @param col -> column on board.
     * @return
     */
    public eMark getMark(int row, int col)
    {
        return board[row][col];
    }


    /**
     * 'isHaveWinner' is method that save boolean , if there are winner in the game or not.
     * @return
     */
    public boolean isHaveWinner() {
        return isHaveWinner;
    }

    /**
     * 'setHaveWinner' is method that sets there are winner.
     * @param isHaveWinner
     */
    public void setHaveWinner(boolean isHaveWinner){
        this.isHaveWinner = isHaveWinner;
    }

    /**
     *  'getOppositeSign' method get the opposite sign. For example if X then O.
     * @param mark -> sign of X or O.
     * @return
     */
    public eMark getOppositeSign(eMark mark) {
        if(mark == eMark.X)
            return eMark.O;
        else
            return eMark.X;
    }

}
