package main.java.msrainy;

import main.java.msrainy.storage.Storage;
import main.java.msrainy.ui.Ui;
import main.java.msrainy.command.Command;
import main.java.msrainy.ui.Parser;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Msrainy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Msrainy("./data/tasks.txt").run();
    }
}
