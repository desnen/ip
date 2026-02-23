package ploopy;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ploopy ploopy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pikachu.jpeg"));
    private Image ploopyImage = new Image(this.getClass().getResourceAsStream("/images/shin.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Ploopy instance */
    public void setPloopy(Ploopy p) {
        ploopy = p;
        String welcome = new Ui().showWelcome();
        dialogContainer.getChildren().add(
                DialogBox.getPloopyDialog(welcome, ploopyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ploopy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPloopyDialog(response, ploopyImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.6));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
