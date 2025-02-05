package msrainy.command;

import java.io.IOException;

import msrainy.TaskList;
import msrainy.command.task.Task;
import msrainy.storage.Storage;
import msrainy.ui.Ui;



/**
 * Represents a command that adds a new task to the task list.
 */
public class Add extends Command {
    private final Task task;

    public Add(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add a task to the task list and update storage.
     *
     * @param tasks   The task list to which the task will be added.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the task.
     * @return false, as this command does not terminate the program.
     * @throws IOException If an I/O error occurs while updating storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String response = tasks.add(task, ui);
        storage.update(task);
        return response;
    }
}
