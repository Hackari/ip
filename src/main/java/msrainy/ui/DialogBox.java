package msrainy.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox instance with the given text and image.
     * Loads the FXML layout for the dialog box.
     *
     * @param text The text to display in the dialog.
     * @param img The image of the speaker.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Changes the style of the dialog box based on the type of command.
     *
     * @param commandType The type of command (e.g., Add, ChangeMark, Delete, Bye).
     */
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "Add":
            dialog.getStyleClass().add("add-label");
            break;
        case "ChangeMark":
            dialog.getStyleClass().add("marked-label");
            break;
        case "Delete":
            dialog.getStyleClass().add("delete-label");
            break;
        case "Bye":
            dialog.getStyleClass().add("bye-label");
            break;
        default:
            // Do nothing
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a DialogBox for user input.
     *
     * @param text The text entered by the user.
     * @param img The user's profile image.
     * @return A DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox for Msrainy's response.
     *
     * @param text The response text.
     * @param img The profile image of Msrainy.
     * @param commandType The type of command to determine the dialog style.
     * @return A flipped DialogBox representing Msrainy's dialog.
     */
    public static DialogBox getMsrainyDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}
