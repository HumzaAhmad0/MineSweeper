package org.minesweeper;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private JFrame mainMenuFrame;
    private JButton btn6x6, btn8x8, btn10x10;
    private JButton btnDifficulty1, btnDifficulty2, btnDifficulty3;
    private JButton btnConfirm;
    private int rows, cols, difficulty;

    public MainMenu() {
        //making the window and the buttons which are displayed to the user
        mainMenuFrame = new JFrame("Minesweeper - Main Menu");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLayout(new BorderLayout());
        mainMenuFrame.setSize(350, 300);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Choose Grid Size and Difficulty", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainMenuFrame.add(titleLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));  // Center alignment
        btn6x6 = createButton("6x6");
        btn8x8 = createButton("8x8");
        btn10x10 = createButton("10x10");
        gridPanel.add(btn6x6);
        gridPanel.add(btn8x8);
        gridPanel.add(btn10x10);
        mainMenuFrame.add(gridPanel, BorderLayout.CENTER);

        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));  // Center alignment
        btnDifficulty1 = createButton("Difficulty 1");
        btnDifficulty2 = createButton("Difficulty 2");
        btnDifficulty3 = createButton("Difficulty 3");
        difficultyPanel.add(btnDifficulty1);
        difficultyPanel.add(btnDifficulty2);
        difficultyPanel.add(btnDifficulty3);

        JPanel confirmPanel = new JPanel();
        btnConfirm = createButton("Confirm");
        confirmPanel.add(btnConfirm);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(difficultyPanel);
        bottomPanel.add(confirmPanel);
        mainMenuFrame.add(bottomPanel, BorderLayout.SOUTH);

        mainMenuFrame.setVisible(true);

        this.difficulty = 1;
        this.rows = 8;
        this.cols = 8;


        //setting the board and diff based on user input (clicking the button)
        btn6x6.addActionListener(e -> updateGrid(6, 6));
        btn8x8.addActionListener(e -> updateGrid(8, 8));
        btn10x10.addActionListener(e -> updateGrid(10, 10));

        btnDifficulty1.addActionListener(e -> updateDifficulty(1));
        btnDifficulty2.addActionListener(e -> updateDifficulty(2));
        btnDifficulty3.addActionListener(e -> updateDifficulty(3));

        btnConfirm.addActionListener(e -> startGame());
    }


    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 50));  // Fixed button size
        return button;
    }

    private void updateGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        System.out.println("Grid size set to: " + rows + "x" + cols);
    }

    private void updateDifficulty(int difficulty) {
        this.difficulty = difficulty;
        System.out.println("Selected Difficulty: " + difficulty);
    }

    private void startGame() {
        //making the choice window invisible and then starting the game and displaying that window etc (within that class)
        mainMenuFrame.setVisible(false);
        GameInitializer.startGame(rows, cols, difficulty);
    }
}
