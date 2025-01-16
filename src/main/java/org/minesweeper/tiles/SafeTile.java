package org.minesweeper.tiles;

public class SafeTile extends Tile {
    protected int adjMineNum;

    public SafeTile(int adjMineNum){
        this.adjMineNum = adjMineNum;
    }
    public int getAdjMineNum() {
        return adjMineNum;
    }
    public void setAdjMineNum(int adjMineNum) {
        this.adjMineNum = adjMineNum;
    }

    @Override
    public String toString() {
        if (isFlagged) {
            return "F";
        } else if (isRevealed) {
            return adjMineNum == 0 ? "0" : String.valueOf(adjMineNum);
        } else {
            return "#";
        }
    }
}

