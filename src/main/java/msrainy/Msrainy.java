package msrainy;

import java.io.FileNotFoundException;
import java.util.Scanner;

import msrainy.command.Command;
import msrainy.storage.Storage;
import msrainy.ui.Parser;
import msrainy.ui.Ui;

/**
 * Initializes the necessary components and handles the main event loop.
 */
public class Msrainy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
     * Runs the main event loop of the application, processing user commands
     * until an exit command is issued.
     */
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
    }

    /**
     * Starts the Msrainy program by initializing and running the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Msrainy("./data/tasks.txt").run();
    }
}
