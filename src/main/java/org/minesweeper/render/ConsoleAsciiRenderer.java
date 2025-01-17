package org.minesweeper.render;

import org.minesweeper.board.GameBoard;
import org.minesweeper.tiles.Tile;

public class ConsoleAsciiRenderer {

    public static void renderBoard(GameBoard gameBoard, int rows, int cols) {
        Tile[][] board = gameBoard.getBoard();

        // Print column indices at the top
        System.out.print("   ");  // Indentation to align with row indices
        for (int col = 0; col < cols; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        // Print the board with row indices
        for (int row = 0; row < rows; row++) {
            System.out.print(row + "  ");  // Row index followed by spacing
            for (int col = 0; col < cols; col++) {
                System.out.print(board[row][col].toString() + " ");
            }
            System.out.println();
        }
    }
    public static void renderBoard(GameBoard gameBoard, int mineNum, int turnNum, int rows, int cols) {
        Tile[][] board = gameBoard.getBoard();

        // Print column indices at the top
        System.out.print("   ");  // Indentation to align with row indices
        for (int col = 0; col < cols; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        // Print the board with row indices
        for (int row = 0; row < rows; row++) {
            System.out.print(row + "  ");  // Row index followed by spacing
            for (int col = 0; col < cols; col++) {
                System.out.print(board[row][col].toString() + " ");
            }
            System.out.println();
        }

        // Display additional information
        System.out.println("\nTurn Number: " + turnNum);
        System.out.println("Number of Mines: " + mineNum);
    }

    public void firstMoveQuestion(){
        System.out.println("""
                    Please enter your choice:
                    reveal
                    quit
                    """);
    }

    public void moveQuestion(){
        System.out.println("""
                    Please enter your choice:
                    reveal
                    flag
                    quit
                    """);
    }

    public void selectDifficulty(){
        System.out.println("""
                    Please select difficulty:
                    Easy: 2 Mines
                    Medium: 5 Mines
                    Hard: 8 mines
                    """);
    }
//    public void invalidDifficulty(String difficultyChoice){
//        System.out.println(difficultyChoice+" is not a valid option\n" + """
//                Please re-enter a valid option
//                Easy: 2 Mines
//                Medium: 5 Mines
//                Hard: 8 mines
//                """);
//    }

    public static void difficultyNumInputError(String difficultyChoice){
        System.out.println();
        System.out.println(difficultyChoice+" is not a valid option\n" + """
                Please re-enter a valid option
                Easy: 2 Mines
                Medium: 5 Mines
                Hard: 8 mines
                NO NUMBERS ALLOWED
                """);
    }   public static void sizeNumInputError(String boardSizeSelected){
        System.out.println();
        System.out.println(boardSizeSelected+" is not a valid option\n" + """
                Please re-enter a valid option
                Small: 3x3
                Medium: 5x5
                Large: 8x8
                NO NUMBERS ALLOWED
                """);
    }
    public static void difficultyWrongTxtError(String difficultyChoice){
        System.out.println();
        System.out.println(difficultyChoice+" is not a valid option\n" + """
                Please re-enter a valid option
                Easy: 2 Mines
                Medium: 5 Mines
                Hard: 8 mines
                ONLY [easy, medium, hard] IS ACCEPTED
                """);
    }
    public static void sizeWrongTxtError(String boardSizeSelected){
        System.out.println();
        System.out.println(boardSizeSelected+" is not a valid option\n" + """
                Please re-enter a valid option
                Small: 3x3
                Medium: 5x5
                Large: 8x8
                ONLY [small, medium, large] IS ACCEPTED
                """);
    }

    public void selectSize(){
        System.out.println("""
                    Please select board size:
                    Small: 3x3
                    Medium: 5x5
                    Large: 8x8
                    """);
    }

    public void displayDifficultySelected(String difficultySelected){
        System.out.println("You have selected "+difficultySelected);

    }
    public void displaySizeSelected(String sizeSelected){
        System.out.println("You have selected "+sizeSelected);

    }
    public void displayMineCount(int mineNum){
        System.out.println("Number of bombs: "+mineNum);

    }

    public void displayQuestionCord(String type, int value){
        System.out.println("Enter the " +type+"-Co-ordinates between ranges 0 to " + value);
    }
}
