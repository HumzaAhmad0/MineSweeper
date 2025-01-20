package org.minesweeper;
import javax.swing.*;

public class GameInitializer  {

    public static void startGame(int rows, int cols, int difficulty) {
        SwingUtilities.invokeLater(() -> new Game(rows, cols, difficulty));
    }

}
