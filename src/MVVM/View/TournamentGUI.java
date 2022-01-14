package MVVM.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TournamentGUI {
    //fields.
    private JFrame startFrame = new JFrame("Tic Tac Toe By YDM");
    private JButton startButton = new JButton("Start New Game");

    public TournamentGUI() {
        setTheFirstScreen();
    }

    /**
     * A method that initializes the opening screen.
     */
    public void setTheFirstScreen() {
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(650, 620);
        startFrame.setLocationRelativeTo(null);
        startFrame.setResizable(false);
        startFrame.setLayout(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/images/tictactoeicon.png");
        startFrame.setIconImage(icon);

        try {
            startFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/images/FirstScreenImage.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        startButton.setBounds(245, 520, 150, 40);
        startButton.setFocusable(false);
        startButton.setBackground(new Color(0xFFFFFF));
        startFrame.add(startButton);

        startButton.addActionListener(e -> startNewGame());

        startFrame.setVisible(true);
    }

    /**
     * A method that closes the opening screen, and transfers it to the game settings screen.
     */
    public void startNewGame() {
        startFrame.dispose();
        OptionScreenGameView viewGame = new OptionScreenGameView();
    }

    public static void main(String[] args) {
        TournamentGUI firstScreenGame = new TournamentGUI();
    }
}
