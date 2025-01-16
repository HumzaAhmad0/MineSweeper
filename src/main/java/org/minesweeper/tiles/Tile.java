package org.minesweeper.tiles;

public abstract class Tile {
    protected boolean isRevealed;
    protected boolean isFlagged;

    public Tile(){
        this.isFlagged = false;
        this.isRevealed = false;
    }

    public void reveal(){
        if(!isFlagged){
            isRevealed = true;
        }
    }

    public void flag(){
        if (!isRevealed){
            isFlagged = !isFlagged;
        }
    }

    public boolean isMine(){
        return false;
    }

    public abstract String toString();
}
