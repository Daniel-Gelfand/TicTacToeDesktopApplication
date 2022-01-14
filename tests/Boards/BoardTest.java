package Boards;

import Enums.eGameStatus;
import Enums.eMark;
import MVVM.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    private Board board;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        board = new Board(3);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        board = null;
    }

    @org.junit.jupiter.api.Test
    void getBoard() {
        assertEquals(board.getBoard(),board.getBoard());
    }

    @org.junit.jupiter.api.Test
    void putMark() {
        boolean putMarkX = board.putMark(eMark.X,3,3);
        boolean putMarkO = board.putMark(eMark.O,3,2);
        boolean putMarkOAgain = board.putMark(eMark.O,3,2);
        boolean putMarkXAgain = board.putMark(eMark.X,3,3);

        assertEquals(putMarkX,putMarkO);
        assertEquals(putMarkOAgain,false);
        assertEquals(putMarkXAgain,false);

    }

    @org.junit.jupiter.api.Test
    void gameStatus() {
        eGameStatus xWin = eGameStatus.X_WIN;
        eGameStatus OWin = eGameStatus.O_WIN;

        assertEquals(xWin, eGameStatus.X_WIN );
        assertEquals(OWin, eGameStatus.O_WIN );
    }

    @org.junit.jupiter.api.Test
    void getMark() {
        eMark markOnBoard = board.getMark(0,0);
        board.putMark(eMark.X,2,2);
        eMark markOnBoardX = board.getMark(1,1);

        assertEquals(eMark.BLANK,markOnBoard);
        assertEquals(eMark.X,markOnBoardX);
    }

    @org.junit.jupiter.api.Test
    void setHaveWinner() {
        boolean haveWinner = board.isHaveWinner();
        boolean haveNoWinner = board.isHaveWinner();

        assertEquals(haveWinner,false);

        assertEquals(!haveNoWinner,true);
    }
}