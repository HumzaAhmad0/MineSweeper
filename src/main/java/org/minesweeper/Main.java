package org.minesweeper;
import org.minesweeper.board.*;
import org.minesweeper.tiles.*;

public class Main {
    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;
        int totalMines = 8;
        gameBoard gameBoard = new gameBoard(rows, cols, totalMines);

        System.out.println("initialised:");
        testBoardInitialization(gameBoard);

        gameBoard.getBoard()[0][0].reveal();
        gameBoard.getBoard()[1][1].reveal();

        System.out.println("Board state after revealing some tiles:");
        testBoardInitialization(gameBoard);

        revealEntireBoard(gameBoard);
        System.out.println("Board state after revealing all tiles:");
        testBoardInitialization(gameBoard);
    }

    public static void testBoardInitialization(gameBoard gameBoard) {
        Tile[][] board = gameBoard.getBoard();

        System.out.println("Board state:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].toString() + " ");
            }
            System.out.println();
        }
    }

    public static void revealEntireBoard(gameBoard gameBoard) {
        Tile[][] board = gameBoard.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].reveal();
            }
        }
        System.out.println("Board state after concealing all tiles:");
        testBoardInitialization(gameBoard);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].testFlip();
            }
        }
    }
}