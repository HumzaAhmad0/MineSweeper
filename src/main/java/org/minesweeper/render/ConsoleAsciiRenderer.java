package org.minesweeper.render;

import org.minesweeper.board.GameBoard;
import org.minesweeper.tiles.Tile;

public class ConsoleAsciiRenderer {

    public static void renderBoard(GameBoard gameBoard) {
        Tile[][] board = gameBoard.getBoard();

        System.out.println("Board state:");
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                System.out.print(tile.toString() + " ");
            }
            System.out.println();
        }
    }
}
