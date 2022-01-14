package Games;

import Enums.eGameStatus;
import Enums.eMark;
import Players.Player;
import Players.PlayerFactory;
import Renderers.RendererFactory;
import Renderers.iRenderer;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A class representing an operating tournament, for a console-type game.
 */
public class TournamentConsole {

    //fields
    private final Player player1;
    private final Player player2;
    private final int numberRounds;
    private final iRenderer renderer;

    /**
     * Constructor initializes the tournament data.
     * @param player1 first player.
     * @param player2 second player.
     * @param numberRounds The length of the tournament.
     * @param renderer board type (Console, none).
     */
    public TournamentConsole(Player player1, Player player2, int numberRounds, iRenderer renderer) {
        this.player1 = player1;
        this.player2 = player2;
        this.numberRounds = numberRounds;
        this.renderer = renderer;
    }

    /**
     * A method that runs the tournament.
     * The method saves the tournament data in a size 3 array, which represents win x win y and also draw.
     * @return Tournament results.
     */
    private int[] playTournament() {

        int[] results = new int[3];
        Arrays.fill(results,0);

        Player[] players = new Player[2];
        int randomPlayerOne,randomPlayerTwo;
        players[0] = player1;
        players[1] = player2;

        for(int i = 0 ; i < numberRounds ; i++)
        {
            System.out.println("Start game number " + (i+1) + "/" + numberRounds);
            randomPlayerOne = i % 2;
            randomPlayerTwo = (randomPlayerOne + 1) % 2;

            Game game = new GameConsole(players[randomPlayerOne],players[randomPlayerTwo], renderer);

            eGameStatus winner = game.runGame();

            if(winner == eGameStatus.DRAW){
                results[2]++;
            }

            if(winner == eGameStatus.X_WIN)
            {
                results[0]++;
            }

            if(winner == eGameStatus.O_WIN)
            {
                results[1]++;
            }
        }

        return results;
}

    /**
     * A method that displays the user menu, for a console-type game.
     */
    private static void userMenu() {
        System.out.println("Welcome to TIC TAC TOE game!!!\n lets start play...");
        String playerType1, playerType2, selection, rendererType, numberOfRoundsStr = null;
        int numberOfRounds;
        int[] result;
        System.out.println("Chose option for player 1 by index: \n1.human  \n2.computer \n3.expert computer");
        Scanner scanner = new Scanner(System.in);
        selection = scanner.nextLine();

        while (!selectionCheck(selection))
        {
            System.out.println("Wrong selection please try again");
            selection = scanner.nextLine();
        }
        playerType1 = playerTypeSelection(selection);

        System.out.println("Chose option for player 2 by index: \n1.human  \n2.computer \n3.expert computer");
        selection = scanner.nextLine();
        while (!selectionCheck(selection))
        {
            System.out.println("Wrong selection please try again");
            selection = scanner.nextLine();
        }
        playerType2 = playerTypeSelection(selection);

        System.out.println("Chose option for renderer type by index: \n1.none(without board) \n2.console \n3.GUI");
        selection = scanner.nextLine();
        while(!selectionCheck(selection))
        {
            System.out.println("Wrong selection please try again");
            selection = scanner.nextLine();
        }
        rendererType = rendererTypeSelection(selection);

        System.out.println("Chose the number of games you want to play:");

        boolean flag = false;
        while(!flag)
        {
            numberOfRoundsStr = scanner.nextLine();
            flag = isDigitsString(numberOfRoundsStr);
            if(!flag) {
                System.out.println("Wrong selection please try again");
            }
        }
        numberOfRounds = Integer.parseInt(numberOfRoundsStr);

        iRenderer renderer = RendererFactory.rendererFactory.buildRenderer(rendererType);

        TournamentConsole tournament = new TournamentConsole(PlayerFactory.playerFactory.buildPlayer(playerType1, "X", eMark.X),
                PlayerFactory.playerFactory.buildPlayer(playerType2, "O", eMark.O),numberOfRounds, renderer);
        result = tournament.playTournament();

        System.out.println("TTT.Players.Player X win : " + result[0]);
        System.out.println("TTT.Players.Player O win : " + result[1]);
        System.out.println("Total draw : " + result[2]);

    }

    /**
     * A method that checks the correctness of the input in the user selection.
     * @param str A parameter that represents the selection.
     * @return true or false.
     */
    public static boolean selectionCheck(String str) {
        return str.equals("1") || str.equals("2") || str.equals("3");
    }

    /**
     * A method that receives a string and checks if it is a number.
     * @param str Choice from the user.
     * @return true or false.
     */
    public static boolean isDigitsString(String str) {
        boolean result = false;

        if((Character.isDigit(str.charAt(0)) || str.charAt(0) == '-'))
            result = true;


        for(int i = 1 ; i < str.length() ; i++){
            if(!Character.isDigit(str.charAt(i)))
                result = false;
        }

        return result;
    }

    /**
     * A method that examines the choice of player type.
     * @param str Choice from the user.
     * @return true or false.
     */
    public static String playerTypeSelection (String str) {
        String playerType;
        if (str.equals("1")) {
            playerType = "Human";
        }else if (str.equals("2")) {
            playerType = "Whatever";
        }else {
            playerType = "TTT.Players.CleverPlayer";
        }
        return playerType;
    }

    /**
     * A method that checks the board type selection.
     * @param str Choice from the user.
     * @return true or false.
     */
    public static String rendererTypeSelection (String str) {

        String playerType;
        if (str.equals("1")) {
            playerType = "None";
        }else if (str.equals("2")) {
            playerType = "Console";
        }else {
            playerType = "Gui";
        }
        return playerType;
    }

    public static void main(String[] args) {
        userMenu();
    }
}
