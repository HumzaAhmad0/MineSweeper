package org.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Game {
    private Board board;
    private JFrame frame;
    private JPanel topPanel;
    private JLabel statusLabel;
    private JLabel timerLabel;
    private JButton retryButton;
    private boolean gameOver;
    private int remainingMines;
    private int remainingTime;
    private Timer timer;

    public Game(int rows, int cols, int difficulty) {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        //based on diff and board size calc the num of mines
        remainingMines = calculateMines(rows, cols, difficulty);
        //depending on diff change the time
        remainingTime = getInitialTime(difficulty);

        topPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Minesweeper: " + remainingMines);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));

        timerLabel = new JLabel("Time Remaining: " + remainingTime + "s");
        timerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        retryButton = new JButton("Retry");
        retryButton.setVisible(false);
        retryButton.addActionListener(this::onRetry);

        topPanel.add(timerLabel, BorderLayout.WEST);
        topPanel.add(statusLabel, BorderLayout.CENTER);
        topPanel.add(retryButton, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);

        //making the board based off the user choice and calculations
        board = new Board(rows, cols, calculateMines(rows, cols, difficulty), this);
        frame.add(board.getBoardPanel(), BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        startTimer(); // Start the timer after the frame is visible
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            if (gameOver) {
                timer.stop();
                return;
            }

            remainingTime--;
            timerLabel.setText("Time Remaining: " + remainingTime + "s");

            if (remainingTime <= 0) {
                timer.stop();
                gameOver(false, "Time Ran Out!");
            }
        });
        timer.start();
    }

    public void gameOver(boolean won) {
        gameOver = true;
        board.revealMines();
        statusLabel.setText(won ? "You Win!" : "Game Over!");
        retryButton.setVisible(true);
    }

    private void gameOver(boolean won, String message) {
        gameOver = true;
        board.revealMines();
        statusLabel.setText(message);
        retryButton.setVisible(true);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private int calculateMines(int rows, int cols, int difficulty) {
        int baseMines = rows * cols / 8;
        switch (difficulty) {
            case 2:
                return baseMines * 2;
            case 3:
                return baseMines * 3;
            default:
                return baseMines;
        }
    }

    private void onRetry(ActionEvent e) {
        frame.dispose();
        new MainMenu();
    }

    private int getInitialTime(int difficulty) {
        switch (difficulty) {
            case 1:
                return 100; // Easy
            case 2:
                return 50;  // Medium
            case 3:
                return 25;  // Hard
            default:
                return 100;
        }
    }
}
