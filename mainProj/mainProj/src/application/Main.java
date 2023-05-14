package application;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    private InformationCenter informationCenter;
    private TicTacToeBoard ticTacToeBoard;

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, Constants.APP_WIDTH, Constants.APP_HEIGHT);
            initLayout(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLayout(BorderPane root) {
        initInfoCenter(root);
        initTileBoard(root);
    }

    private void initInfoCenter(BorderPane root) {
        informationCenter = new InformationCenter();
        informationCenter.setStartButtonOnAction(startNewGame());
        root.getChildren().add(informationCenter.getStackPane());

    }

    private EventHandler<ActionEvent> startNewGame() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                informationCenter.hideStartButton();
                informationCenter.updateMessage("Player X's Turn");
                ticTacToeBoard.startNewGame();
            }
        };
    }

    private void initTileBoard(BorderPane root) {
        ticTacToeBoard = new TicTacToeBoard(informationCenter);
        root.getChildren().add(ticTacToeBoard.getsStackPane());
    }
    public static void main(String[] args) {
        launch(args);
    }
}

    
