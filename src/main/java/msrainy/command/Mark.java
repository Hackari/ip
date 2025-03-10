package msrainy.command;

import java.io.IOException;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

/**
 * Represents a command to mark or unmark a task as completed.
 */
public class Mark extends Command {
    private final int index;
    private final boolean isMarking;

    /**
     * Creates a command to mark or unmark a task.
     *
     * @param index     The index of the task to be modified.
     * @param isMarking True if marking the task as completed, false if unmarking.
     */
    public Mark(int index, boolean isMarking) {
        this.index = index;
        this.isMarking = isMarking;
    }

    /**
     * Executes the command to change the mark status of a task.
     *
     * @param tasks   The task list containing the task to be marked or unmarked.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to update the saved tasks.
     * @return A message indicating the task's updated mark status.
     * @throws IOException If an I/O error occurs while updating storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String response = tasks.changeMark(index, isMarking);
        storage.update(tasks);
        return response;
    }
}
