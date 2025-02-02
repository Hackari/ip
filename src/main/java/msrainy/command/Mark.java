package msrainy.command;

import msrainy.TaskList;
import msrainy.storage.Storage;
import msrainy.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to mark or unmark a task as completed.
 */
public class Mark extends Command {
    private final int index;
    private final boolean isMarking;

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
     * @return false, as this command does not terminate the program.
     * @throws IOException If an I/O error occurs while updating storage.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.changeMark(index, isMarking);
        storage.update(tasks);
        return false;
    }
}
