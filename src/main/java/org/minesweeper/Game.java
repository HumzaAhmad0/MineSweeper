package org.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Game {
    private Board board;
    private JFrame gameFrame;
    private JPanel topPanel;
    private JLabel statusLabel;
    private JLabel timerLabel;
    private JButton retryButton;
    private JButton saveScoreButton;

    public boolean isGameOver() {
        return gameOver;
    }

    private boolean gameOver;
    private int remainingMines;
    private int remainingTime;
    private Timer timer;
    private int rows, cols, difficulty;

    public Game(int rows, int cols, int difficulty) {
        this.rows = rows;
        this.cols = cols;
        this.difficulty = difficulty;

        gameFrame = new JFrame("Minesweeper");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setResizable(false);

        remainingMines = calculateMines(rows, cols, difficulty);
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

        saveScoreButton = new JButton("Save Score");
        saveScoreButton.setVisible(false); // Initially hidden
        saveScoreButton.addActionListener(this::onSaveScore);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(retryButton);
        buttonPanel.add(saveScoreButton);

        topPanel.add(timerLabel, BorderLayout.WEST);
        topPanel.add(statusLabel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        gameFrame.add(topPanel, BorderLayout.NORTH);

        board = new Board(rows, cols, calculateMines(rows, cols, difficulty), this);
        gameFrame.add(board.getBoardPanel(), BorderLayout.CENTER);

        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        startTimer();
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

        if (won) {
            saveScoreButton.setVisible(true);
        } else {
            saveScoreButton.setVisible(false);
        }
    }

    private void gameOver(boolean won, String message) {
        gameOver = true;
        board.revealMines();
        statusLabel.setText(message);
        retryButton.setVisible(true);
        saveScoreButton.setVisible(false); // Always hidden if the player loses
    }

    private void onRetry(ActionEvent e) {
        gameFrame.dispose();
        new MainMenu();
    }

    private void onSaveScore(ActionEvent e) {
        if (!gameOver || !saveScoreButton.isEnabled()) return;

        String playerName = JOptionPane.showInputDialog(gameFrame, "Enter your name:", "Save Score", JOptionPane.PLAIN_MESSAGE);
        if (playerName == null || playerName.trim().isEmpty()) return;

        int score = calculateScore();
        Leaderboard.saveScore(rows, cols, difficulty, playerName, score);
        JOptionPane.showMessageDialog(gameFrame, "Score saved!", "Success", JOptionPane.INFORMATION_MESSAGE);

        saveScoreButton.setEnabled(false); // Disable the button after saving
    }


    private int calculateScore() {
        int baseScore = rows * cols;
        int timeBonus = remainingTime * (difficulty + 1);
        return baseScore + timeBonus;
    }

    private int calculateMines(int rows, int cols, int difficulty) {
        int baseMines = rows * cols / 8;
        switch (difficulty) {
            case 2:
                //return baseMines * 2;
                return 2;
            case 3:
                return baseMines * 3;
            default:
                return baseMines;
        }
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
