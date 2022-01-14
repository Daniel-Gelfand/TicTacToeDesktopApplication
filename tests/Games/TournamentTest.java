package Games;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTest {

    @Test
    void selectionCheck() {
        boolean selectionChose1 = TournamentConsole.selectionCheck("1");
        boolean selectionChose2 = TournamentConsole.selectionCheck("2");
        boolean selectionChose3 = TournamentConsole.selectionCheck("3");
        boolean selectionChose4 = TournamentConsole.selectionCheck("D");
        boolean selectionChose5 = TournamentConsole.selectionCheck("a");
        boolean selectionChose6 = TournamentConsole.selectionCheck("123");


        assertEquals(true,selectionChose1);
        assertEquals(true,selectionChose2);
        assertEquals(true,selectionChose3);

        assertEquals(false,selectionChose4);
        assertEquals(false,selectionChose5);
        assertEquals(false,selectionChose6);
    }

    @Test
    void isDigitsString() {
        boolean isDigit = TournamentConsole.isDigitsString("5");
        boolean isDigit2 = TournamentConsole.isDigitsString("-5");

        boolean isString = TournamentConsole.isDigitsString("TESTESTESTESTESTESTEST");
        boolean isString2 = TournamentConsole.isDigitsString("dsa321");
        boolean isString3 = TournamentConsole.isDigitsString("213ds2");

        assertEquals(true,isDigit);
        assertEquals(true,isDigit2);

        assertEquals(false,isString);
        assertEquals(false,isString2);
        assertEquals(false,isString3);
    }

    @Test
    void playerTypeSelection() {
        String playerTypeSelectionHuman = TournamentConsole.playerTypeSelection("1");
        assertEquals("Human",playerTypeSelectionHuman);
        String playerTypeSelectionWhatever = TournamentConsole.playerTypeSelection("2");
        assertEquals("Whatever",playerTypeSelectionWhatever);
        String playerTypeSelectionCleverPlayer = TournamentConsole.playerTypeSelection("asd");
        assertEquals("TTT.Players.CleverPlayer",playerTypeSelectionCleverPlayer);
    }

    @Test
    void rendererTypeSelection() {
        String rendererTypeSelectionNone = TournamentConsole.rendererTypeSelection("1");
        String rendererTypeSelectionConsole = TournamentConsole.rendererTypeSelection("2");
        String rendererTypeSelectionGui = TournamentConsole.rendererTypeSelection("3");
        assertEquals("None",rendererTypeSelectionNone);
        assertEquals("Console",rendererTypeSelectionConsole);
        assertEquals("Gui",rendererTypeSelectionGui);
    }
}