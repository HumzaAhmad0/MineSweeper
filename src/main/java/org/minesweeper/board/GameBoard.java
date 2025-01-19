package org.minesweeper.board;
import org.minesweeper.GameManager;
import org.minesweeper.tiles.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class GameBoard {
    private Tile[][] board;
    private int rows;
    private int cols;
    private int totalMines;
    private int firstMoveX;
    private int firstMoveY;
    private GameManager gameManager;


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

    public GameBoard(int rows, int cols, int totalMines, int firstMoveX, int firstMoveY, GameManager gameManager){
        this.rows = rows;
        this.cols = cols;
        this.totalMines = totalMines;
        this.board = new Tile[rows][cols];
        this.firstMoveX = firstMoveX;
        this.firstMoveY = firstMoveY;
        this.gameManager = gameManager;
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

    public void revealTile(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return; // Out of bounds
        }

        Tile tile = board[x][y];

        if (tile.isRevealed() || tile.isFlagged()) {
            return; // Tile already revealed or flagged
        }

        tile.reveal();

        if (tile.isMine()) {
            System.out.println("Game Over! You revealed a mine.");
            gameManager.setGameOver(true);
            return;
        }

        // If the tile is a SafeTile with no adjacent mines (0), perform a flood fill
        if (tile instanceof SafeTile) {
            SafeTile safeTile = (SafeTile) tile;
            int adjMines = safeTile.getAdjMineNum();

            // If the tile has no adjacent mines (0), flood fill adjacent tiles
            if (adjMines == 0) {
                // Use a queue for flood fill (BFS)
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{x, y});

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    int currentX = current[0];
                    int currentY = current[1];

                    // Check all 8 adjacent tiles (N, S, E, W, NE, NW, SE, SW)
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int adjX = currentX + i;
                            int adjY = currentY + j;

                            // Skip out-of-bounds
                            if (adjX < 0 || adjX >= rows || adjY < 0 || adjY >= cols) {
                                continue;
                            }

                            Tile adjacentTile = board[adjX][adjY];

                            // Only reveal adjacent tiles that are safe and not revealed or flagged
                            if (!adjacentTile.isRevealed() && !adjacentTile.isFlagged() && adjacentTile instanceof SafeTile) {
                                adjacentTile.reveal();
                                SafeTile adjacentSafeTile = (SafeTile) adjacentTile;

                                // If this adjacent tile has no adjacent mines, add it to the queue for further flood-fill
                                if (adjacentSafeTile.getAdjMineNum() == 0) {
                                    queue.add(new int[]{adjX, adjY});
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
