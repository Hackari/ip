package main.java.msrainy.command;

import main.java.msrainy.TaskList;
import main.java.msrainy.command.task.Task;
import main.java.msrainy.storage.Storage;
import main.java.msrainy.ui.Ui;

import java.io.IOException;

public class Add extends Command {
    private final Task task;

    public Add(Task task) {
        this.task = task;
    }

    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task, ui);
        storage.update(task);
        return false;
    }
}
