package msrainy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;


import msrainy.command.Command;
import msrainy.storage.Storage;
import msrainy.ui.DialogBox;
import msrainy.ui.MainWindow;
import msrainy.ui.Parser;
import msrainy.ui.Ui;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

/**
 * Initializes the necessary components and handles the main event loop.
 */
public class Msrainy extends Application {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * Initializes the Msrainy application, setting up storage, user interface,
     * and task management.
     *
     * @param filePath The file path where task data is stored.
     */
    public Msrainy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), ui);
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList(ui);
        }
    }

    public Msrainy() {
        this("./data/tasks.txt");
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMsrainy(this);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("The number you entered is out of bounds. Use list to see available indices.");
            return"The number you entered is out of bounds. Use list to see available indices.";
        } catch (Exception e) {
            ui.showError(e.getMessage());
            return e.getMessage();
        }
        return response;
    }

    /**
     * Runs the main event loop of the application, processing user commands
     * until the exit command is issued.
    public void run() {
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(scanner);
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                isExit = c.execute(tasks, ui, storage);
            } catch (IndexOutOfBoundsException e) {
                ui.showError("The number you entered is out of bounds. Use list to see available indices.");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }*/

    /**
     * Starts the Msrainy program by initializing and running the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Msrainy("./data/tasks.txt");
    }

}
