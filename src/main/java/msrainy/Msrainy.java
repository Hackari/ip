package msrainy;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import msrainy.command.Command;
import msrainy.storage.Storage;
import msrainy.ui.MainWindow;
import msrainy.ui.Parser;
import msrainy.ui.Ui;

/**
 * Initializes the necessary components and handles the main event loop.
 */
public class Msrainy extends javafx.application.Application {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private String commandType;

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

    /**
     * Default constructor initializing the application with a default file path.
     */
    public Msrainy() {
        this("./data/tasks.txt");
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMsrainy(this);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user input.
     * @return The response generated by the application.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            commandType = c.getClass().getSimpleName();
            return c.execute(tasks, ui, storage);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("The number you entered is out of bounds. Use list to see available indices.");
            return "The number you entered is out of bounds. Use list to see available indices.";
        } catch (Exception e) {
            ui.showError(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Starts the Msrainy program by initializing and running the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
