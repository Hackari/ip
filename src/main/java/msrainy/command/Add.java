package msrainy.command;

import java.io.IOException;

import msrainy.TaskList;
import msrainy.command.task.Task;
import msrainy.storage.Storage;
import msrainy.ui.Ui;



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
