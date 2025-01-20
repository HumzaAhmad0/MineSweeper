package org.minesweeper;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private JFrame mainMenuFrame;
    private JButton btn6x6, btn8x8, btn10x10;
    private JButton btnDifficulty1, btnDifficulty2, btnDifficulty3;
    private JButton btnLeaderboard6x6, btnLeaderboard8x8, btnLeaderboard10x10;
    private JButton btnConfirm;
    private int rows;
    private int cols;
    private int difficulty;

    private JButton selectedGridButton;       // To track the selected grid size button
    private JButton selectedDifficultyButton; // To track the selected difficulty button

    public MainMenu() {
        mainMenuFrame = new JFrame("Minesweeper - Main Menu");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLayout(new BorderLayout());
        mainMenuFrame.setSize(400, 350);
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Choose Grid Size and Difficulty", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainMenuFrame.add(titleLabel, BorderLayout.NORTH);

        // Grid size buttons
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btn6x6 = createButton("6x6");
        btn8x8 = createButton("8x8");
        btn10x10 = createButton("10x10");
        gridPanel.add(btn6x6);
        gridPanel.add(btn8x8);
        gridPanel.add(btn10x10);

        // Difficulty buttons
        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnDifficulty1 = createButton("Difficulty 1");
        btnDifficulty2 = createButton("Difficulty 2");
        btnDifficulty3 = createButton("Difficulty 3");
        difficultyPanel.add(btnDifficulty1);
        difficultyPanel.add(btnDifficulty2);
        difficultyPanel.add(btnDifficulty3);

        // Confirm button
        btnConfirm = createButton("Confirm");
        JPanel confirmPanel = new JPanel();
        confirmPanel.add(btnConfirm);

        // Leaderboard buttons
        JPanel leaderboardPanel = new JPanel(new FlowLayout());
        btnLeaderboard6x6 = createButton("6x6 Ranks");
        btnLeaderboard8x8 = createButton("8x8 Ranks");
        btnLeaderboard10x10 = createButton("10x10 Ranks");
        leaderboardPanel.add(btnLeaderboard6x6);
        leaderboardPanel.add(btnLeaderboard8x8);
        leaderboardPanel.add(btnLeaderboard10x10);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(gridPanel);
        centerPanel.add(difficultyPanel);
        centerPanel.add(leaderboardPanel);
        centerPanel.add(btnConfirm);
        mainMenuFrame.add(centerPanel, BorderLayout.CENTER);

        mainMenuFrame.setVisible(true);

        // Set default selections
        this.rows = 8;
        this.cols = 8;
        this.difficulty = 1;


        selectedGridButton = btn8x8;
        selectedDifficultyButton = btnDifficulty1;
        setSelectedButton(btn8x8, true); // Default grid size is 8x8
        setSelectedButton(btnDifficulty1, true); // Default difficulty is 1


        btn6x6.addActionListener(e -> selectGridSize(btn6x6, 6, 6));
        btn8x8.addActionListener(e -> selectGridSize(btn8x8, 8, 8));
        btn10x10.addActionListener(e -> selectGridSize(btn10x10, 10, 10));


        btnDifficulty1.addActionListener(e -> selectDifficulty(btnDifficulty1, 1));
        btnDifficulty2.addActionListener(e -> selectDifficulty(btnDifficulty2, 2));
        btnDifficulty3.addActionListener(e -> selectDifficulty(btnDifficulty3, 3));


        btnLeaderboard6x6.addActionListener(e -> Leaderboard.displayLeaderboard(6, 6, difficulty));
        btnLeaderboard8x8.addActionListener(e -> Leaderboard.displayLeaderboard(8, 8, difficulty));
        btnLeaderboard10x10.addActionListener(e -> Leaderboard.displayLeaderboard(10, 10, difficulty));


        btnConfirm.addActionListener(e -> startGame());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(115, 50));
        return button;
    }

    private void selectGridSize(JButton button, int rows, int cols) {
        setSelectedButton(selectedGridButton, false); // Deselect previous button
        setSelectedButton(button, true); // Select new button
        selectedGridButton = button;
        this.rows = rows;
        this.cols = cols;
        System.out.println("Grid size set to: " + rows + "x" + cols);
    }

    private void selectDifficulty(JButton button, int difficulty) {
        setSelectedButton(selectedDifficultyButton, false); // Deselect previous button
        setSelectedButton(button, true); // Select new button
        selectedDifficultyButton = button;
        this.difficulty = difficulty;
        System.out.println("Selected Difficulty: " + difficulty);
    }

    private void setSelectedButton(JButton button, boolean isSelected) {
        if (button != null) {
            if (isSelected) {
                button.setBackground(Color.getHSBColor(12,12,12));
                button.setOpaque(true);
                button.setBorderPainted(false);
            } else {
                button.setBackground(UIManager.getColor("Button.background"));
                button.setOpaque(true);
                button.setBorderPainted(true);
            }
        }
    }

    private void startGame() {
        mainMenuFrame.setVisible(false);
        GameInitializer.startGame(rows, cols, difficulty);
    }
}
