package org.minesweeper;

import org.minesweeper.tiles.Tile;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int rows;
    private int cols;

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    private int mineCount;
    private Tile[][] tiles;
    private ArrayList<Tile> mines;
    private JPanel boardPanel;
    private boolean firstClick = true;

    public Game getGame() {
        return game;
    }


    public void setGame(Game game) {
        this.game = game;
    }

    private Game game;
    private int tilesClicked;

    public Board(int rows, int cols, int mineCount, Game game) {
        this.rows = rows;
        this.cols = cols;
        this.mineCount = mineCount;
        this.game = game;
        this.tilesClicked = 0;
        this.tiles = new Tile[rows][cols];
        this.mines = new ArrayList<>();

        boardPanel = new JPanel(new GridLayout(rows, cols));
        initTiles();
        placeMines();
    }

    private void initTiles() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile tile = new Tile(r, c, this);
                tiles[r][c] = tile;
                boardPanel.add(tile);
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int minesToPlace = mineCount;

        while (minesToPlace > 0) {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);

            Tile tile = tiles[r][c];
            if (!tile.isMine()) {
                tile.setMine(true);
                mines.add(tile);
                minesToPlace--;
            }
        }
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public void revealMines() {
        for (Tile mine : mines) {
            mine.setTextForGameOver(true);
        }
    }

    public void checkTile(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || !tiles[r][c].isEnabled() || tiles[r][c].isFlagged()) {
            return;
        }

        if (firstClick) {
            firstClick = false;
            Tile clickedTile = tiles[r][c];

            if (clickedTile.isMine()) {
                swapMineWithNonMineTile(clickedTile);
            }

            clickedTile.reveal(false);
        }

        Tile tile = tiles[r][c];
        tile.reveal(false);

        if (tile.isMine()) {
            tile.setText("X");
            tile.setBackground(Color.RED);
            game.gameOver(false);
        } else {
            tilesClicked++;
            int adjacentMines = countAdjacentMines(r, c);
            if (adjacentMines > 0) {
                tile.setText(Integer.toString(adjacentMines));
            } else {
                for (int rowChange = -1; rowChange <= 1; rowChange++) {
                    for (int colChange = -1; colChange <= 1; colChange++) {
                        checkTile(r + rowChange, c + colChange);
                    }
                }
            }

            if (tilesClicked == rows * cols - mineCount) {
                game.gameOver(true);
            }
        }
    }

    private int countAdjacentMines(int r, int c) {
        int count = 0;
        for (int rowChange = -1; rowChange <= 1; rowChange++) {
            for (int colChange = -1; colChange <= 1; colChange++) {
                int neighbourR = r + rowChange, neighbourC = c + colChange;
                if (neighbourR >= 0 && neighbourR < rows && neighbourC >= 0 && neighbourC < cols && tiles[neighbourR][neighbourC].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    private void swapMineWithNonMineTile(Tile clickedMineTile) {
        Random random = new Random();

        Tile swapTile;
        do {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);
            swapTile = tiles[r][c];
        } while (swapTile.isMine());

        // Swap the mines between the two tiles
        clickedMineTile.setMine(false);
        swapTile.setMine(true);

    }

}
