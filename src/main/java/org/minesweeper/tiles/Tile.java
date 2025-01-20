package org.minesweeper.tiles;

import org.minesweeper.Board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tile extends JButton {
    private int row;
    private int col;
    private boolean isMine;
    private boolean revealed;
    private boolean flagged;
    private Board board;
    private Color defaultBackground;

    public Tile(int row, int col, Board board) {
        this.row = row;
        this.col = col;
        this.board = board;
        this.isMine = false;
        this.revealed = false;
        this.flagged = false;

        setFont(new Font("Arial", Font.BOLD, 30));
        setPreferredSize(new Dimension(70, 70));
        setMargin(new Insets(0, 0, 0, 0));

        defaultBackground = getBackground();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (board.getGame().isGameOver()) return;

                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (!flagged) {
                        board.checkTile(row, col);
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    toggleFlag();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!revealed && !flagged) {
                    setBackground(defaultBackground.darker());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!revealed && !flagged) {
                    setBackground(defaultBackground);
                }
            }
        });


    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void reveal(boolean showMine) {
        if (!revealed && !flagged) { // Only reveal if not flagged
            revealed = true;
            setEnabled(false);
            setBackground(defaultBackground);

            if (isMine && showMine) {
                setText("X");
            }
        }
    }

    private void toggleFlag() {
        if (!revealed) {
            flagged = !flagged;
            setText(flagged ? "F" : "");
            setBackground(defaultBackground);
        }
    }

    public void setTextForGameOver(boolean showMine) {
        if (revealed) return;

        if (isMine && showMine) {
            setText("X");
        }
    }
}
