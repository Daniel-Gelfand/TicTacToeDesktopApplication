package Renderers;

import Enums.eGameStatus;
import Enums.eMark;
import Games.GameGui;
import MVVM.Board;
import MVVM.ViewModel;
import Players.HumanPlayer;
import Players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationGuiRenderer extends JFrame implements ActionListener, iRenderer {

    private JFrame frame;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JPanel statisticsPanel;
    private JLabel textfield;
    private JButton[] buttons;
    private ViewModel vm;
    private Player playerX;
    private Player playerO;
    private int turnToPlay;
    private Board myBoard;
    private int boardSize;

    /**
     * statistics[0] = X | statistics[1] = O | statistics[2] = Draw
     */
    private static int[] statistics = new int[3];;

    private boolean player1Turn = true;
    private static int locationInBoard;

    ApplicationGuiRenderer() {

    }

    public ApplicationGuiRenderer(Player playerX, Player playerO, Board board, int boardSize) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.myBoard = board;
        this.boardSize = boardSize;
        turnToPlay = 0;
    }

    public void init() {
        frame = new JFrame("Tic Tac Toe");
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        textfield = new JLabel();
        buttons = new JButton[Board.SIZE * Board.SIZE];
    }

    /**
     * 'start' method is method that set all SWING buttons/textfields and more..
     */
    public void start() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 700);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage("src/images/tictactoeicon.png");
        frame.setIconImage(icon);

        frame.setVisible(true);

        textfield.setBackground(new Color(45,45,50));
        textfield.setForeground(new Color(0xFFFFFF));
        textfield.setFont(new Font("Bodoni MT", Font.BOLD, 85));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(Board.SIZE, Board.SIZE, 10 , 10));
        buttonPanel.setBackground(new Color(0xFFFFFF));


        for (int i = 0; i < Board.SIZE * Board.SIZE; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Bodoni MT", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(45,45,50));
            buttons[i].addActionListener(this);

        }

        statisticsPanel = new JPanel();
        statisticsPanel.setPreferredSize(new Dimension(100, 50));
        statisticsPanel.setBackground(new Color(45,45,50));
        JLabel label = new JLabel("X Wins: " + statistics[0] + "   O Wins: " + statistics[1] + "   Draws: " + statistics[2]);
        label.setFont(new Font("Bodoni MT", Font.BOLD, 32));
        label.setForeground(Color.WHITE);
        statisticsPanel.add(label);
        titlePanel.add(textfield);
        frame.add(statisticsPanel, BorderLayout.SOUTH);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
    }

    public void setViewModel(ViewModel vm) {
        this.vm = vm;
    }

    /**
     * 'actionPerformed' is method that set action to the buttons.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < Board.SIZE * Board.SIZE; i++) {

            if (e.getSource() == buttons[i]) {
                locationInBoard = getLocationInBoard(i);

                eMark sign;
                if(turnToPlay % 2 == 0) {
                    sign = playerX.getDefaultSign();
                }
                else {
                    sign = playerO.getDefaultSign();
                }

                int col = locationInBoard % 10;
                int row = locationInBoard / 10;

                if (myBoard.getBoard()[row-1][col-1] == eMark.BLANK) {
                    vm.getCoordinatesFromGui(row, col, sign);
                    turnToPlay++;

                    if (!onlyHuman(playerX, playerO)) {
                        turnToPlay++;
                    }
                }
            }
        }
    }

    /**
     * 'getLocationInBoard' is method that give the current location in the board.
     *
     * @return
     */
    public int getLocationInBoard(int currentI) {
        return (currentI / Board.SIZE * 10 + 10) + (currentI % Board.SIZE + 1);
    }

    /**
     * 'renderBoard' is method that rendered the board.
     * @param board
     */
    @Override
    public void renderBoard(Board board) {

        int row, col;
        row = locationInBoard / 10;
        col = locationInBoard % 10;
        if (player1Turn) {
            board.putMark(eMark.X,row,col);
        }else {
            board.putMark(eMark.O,row,col);
        }
    }


    /**
     * 'showBoard' is method that goes across the board and places the marks.
     * * @param board -> board is argument of Board class.
     */
    public void showBoard(eMark[][] board) {
        int row;
        int col;

        for (int i = 0; i < Board.SIZE * Board.SIZE; i++) {

            locationInBoard = getLocationInBoard(i);
            col = locationInBoard % 10 -1;
            row = locationInBoard / 10 -1;

            if (board[row][col] == eMark.X) {
                buttons[i].setForeground( Color.BLACK);
                //buttons[i].setText("X");
                buttons[i].setIcon(new ImageIcon("src/images/RedX.png"));

            }
            else if (board[row][col] == eMark.O){
                // photos
                buttons[i].setForeground(Color.pink);
                buttons[i].setIcon(new ImageIcon("src/images/GreenO.png"));
                //buttons[i].setText("O");
                }
            }

        }

    public void disableAll(eGameStatus status, String message) {
        JOptionPane.showMessageDialog(frame,
                message,
                "WINNER",
                JOptionPane.CLOSED_OPTION);
        updateStatisticsGameAndStartNewGame(status);
        //System.exit(0);
    }

    /**
     * 'showTurn is method that shows up the turn of the player.
     * @param message -> message to text field.
     */
    public void showTurn(String message) {
        textfield.setText(message);
    }

    public void showMessage(String message) {
        textfield.setText(message);
    }

    /**
     * 'onlyHuman' is method returned if there are human player or computer player.
     * @param currentPlayerX -> Player 1
     * @param secondPlayerO -> Player 2
     * @return
     */
    public boolean onlyHuman(Player currentPlayerX, Player secondPlayerO){

        int flag = 0;
        eMark sign = eMark.BLANK;

        if (!(currentPlayerX instanceof HumanPlayer)){
            try {
                Thread.sleep(50);
            }catch (Exception e)
            {}

            locationInBoard = currentPlayerX.getCoordinates(myBoard);
            flag = 1;
            sign = currentPlayerX.getDefaultSign();
        }
        if(!(secondPlayerO instanceof HumanPlayer)){
            try {
                Thread.sleep(50);
            }catch (Exception e)
            {}

            locationInBoard = secondPlayerO.getCoordinates(myBoard);
            flag = 1;
            sign = secondPlayerO.getDefaultSign();
        }
        if(flag == 1) {
            int col = locationInBoard % 10;
            int row = locationInBoard / 10;
            vm.getCoordinatesFromGui(row, col, sign);
            return false;
        }

        return true;
    }

    /**
     * statistics[0] = X | statistics[1] = O | statistics[2] = Draw
     * @param mark -> sign of X or O.
     */
    private void updateStatisticsGameAndStartNewGame(eGameStatus mark){

        switch(mark) {
            case DRAW:
                statistics[2]++;
                break;
            case X_WIN:
                statistics[0]++;
                break;
            case O_WIN:
                statistics[1]++;
                break;
            default:
                break;
        }

        frame.dispose();
        GameGui game = new GameGui(playerX, playerO, new VoidRenderer());
        game.setBoardSize(boardSize);
        game.runGame();

    }
}

