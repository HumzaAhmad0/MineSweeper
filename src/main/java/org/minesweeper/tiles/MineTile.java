package org.minesweeper.tiles;

public class MineTile extends Tile {

    public boolean isMine(){
        return true;
    }

    @Override
    public String toString() {

        if (isRevealed) {
            return "X";
        } else if (isFlagged) {
            return "F";
        } else {
            return "#";
        }
    }
}
