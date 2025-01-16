package org.minesweeper.board;
import org.minesweeper.tiles.*;

import java.util.Random;


public class gameBoard {


    private Tile[][] board;
    private int rows;
    private int cols;
    private int totalMines;

    public Tile[][] getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getTotalMines() {
        return totalMines;
    }

    public gameBoard(int rows, int cols, int totalMines) {
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        this.board = new Tile[rows][cols];
        makeBoard();
    }

    public void makeBoard(){
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                board[i][j] = new SafeTile(0);
            }
        }
        randomMines();
        calculateAdjacentMineCounts();
    }

    public void randomMines(){
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int x = rand.nextInt(rows);
            int y = rand.nextInt(cols);
            if (!(board[x][y] instanceof MineTile)) {
                board[x][y] = new MineTile();
                minesPlaced++;
            }
        }
    }
    private void calculateAdjacentMineCounts() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!(board[i][j] instanceof MineTile)) {
                    int adjMines = countAdjacentMines(i, j);
                    ((SafeTile) board[i][j]).setAdjMineNum(adjMines);
                }
            }
        }
    }
    private int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i >= 0 && x + i < rows && y + j >= 0 && y + j < cols) {
                    if (board[x + i][y + j].isMine()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
