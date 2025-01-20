package org.minesweeper;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Leaderboard {
    private static final String LEADERBOARD_DIRECTORY = "leaderboards";

    static {
        File dir = new File(LEADERBOARD_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void saveScore(int rows, int cols, int difficulty, String playerName, int score) {
        String fileName = getLeaderboardFileName(rows, cols);
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(playerName + "," + difficulty + "," + score + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save score!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void displayLeaderboard(int rows, int cols, int difficulty) {
        String fileName = getLeaderboardFileName(rows, cols);
        StringBuilder leaderboardText = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            leaderboardText.append("Leaderboard for ").append(rows).append("x").append(cols)
                    .append(" (Difficulty ").append(difficulty).append("):\n");
            leaderboardText.append("Name    Score\n"); // Header with spacing

            List<String[]> scores = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[1]) == difficulty) { // Filter by difficulty
                    scores.add(parts);
                }
            }

            scores.sort((a, b) -> Integer.compare(Integer.parseInt(b[2]), Integer.parseInt(a[2]))); // Sort descending

            for (String[] entry : scores) {
                leaderboardText.append(String.format("%-10s %d\n", entry[0], Integer.parseInt(entry[2])));
            }

        } catch (IOException e) {
            leaderboardText.append("No scores available for this board size and difficulty.");
        }

        JOptionPane.showMessageDialog(null, leaderboardText.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String getLeaderboardFileName(int rows, int cols) {
        return LEADERBOARD_DIRECTORY + "/leaderboard_" + rows + "x" + cols + ".txt";
    }
}
