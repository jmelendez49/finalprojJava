package application;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

// Withholds information that is devleoped through the app
public class InformationCenter {
    
    private StackPane pane;
    private Label message;
    private Button startGameButton;

    // Setting up constraints for the following code
    public InformationCenter() {
        pane = new StackPane();
        pane.setMinSize(Constants.APP_WIDTH, Constants.INFO_CENTER_HEIGHT);
        pane.setTranslateX(Constants.APP_WIDTH / 2);
        pane.setTranslateY(Constants.INFO_CENTER_HEIGHT / 2);

        message = new Label("Tic Tac Toe");
        message.setMinSize(Constants.APP_WIDTH, Constants.INFO_CENTER_HEIGHT);
        message.setFont(Font.font(24));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-20);
        pane.getChildren().add(message);

        startGameButton = new Button("Start New Game");
        startGameButton.setMinSize(135, 30);
        startGameButton.setTranslateY(20);
        pane.getChildren().add(startGameButton);
    }

    // Initation of the Tic Tac Toe Game
    public StackPane getStackPane() {
        return pane;
    }

    public void updateMessage(String message){
        this.message.setText(message);
    }

    public void showStartButton() {
        startGameButton.setVisible(true);
    }
    public void hideStartButton() {
        startGameButton.setVisible(false);
    }

    public void setStartButtonOnAction(EventHandler<ActionEvent> onAction) {
        startGameButton.setOnAction(onAction);
    }
}
