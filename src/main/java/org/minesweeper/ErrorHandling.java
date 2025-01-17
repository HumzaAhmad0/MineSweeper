package org.minesweeper;

import org.minesweeper.render.ConsoleAsciiRenderer;

public class ErrorHandling {
    private String difficultyChoice;
    private String boardSizeSelected;
    private String boardSize;
    ConsoleAsciiRenderer consoleAsciiRenderer = new ConsoleAsciiRenderer();

    public ErrorHandling(){
    }

    public boolean difficultyCheck(String difficultyChoice){
        this.difficultyChoice = difficultyChoice;
        if (difficultyChoice.matches(".*\\d.*")){
            ConsoleAsciiRenderer.difficultyNumInputError(difficultyChoice);
            return false;
        }else if (difficultyChoice.equalsIgnoreCase("easy") || difficultyChoice.equalsIgnoreCase("medium") || difficultyChoice.equalsIgnoreCase("hard")){
            return true;
        }else{
            ConsoleAsciiRenderer.difficultyWrongTxtError(difficultyChoice);
            return false;
        }
    }
    public boolean sizeCheck(String boardSizeSelected){
        this.boardSizeSelected = boardSizeSelected;
        if (boardSizeSelected.matches(".*\\d.*")){
            ConsoleAsciiRenderer.sizeNumInputError(boardSizeSelected);
            return false;
        }else if (boardSizeSelected.equalsIgnoreCase("small") || boardSizeSelected.equalsIgnoreCase("medium") || boardSizeSelected.equalsIgnoreCase("large")){
            return true;
        }else{
            ConsoleAsciiRenderer.sizeWrongTxtError(boardSizeSelected);
            return false;
        }
    }

}
