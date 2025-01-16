package org.minesweeper.tiles;

public class MineTile extends Tile {

    public boolean isMine(){
        return true;
    }

    @Override
    public String toString() {
        return isRevealed ? "X":"#" ;
    }
}
