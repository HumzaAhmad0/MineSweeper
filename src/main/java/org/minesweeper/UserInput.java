package org.minesweeper;

import org.minesweeper.board.GameBoard;
import org.minesweeper.tiles.Tile;

public class UserInput {
    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    protected String userChoice;
    protected int xCord;
    protected int yCord;
    protected GameBoard gameBoard;


    public UserInput(String userChoice, int xCord, int yCord, GameBoard gameBoard){
        this.userChoice = userChoice;
        this.xCord = xCord;
        this.yCord = yCord;
        this.gameBoard = gameBoard;

        if (userChoice.equalsIgnoreCase("flag")){
            gameBoard.getBoard()[xCord][yCord].flag();
        } else if (userChoice == "reveal") {
            gameBoard.getBoard()[xCord][yCord].reveal();
        }else {
            error(userChoice);
        }

    }

    public int error(String userChoice){
        System.out.println(userChoice + "is not a valid input please try again");
        return 2;
    }



}
