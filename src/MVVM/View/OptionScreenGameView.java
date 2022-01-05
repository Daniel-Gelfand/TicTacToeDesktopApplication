package MVVM.View;

import Enums.eMark;
import Games.Game;
import Games.GameGui;
import Games.TournamentConsole;
import Players.CleverPlayer;
import Players.HumanPlayer;
import Players.WhateverPlayer;
import Renderers.VoidRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class OptionScreenGameView extends JFrame {

    private JFrame startFrame = new JFrame("Game options");
    private JPanel playersPanel = new JPanel();
    private JPanel boardSizePanel = new JPanel();
    private JButton[] participantsSelectionButtons = new JButton[2];
    private JComboBox levelComboBox;
    private JComboBox boardComboBox;

    private JPanel buttonPanel = new JPanel();
    private TournamentConsole tournament;

    public OptionScreenGameView() {
        setStartFrame();
    }

    public void setStartFrame() {

        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(675, 675);
        startFrame.setResizable(false);
        startFrame.setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/images/tictactoeicon.png");
        startFrame.setIconImage(icon);
        try {
            startFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/images/GameOption.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        startFrame.setLayout(null);

        String[] titleForButtons = {"Player vs Player", "Player vs CPU"};
        int x = 300;
        for (int i = 0; i < 2; i++) {

            participantsSelectionButtons[i] = new JButton();
            participantsSelectionButtons[i].setFocusable(false);
            participantsSelectionButtons[i].setText(titleForButtons[i]);
            participantsSelectionButtons[i].setFont(new Font("Bodoni MT", Font.BOLD, 30));
            participantsSelectionButtons[i].setForeground(new Color(45,45,50));
            participantsSelectionButtons[i].setBackground(new Color(0xEAE4E4));
            participantsSelectionButtons[i].setBounds(x, 230, 135, 50);
            x += 145;
            startFrame.add(participantsSelectionButtons[i]);
        }

        participantsSelectionButtons[0].setBounds(250, 520, 300, 60);
        participantsSelectionButtons[1].setBounds(250, 410, 300, 60);
        participantsSelectionButtons[0].addActionListener(e -> startPlayerVsPlayer());
        participantsSelectionButtons[1].addActionListener(e -> startPlayerVsCPU());

        x = 300;
        String[] levels = {"             Easy", "             Hard"};
        levelComboBox = new JComboBox(levels);
        levelComboBox.setBounds(250, 180, 300, 60);
        levelComboBox.setBackground(new Color(255, 255, 255));
        levelComboBox.setFont(new Font("Bodoni MT", Font.BOLD, 30));
        levelComboBox.setForeground(new Color(45,45,50));
        startFrame.add(levelComboBox);

        String[] boardSize = {"             3x3", "             4x4", "             5x5"};
        x += 145;
        boardComboBox = new JComboBox(boardSize);
        boardComboBox.setBounds(250, 300, 300, 60);
        boardComboBox.setBackground(new Color(255, 255, 255));
        boardComboBox.setFont(new Font("Bodoni MT", Font.BOLD, 30));
        boardComboBox.setForeground(new Color(45,45,50));

        startFrame.add(boardComboBox);
        startFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void startPlayLevelEasy() {
        Game game = new GameGui(new HumanPlayer("X", eMark.X), new WhateverPlayer("O", eMark.O), new VoidRenderer());
        game.run();
        startFrame.dispose();
    }

    public void startPlayerVsCPU() {

        if (levelComboBox.getSelectedItem() == "Easy")
        {
            Game game = new GameGui(new HumanPlayer("X", eMark.X), new WhateverPlayer("O", eMark.O), new VoidRenderer());
            game.run();
            startFrame.dispose();
        }
        else
        {
            Game game = new GameGui(new HumanPlayer("X", eMark.X), new CleverPlayer("O", eMark.O), new VoidRenderer());
            game.run();
            startFrame.dispose();
        }
    }

    public void startPlayerVsPlayer() {
        GameGui game = new GameGui(new HumanPlayer("X", eMark.X), new HumanPlayer("O", eMark.O), new VoidRenderer());

        char sizeChar = boardComboBox.getSelectedItem().toString().trim().charAt(0);
        int size = Integer.parseInt(String.valueOf(sizeChar));
        game.setBoardSize(size);
        game.run();

        startFrame.dispose();
    }

    public static void main(String[] args) {
        OptionScreenGameView viewGame = new OptionScreenGameView();
    }
}

