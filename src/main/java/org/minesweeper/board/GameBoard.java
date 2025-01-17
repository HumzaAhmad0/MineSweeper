package org.minesweeper.board;
import org.minesweeper.tiles.*;

import java.util.Random;


public class GameBoard {
    private Tile[][] board;
    private int rows;
    private int cols;
    private int totalMines;
    private int firstMoveX;
    private int firstMoveY;

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

    public GameBoard(int rows, int cols, int totalMines, int firstMoveX, int firstMoveY){
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        this.board = new Tile[rows][cols];
        this.firstMoveX = firstMoveX;
        this.firstMoveY = firstMoveY;
        makeActualBoard(firstMoveX, firstMoveY);
    }

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        //this.totalMines = totalMines;
        this.board = new Tile[rows][cols];
        makeBoard();
    }

    public void makeBoard(){
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                board[i][j] = new SafeTile(0);
            }
        }
       // randomMines();
       // calculateAdjacentMineCounts();
    }
    public void makeActualBoard(int firstMoveX, int firstMoveY){
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                board[i][j] = new SafeTile(0);
            }
        }
        randomMines(firstMoveX, firstMoveY);
        calculateAdjacentMineCounts();
    }

    public void randomMines(int firstMoveX, int firstMoveY){
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
    private boolean safeZone(int x, int y, int firstMoveX, int firstMoveY){
        for (int i = -1; i <= 1; i++){
            for(int j = -1; j<= 1;j++){
                int safeX = firstMoveX +1;
                int safeY = firstMoveY +1;

                if (safeX>0 && safeX < rows && safeY >= 0 && safeY < cols){
                    if (x == safeX && y == safeY){
                        return true;
                    }
                }

            }
        }
        return false;
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
