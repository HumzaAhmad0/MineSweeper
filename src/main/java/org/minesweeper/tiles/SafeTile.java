package org.minesweeper.tiles;

public class SafeTile extends Tile {
    protected int adjMineNum;
    //maybe private ask riccardo
    public SafeTile(int adjMineNum){
        this.adjMineNum = adjMineNum;
    }

    @Override
    public String toString(){
        if(isRevealed){
            return adjMineNum == 0? "":String.valueOf(adjMineNum);
        }else {
            return "#";
        }
    }
}
