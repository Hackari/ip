package msrainy.command;

import java.io.IOException;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class Delete extends Command {
    private final int index;

    /**
     * Constructs a Delete command with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public Delete(int index) {
        this.index = index;
    }

    /**
     * Executes the command to remove a task from the task list and update storage.
     *
     * @param tasks   The task list from which the task will be removed.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to update the saved tasks.
     * @return A message indicating the result of the deletion.
     * @throws IOException If an I/O error occurs while updating storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String response = tasks.remove(index);
        storage.update(tasks);
        return response;
    }
}
