package org.minesweeper;

import org.minesweeper.board.GameBoard;
import org.minesweeper.render.ConsoleAsciiRenderer;
import java.util.Scanner;

public class GameManager {
    int xCord;
    int yCord;

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    boolean gameOver;
    ConsoleAsciiRenderer consoleAsciiRenderer = new ConsoleAsciiRenderer();

    public GameManager(int xCord, int yCord, boolean gameOver){
        boolean validResponse = true;

        this.xCord = xCord;
        this.yCord = yCord;
        this.gameOver = gameOver;
        Scanner sc = new Scanner(System.in);
        ErrorHandling errorHandling = new ErrorHandling();
        String difficultySelected;

        consoleAsciiRenderer.selectDifficulty();
        do {
            difficultySelected = sc.next().toLowerCase();
            validResponse =  errorHandling.difficultyCheck(difficultySelected);
        }while (!validResponse);
        consoleAsciiRenderer.displayDifficultySelected(difficultySelected);

        int mineNum = switch (difficultySelected) {
            case "easy" -> 2;
            case "medium" -> 5;
            case "hard" -> 8;
            default -> 0;
        };

        String boardSizeSelected;
        consoleAsciiRenderer.selectSize();
        do {
            boardSizeSelected = sc.next().toLowerCase();
            validResponse =  errorHandling.sizeCheck(boardSizeSelected);
        }while (!validResponse);
        consoleAsciiRenderer.displaySizeSelected(boardSizeSelected);

        int rows = 0;
        int cols = switch (boardSizeSelected) {
            case "small" -> {
                rows = 3;
                yield 3;
            }
            case "medium" -> {
                rows = 5;
                yield 5;
            }
            case "large" -> {
                rows = 8;
                yield 8;
            }
            default -> 0;
        };
        GameBoard gameBoard = new GameBoard(rows, cols);
        ConsoleAsciiRenderer.renderBoard(gameBoard, rows, cols);
        consoleAsciiRenderer.displayMineCount(mineNum);

        int turnNumber = 0;
        int firstMoveX;
        int firstMoveY;
        int numOfSafeTiles = (rows*cols) - mineNum;
        String response;
        while(!gameOver){
            //maybe make this into user turn method or class?
            //need to add a while loop to check for errors in user responses
            if (gameOver) {
                consoleAsciiRenderer.gameOverFail();
                do {
                    response = sc.next().toLowerCase();
                    validResponse =  errorHandling.gameOverResponse(response);
                }
                while (!validResponse);

                if (response.equalsIgnoreCase("quit")){
                    ConsoleAsciiRenderer.endScreen();
                    System.exit(0);
                    //i need to quit the console or something here
                } else if (response.equalsIgnoreCase("retry")) {
                    // need to restart from the start of the class basically
                }
                break;
            }

            String userChoice;
            if(turnNumber == 0){
                consoleAsciiRenderer.firstMoveQuestion();
            }else{
                consoleAsciiRenderer.moveQuestion();
            }
            userChoice = sc.next();

            int[] userCords;

            //GameBoard gameBoard;
            if (userChoice.equalsIgnoreCase("reveal") || userChoice.equalsIgnoreCase("flag") || userChoice.equalsIgnoreCase("quit")){
                switch (userChoice){
                    case "reveal":
                        userCords = obtainUserCords(rows,cols);
                        xCord = userCords[0]; yCord = userCords[1];
                        if(turnNumber == 0){
                            firstMoveX = xCord; firstMoveY = yCord;
                            gameBoard.makeActualBoard(firstMoveX,firstMoveY);
                            gameBoard = new GameBoard(rows, cols, mineNum, firstMoveX, firstMoveY, this);

                        }
                        //gameBoard.getBoard()[xCord][yCord].reveal();
                        gameBoard.revealTile(xCord, yCord);
                        System.out.println(xCord + ", " + yCord);
                        turnNumber++;
                        break;
                    case "flag":
                        userCords = obtainUserCords(rows,cols);
                        xCord = userCords[0]; yCord = userCords[1];
                        gameBoard.getBoard()[xCord][yCord].flag();
                        System.out.println(xCord + ", " + yCord);
                        break;
                    case "quit":
                        System.out.println("Game Ended");
                        gameOver = true;
                        System.exit(0);
                        break;
                }
            }
            System.out.println(userChoice);
            ConsoleAsciiRenderer.renderBoard(gameBoard, mineNum,turnNumber, rows, cols);
        }

    }

    public int[] obtainUserCords(int rows, int cols){
        int [] cords = new int[2];
        Scanner sc = new Scanner(System.in);

        //need to add while loop for error handling
        String cordAsked = "X";
        consoleAsciiRenderer.displayQuestionCord(cordAsked, (rows-1));
        int tempXCord = sc.nextInt();
        cordAsked = "Y";
        consoleAsciiRenderer.displayQuestionCord(cordAsked, (cols-1));
        int tempYCord = sc.nextInt();

        cords[0] =tempXCord; cords[1] = tempYCord;

        return cords;
    }

}
