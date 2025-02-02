package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class Delete extends Command {
    private final int index;

    public Delete(int index) {
        this.index = index;
    }

    /**
     * Executes the command to remove a task from the task list and update storage.
     *
     * @param tasks   The task list from which the task will be removed.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to update the saved tasks.
     * @return false, as this command does not terminate the program.
     * @throws IOException If an I/O error occurs while updating storage.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.remove(index);
        storage.update(tasks);
        return false;
    }
}
