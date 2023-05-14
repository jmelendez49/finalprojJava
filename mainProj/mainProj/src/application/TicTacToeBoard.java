package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TicTacToeBoard {
    
    // Tic Tac Toe Board Initated
    private StackPane pane;
    private InformationCenter informationCenter;
    private Tile[][] tiles = new Tile[3][3];
    private Line winningLine;

    // Player Initated
    private char playerTurn = 'X';

    // Determination of game ended or not
    private boolean isEndOfGame = false;

    // Setting up the actual tic tac toe board
    public TicTacToeBoard(InformationCenter informationCenter) {
        this.informationCenter = informationCenter;
        pane = new StackPane();
        pane.setMinSize(Constants.APP_WIDTH, Constants.TILE_BOARD_HEIGHT);
        pane.setTranslateX(Constants.APP_WIDTH / 2);
        pane.setTranslateY((Constants.TILE_BOARD_HEIGHT / 2) + Constants.INFO_CENTER_HEIGHT);

        addAllPos();

        winningLine = new Line();
        pane.getChildren().add(winningLine);
    }

    // Setting up positions of the tiles
    private void addAllPos() {
        for (int row = 0; row < 3; row++ ) {
            for (int col = 0; col < 3; col++) {
                Tile tile = new Tile();
                tile.getsStackPane().setTranslateX((col * 100) - 100);
                tile.getsStackPane().setTranslateY((row * 100) - 100);
                pane.getChildren().add(tile.getsStackPane());
                tiles[row][col] = tile;
            }
        }
    }

    public void startNewGame() {
        isEndOfGame = false;
        playerTurn = 'X';
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                tiles[row][col].setValue("");
            }
        }
        winningLine.setVisible(false);
    }

    // This changes the players turn depending on who went.
    public void changePlayerTurn() {
        if (playerTurn == 'X') {
            playerTurn = 'O';
        } else {
            playerTurn = 'X';
        }
        informationCenter.updateMessage("It is Player " + playerTurn + "'s turn!");
    }

    public String getPlayerTurn() {
        return String.valueOf(playerTurn);
    }

    public StackPane getsStackPane() {
        return pane;
    }

    // Main code that checks for wins, or ties
    public void checkForWinner() {
        checkRowsForWinner();
        checkColsForWinner();
        checkTopLeftToBottomRightForWinner();
        checkTopRightToBottomLeftForWInner();
        CheckStalemate();
    }

    // Checks for any wins through the rows
        private void checkRowsForWinner() {
            for (int row = 0; row < 3; row++) {
                if (tiles[row][0].getValue().equals(tiles[row][1].getValue())
                        && tiles[row][0].getValue().equals(tiles[row][2].getValue())
                        && !tiles[row][0].getValue().isEmpty()) {
                            String winner = tiles[row][0].getValue();
                            endGame(winner, new WinningTiles(tiles[row][0], tiles[row][1], tiles[row][2]));
                            return;
                        }
                    }
                }

    
    // Checks for any wins through the columns       
        private void checkColsForWinner() {
            if (!isEndOfGame) {
                for (int col = 0; col < 3; col++) {
                    if (tiles[0][col].getValue().equals(tiles[1][col].getValue()) 
                            && tiles[0][col].getValue().equals(tiles[2][col].getValue()) 
                            && !tiles[0][col].getValue().isEmpty()) {
                                String winner = tiles[0][col].getValue();
                                endGame(winner, new WinningTiles(tiles[0][col], tiles[1][col], tiles[2][col]));
                                return;
                        }
                    }
                }
            }
    // Checks for any wins through diagonals 
        private void checkTopLeftToBottomRightForWinner() {
            if (!isEndOfGame) {
              if (tiles[0][0].getValue().equals(tiles[1][1].getValue()) 
                    && tiles [0][0].getValue().equals(tiles[2][2].getValue())
                    && !tiles[0][0].getValue().isEmpty()) {
                        String winner = tiles[0][0].getValue();
                        endGame(winner, new WinningTiles(tiles[0][0], tiles[1][1], tiles[2][2]));
                        return;
                }  
            }
        }

    // Checks for any wins through diagonals again
        private void checkTopRightToBottomLeftForWInner() {
            if (!isEndOfGame) {
                if (tiles[0][2].getValue().equals(tiles[1][1].getValue()) 
                    && tiles [0][2].getValue().equals(tiles[2][0].getValue())
                    && !tiles[0][2].getValue().isEmpty()) {
                        String winner = tiles[0][2].getValue();
                        endGame(winner, new WinningTiles(tiles[0][2], tiles[1][1], tiles[2][0]));
                        return;
                }  
            }
        }

    // If no one has won through any of the methods above, then they tie
    // resulting in a stalemate
        private void CheckStalemate() {
            if(!isEndOfGame) {
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        if(tiles[row][col].getValue().isEmpty()) {
                            return;
                        }
                    }
                }

                isEndOfGame = true;
                informationCenter.updateMessage("It's a tie!");
                informationCenter.showStartButton();
            }
        }

        private void endGame(String winner, WinningTiles winningTiles) {
            isEndOfGame = true;
            drawWinningLine(winningTiles);
            informationCenter.updateMessage("Player" + winner + "has won!");
            informationCenter.showStartButton();
        }

        private void drawWinningLine(WinningTiles winningTiles) {
            winningLine.setStartX(winningTiles.start.getsStackPane().getTranslateX());
            winningLine.setStartY(winningTiles.start.getsStackPane().getTranslateY());
            winningLine.setEndX(winningTiles.end.getsStackPane().getTranslateX());
            winningLine.setEndY(winningTiles.end.getsStackPane().getTranslateY());
            winningLine.setTranslateX(winningTiles.middle.getsStackPane().getTranslateX());
            winningLine.setTranslateY(winningTiles.middle.getsStackPane().getTranslateY());

            winningLine.setVisible(true);
        }

        private class WinningTiles {
            Tile start;
            Tile middle;
            Tile end;

            public WinningTiles(Tile start, Tile middle, Tile end) {
                this.start = start;
                this.middle = middle;
                this.end = end;
            }
        }

    // Once tile board has been set up, this configures the lines, colors and attributes
    // of the board
    private class Tile {

        private StackPane pane;
        private Label label;

        public Tile() {
            pane = new StackPane();
            pane.setMinSize(100, 100);

            Rectangle border = new Rectangle();
            border.setWidth(100);
            border.setHeight(100);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            Label label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(event -> {
                if (label.getText().isEmpty() && !isEndOfGame) {
                    label.setText(getPlayerTurn());
                    changePlayerTurn();
                    checkForWinner();
                }
            });
        }
        
        public StackPane getsStackPane() {
            return pane;
        }

        public String getValue() {
            return label.getText();
        }

        public void setValue(String value) {
            label.setText(value);
        }
    }    
}
