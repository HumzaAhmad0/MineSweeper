package org.minesweeper;
import org.minesweeper.board.*;
import org.minesweeper.tiles.*;

public class Main {
    public static void main(String[] args) {
        boolean gameOver = false;
        GameManager gameManager = new GameManager(1, 1, gameOver);
    }
}