package msrainy.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import msrainy.Msrainy;

import java.util.Random;

/**
 * Controller for the main GUI of the application.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Msrainy msrainy;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private final Image msrainyImage = new Image(this.getClass().getResourceAsStream("/images/Msrainy.jpg"));
    private final Image msrainyHappyImage = new Image(this.getClass().getResourceAsStream("/images/MsrainyHappy.jpg"));
    private final Image msrainySadImage = new Image(this.getClass().getResourceAsStream("/images/MsrainySad.jpg"));

    /**
     * Initializes the MainWindow, setting up scroll behavior.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Msrainy instance for handling user interactions.
     *
     * @param m The Msrainy instance to be used.
     */
    public void setMsrainy(Msrainy m) {
        this.msrainy = m;
    }

    /**
     * Handles user input by creating dialog boxes for user input and bot response.
     * The user input is cleared after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }
        String response = msrainy.getResponse(input);
        String commandType = msrainy.getCommandType();
        Image currentImage = getImage(commandType);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMsrainyDialog(response, currentImage, commandType)
        );
        userInput.clear();
        checkExit(commandType);
    }

    private Image getImage(String commandType) {
        if (commandType.equals("Bye")) {
            return msrainySadImage;
        }
        Random random = new Random();
        if (random.nextBoolean()) {
            return msrainyImage;
        }
        return msrainyHappyImage;
    }

    private void checkExit(String commandType) {
        if (commandType.equals("Bye")) {
            Platform.exit();
        }
    }
}
