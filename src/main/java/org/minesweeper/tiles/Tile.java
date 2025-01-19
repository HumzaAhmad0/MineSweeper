package org.minesweeper.tiles;

public abstract class Tile {
    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

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

    public void testFlip(){
        if(!isFlagged){
            isRevealed = false;
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
