package org.minesweeper;

import org.minesweeper.board.GameBoard;
import org.minesweeper.render.ConsoleAsciiRenderer;

import java.util.Locale;
import java.util.Scanner;

public class GameManager {

    int xCord;
    int yCord;
    boolean gameOver;

    public GameManager(int xCord, int yCord, boolean gameOver){
        boolean validResponse = true;

        this.xCord = xCord;
        this.yCord = yCord;
        this.gameOver = gameOver;


        Scanner sc = new Scanner(System.in);

        //need to add a while loop to check for errors in user responses
        System.out.println("""
                    Please select difficulty:
                    Easy: 2 Mines
                    Medium: 5 Mines
                    Hard: 8 mines
                    """);
        String difficultySelected = sc.next().toLowerCase();
        System.out.println(difficultySelected);
        int mineNum = 0;
        switch (difficultySelected){
            case "easy":
                mineNum = 2;
                break;
            case "medium":
                mineNum = 5;
                break;
            case "hard":
                mineNum = 8;
                break;
        }

        //need to add a while loop to check for errors in user responses
        System.out.println("""
                    Please select board size:
                    Small: 3x3
                    Medium: 5x5
                    Large: 8x8
                    """);
        String boardSizeSelected = sc.next().toLowerCase();
        System.out.println(boardSizeSelected);
        int rows = 0;
        int cols = 0;
        switch (boardSizeSelected){
            case "small":
                rows = 3;cols = 3;
                break;
            case "medium":
                rows = 5;cols = 5;
                break;
            case "large":
                rows = 8;cols = 8;
                break;
        }
        GameBoard gameBoard = new GameBoard(rows, cols, mineNum);
        ConsoleAsciiRenderer consoleAsciiRenderer = new ConsoleAsciiRenderer();
        consoleAsciiRenderer.renderBoard(gameBoard);
        System.out.println(mineNum+" Number of Mines");
        System.out.println("Initial board");

        while(gameOver == false){
            //maybe make this into user turn method or class?
            //need to add a while loop to check for errors in user responses
            System.out.println("""
                    Please enter your choice:
                    reveal
                    flag
                    quit
                    """);
            String userChoice = sc.next();
            int[] userCords;
            if (userChoice.equalsIgnoreCase("reveal") || userChoice.equalsIgnoreCase("flag") || userChoice.equalsIgnoreCase("quit")){
                switch (userChoice){
                    case "reveal":
                        userCords = obtainUserCords(rows,cols);
                        xCord = userCords[0]; yCord = userCords[1];
                        gameBoard.getBoard()[xCord][yCord].reveal();
                        System.out.println(xCord + ", " + yCord);
                        break;
                    case "flag":
                        userCords = obtainUserCords(rows,cols);
                        xCord = userCords[0]; yCord = userCords[1];
                        gameBoard.getBoard()[xCord][yCord].flag();
                        System.out.println(xCord + ", " + yCord);
                        break;
                    case "quit":
                        System.out.println("Game Ended");
                        break;
                }
            }
            System.out.println(userChoice);
            consoleAsciiRenderer.renderBoard(gameBoard);
            System.out.println(mineNum+" Number of Mines");
            System.out.println("Board after user choice");

        }

    }

    public int[] obtainUserCords(int rows, int cols){
        int [] cords = new int[2];
        Scanner sc = new Scanner(System.in);

        //need to add while loop for error handling
        System.out.println("Enter the X-Co-ordinates between ranges 0 to " + (rows-1));
        int tempXCord = sc.nextInt();
        System.out.println("Enter the Y-Co-ordinates between ranges 0 to " + (cols-1));
        int tempYCord = sc.nextInt();

        cords[0] =tempXCord; cords[1] = tempYCord;

        return cords;
    }

}
